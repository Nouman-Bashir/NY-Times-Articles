package com.ny.times.nytimesarticles.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerviewItemDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildLayoutPosition(view)
        if (position != RecyclerView.NO_POSITION) {
            outRect.set(offset, offset, offset, offset)
        } else {
            outRect.set(0, 0, 0, 0)
        }
    }
}
