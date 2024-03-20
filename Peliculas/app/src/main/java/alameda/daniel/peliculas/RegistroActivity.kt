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

    lateinit var et_correo: EditText
    lateinit var et_contra1: EditText
    lateinit var et_contra2: EditText
    lateinit var btn_registrar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)


        et_correo = findViewById(R.id.et_correo_registro)
        et_contra1 = findViewById(R.id.et_contra1_registro)
        et_contra2 = findViewById(R.id.et_contra2_registro)
        btn_registrar = findViewById(R.id.btn_registrar)

        auth = Firebase.auth

        btn_registrar.setOnClickListener{
            var correo: String = et_correo.text.toString()
            var contra1: String = et_contra2.text.toString()
            var contra2: String = et_contra2.text.toString()

            if (!correo.isNullOrEmpty() && !contra1.isNullOrEmpty() && !contra2.isNullOrEmpty()){

                if (contra1==contra2){

                    auth.createUserWithEmailAndPassword(correo, contra1)
                        .addOnCompleteListener(this){task ->
                            if (task.isSuccessful){
                                val user = auth.currentUser
                                Log.d("exito", "createUserWithEmail:success")
                                Toast.makeText(
                                    this,
                                    "Se ha registrado${user?.email}",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                // updateUI(user)
                            } else{
                                Log.w("error", "createUserWithEmail:failure", task.exception)
                                Toast.makeText(
                                    this,
                                    "No se pudo registrar el usuario",
                                    Toast.LENGTH_SHORT,
                                ).show()
                                finish()
                                // updateUI(null)
                            }
                        }

                }else{
                    Toast.makeText(this,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this,"Ingresar datos de correo y contraseña", Toast.LENGTH_SHORT).show()
            }
        }
    }
}