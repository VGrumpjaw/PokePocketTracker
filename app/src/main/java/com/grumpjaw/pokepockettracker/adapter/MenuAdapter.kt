package com.grumpjaw.pokepockettracker.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.grumpjaw.pokepockettracker.R

class MenuAdapter : ListAdapter<String, MenuAdapter.MenuViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<String>() {
                override fun areItemsTheSame(
                    oldItem: String,
                    newItem: String,
                ): Boolean = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: String,
                    newItem: String,
                ): Boolean = oldItem == newItem
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_menu, parent, false)
        return MenuViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MenuViewHolder,
        position: Int,
    ) {
        holder.bind(getItem(position))
    }

    class MenuViewHolder(
        view: View,
    ) : RecyclerView.ViewHolder(view) {
        private val textView: TextView = view.findViewById(R.id.textView)

        fun bind(item: String) {
            textView.text = item
        }
    }
}
