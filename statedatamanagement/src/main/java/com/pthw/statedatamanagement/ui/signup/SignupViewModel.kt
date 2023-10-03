package com.pthw.statedatamanagement.ui.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by P.T.H.W on 02/10/2023.
 */
@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {
    var firstName: String? = null
    var lastName: String? = null
    var emailAddress: String? = null
    var dob: String? = null
    var gender: Int = 0
    var nationality: String? = null
    var residence: String? = null
}