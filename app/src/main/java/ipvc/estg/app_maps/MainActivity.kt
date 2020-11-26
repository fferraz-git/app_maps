package ipvc.estg.app_maps

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ipvc.estg.app_maps.api.LoginEndPoints
import ipvc.estg.app_maps.api.LoginOutputPost
import ipvc.estg.app_maps.api.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    private lateinit var usernameEditTextView: EditText
    private lateinit var passwordEditTextView: EditText
    private lateinit var submit_login_button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditTextView = findViewById(R.id.login_username)
        passwordEditTextView = findViewById(R.id.login_password)
        submit_login_button = findViewById(R.id.submit_login_button)

        //vai buscar o sharedpreferences
        val sharedPref: SharedPreferences = getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE )

        //
        val automatic_login_check = sharedPref.getBoolean(getString(R.string.automatic_login_check), false)
        //Log.d("SP_AutoLoginCheck", "$automatic_login_check")

        //se verdade e pq ja tenho um user logado
        if( automatic_login_check ) {
            val intent = Intent(this@MainActivity, activity_maps1::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun login( view: View) {

        val username = usernameEditTextView.text.toString()
        val password = passwordEditTextView.text.toString()

        //validaçoes se vazio
        if ( TextUtils.isEmpty(username) ) {
            Toast.makeText(this, R.string.fieldusernameemptylabel, Toast.LENGTH_LONG).show()
            return
        }
        else if ( TextUtils.isEmpty(password) ) {
            Toast.makeText(this, R.string.fieldpasswordemptylabel, Toast.LENGTH_LONG).show()
            return
        }
        else {

            //fazer a ligaçao
            val request = ServiceBuilder.buildService(LoginEndPoints::class.java)
            val call = request.create(username, password)

            call.enqueue(object : Callback<LoginOutputPost> {

                //metodo de onresponse
                override fun onResponse(call: Call<LoginOutputPost>, response: Response<LoginOutputPost>) {

                    //verifica se a comunicaçao foi bem sucedida
                    if (response.isSuccessful) {
                        val c: LoginOutputPost = response.body()!!

                        //verifica se a resposta for do parametro sucess for veradadeira
                        if (c.success) {
                            Toast.makeText(this@MainActivity, R.string.logincorrectlabel, Toast.LENGTH_SHORT).show()

                            // guarda os parametros todos no shared preferences para fazer o login automatico
                            val sharedPref: SharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE )
                            with ( sharedPref.edit() ) {
                                putBoolean(getString(R.string.automatic_login_check), true)
                                putString(getString(R.string.automatic_login_username), username )
                                putString(getString(R.string.automatic_login_password), password )
                                commit()
                            }

                            // muda para a ativadade dos mapas
                            val intent = Intent(this@MainActivity, activity_maps1::class.java)
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(this@MainActivity, R.string.loginincorrectlabel, Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                override fun onFailure(call: Call<LoginOutputPost>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }
}