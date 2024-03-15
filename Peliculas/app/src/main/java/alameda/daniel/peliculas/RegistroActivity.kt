package alameda.daniel.peliculas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class RegistroActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    lateinit var et_coreo: EditText
    lateinit var et_contra1: EditText
    lateinit var et_contra2: EditText
    lateinit var btn_registrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        et_coreo = findViewById(R.id.et_correo_registro)
        et_contra1 = findViewById(R.id.et_contra1_registro)
        et_contra2 = findViewById(R.id.et_contra2_registro)
        btn_registrar = findViewById(R.id.btn_registrar)

        auth = Firebase.auth

        btn_registrar.setOnClickListener{
            var correo: String = et_coreo.text.toString()
            var contra1: String = et_contra1.text.toString()
            var contra2: String = et_contra2.text.toString()

            if (correo.isNullOrEmpty() && !contra1.isNullOrEmpty() && !contra2.isNullOrEmpty()){
                if(contra1 == contra2){

                    auth.createUserWithEmailAndPassword(correo, contra1)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d("succes", "createUserWithEmail:success")
                                Toast.makeText(
                                    baseContext,
                                    "Se ha registrado correctamente",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                //updateUI(user)
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w("error", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    baseContext,
                                    "No se pudo registrar el usuario",
                                    Toast.LENGTH_SHORT,
                                ).show()
                               // updateUI(null)
                            }
                        }

                }else{
                    Toast.makeText(this, "Las contraseña no coinciden", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Ingresar datos de correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }


    }
}