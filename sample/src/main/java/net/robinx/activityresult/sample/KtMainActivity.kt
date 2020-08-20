package net.robinx.activityresult.sample

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.robinx.activityresult.ActivityResult

/**
 * Created by Robin on 2019-07-09. <br>
 * Email: robinxdroid@gmail.com <br>
 * Blog: http://robinx.net/
 */
class KtMainActivity : AppCompatActivity(R.layout.activity_main_kt) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tvReturn = findViewById<TextView>(R.id.tv_return)

        findViewById<Button>(R.id.btn_go).setOnClickListener {
            val otherIntent = OtherActivity.getIntent(this@KtMainActivity)
            ActivityResult.with(this@KtMainActivity)
                    .start(otherIntent) { _, _, intent ->
                        intent?.let {
                            val data = intent.getStringExtra("data")
                            tvReturn.text = data
                        }

                    }
        }
    }
}