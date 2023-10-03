package com.pthw.codemanagement.view.home.adapter

import android.view.ViewGroup
import com.pthw.codemanagement.R
import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import com.pthw.codemanagement.databinding.ListItemMovieRecommendedBinding
import com.pthw.codemanagement.utils.AppConstants
import com.pthw.sharebase.core.recyclerview.BaseListAdapter
import com.pthw.sharebase.core.recyclerview.BaseViewHolder
import com.pthw.sharebase.core.recyclerview.diffCallBackWith
import com.pthw.sharebase.extensions.inflater
import com.pthw.sharebase.extensions.loadFromUrl
import kotlin.math.roundToInt

class PopularMoviesAdapter(
    private val onItemClick: (MovieDataVO) -> Unit,
) : BaseListAdapter<MovieDataVO, PopularMoviesAdapter.PopularMoviesViewHolder>(
    diffCallBackWith(
        areItemTheSame = { old, new -> old.id == new.id },
        areContentsTheSame = { old, new -> old == new }
    )
) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularMoviesViewHolder {
        val binding = ListItemMovieRecommendedBinding.inflate(parent.inflater(), parent, false)
        return PopularMoviesViewHolder(binding, onItemClick)
    }


    inner class PopularMoviesViewHolder(
        private val binding: ListItemMovieRecommendedBinding,
        private val onItemClick: (MovieDataVO) -> Unit
    ) : BaseViewHolder<MovieDataVO>(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick.invoke(getItem(adapterPosition))
            }
        }

        override fun bind(item: MovieDataVO) {
            binding.apply {
                ivMoviePhoto.loadFromUrl(AppConstants.BASE_IMAGE_URL + item.posterPath)
                tvMovieName.text = item.title
                tvPercentage.text = ((item.voteAverage * 100) / 10).roundToInt().toString() + "%"
                ivFavorite.setImageResource(if (item.isFavorite) R.drawable.ic_baseline_favorite_fill else R.drawable.ic_baseline_favorite_border_24)
            }
        }
    }


}


