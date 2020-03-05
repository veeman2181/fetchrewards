package com.example.fetchrewardstest.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.fetchrewardstest.BR
import com.example.fetchrewardstest.R
import com.example.fetchrewardstest.model.Item

class ListItemAdapter(var context: Context) : RecyclerView.Adapter<ListItemAdapter.ViewHolder>() {
    private var mItemList: List<Item> = emptyList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(LayoutInflater.from(context), parent, viewType)
    }

    override fun getItemCount(): Int = mItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mItemList[position])
    }

    override fun getItemViewType(position: Int): Int {
        // Check if previous ListId is same as current ListId then return layout id accordingly
        return if (mItemList[position].listId != mItemList.getOrElse(position - 1) { Item() }.listId) {
            R.layout.item_list_id_and_name
        } else {
            R.layout.item_name
        }
    }

    fun setList(list: List<Item>) {
        this.mItemList = list
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        companion object {
            fun create(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): ViewHolder {
                val binding =
                    DataBindingUtil.inflate<ViewDataBinding>(inflater, viewType, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(data: Any) {
            binding.setVariable(BR.itemmodel, data)
            binding.executePendingBindings()
        }
    }
}