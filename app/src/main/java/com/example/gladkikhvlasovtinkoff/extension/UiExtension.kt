package com.example.gladkikhvlasovtinkoff.extension

import android.content.Context
import android.provider.Settings.Global.getString
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.currency.CustomGridLayoutManager
import com.google.android.material.textview.MaterialTextView

//TODO change to extension
fun setupNavigation(
    fragment: Fragment,
    activity: AppCompatActivity,
    navController: NavController,
    action: NavDirections
) {
    activity
        .onBackPressedDispatcher
        .addCallback(fragment, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow)
                navController.navigate(action)
            }
        })
}

 
fun EditText.observeTextChanged(button: AppCompatButton) {
    doOnTextChanged { _, _, _, _ ->
        val isEnabled = text.toString() != ""
        if (isEnabled) {
            button.setEnabled(context)
        } else
            button.setDisabled(context)
    }
}

 
fun EditText.setupTextStyleAndObserve(buttonObserver: Button) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            this@setupTextStyleAndObserve.removeTextChangedListener(this)
            val isEnabled = s.toString() != ""
            this@setupTextStyleAndObserve.setText(
                s?.toString()?.styleInput() ?: ""
            )

            if (isEnabled) {
                buttonObserver.setEnabled(context)
            } else
                buttonObserver.setDisabled(context)

            this@setupTextStyleAndObserve.setSelection(
                this@setupTextStyleAndObserve.text?.toString()?.length ?: 0
            )
            this@setupTextStyleAndObserve.addTextChangedListener(this)
        }
    })
}

fun Boolean.expandRecyclerView(context : Context,
                               layoutManager : CustomGridLayoutManager,
                               motionLayout: MotionLayout,
                               textView : MaterialTextView,
                                imageView: AppCompatImageView) : Boolean {
     if (true) {
         layoutManager.setScrollEnabled(false)
         motionLayout.transitionToStart()
         textView.text = context.getString(R.string.show_more)
         imageView.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_down_expand
            )
        )
        return false
    } else  {
        layoutManager.setScrollEnabled(true)
        motionLayout.transitionToEnd()
        textView.text = context.getString(R.string.hide_show_more)
        imageView.setImageDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.ic_up_expand
            )
        )
        return true
    }
}