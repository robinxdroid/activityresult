package net.robinx.activityresult

import android.app.Activity
import android.app.Fragment
import android.content.Intent

/**
 * Created by Robin on 2019-07-02. <br>
 * Email: robinxdroid@gmail.com <br>
 * Blog: http://robinx.net/
 */
class ActivityResult(activity: Activity) {
    private var activityResultFragment: ActivityResultFragment

    init {
        activityResultFragment = getActivityResultFragment(activity)
    }

    companion object {
        @JvmStatic
        fun with(activity: Activity): ActivityResult {
            return ActivityResult(activity)
        }
    }

    private fun getActivityResultFragment(activity: Activity): ActivityResultFragment {
        var activityResultFragment: ActivityResultFragment? = findActivityResultFragment(activity)
        val isNewInstance = activityResultFragment == null
        if (isNewInstance) {
            activityResultFragment = ActivityResultFragment()
            val fragmentManager = activity.fragmentManager
            fragmentManager
                    .beginTransaction()
                    .add(activityResultFragment, ActivityResultFragment::class.java.name)
                    .commitAllowingStateLoss()
            fragmentManager.executePendingTransactions()
        }
        return activityResultFragment!!
    }

    private fun findActivityResultFragment(activity: Activity): ActivityResultFragment? {
        val fragment: Fragment? = activity.fragmentManager.findFragmentByTag(ActivityResultFragment::class.java.name)
        fragment?.let {
            return fragment as ActivityResultFragment
        }
        return null
    }

    fun request(intent: Intent?,
                requestCode: Int = ActivityResultFragment.defaultRequestCode,
                poster: ((requestCode: Int, resultCode: Int, data: Intent?) -> Unit)? = null) {
        intent?.let {
            requestFromFragment(intent, requestCode, poster)
        }

    }

    private fun requestFromFragment(intent: Intent,
                                    requestCode: Int = ActivityResultFragment.defaultRequestCode,
                                    poster: ((requestCode: Int, resultCode: Int, data: Intent?) -> Unit)? = null) {
        activityResultFragment.poster = poster
        activityResultFragment.openForResult(intent, requestCode)
    }

}