package com.pthw.codemanagement.view.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import com.pthw.codemanagement.domain.homepage.FetchPopularMovies
import com.pthw.codemanagement.domain.homepage.FetchUpcomingMovies
import com.pthw.codemanagement.domain.homepage.GetPopularMoviesFromCache
import com.pthw.codemanagement.domain.homepage.GetUpcomingMoviesFromCache
import com.pthw.codemanagement.utils.exceptionhandler.ExceptionToStringMapper
import com.pthw.codemanagement.utils.viewstate.ListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by P.T.H.W on 02/10/2023.
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val exception: ExceptionToStringMapper,
    private val fetchPopularMovies: FetchPopularMovies,
    private val fetchUpcomingMovies: FetchUpcomingMovies,
    private val getPopularMoviesFromCache: GetPopularMoviesFromCache,
    private val getUpcomingMoviesFromCache: GetUpcomingMoviesFromCache
) : ViewModel() {

    private val _homePopularMoviesFlow: MutableStateFlow<ListViewState<MovieDataVO>> =
        MutableStateFlow(ListViewState.Idle())
    val homePopularMoviesFlow = _homePopularMoviesFlow.asStateFlow()

    private val _homeUpComingMoviesFlow: MutableStateFlow<ListViewState<MovieDataVO>> =
        MutableStateFlow(ListViewState.Idle())
    val homeUpComingMoviesFlow = _homeUpComingMoviesFlow.asStateFlow()

    init {
        // popular
        fetchPopularMovies()
        viewModelScope.launch {
            _homePopularMoviesFlow.value = ListViewState.Loading()
            getPopularMoviesFromCache.execute(Unit)
                .collectLatest {
                    _homePopularMoviesFlow.value = ListViewState.Success(it)
                }
        }

        // upcoming
        fetchUpComingMovies()
        viewModelScope.launch {
            _homeUpComingMoviesFlow.value = ListViewState.Loading()
            getUpcomingMoviesFromCache.execute(Unit)
                .collectLatest {
                    _homeUpComingMoviesFlow.value = ListViewState.Success(it)
                }
        }
    }

    // Network call and save to Db
    private fun fetchPopularMovies() {
        viewModelScope.launch {
            runCatching {
                fetchPopularMovies.execute(Unit)
                _homePopularMoviesFlow.value = ListViewState.Idle()
            }.getOrElse {
                Timber.e(it)
                Timber.e(exception.map(it))
                _homePopularMoviesFlow.value = ListViewState.Error(exception.map(it))
            }
        }
    }

    private fun fetchUpComingMovies() {
        viewModelScope.launch {
            runCatching {
                fetchUpcomingMovies.execute(Unit)
                _homeUpComingMoviesFlow.value = ListViewState.Idle()
            }.getOrElse {
                Timber.e(it)
                Timber.e(exception.map(it))
                _homeUpComingMoviesFlow.value = ListViewState.Error(exception.map(it))
            }
        }
    }

}