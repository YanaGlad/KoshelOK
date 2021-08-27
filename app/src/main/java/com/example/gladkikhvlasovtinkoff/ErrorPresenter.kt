package com.example.gladkikhvlasovtinkoff

import android.view.View

interface ErrorPresenter {
    fun showNetworkError(listener: View.OnClickListener?)

    fun showUnexpectedError()
}