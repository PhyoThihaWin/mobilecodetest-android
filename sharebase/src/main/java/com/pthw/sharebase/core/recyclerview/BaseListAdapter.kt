package com.pthw.sharebase.core.recyclerview

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

abstract class BaseListAdapter<itemType, VH : BaseViewHolder<itemType>> :
    ListAdapter<itemType, VH> {

    protected constructor(diffCallback: DiffUtil.ItemCallback<itemType>) : super(diffCallback)

    protected constructor(config: AsyncDifferConfig<itemType>) : super(config)

    fun getItemAtPosition(position: Int): itemType {
        return getItem(position)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }

}

inline fun <T : Any> diffCallBackWith(
    crossinline areItemTheSame: ((T, T) -> Boolean),
    crossinline areContentsTheSame: ((T, T) -> Boolean)
): DiffUtil.ItemCallback<T> {
    return object : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            return areItemTheSame.invoke(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            return areContentsTheSame.invoke(oldItem, newItem)
        }
    }

}