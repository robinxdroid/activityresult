package net.robinx.activityresult

import android.app.Fragment
import android.content.Intent
import android.os.Bundle

/**
 * Created by Robin on 2019-07-02. <br>
 * Email: robinxdroid@gmail.com <br>
 * Blog: http://robinx.net/
 */
class ActivityResultFragment : Fragment() {
    companion object {
        const val defaultRequestCode = 0
    }

    var poster: ((requestCode: Int, resultCode: Int, data: Intent?) -> Unit)? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun openForResult(targetIntent: Intent, requestCode: Int = defaultRequestCode) {
        this.startActivityForResult(targetIntent, requestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        poster?.let {
            poster?.invoke(requestCode, resultCode, data)
        }
    }

}