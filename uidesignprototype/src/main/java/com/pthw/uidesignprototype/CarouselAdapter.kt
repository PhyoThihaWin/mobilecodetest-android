package com.pthw.uidesignprototype

import android.view.ViewGroup
import com.pthw.sharebase.core.recyclerview.BaseListAdapter
import com.pthw.sharebase.core.recyclerview.BaseViewHolder
import com.pthw.sharebase.core.recyclerview.diffCallBackWith
import com.pthw.sharebase.extensions.inflater
import com.pthw.sharebase.extensions.loadFromUrl
import com.pthw.uidesignprototype.databinding.ListiItemCarouselBinding

/**
 * Created by P.T.H.W on 03/10/2023.
 */
class CarouselAdapter() : BaseListAdapter<String, CarouselAdapter.CarouselViewHolder>(
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
    ): CarouselViewHolder {
        val binding = ListiItemCarouselBinding.inflate(parent.inflater(), parent, false)
        return CarouselViewHolder(binding)
    }


    inner class CarouselViewHolder(
        private val binding: ListiItemCarouselBinding,
    ) : BaseViewHolder<String>(binding.root) {

        override fun bind(item: String) {
            binding.apply {
                ivCarousel.loadFromUrl(item)
            }
        }
    }


    companion object {
        val MockData = arrayListOf(
            "https://c4.wallpaperflare.com/wallpaper/796/59/790/sea-beach-landscape-palm-trees-wallpaper-preview.jpg",
            "https://img.freepik.com/free-photo/beautiful-landscape-sea-ocean-with-silhouette-coconut-palm-tree-sunset-sunrise_74190-14109.jpg",
            "https://cdn.wallpapersafari.com/13/77/CO1R0y.jpg",
            "https://wallpapercave.com/wp/wp10507324.jpg",
            "https://img.freepik.com/free-photo/beautiful-landscape-sea-ocean-with-silhouette-coconut-palm-tree-sunset-sunrise_74190-14109.jpg",
            "https://c4.wallpaperflare.com/wallpaper/796/59/790/sea-beach-landscape-palm-trees-wallpaper-preview.jpg",
            "https://img.freepik.com/free-photo/beautiful-landscape-sea-ocean-with-silhouette-coconut-palm-tree-sunset-sunrise_74190-14109.jpg",
            "https://cdn.wallpapersafari.com/13/77/CO1R0y.jpg",
        )
    }

}

