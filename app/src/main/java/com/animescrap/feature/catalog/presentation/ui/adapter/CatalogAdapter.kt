package com.animescrap.feature.catalog.presentation.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.animescrap.R
import com.animescrap.data.model.catalog.domain.CatalogDomain
import kotlinx.android.synthetic.main.row_data_home.view.*

class CatalogAdapter(private val onItemClickListener: ((catalogDomain: CatalogDomain) -> Unit)) :
    RecyclerView.Adapter<CatalogAdapter.ItemViewHolder>() {

    private var listItem = ArrayList<CatalogDomain>()
    private var listItemFiltered = ArrayList<CatalogDomain>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.row_data_catalog, p0, false)
        return ItemViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ItemViewHolder, p1: Int) {

        val dataItem = listItem[p1]
        holder.bindView(dataItem)
    }

    fun addList(listItem: List<CatalogDomain>) {
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
        private val onItemClickListener: ((catalogDomain: CatalogDomain) -> Unit)
    ) : RecyclerView.ViewHolder(view) {

        fun bindView(catalogDomain: CatalogDomain) = with(view) {
            text_title.text = catalogDomain.title
            image_cover.load(catalogDomain.image)

            this.setOnClickListener {
                onItemClickListener.invoke(catalogDomain)
            }
        }
    }
}