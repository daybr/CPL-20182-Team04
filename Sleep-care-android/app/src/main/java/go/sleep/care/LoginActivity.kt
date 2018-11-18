package go.sleep.care

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.widget.Button

class LoginActivity : AppCompatActivity() {
    private val nextButton by lazy {
        findViewById<Button>(R.id.next_button)
    }

    private val passwordEditText by lazy {
        findViewById<TextInputEditText>(R.id.password_edit_text)
    }

    private val passwordTextInput by lazy {
        findViewById<TextInputLayout>(R.id.password_text_input)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.gsc_login_activity)

        createNextButton()
    }

    private fun createNextButton() {
        nextButton.setOnClickListener {

            if (!isPasswordValid(passwordEditText.text)) {
                passwordTextInput.error = getString(R.string.shr_error_password)
            } else {
                passwordTextInput.error = null // Clear the error

                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("key", "fuck")
                startActivity(intent)
            }
        }
    }



    /*
        In reality, this will have more complex logic including, but not limited to, actual
        authentication of the username and password.
     */
    private fun isPasswordValid(text: Editable?): Boolean {
        return text != null
    }
}
