package com.lage.appdeportiva

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_auth.*

class AuthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        // Setup
        setup()
    }

    private fun setup() {
        title = "Autenticación"
        signinbutton.setOnClickListener{
            if (emailedittext.text.isNotEmpty() && passwordedittext.text.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailedittext.text.toString(),
                        passwordedittext.text.toString()).addOnCompleteListener{
                            if (it.isSuccessful){
                                showHome(it.result?.user?.email ?:"")
                            } else {
                                showAlert()
                            }
                }
            }
        }
        loginbutton.setOnClickListener{
            if (emailedittext.text.isNotEmpty() && passwordedittext.text.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(emailedittext.text.toString(),
                        passwordedittext.text.toString()).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?:"")
                    } else {
                        showAlert()
                    }
                }
            }
        }
    }

    private fun showAlert() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar",null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showHome(email: String) {
        val homeIntent = Intent(this, HomeActivity::class.java).apply{
            putExtra("email",email)
        }
        startActivity(homeIntent)
    }
}