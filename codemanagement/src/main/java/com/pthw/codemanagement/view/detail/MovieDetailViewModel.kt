package com.pthw.codemanagement.view.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import com.pthw.codemanagement.domain.coroutine.TwoParams
import com.pthw.codemanagement.domain.moviedetailpage.GetMovieDetail
import com.pthw.codemanagement.domain.moviedetailpage.UpdateMovieFavorite
import com.pthw.codemanagement.utils.exceptionhandler.ExceptionToStringMapper
import com.pthw.codemanagement.utils.viewstate.ObjViewState
import com.pthw.sharebase.extensions.orFalse
import com.pthw.sharebase.extensions.orZero
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by P.T.H.W on 03/10/2023.
 */

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val exception: ExceptionToStringMapper,
    private val getMovieDetail: GetMovieDetail,
    private val updateMovieFavorite: UpdateMovieFavorite
) : ViewModel() {

    var movieDataVO: MovieDataVO? = null

    private val _movieDetailFlow: MutableStateFlow<ObjViewState<MovieDataVO>> =
        MutableStateFlow(ObjViewState.Idle())
    val movieDetailFlow = _movieDetailFlow.asStateFlow()

    fun getMovieDetail(movieId: Int) {
        viewModelScope.launch {
            getMovieDetail.execute(movieId)
                .collectLatest {
                    _movieDetailFlow.value = ObjViewState.Success(it)
                }
        }
    }

    fun updateFavorite() {
        viewModelScope.launch {
            updateMovieFavorite.execute(
                TwoParams(
                    movieDataVO?.id.orZero(),
                    !movieDataVO?.isFavorite.orFalse()
                )
            )
        }
    }

}