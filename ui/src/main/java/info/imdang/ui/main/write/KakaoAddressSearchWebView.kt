package info.imdang.ui.main.write

import android.annotation.SuppressLint
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@SuppressLint("JavascriptInterface")
@Composable
internal fun KakaoAddressSearchWebView(
    onAddressSelected: (PostcodeResult) -> Unit,
    onCloseCallback: () -> Unit,
) {
    var webView by remember { mutableStateOf<WebView?>(null) }

    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webView = this

                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    allowFileAccess = false
                    allowContentAccess = false
                }

                // JavaScript Interface 추가
                addJavascriptInterface(
                    PostcodeJavaScriptInterface(
                        onComplete = onAddressSelected,
                        onCloseCallback = onCloseCallback
                    ),
                    "Android"
                )

                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView?, url: String?) {
                        super.onPageFinished(view, url)
                        // 페이지 로드 완료 후 우편번호 검색 실행
                        view?.evaluateJavascript("execDaumPostcode()", null)
                    }
                }

                // HTML 콘텐츠 로드
                loadDataWithBaseURL(
                    "https://postcode.map.daum.net",
                    getPostcodeHtml(),
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    )

    DisposableEffect(webView) {
        onDispose {
            webView?.let { view ->
                view.removeJavascriptInterface("Android")
                view.destroy()
            }
        }
    }
}

// JavaScript Interface 클래스
class PostcodeJavaScriptInterface(
    private val onComplete: (PostcodeResult) -> Unit,
    private val onCloseCallback: () -> Unit,
) {
    @JavascriptInterface
    fun onComplete(
        zonecode: String,
        roadAddress: String,
        jibunAddress: String,
        extraAddress: String,
        buildingName: String
    ) {
        onComplete(
            PostcodeResult(
                zonecode = zonecode,
                roadAddress = roadAddress,
                jibunAddress = jibunAddress,
                extraAddress = extraAddress,
                buildingName = buildingName
            )
        )
    }

    @JavascriptInterface
    fun onClose() {
        onCloseCallback()
    }
}

// 우편번호 검색 결과 데이터 클래스
data class PostcodeResult(
    val zonecode: String,        // 우편번호
    val roadAddress: String,     // 도로명 주소
    val jibunAddress: String,    // 지번 주소
    val extraAddress: String,    // 참고 항목
    val buildingName: String     // 건물명
) {
    val fullAddress: String
        get() = roadAddress + extraAddress

    val isEmpty: Boolean
        get() = zonecode.isEmpty() && roadAddress.isEmpty()
}

// HTML 콘텐츠 생성 함수
private fun getPostcodeHtml(): String {
    return """
        <!DOCTYPE html>
        <html>
        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>우편번호 검색</title>
            <style>
                body {
                    margin: 0;
                    padding: 0;
                    font-family: 'Malgun Gothic', sans-serif;
                }
                #layer {
                    position: fixed;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                    background: white;
                    z-index: 100;
                }
                #postcode {
                    position: absolute;
                    top: 0;
                    left: 0;
                    width: 100%;
                    height: 100%;
                }
            </style>
        </head>
        <body>
            <div id="layer">
                <div id="postcode"></div>
            </div>
            
            <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
            <script>
                function execDaumPostcode() {
                    new daum.Postcode({
                        oncomplete: function(data) {
                            // 도로명 주소 변수
                            var roadAddr = data.roadAddress;
                            var extraRoadAddr = '';
                            
                            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                            if(data.bname !== '' && /[동|로|가]${'$'}/g.test(data.bname)){
                                extraRoadAddr += data.bname;
                            }
                            
                            // 건물명이 있고, 공동주택일 경우 추가한다.
                            if(data.buildingName !== '' && data.apartment === 'Y'){
                               extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                            }
                            
                            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                            if(extraRoadAddr !== ''){
                                extraRoadAddr = ' (' + extraRoadAddr + ')';
                            }
                            
                            // Android로 결과 전달
                            Android.onComplete(
                                data.zonecode,
                                roadAddr,
                                data.jibunAddress,
                                extraRoadAddr,
                                data.buildingName || ''
                            );
                        },
                        onclose: function(state) {
                            // 사용자가 닫기 버튼을 클릭한 경우
                            Android.onClose();
                        },
                        width: '100%',
                        height: '100%'
                    }).embed(document.getElementById('postcode'));
                }
            </script>
        </body>
        </html>
    """.trimIndent()
}