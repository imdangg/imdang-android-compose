package info.imdang.ui.main.write

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebView.setWebContentsDebuggingEnabled
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import info.imdang.core.presentation.model.PostcodeResultModel
import info.imdang.imdang.core.component.theme.White


@SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
@Composable
internal fun KakaoAddressSearchWebView(
    modifier: Modifier = Modifier,
    onAddressSelected: (PostcodeResultModel) -> Unit,
    onCloseCallback: () -> Unit,
) {
    val tag = "KakaoAddressSearchWebView"
    val context = LocalContext.current

    val webView = remember {
        WebView(context).apply {
            settings.apply {
                javaScriptEnabled = true
                domStorageEnabled = true
                allowFileAccess = false
                allowContentAccess = false
            }
            setWebContentsDebuggingEnabled(true)

            webChromeClient = WebChromeClient()
            webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    view?.evaluateJavascript("execDaumPostcode()", null)
                }

                override fun onReceivedError(
                    view: WebView?,
                    request: WebResourceRequest?,
                    error: WebResourceError?
                ) {
                    super.onReceivedError(view, request, error)
                    Log.e(tag, "Resource error: ${error?.description}")
                }
            }

            addJavascriptInterface(
                PostcodeJavaScriptInterface(onAddressSelected, onCloseCallback),
                "Android"
            )

            setBackgroundColor(White.toArgb())

            // 다음 우편번호 서비스 로드
            loadDataWithBaseURL(
                "https://postcode.map.daum.net",
                getPostcodeHtml(),
                "text/html",
                "UTF-8",
                null
            )
        }
    }

    AndroidView(
        factory = { webView },
        modifier = modifier
    )
}

// JavaScript Interface 클래스
class PostcodeJavaScriptInterface(
    private val onComplete: (PostcodeResultModel) -> Unit,
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
            PostcodeResultModel(
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