package com.newsapp.app.ui.newdetails

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.transition.Slide
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.newsapp.app.R
import com.newsapp.app.base.BaseActivity
import com.newsapp.app.constants.AppConstants
import com.newsapp.app.dataclass.Article
import kotlinx.android.synthetic.main.activity_details.*


class ArticleDetailActivity : BaseActivity() {
    private var article: Article? = null

    override val resourceId: Int
        get() = R.layout.activity_details

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        article = getArticle()
        initViews()
        setViewsData()
        initActivityTransitions()
    }

    private fun getArticle(): Article {
        return intent.getParcelableExtra(AppConstants.KEY_ARTICLE)!!
    }

    private fun initViews() {
        setSupportActionBar(findViewById<View>(R.id.toolbar) as Toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        val itemTitle = article!!.title
        collapsing_toolbar.title = itemTitle
        collapsing_toolbar.setExpandedTitleColor(ContextCompat.getColor(this,android.R.color.transparent))
    }

    private fun setViewsData() {
        Glide.with(image.context)
            .asBitmap()
            .load(article!!.urlToImage).listener(object :
                RequestListener<Bitmap> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Bitmap?,
                    model: Any?,
                    target: Target<Bitmap>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    image.setImageBitmap(resource)
                    if (image.drawable != null) {
                        val bitmap = (image.drawable as BitmapDrawable).bitmap
                        Palette.from(bitmap!!).generate { palette -> applyPalette(palette!!) }
                    }
                    return true
                }

            }).into(image)
        tv_title.text = (article!!.title)
        tv_description.text = article!!.description

        if (article!!.content != null) {
            val content =
                article!!.content?.split("[+")
            if (content?.size!! >= 2) {
                val viewMore = content[1]
                val message = content[0]
                tv_description.text = String.format(
                    "%s %s", message, viewMore.replace(
                        viewMore,
                        getString(R.string.s_view_more)
                    )
                )
                addClickToViewMore()
            }
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return try {
            super.dispatchTouchEvent(ev)
        } catch (e: NullPointerException) {
            false
        }
    }

    private fun initActivityTransitions() {
        val transition = Slide()
        transition.excludeTarget(android.R.id.statusBarBackground, true)
        window.enterTransition = transition
        window.returnTransition = transition
    }

    private fun applyPalette(palette: Palette) {
        val primaryDark = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        val primary = ContextCompat.getColor(this,R.color.colorPrimary)
        collapsing_toolbar.setContentScrimColor(palette.getMutedColor(primary))
        collapsing_toolbar.setStatusBarScrimColor(palette.getDarkMutedColor(primaryDark))
        supportStartPostponedEnterTransition()
        setTransparentStatusBar(palette)
    }

    private fun setTransparentStatusBar(palette: Palette) {
        val color = palette.getMutedColor(ContextCompat.getColor(this,R.color.colorPrimary))
        window?.statusBarColor = color
    }

    private fun addClickToViewMore() {
        val span =
            Spannable.Factory.getInstance().newSpannable(tv_description.text.toString())
        span.setSpan(
            object : ClickableSpan() {
                override fun onClick(v: View) {
                    openInBrowser()
                }
            }, tv_description.text.toString().indexOf(getString(R.string.s_view_more)),
            tv_description.text.toString().length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        span.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorAccent)),
            tv_description.text.toString().indexOf(getString(R.string.s_view_more)),
            tv_description.text.toString().length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        tv_description.text = span
        tv_description.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun openInBrowser() {
        try {
            val url = article!!.url
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        } catch (e: Exception) {
            Toast.makeText(
                this@ArticleDetailActivity,
                getString(R.string.s_browser_not_found),
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}