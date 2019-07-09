package net.robinx.activityresult.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button

/**
 * Created by Robin on 2019-07-09. <br>
 * Email: robinxdroid@gmail.com <br>
 * Blog: http://robinx.net/
 */
class OtherActivity : AppCompatActivity() {

    companion object{
        @JvmStatic
        fun getIntent(context: Context) = Intent(context,OtherActivity::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        findViewById<Button>(R.id.btn_return).setOnClickListener {
            val data = Intent()
            data.putExtra("data","仙路尽头谁为峰")
            setResult(Activity.RESULT_OK,data)
            finish()
        }
    }
}