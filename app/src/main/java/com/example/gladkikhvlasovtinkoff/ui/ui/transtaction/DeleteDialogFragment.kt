package com.example.gladkikhvlasovtinkoff.ui.ui.transtaction

import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.gladkikhvlasovtinkoff.R
import com.example.gladkikhvlasovtinkoff.model.WalletData
import com.example.gladkikhvlasovtinkoff.ui.ui.wallets.DeleteHelper

class DeleteDialogFragment <T> (val deleteHelper: DeleteHelper<T>, val data: T? = null) : DialogFragment() {

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
                .setPositiveButton(getString(R.string.delete)) { _, _ ->
                    deleteHelper.delete(data!!)
                }

            val alert: AlertDialog = builder.create()
            alert.show()
            val delete: Button = alert.getButton(DialogInterface.BUTTON_POSITIVE)
            delete.setTextColor(Color.RED)
            alert
        } ?: throw NullPointerException()
    }
}