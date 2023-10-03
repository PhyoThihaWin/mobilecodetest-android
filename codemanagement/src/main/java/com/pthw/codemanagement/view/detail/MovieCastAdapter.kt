package com.pthw.codemanagement.view.detail

import android.view.ViewGroup
import com.pthw.codemanagement.databinding.ListItemMovieCastBinding
import com.pthw.sharebase.core.recyclerview.BaseListAdapter
import com.pthw.sharebase.core.recyclerview.BaseViewHolder
import com.pthw.sharebase.core.recyclerview.diffCallBackWith
import com.pthw.sharebase.extensions.inflater
import com.pthw.sharebase.extensions.loadFromUrl

class MovieCastAdapter(
) : BaseListAdapter<MovieCast, MovieCastAdapter.MovieCastViewHolder>(
    diffCallBackWith(
        areItemTheSame = { old, new -> old.name == new.name },
        areContentsTheSame = { old, new -> old == new }
    )
) {

    init {
        this.submitList(MockData)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieCastViewHolder {
        val binding = ListItemMovieCastBinding.inflate(parent.inflater(), parent, false)
        return MovieCastViewHolder(binding)
    }


    inner class MovieCastViewHolder(
        private val binding: ListItemMovieCastBinding,
    ) : BaseViewHolder<MovieCast>(binding.root) {

        override fun bind(item: MovieCast) {
            binding.apply {
                ivCastPhoto.loadFromUrl(item.profile)
                tvCastName.text = item.name
            }
        }
    }


    companion object {
        val MockData = arrayListOf(
            MovieCast(
                name = "Leonardo DiCaprio",
                profile = "https://cdn.britannica.com/65/227665-050-D74A477E/American-actor-Leonardo-DiCaprio-2016.jpg"
            ),
            MovieCast(
                name = "Cillian Murphy",
                profile = "https://m.media-amazon.com/images/M/MV5BMDUxY2M1NTQtNzcwNC00ZDljLTk4YjctYzJjZGNiYTc0YTE1XkEyXkFqcGdeQXVyMTY5MDA5MDc3._V1_FMjpg_UY8256_.jpg"
            ),
            MovieCast(
                name = "Rorbet Downey Jr",
                profile = "https://m.media-amazon.com/images/M/MV5BNzg1MTUyNDYxOF5BMl5BanBnXkFtZTgwNTQ4MTE2MjE@._V1_.jpg"
            ),
            MovieCast(
                name = "Leonardo DiCaprio",
                profile = "https://cdn.britannica.com/65/227665-050-D74A477E/American-actor-Leonardo-DiCaprio-2016.jpg"
            ),
        )
    }

}

data class MovieCast(
    val profile: String,
    val name: String
)


