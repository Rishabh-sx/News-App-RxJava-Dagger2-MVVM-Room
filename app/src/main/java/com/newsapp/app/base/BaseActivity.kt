package com.newsapp.app.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.RelativeLayout
import com.airbnb.lottie.LottieAnimationView
import com.newsapp.app.R
import com.newsapp.app.util.hide
import com.newsapp.app.util.show
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    private var baseContainer: RelativeLayout? = null

    /**
     * Method is used by the sub class for passing the id of the layout ot be inflated in the relative layout
     *
     * @return id of the resource to be inflated
     */
    protected abstract val resourceId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        baseContainer = findViewById(R.id.rl_base_container)
        setLayout()
    }

    /**
     * Method is used to set the layout in the Base Activity.
     * Layout params of the inserted child is match parent
     */
    private fun setLayout() {
        if (resourceId != -1) {
            removeLayout()
            val layoutParams = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
            val layoutInflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = layoutInflater.inflate(resourceId, null)
            baseContainer!!.addView(view, layoutParams)
        }
    }


    /**
     * hides keyboard onClick anywhere besides edit text
     *
     * @param ev
     * @return
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val view = currentFocus
        if (view != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE) && view is EditText && !view.javaClass.name.startsWith("android.webkit.")) {
            val scrcoords = IntArray(2)
            view.getLocationOnScreen(scrcoords)
            val x = ev.rawX + view.left - scrcoords[0]
            val y = ev.rawY + view.top - scrcoords[1]
            if (x < view.left || x > view.right || y < view.top || y > view.bottom)
                (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(this.window.decorView.applicationWindowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    /**
     * This method is used to remove the view already present as a child in relative layout.
     */
    private fun removeLayout() {
        if (baseContainer!!.childCount >= 1)
            baseContainer!!.removeAllViews()
    }

    fun startAnimation(animationView: LottieAnimationView) {
        animationView.playAnimation()
        animationView.show()
    }

    fun stopAnimation(animationView: LottieAnimationView) {
        animationView.cancelAnimation()
        animationView.hide()
    }

}
