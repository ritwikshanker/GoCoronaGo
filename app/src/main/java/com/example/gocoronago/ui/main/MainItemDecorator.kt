package com.example.gocoronago.ui.main

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gocoronago.base.UiUtils

class MainItemDecorator(val context: Context) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        val adapter = parent.adapter
        if (adapter is MainAdapter) {
            if (position >= 0) {
                val item = adapter.getItem(position)
                setItemOffset(outRect, view, parent, state, item, position)
            } else {
                // viewholder has been removed
                setItemOffsetOnRemoval(outRect, view, parent, state, position)
            }
        }
    }

    private fun setItemOffsetOnRemoval(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State, position: Int
    ) {
        val item = view.tag
        if (item != null) {
            setItemOffset(outRect, view, parent, state, item, position)
        }
    }

    private fun setItemOffset(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
        item: Any,
        position: Int
    ) {
        outRect.top = UiUtils.dpToPx(10)
        outRect.left = UiUtils.dpToPx(10)
        outRect.right = UiUtils.dpToPx(10)
        view.tag = item
    }
}