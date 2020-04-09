package com.animescrap.feature.home.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.animescrap.R
import com.animescrap.core.customview.ItemReload
import com.animescrap.data.model.home.domain.NewEpisodeDomain
import kotlinx.android.synthetic.main.row_data_bottom.view.*
import kotlinx.android.synthetic.main.row_data_home.view.*

class HomeAdapter(private val onItemClickListener: ((newEpisodeDomain: NewEpisodeDomain) -> Unit),
                  private val onRetryClickListener: (() -> Unit)) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val ITEM_LIST = 0
        const val ITEM_BOTTOM = 1
    }

    private var listItem = ArrayList<NewEpisodeDomain>()
    private var isLoadingAdded = false
    private var retryPageLoad = false

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        return if (p1 == ITEM_LIST) {
            ItemViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.row_data_home, p0, false), onItemClickListener)
        } else {
            ItemBottomViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.row_data_bottom, p0, false), onRetryClickListener)
        }
    }

    override fun getItemCount(): Int {
        return if (isLoadingAdded) {
            listItem.size + 1
        } else {
            listItem.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position < listItem.size) ITEM_LIST else ITEM_BOTTOM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, p1: Int) {
        when (holder) {
            is ItemViewHolder -> {
                val dataItem = listItem[p1]
                holder.bindView(dataItem)
            }
            is ItemBottomViewHolder -> {
                holder.bindView(retryPageLoad)
            }
        }
    }

    fun addList(listItem: List<NewEpisodeDomain>) {
        val initPosition = this.listItem.size
        this.listItem = listItem as ArrayList<NewEpisodeDomain>

        notifyItemRangeInserted(initPosition, this.listItem.size)
        addItemBottom()
    }

    fun clearList() {
        isLoadingAdded = false
        this.listItem.clear()
    }

    fun addItemBottom() {
        isLoadingAdded = true
    }

    fun showErrorRetry(showError: Boolean) {
        retryPageLoad = showError
        notifyItemChanged(this.listItem.size, 1)
    }

    fun listItemIsEmpty(onRetryListener: (() -> Unit)) {
        if (this.listItem.isNullOrEmpty()) {
            onRetryListener.invoke()
        }
    }

    class ItemViewHolder(
        private val view: View,
        private val onItemClickListener: ((newEpisodeDomain: NewEpisodeDomain) -> Unit)
    ) :
        RecyclerView.ViewHolder(view) {

        fun bindView(newEpisodeDomain: NewEpisodeDomain) = with(view) {
            text_title.text = newEpisodeDomain.title
            text_sub_title.text = newEpisodeDomain.subTitle
            image_cover.load(newEpisodeDomain.image)

            this.setOnClickListener {
                onItemClickListener.invoke(newEpisodeDomain)
            }
        }
    }

    class ItemBottomViewHolder(private val view: View, private val onRetryClickListener: (() -> Unit)) :
        RecyclerView.ViewHolder(view) {

        private val itemBottom: ItemReload = view.item_bottom

        fun bindView(retryPageLoad: Boolean) = with(view) {
            if (retryPageLoad) {
                itemBottom.showErrorRetry()
            } else {
                itemBottom.showLoading()
            }

            itemBottom.buttonRetry.setOnClickListener {
                onRetryClickListener.invoke()
            }
        }
    }
}