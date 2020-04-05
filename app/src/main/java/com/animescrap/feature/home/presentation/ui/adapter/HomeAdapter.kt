package com.animescrap.feature.home.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.animescrap.R
import com.animescrap.data.model.home.domain.NewEpisodeDomain
import kotlinx.android.synthetic.main.row_data_home.view.*

class HomeAdapter(private val onItemClickListener: ((newEpisodeDomain: NewEpisodeDomain) -> Unit)) :
    RecyclerView.Adapter<HomeAdapter.ItemViewHolder>() {

    private var listItem = ArrayList<NewEpisodeDomain>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.row_data_home, p0, false)
        return ItemViewHolder(view, onItemClickListener)
    }

    override fun getItemCount(): Int = listItem.size

    override fun onBindViewHolder(holder: ItemViewHolder, p1: Int) {

        val dataItem = listItem[p1]
        holder.bindView(dataItem)
    }

    fun addList(listItem: List<NewEpisodeDomain>) {
        this.listItem.addAll(listItem)
        notifyItemRangeInserted(this.listItem.size - listItem.size, this.listItem.size)
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
}