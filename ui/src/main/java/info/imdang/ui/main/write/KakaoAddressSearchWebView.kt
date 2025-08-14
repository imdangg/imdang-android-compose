package info.imdang.ui.main.write

import android.annotation.SuppressLint
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
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
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    allowFileAccess = false
                    allowContentAccess = false
                }

                addJavascriptInterface(
                    PostcodeJavaScriptInterface(onAddressSelected, onCloseCallback),
                    "Android"
                )

                setBackgroundColor(White.toArgb())

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
                        Log.e("KakaoAddressWebView", "Resource error: ${error?.description}")
                    }
                }

                // 다음 우편번호 서비스 로드
                loadDataWithBaseURL(
                    "https://postcode.map.daum.net",
                    createPostcodeHtml(),
                    "text/html",
                    "UTF-8",
                    null
                )
            }
        },
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

private fun createPostcodeHtml(): String = """
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>우편번호 검색</title>
        <style>
            * { margin: 0; padding: 0; box-sizing: border-box; }
            body { font-family: 'Malgun Gothic', sans-serif; }
            #postcode { width: 100%; height: 100vh; }
        </style>
    </head>
    <body>
        <div id="postcode"></div>
        
        <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
        <script>
            function execDaumPostcode() {
                new daum.Postcode({
                    oncomplete: function(data) {
                        // 참고항목 생성
                        let extraAddr = '';
                        
                        if (data.bname && /[동|로|가]$/.test(data.bname)) {
                            extraAddr += data.bname;
                        }
                        
                        if (data.buildingName && data.apartment === 'Y') {
                            extraAddr += (extraAddr ? ', ' : '') + data.buildingName;
                        }
                        
                        if (extraAddr) {
                            extraAddr = ' (' + extraAddr + ')';
                        }
                        
                        // Android로 결과 전달
                        Android.onComplete(
                            data.zonecode,
                            data.roadAddress,
                            data.jibunAddress,
                            extraAddr,
                            data.buildingName || ''
                        );
                    },
                    onclose: function() {
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