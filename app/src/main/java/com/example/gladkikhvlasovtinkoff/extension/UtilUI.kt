package com.example.gladkikhvlasovtinkoff.extension

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import com.example.gladkikhvlasovtinkoff.MainActivity
import com.example.gladkikhvlasovtinkoff.R
import com.google.android.material.textfield.TextInputEditText

//TODO change to extension
fun setupNaviagtion(
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


fun setupTextStyleAndObserve(editText : TextInputEditText, confirmButton : AppCompatButton, context : Context) {
    editText.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            editText.removeTextChangedListener(this)
            val isEnabled = s.toString() != ""
            editText.setText(
                s?.toString()?.styleInput() ?: ""
            )

            if (isEnabled) {
                confirmButton.setEnabled(context)
            } else
                confirmButton.setDisabled(context)

            editText.setSelection(
                editText.text?.toString()?.length ?: 0
            )
            editText.addTextChangedListener(this)
        }
    })
}