package udesc.br.yota.ui.ui.login

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class LoginViewModel() : ViewModel() {

    protected val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    abstract fun login(context: AppCompatActivity, username: String, password: String)
    abstract fun loginDataChanged(username: String, password: String)
    protected abstract fun isUserNameValid(username: String): Boolean
    protected abstract fun isPasswordValid(password: String): Boolean
    protected abstract fun isUserLoggedIn() : Boolean

}