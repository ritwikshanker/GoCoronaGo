package com.example.gocoronago.ui.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gocoronago.homepage.CountriesItem

class MainAdapter(val context: Context) :
    ListAdapter<Any, RecyclerView.ViewHolder>(MoviesDiffCallback(context)) {
    companion object {
        val TAG = MainAdapter::class.java.name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var viewHolder: RecyclerView.ViewHolder? = null
        when (viewType) {
            MainViewHolder.layout -> viewHolder = MainViewHolder.create(context, inflater, parent)
        }
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        Log.d(TAG, "onBindViewHolder: $item")
        when (holder) {
            is MainViewHolder -> holder.bind(item as CountriesItem)
        }
    }

    override fun getItemViewType(position: Int): Int {
        super.getItemViewType(position)
        var itemViewType: Int = MainViewHolder.layout
        val item = getItem(position)

        when (item) {
            is CountriesItem -> itemViewType = MainViewHolder.layout
        }
        return itemViewType
    }

    public override fun getItem(position: Int): Any {
        return super.getItem(position)
    }

}
