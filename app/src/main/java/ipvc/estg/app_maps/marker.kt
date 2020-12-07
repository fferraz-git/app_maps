package ipvc.estg.app_maps

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import ipvc.estg.app_maps.api.LoginEndPoints
import ipvc.estg.app_maps.api.ServiceBuilder
import ipvc.estg.app_maps.api.TicketOutputPost
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class marker : AppCompatActivity() {

    private lateinit var createmarkerView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.marker)

        var tipo = String()
        var lastposition: String

        val sharedPref: SharedPreferences = getSharedPreferences(getString(R.string.preference_file_key),
            Context.MODE_PRIVATE)

        var username:String? = sharedPref.getString("automatic_login_username", null)
        var foto="/URL/HELLO"
        val date= intent.getStringExtra(EXTRA_LOCAL)

        lastposition= date.toString()
        createmarkerView = findViewById(R.id.marker_texto)

        val texto = createmarkerView.text
        val types = resources.getStringArray(R.array.Types)
        val spinner = findViewById<Spinner>(R.id.spinner1)

        if (spinner != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, types)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object:
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View,
                                            position: Int,
                                            id: Long) {
                    tipo=types[position]
                }
                override fun onNothingSelected(parent: AdapterView<*>) {
                }
            }
        }

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val request = ServiceBuilder.buildService(LoginEndPoints::class.java)
            val call = request.create(
                username.toString(),
                tipo,
                texto,
                lastposition,
                foto

            )

            call.enqueue(object : Callback<TicketOutputPost> {
                override fun onResponse(call: Call<TicketOutputPost>, response: Response<TicketOutputPost>)
                {
                    if (response.isSuccessful) {
                        val c: TicketOutputPost = response.body()!!
                        if (c.success) {
                            Toast.makeText(this@marker,"certo", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@marker, activity_maps1::class.java)
                            startActivity(intent)
                            finish()
                        } else Toast.makeText(this@marker,"errado", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<TicketOutputPost>, t: Throwable) {
                    Toast.makeText(this@marker, "${t.message}", Toast.LENGTH_SHORT).show()
                }
            })

            finish()
        }
    }
    companion object {
        const val EXTRA_LOCAL = "com.example.android.wordlistsql.LOCAL"
    }
}