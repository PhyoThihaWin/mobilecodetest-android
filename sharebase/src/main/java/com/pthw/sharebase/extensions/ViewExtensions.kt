package com.pthw.sharebase.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.CountDownTimer
import android.os.SystemClock
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowInsetsController
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar


fun View.setVisible(isVisible: Boolean) {
    if (isVisible) {
        this.visibility = View.VISIBLE
    } else {
        this.visibility = View.GONE
    }
}


fun View.show() {
    if (this.visibility == View.VISIBLE) return
    this.visibility = View.VISIBLE
}

fun View.hide() {
    if (this.visibility == View.INVISIBLE) return
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    if (this.visibility == View.GONE) return
    this.visibility = View.GONE
}

fun Array<View>.setVisible(isVisible: Boolean) {

    val visibility = if (isVisible) {
        View.VISIBLE
    } else {
        View.GONE
    }

    this.forEach {
        it.visibility = visibility
    }
}

fun ViewGroup.inflater(): LayoutInflater {
    return this.context.inflater()
}


fun TextView.hideAsTextPassword() {
    transformationMethod = PasswordTransformationMethod.getInstance()
}

fun TextView.showAsTextPassword() {
    transformationMethod = HideReturnsTransformationMethod.getInstance()
}


inline fun View.snack(
    @StringRes messageRes: Int,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit
) {
    snack(resources.getString(messageRes), length, f)
}

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    val snack = Snackbar.make(this, message, length)
    snack.f()
    snack.show()
}

fun Snackbar.action(@StringRes actionRes: Int, color: Int? = null, listener: (View) -> Unit) {
    action(view.resources.getString(actionRes), color, listener)
}

fun Snackbar.action(action: String, color: Int? = null, listener: (View) -> Unit) {
    setAction(action, listener)
    color?.let { setActionTextColor(ContextCompat.getColor(context, color)) }
}

fun ViewPager2.reduceDragSensitivity(f: Int = 4) {
    val recyclerViewField = ViewPager2::class.java.getDeclaredField("mRecyclerView")
    recyclerViewField.isAccessible = true
    val recyclerView = recyclerViewField.get(this) as RecyclerView

    val touchSlopField = RecyclerView::class.java.getDeclaredField("mTouchSlop")
    touchSlopField.isAccessible = true
    val touchSlop = touchSlopField.get(recyclerView) as Int
    touchSlopField.set(recyclerView, touchSlop * f)       // "8" was obtained experimentally
}

fun View.safeClick(listener: View.OnClickListener, blockInMillis: Long = 500) {
    var lastClickTime: Long = 0
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < blockInMillis) return@setOnClickListener
        lastClickTime = SystemClock.elapsedRealtime()
        listener.onClick(this)
    }
}

fun View.preventDoubleClick(delay: Long = 1000) {
    this.isEnabled = false
    this.postDelayed({ this.isEnabled = true }, delay)
}

fun EditText.afterTextChangedDelayed(delay: Long = 50, afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        var timer: CountDownTimer? = null

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun afterTextChanged(editable: Editable?) {
            timer?.cancel()
            timer = object : CountDownTimer(delay, 1000) {
                override fun onTick(millisUntilFinished: Long) {}
                override fun onFinish() {
                    afterTextChanged.invoke(editable.toString())
                }
            }.start()
        }
    })
}

fun EditText.showKeyboard(activity: Activity) {
    this.requestFocus()
    val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val isShowing = imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
    if (!isShowing) activity.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
}

fun WebView.initAndLoadWebView(url: String, preLoadedView: View) {
    settings.apply {
        javaScriptEnabled = true
        loadWithOverviewMode = true
        useWideViewPort = true
    }
    loadUrl(url)
    webViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            return true
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            preLoadedView.show()
            view!!.hide()
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            preLoadedView.hide()
            view!!.show()
        }
    }
}

fun BottomNavigationView.hideBottomNavigationView() {
//    if (this.translationY == 0f) {
    this.clearAnimation()
    this.animate().translationY(this.height.toFloat()).alpha(0f)
        .setDuration(400).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                this@hideBottomNavigationView.gone()
            }
        })

//    }
}

fun BottomNavigationView.showBottomNavigationView() {
//    if (this.translationY != 0f) {
    this.clearAnimation()
    this.animate().translationY(0f).alpha(1f)
        .setDuration(400).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                this@showBottomNavigationView.show()
            }
        })
//    }
}

fun HorizontalScrollView.canScroll(): Boolean {
    val child = this.getChildAt(0)
    if (child != null) {
        val childWidth = child.width
        return this.width < childWidth + this.paddingLeft + this.paddingRight
    }
    return false
}

private fun ScrollView.canScroll(): Boolean {
    val child = this.getChildAt(0)
    if (child != null) {
        val childHeight = child.height
        return this.height < childHeight + this.paddingTop + this.paddingBottom
    }
    return false
}

fun Window.setStatusBarColor(
    activity: Activity,
    @ColorRes color: Int,
    lightStatusBar: Boolean = false
) {
    activity.runOnUiThread {
        this.statusBarColor = activity.useColor(color)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.R) {
            if (lightStatusBar) {
                this.insetsController?.setSystemBarsAppearance(
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                    WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                this.insetsController?.setSystemBarsAppearance(
                    0, WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
                )
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (lightStatusBar) {
                this.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                this.decorView.systemUiVisibility = 0
            }
        }
    }
}
