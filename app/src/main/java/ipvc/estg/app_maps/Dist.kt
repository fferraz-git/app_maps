package ipvc.estg.app_maps

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.dist.*

class Dist : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.dist)

        val button1 = findViewById<Button>(R.id.button_save)

        button1.setOnClickListener {
            if (TextUtils.isEmpty(edit_word.text.toString())) {
                Toast.makeText(this, R.string.Empty, Toast.LENGTH_LONG).show()
            }else{
                val replyIntent = Intent()
                replyIntent.putExtra(EXTRA_REPLY, edit_word.text.toString().toDouble())
                setResult(Activity.RESULT_OK, replyIntent)
                finish()
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}