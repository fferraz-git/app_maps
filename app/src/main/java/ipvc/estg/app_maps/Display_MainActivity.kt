package ipvc.estg.app_maps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import ipvc.estg.app_maps.adapter.UserAdapter
import ipvc.estg.app_maps.api.EndPoints
import ipvc.estg.app_maps.api.OutputPost
import ipvc.estg.app_maps.api.ServiceBuilder
import ipvc.estg.app_maps.api.User
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Display_MainActivity : AppCompatActivity() {

    // solve internet issue on emulator
    // https://medium.com/@cafonsomota/android-emulator-when-theres-no-connection-to-the-internet-129e8b63b7ce'
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        /*val call = request.getUsers()

        call.enqueue(object : Callback<List<User>>{
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful){
                    recyclerView.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@Display_MainActivity)
                        adapter = UserAdapter(response.body()!!)
                    }
                }
            }
            override fun onFailure(call: Call<List<User>>, t: Throwable) =
                Toast.makeText(this@Display_MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
        })*/
    }

    /*
    fun getSingle(view: View) {

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.getUserById(2) // estaticamente o valor 2. deverá depois passar a ser dinamico

        call.enqueue(object : Callback<User>{
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful){
                    val c: User = response.body()!!
                    Toast.makeText(this@Display_MainActivity, c.address.zipcode, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(this@Display_MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }*/

    /*
    fun post(view: View) {

        val request = ServiceBuilder.buildService(EndPoints::class.java)
        val call = request.postTest("teste")

        call.enqueue(object : Callback<OutputPost>{
            override fun onResponse(call: Call<OutputPost>, response: Response<OutputPost>) {
                if (response.isSuccessful){
                    val c: OutputPost = response.body()!!
                    Toast.makeText(this@Display_MainActivity, c.id.toString() + "-" + c.title, Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<OutputPost>, t: Throwable) {
                Toast.makeText(this@Display_MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }*/

    fun map(view: View) {
        val intent = Intent(this, activity_maps1::class.java).apply {
        }
        startActivity(intent)

    }
}