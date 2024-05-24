package udesc.br.yota.ui.ui.login

import android.content.Intent
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.yota.R
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import udesc.br.yota.MainActivity

class LoginViewModelFirebase : LoginViewModel()  {

    private val auth = Firebase.auth
    override fun login(context: AppCompatActivity, username: String, password: String) {
        auth.signInWithEmailAndPassword(username, password)
            .addOnSuccessListener { task ->
                task.user?.let {
                    context.startActivity(Intent(context, MainActivity::class.java))
                    context.finish()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(context, "Error logging in: ${e.message}", Toast.LENGTH_SHORT).show()
            }
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

    override fun isUserLoggedIn() : Boolean {
        return auth.currentUser != null
    }
}