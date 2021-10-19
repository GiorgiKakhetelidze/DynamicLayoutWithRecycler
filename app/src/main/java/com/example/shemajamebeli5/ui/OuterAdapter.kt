package com.example.shemajamebeli5.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli5.databinding.OuterItemBinding
import com.example.shemajamebeli5.model.UserSubListItem

class OuterAdapter : RecyclerView.Adapter<OuterAdapter.OuterItemViewHolder>() {

    var data: List<List<UserSubListItem>> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OuterItemViewHolder {
        return OuterItemViewHolder(
            OuterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: OuterItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = data.size

    inner class OuterItemViewHolder(private val binding: OuterItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {

            binding.outerItemRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
            val innerAdapter = InnerAdapter()
            innerAdapter.data = data[adapterPosition]
            binding.outerItemRecyclerView.adapter = innerAdapter
        }
    }
}