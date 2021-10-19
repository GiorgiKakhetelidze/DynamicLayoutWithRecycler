package com.example.shemajamebeli5.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli5.R
import com.example.shemajamebeli5.databinding.InnerItemChooserBinding
import com.example.shemajamebeli5.databinding.InnerItemInputBinding
import com.example.shemajamebeli5.extensions.markRequired
import com.example.shemajamebeli5.model.UserSubListItem
import java.lang.RuntimeException

class InnerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<UserSubListItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemViewType(position: Int) =
        if (data[position].field_type == INPUT) VIEW_TYPE_INPUT else VIEW_TYPE_CHOOSER

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            VIEW_TYPE_INPUT -> InnerItemInputViewHolder(
                binding = InnerItemInputBinding.inflate(LayoutInflater.from(parent.context))
            )
            VIEW_TYPE_CHOOSER -> InnerItemChooserViewHolder(
                binding = InnerItemChooserBinding.inflate(LayoutInflater.from(parent.context))
            )
            else -> throw RuntimeException("Unknown ViewType")
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is InnerItemInputViewHolder -> holder.bind()
            is InnerItemChooserViewHolder -> holder.bind()
        }
    }

    override fun getItemCount() = data.size

    inner class InnerItemInputViewHolder(private val binding: InnerItemInputBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var curData: UserSubListItem

        fun bind() {
            curData = data[adapterPosition]
            binding.txtInputLayoutView.setTag(R.string.id_key, curData.field_id)
            binding.txtInputLayoutView.setTag(R.string.required_key, curData.required)
            binding.txtInputLayoutView.hint = curData.hint
            binding.txtInputLayoutView.isActivated = curData.isActive
            binding.txtInputLayoutView.markRequired(curData.required.toBoolean())
        }

    }

    inner class InnerItemChooserViewHolder(private val binding: InnerItemChooserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var curData: UserSubListItem
        fun bind() {
            curData = data[adapterPosition]
            binding.chooserInputLayout.setTag(R.string.id_key, curData.field_id)
            binding.chooserInputLayout.setTag(R.string.required_key, curData.required)
            binding.chooserInputLayout.hint = curData.hint
            binding.chooserInputLayout.isActivated = curData.isActive
            binding.chooserInputLayout.markRequired(curData.required.toBoolean())

        }
        
    }

    companion object {
        const val VIEW_TYPE_INPUT = 1
        const val VIEW_TYPE_CHOOSER = 2
        const val INPUT = "input"
    }

}


