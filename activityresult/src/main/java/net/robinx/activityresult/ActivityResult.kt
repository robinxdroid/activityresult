package net.robinx.activityresult

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

/**
 * Created by Robin on 2019-07-02. <br>
 * Email: robinxdroid@gmail.com <br>
 * Blog: http://robinx.net/
 */
class ActivityResult(activity: FragmentActivity) {
    private val activityResultFragment: ActivityResultFragment by lazy {
        getActivityResultFragment(activity)
    }

    companion object {
        @JvmStatic
        fun with(activity: FragmentActivity): ActivityResult {
            return ActivityResult(activity)
        }
    }

    private fun getActivityResultFragment(activity: FragmentActivity): ActivityResultFragment {
        var fragment: ActivityResultFragment? = findActivityResultFragment(activity)
        val isNewInstance = fragment == null
        if (isNewInstance) {
            fragment = ActivityResultFragment()
            val fragmentManager = activity.supportFragmentManager
            fragmentManager
                .beginTransaction()
                .add(fragment, ActivityResultFragment::class.java.name)
                .commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
        return fragment!!
    }

    private fun findActivityResultFragment(activity: FragmentActivity): ActivityResultFragment? {
        val fragment: Fragment? = activity.supportFragmentManager.findFragmentByTag(ActivityResultFragment::class.java.name)
        fragment?.let {
            return fragment as ActivityResultFragment
        }
        return null
    }

    fun start(intent: Intent,
              requestCode: Int = ActivityResultFragment.defaultRequestCode,
              poster: ((requestCode: Int, resultCode: Int, data: Intent?) -> Unit)? = null) = requestFromFragment(intent, requestCode, poster)


    private fun requestFromFragment(intent: Intent,
                                    requestCode: Int = ActivityResultFragment.defaultRequestCode,
                                    poster: ((requestCode: Int, resultCode: Int, data: Intent?) -> Unit)? = null) {
        activityResultFragment.poster = poster
        activityResultFragment.openForResult(intent, requestCode)
    }
}

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