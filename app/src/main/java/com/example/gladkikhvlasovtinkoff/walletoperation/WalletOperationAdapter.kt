package com.example.gladkikhvlasovtinkoff.walletoperation

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.gladkikhvlasovtinkoff.databinding.WalletOperationDataItemBinding
import com.example.gladkikhvlasovtinkoff.util.loadImage
import java.util.*

class WalletOperationAdapter internal constructor(
    context: Context,
    _walletModels: ArrayList<WalletOperationModel>,
) :
    RecyclerView.Adapter<WalletOperationViewHolder>() {
    var context: Context
    var walletModels: ArrayList<WalletOperationModel>

    private val requestListener = object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any,
            target: Target<Drawable>,
            isFirstResource: Boolean
        ): Boolean {
            return false
        }

        override fun onResourceReady(
            resource: Drawable,
            model: Any,
            target: Target<Drawable>,
            dataSource: DataSource,
            isFirstResource: Boolean
        ): Boolean {

            return false
        }
    }

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

    fun update(modelList: ArrayList<WalletOperationModel>) {
        walletModels = modelList
    }

    init {
        this.context = context
        this.walletModels = _walletModels
    }
}