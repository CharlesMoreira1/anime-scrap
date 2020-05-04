package com.animescrap.feature.history.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.animescrap.R
import com.animescrap.data.model.history.domain.HistoryDomain
import kotlinx.android.synthetic.main.row_data_home.view.*

class HistoryAdapter(private val onItemClickListener: ((historyDomain: HistoryDomain) -> Unit)) :
    RecyclerView.Adapter<HistoryAdapter.ItemViewHolder>() {

    private var listItem = ArrayList<HistoryDomain>()
    private var listItemFiltered = ArrayList<HistoryDomain>()

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_data_history, parent, false)
        return ItemViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ItemViewHolder, p1: Int) {

        val dataItem = listItem[p1]
        holder.bindView(dataItem)
    }

    fun addList(listItem: List<HistoryDomain>) {
        this.listItem.addAll(listItem)
        notifyItemRangeInserted(this.listItem.size - listItem.size, this.listItem.size)

        this.listItemFiltered = ArrayList(listItem)
    }

    fun clearList() {
        this.listItem.clear()
        notifyDataSetChanged()
    }

    fun listItemIsEmpty(onRetryListener: (() -> Unit)) {
        if (this.listItem.isNullOrEmpty()) {
            onRetryListener.invoke()
        }
    }

    @SuppressLint("DefaultLocale")
    fun filterList(text: String) {
        this.listItem.clear()

        if (text.isEmpty()) {
            this.listItem.addAll(listItemFiltered)
        } else {
            listItemFiltered.forEach {
                if (it.title.toLowerCase().contains(text.toLowerCase())) {
                    this.listItem.add(it)
                }
            }
        }
        notifyDataSetChanged()
    }

    class ItemViewHolder(
        private val view: View,
        private val onItemClickListener: ((historyDomain: HistoryDomain) -> Unit)
    ) : RecyclerView.ViewHolder(view) {

        fun bindView(historyDomain: HistoryDomain) = with(view) {
            text_title.text = historyDomain.title
            image_cover.load(historyDomain.image)

            this.setOnClickListener {
                onItemClickListener.invoke(historyDomain)
            }
        }
    }
}