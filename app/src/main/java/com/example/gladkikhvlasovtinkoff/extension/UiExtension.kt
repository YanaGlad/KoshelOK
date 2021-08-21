package com.example.gladkikhvlasovtinkoff.extension

import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R

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


fun EditText.setupTextStyleAndObserve(buttonObserver : Button) {
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