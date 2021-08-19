package com.example.gladkikhvlasovtinkoff.ui.ui.walletoperation

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.gladkikhvlasovtinkoff.R

class DeleteDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle(getString(R.string.sure_delete))
                .setCancelable(true)
                .setNegativeButton(getString(R.string.cancel)) { dialog, id ->
                    dialog.cancel()
                    Toast.makeText(
                        activity, "CANCEL",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setPositiveButton(getString(R.string.delete)) { dialog, id ->
                    Toast.makeText(
                        activity, "DELETE",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            val alert: AlertDialog = builder.create()
            alert.show()
            val delete: Button = alert.getButton(DialogInterface.BUTTON_POSITIVE)
            delete.setTextColor(Color.RED)
            alert

        } ?: throw NullPointerException()


    }
}