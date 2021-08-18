package com.example.gladkikhvlasovtinkoff.walletoperation

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.gladkikhvlasovtinkoff.databinding.WalletOperationDataItemBinding
import com.example.gladkikhvlasovtinkoff.util.ItemTouchHelperListener
import com.example.gladkikhvlasovtinkoff.util.loadImage
import java.util.*

class WalletOperationAdapter internal constructor(
    context: Context,
    _walletModels: ArrayList<WalletOperationModel>,
) :
    RecyclerView.Adapter<WalletOperationViewHolder>(), ItemTouchHelperListener {
    var context: Context
    var walletModels: ArrayList<WalletOperationModel>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletOperationViewHolder {
        return WalletOperationViewHolder(
            WalletOperationDataItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            context
        )
    }

    override fun onBindViewHolder(holder: WalletOperationViewHolder, position: Int) {
        val walletModel: WalletOperationModel = walletModels[position]

        holder.binding.dateOperation.text = walletModel.date
        loadImage(context, walletModel.image, holder.binding.imageOperation)

        holder.binding.money.text = walletModel.money
        holder.binding.dateOperation.text = walletModel.date
        holder.binding.subtitleOperation.text = walletModel.subtitle
        holder.binding.titleOperation.text = walletModel.title
        holder.binding.time.text = walletModel.time


    }

    override fun getItemCount(): Int {
        return walletModels.size
    }

    override fun onItemSwipe(position: Int) {
        walletModels[position].isVisible = !walletModels[position].isVisible
        //notifyDataSetChanged()
    }

    fun update(modelList: ArrayList<WalletOperationModel>) {
        walletModels = modelList
    }

    init {
        this.context = context
        this.walletModels = _walletModels
    }

//    override fun onItemMove(fromPosition: Int, toPosition: Int): Boolean {
//        Collections.swap(walletModels, fromPosition, toPosition)
//        notifyItemMoved(fromPosition, toPosition)
//        return true
//    }


}