package com.newsapp.app.util


import android.app.Activity
import android.content.Intent
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.newsapp.app.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun View.hide() {
    this.visibility = View.GONE
}

fun View.show(){
    this.visibility = View.VISIBLE
}

object AppUtils {

    fun formatDate(createdAt: String?): String? {
        val form = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        form.timeZone = TimeZone.getTimeZone("UTC")
        var date: Date? = null
        try {
            date = form.parse(createdAt)
            val cal = Calendar.getInstance()
            val tz = cal.timeZone
            form.timeZone = tz
            val simpleDateFormat = SimpleDateFormat("MM/dd/yyyy")
            simpleDateFormat.timeZone = tz //HH:mm
            return simpleDateFormat.format(date)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    fun shareText(activity: Activity, url: String,title:String) {
        val share = Intent(Intent.ACTION_SEND)
        share.type = "text/plain"
        share.putExtra(Intent.EXTRA_TEXT, url)
        activity.startActivity(
            Intent.createChooser(
                share, title)
            )
    }

}
