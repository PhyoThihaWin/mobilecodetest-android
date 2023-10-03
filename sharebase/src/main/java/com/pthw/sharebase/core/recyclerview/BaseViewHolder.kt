package com.pthw.sharebase.core.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<itemType> protected constructor(
    itemView: View,
) : RecyclerView.ViewHolder(itemView) {

    abstract fun bind(item: itemType)
}