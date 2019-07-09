package net.robinx.activityresult.app

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import net.robinx.activityresult.ActivityResult

/**
 * Created by Robin on 2019-07-09. <br>
 * Email: robinxdroid@gmail.com <br>
 * Blog: http://robinx.net/
 */
class KtMainActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_kt)

        val tvReturn = findViewById<TextView>(R.id.tv_return)

        findViewById<Button>(R.id.btn_go).setOnClickListener{
            ActivityResult.with(this@KtMainActivity)
                    .request(OtherActivity.getIntent(this@KtMainActivity)) { _, _, intent ->
                        intent?.let {
                            val data = intent.getStringExtra("data")
                            tvReturn.text = data
                        }

                    }
        }
    }
}