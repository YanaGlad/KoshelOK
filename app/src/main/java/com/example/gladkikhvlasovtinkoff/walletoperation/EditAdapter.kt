package com.example.gladkikhvlasovtinkoff.walletoperation

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.gladkikhvlasovtinkoff.databinding.EditItemBinding
import com.example.gladkikhvlasovtinkoff.databinding.WalletOperationDataItemBinding
import com.example.gladkikhvlasovtinkoff.util.loadImage
import java.util.*
import kotlin.collections.ArrayList

class EditAdapter internal constructor(
    context: Context,
    val size : Int,
) :
    RecyclerView.Adapter<EditHolder>() {
    var context: Context
    var editModels: ArrayList<EditModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EditHolder {
        return EditHolder(
            EditItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context
        )
    }

    override fun onBindViewHolder(holder: EditHolder, position: Int) {
        holder.binding.edit.setOnClickListener {
            Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show()
        }

        holder.binding.delete.setOnClickListener {
            Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return size
    }


    init {
        this.context = context
        this.editModels = ArrayList()
        for(i in 0..size)
            editModels.add(EditModel())
    }

//    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
//        Collections.swap(walletModels, fromPosition, toPosition)
//        notifyItemMoved(fromPosition, toPosition)
//        return true
//    }


}