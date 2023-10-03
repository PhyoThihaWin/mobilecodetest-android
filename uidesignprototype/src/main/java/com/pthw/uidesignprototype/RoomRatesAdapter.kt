package com.pthw.uidesignprototype

import android.view.ViewGroup
import com.pthw.sharebase.core.recyclerview.BaseListAdapter
import com.pthw.sharebase.core.recyclerview.BaseViewHolder
import com.pthw.sharebase.core.recyclerview.diffCallBackWith
import com.pthw.sharebase.extensions.inflater
import com.pthw.sharebase.extensions.loadFromUrl
import com.pthw.uidesignprototype.databinding.ListItemByRatesBinding
import com.pthw.uidesignprototype.databinding.ListItemByRoomBinding
import com.pthw.uidesignprototype.databinding.ListiItemCarouselBinding

/**
 * Created by P.T.H.W on 03/10/2023.
 */
class RoomRatesAdapter(
    val byRoom: Boolean = true
) : BaseListAdapter<String, BaseViewHolder<String>>(
    diffCallBackWith(
        areItemTheSame = { old, new -> old == new },
        areContentsTheSame = { old, new -> old == new }
    )
) {

    init {
        this.submitList(MockData)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<String> {
        return if (byRoom) {
            val binding = ListItemByRoomBinding.inflate(parent.inflater(), parent, false)
            ByRoomViewHolder(binding)
        } else {
            val binding = ListItemByRatesBinding.inflate(parent.inflater(), parent, false)
            return ByRatesViewHolder(binding)
        }
    }


    inner class ByRoomViewHolder(
        private val binding: ListItemByRoomBinding,
    ) : BaseViewHolder<String>(binding.root) {

        override fun bind(item: String) {
            binding.apply {
                ivLandscape.loadFromUrl(item)
            }
        }
    }

    inner class ByRatesViewHolder(
        private val binding: ListItemByRatesBinding,
    ) : BaseViewHolder<String>(binding.root) {

        override fun bind(item: String) {
            binding.apply {
            }
        }
    }


    companion object {
        val MockData = arrayListOf(
            "https://www.azcentral.com/gcdn/presto/2022/07/21/PPHX/ea378212-200d-4bfb-aef6-f7eb68798839-Ambiente_Bedroom_PC-Jeff_Zaruba.jpg?crop=2879,1620,x0,y146&width=2879&height=1620&format=pjpg&auto=webp",
            "https://hips.hearstapps.com/edc.h-cdn.co/assets/16/24/2048x1024/landscape-1466014759-hotel-room-lead.jpg?resize=1200:*",
            "https://crossroadsbb.com/images/2020/10/16/s-room.jpg",
            "https://hips.hearstapps.com/edc.h-cdn.co/assets/16/24/2048x1024/landscape-1466014759-hotel-room-lead.jpg?resize=1200:*",
        )
    }

}

