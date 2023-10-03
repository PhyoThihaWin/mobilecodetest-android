package com.pthw.codemanagement.view.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.pthw.codemanagement.R
import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import com.pthw.codemanagement.databinding.ActivityMovieDetailBinding
import com.pthw.codemanagement.utils.AppConstants
import com.pthw.codemanagement.utils.viewstate.renderState
import com.pthw.sharebase.core.BaseActivity
import com.pthw.sharebase.extensions.inflater
import com.pthw.sharebase.extensions.loadFromUrl
import com.pthw.sharebase.extensions.orFalse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@AndroidEntryPoint
class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding>() {
    override val binding: ActivityMovieDetailBinding by lazy {
        ActivityMovieDetailBinding.inflate(inflater())
    }

    companion object {
        const val MOVIE_ID = "movie_id"
        fun newInstance(context: Context, movieId: Int) {
            val intent = Intent(context, MovieDetailActivity::class.java)
            intent.putExtra(MOVIE_ID, movieId)
            context.startActivity(intent)
        }
    }

    private val viewModel by viewModels<MovieDetailViewModel>()

    private val castAdapter by lazy {
        MovieCastAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setupUI()
        binding.renderState()

        viewModel.getMovieDetail(intent.getIntExtra(MOVIE_ID, 0))
    }

    private fun ActivityMovieDetailBinding.setupUI() {
        ivBack.setOnClickListener {
            finish()
        }
        ivFavorite.setOnClickListener {
            if (viewModel.movieDataVO?.isFavorite.orFalse()) {
                ivFavorite.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            } else {
                ivFavorite.setImageResource(R.drawable.ic_baseline_favorite_fill)
            }
            viewModel.updateFavorite()
        }

        rvCast.adapter = castAdapter
    }

    private fun ActivityMovieDetailBinding.renderState() {
        lifecycleScope.launch {
            viewModel.movieDetailFlow.collectLatest {
                it.renderState(
                    success = { data ->
                        prepareData(data)
                    }
                )
            }
        }
    }

    private fun ActivityMovieDetailBinding.prepareData(item: MovieDataVO) {
        viewModel.movieDataVO = item
        ivMoviePhoto.loadFromUrl(AppConstants.BASE_IMAGE_URL + item.posterPath)
        tvMovieName.text = item.title
        tvMovieDesc.text = item.overview
        tvPercentage.text = ((item.voteAverage * 100) / 10).roundToInt().toString() + "%"
        ivFavorite.setImageResource(if (item.isFavorite) R.drawable.ic_baseline_favorite_fill else R.drawable.ic_baseline_favorite_border_24)
    }
}