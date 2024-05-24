package udesc.br.yota.ui.ui.login

import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import com.example.yota.R

class LoginViewModelStub() : LoginViewModel() {
    override fun login(context: AppCompatActivity, username: String, password: String) {

    }

    override fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = LoginFormState(isDataValid = true)
        }
    }

    override fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    override fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    override fun isUserLoggedIn(): Boolean {
        return true
    }
}