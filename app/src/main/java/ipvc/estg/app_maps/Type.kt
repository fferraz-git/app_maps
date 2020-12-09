package ipvc.estg.app_maps

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Type : AppCompatActivity() {

    public override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.type)

        val button1 = findViewById<Button>(R.id.save1)
        val button2 = findViewById<Button>(R.id.save2)
        val button3 = findViewById<Button>(R.id.save3)

        button1.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_REPLY, 1)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }

        button2.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_REPLY, 2)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }

        button3.setOnClickListener {
            val replyIntent = Intent()
            replyIntent.putExtra(EXTRA_REPLY, 3)
            setResult(Activity.RESULT_OK, replyIntent)
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}