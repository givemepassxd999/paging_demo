package com.example.paging_demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.github_item.view.*

class GithubAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val itemsList = arrayListOf<String>()

    fun setData(list: List<String>) {
        itemsList.clear()
        itemsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.github_item, parent, false)
        return GithubViewHolder(view)
    }

    override fun getItemCount() = itemsList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as GithubViewHolder).bind(itemsList[position])
    }

    inner class GithubViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(content: String) {
            itemView.name.text = content
        }
    }
}