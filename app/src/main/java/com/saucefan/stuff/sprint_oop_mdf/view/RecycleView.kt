package com.saucefan.stuff.sprint_oop_mdf.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.saucefan.stuff.sprint_oop_mdf.R
import com.saucefan.stuff.sprint_oop_mdf.model.AoeTypes
import com.saucefan.stuff.sprint_oop_mdf.viewmodel.AoeRepository.AoeArrayList
import kotlinx.android.synthetic.main.item_list_content.view.*

class SimpleItemRecyclerViewAdapter(
    private val parentActivity: ItemListActivity,
    private val twoPane: Boolean
) :
    RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
    private val onClickListener: View.OnClickListener


    init {

        onClickListener = View.OnClickListener { v ->
            val item = v.tag as AoeTypes
            if (twoPane) {
                val fragment = ItemDetailFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable("detailview", item)
                        putString(ItemDetailFragment.ARG_ITEM_ID, item.id.toString())
                    }
                }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, ItemDetailActivity::class.java).apply {
                    putExtra("detailview", item)
                    putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id.toString())
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = AoeArrayList[position]
        holder.idView.text = item.id.toString()
        holder.contentView.text = item.show()

        if (item.isFavorite){
            holder.contentView.setBackgroundColor(
                ContextCompat.getColor(holder.contentView.context,
                    R.color.highlight))
        }else holder.contentView.setBackgroundColor(
            ContextCompat.getColor(holder.contentView.context,
                R.color.background))
        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)

        }
    }

    override fun getItemCount() = AoeArrayList.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idView: TextView = view.id_text
        val contentView: TextView = view.content
    }
}
