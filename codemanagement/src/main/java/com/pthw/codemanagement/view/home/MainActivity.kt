package com.pthw.codemanagement.view.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.pthw.codemanagement.databinding.ActivityMainBinding
import com.pthw.codemanagement.utils.viewstate.renderState
import com.pthw.codemanagement.view.home.adapter.PopularMoviesAdapter
import com.pthw.codemanagement.view.home.adapter.UpComingMoviesAdapter
import com.pthw.codemanagement.view.detail.MovieDetailActivity
import com.pthw.sharebase.core.BaseActivity
import com.pthw.sharebase.extensions.gone
import com.pthw.sharebase.extensions.hide
import com.pthw.sharebase.extensions.inflater
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(inflater())
    }

    private val viewModel by viewModels<MainViewModel>()

    private val recommendedMoviesAdapter by lazy {
        PopularMoviesAdapter {
            MovieDetailActivity.newInstance(this, it.id)
        }
    }

    private val upComingMoviesAdapter by lazy {
        UpComingMoviesAdapter {
            MovieDetailActivity.newInstance(this, it.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setupUI()
        renderState()
    }

    private fun ActivityMainBinding.setupUI() {
        rvRecommended.adapter = recommendedMoviesAdapter
        rvUpcoming.adapter = upComingMoviesAdapter
    }

    private fun renderState() {
        lifecycleScope.launch {
            viewModel.homePopularMoviesFlow.collectLatest {
                it.renderState(
                    loading = {
                        binding.tvRecommendedLabel.gone()
                    },
                    success = { data ->
                        if (data.isNotEmpty()) binding.ivEmptyView.gone()
                        recommendedMoviesAdapter.submitList(data)
                    },
                    error = {
                        binding.tvRecommendedLabel.gone()

                    }
                )
            }
        }

        lifecycleScope.launch {
            viewModel.homeUpComingMoviesFlow.collectLatest {
                it.renderState(
                    loading = {
                        binding.tvUpcomingLabel.gone()
                    },
                    success = { data ->
                        if (data.isNotEmpty()) binding.ivEmptyView.gone()
                        upComingMoviesAdapter.submitList(data)

                        Timber.w("Reached upcoming!!")
                    },
                    error = {
                        binding.tvRecommendedLabel.gone()
                    }
                )
            }
        }
    }
}