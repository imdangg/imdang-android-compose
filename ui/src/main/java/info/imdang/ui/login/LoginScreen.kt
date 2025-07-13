package info.imdang.ui.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import info.imdang.core.presentation.login.LoginViewModel
import info.imdang.imdang.core.component.theme.Gray25
import info.imdang.imdang.core.component.theme.ImdangAppNewTheme
import info.imdang.imdang.core.component.theme.ImdangPreview
import info.imdang.ui.R

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    LoginScreen(
        onClickedLogin = { loginPlatform ->
            when (loginPlatform) {
                LoginPlatform.GOOGLE -> {

                }

                LoginPlatform.KAKAO -> {
                    LoginUtil.startKakaoLogin(
                        context = context,
                        onSuccess = {},
                        onFailure = {}
                    )
                }
            }
        }
    )
}

@Composable
internal fun LoginScreen(
    onClickedLogin: (LoginPlatform) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray25)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(1.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_logo),
            contentDescription = "Logo",
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 40.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            LoginPlatform.all.forEach {
                LoginButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    loginPlatform = it,
                    onClicked = onClickedLogin,
                )
            }
        }
    }
}

@Composable
internal fun LoginButton(
    modifier: Modifier = Modifier,
    loginPlatform: LoginPlatform,
    onClicked: (LoginPlatform) -> Unit,
) {

    Button(
        modifier = modifier,
        onClick = { onClicked(loginPlatform) },
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(loginPlatform.backgroundColor),
        border = loginPlatform.borderColor?.let { BorderStroke(1.dp, it) },
        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(loginPlatform.labelRes),
                style = MaterialTheme.typography.titleMedium,
                color = loginPlatform.textColor
            )

            Image(
                painter = painterResource(loginPlatform.iconRes),
                contentDescription = stringResource(loginPlatform.labelRes),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .size(24.dp)
            )
        }
    }
}

@ImdangPreview
@Composable
private fun LoginScreenPreview() {
    ImdangAppNewTheme {
        LoginScreen(
            onClickedLogin = {}
        )
    }
}