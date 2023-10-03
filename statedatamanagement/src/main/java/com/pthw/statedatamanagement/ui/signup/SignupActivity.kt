package com.pthw.statedatamanagement.ui.signup

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.pthw.sharebase.core.BaseActivity
import com.pthw.sharebase.extensions.afterTextChangedDelayed
import com.pthw.sharebase.extensions.dateFormatterDMY
import com.pthw.sharebase.extensions.inflater
import com.pthw.sharebase.extensions.isEmailValid
import com.pthw.sharebase.extensions.showDateTimePicker
import com.pthw.sharebase.extensions.showShortToast
import com.pthw.statedatamanagement.databinding.ActivitySignupBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.Date

@AndroidEntryPoint
class SignupActivity : BaseActivity<ActivitySignupBinding>() {

    companion object {
        fun newInstance(context: Context) {
            val intent = Intent(context, SignupActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val viewModel by viewModels<SignupViewModel>()

    override val binding: ActivitySignupBinding by lazy {
        ActivitySignupBinding.inflate(inflater())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.setupUI()
        binding.inputBoxTextChangeListener()

        binding.prepareInitialFormData()
    }

    private fun ActivitySignupBinding.setupUI() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.btbCreateNewAccount.setOnClickListener {
            if (signupValidationCheck()) {
                viewModel.firstName = tilSignupFirstName.editText?.text.toString()
                viewModel.lastName = tilSignupLastName.editText?.text.toString()
                viewModel.emailAddress = tilSignupEmailAddress.editText?.text.toString()
                viewModel.dob = tilSignupDob.editText?.text.toString()
                viewModel.nationality = tilSignupNationality.editText?.text.toString()
                viewModel.residence = tilSignupResidence.editText?.text.toString()
                viewModel.gender = tabGender.selectedTabPosition
                showShortToast("Form validation success and account created!")
            }
        }

        // date of birth calendar
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -5)
        tilSignupDob.editText?.setOnClickListener {
            supportFragmentManager.showDateTimePicker(
                title = "Select birthday",
                start = calendar.timeInMillis,
                before = calendar.timeInMillis
            ) {
                val dateString = Date(it).dateFormatterDMY()
                binding.tilSignupDob.editText?.setText(dateString)
            }
        }
    }

    private fun ActivitySignupBinding.prepareInitialFormData() {
        tilSignupFirstName.editText?.setText(viewModel.firstName)
        tilSignupLastName.editText?.setText(viewModel.lastName)
        tilSignupEmailAddress.editText?.setText(viewModel.emailAddress)
        tilSignupDob.editText?.setText(viewModel.dob)
        tilSignupNationality.editText?.setText(viewModel.nationality)
        tilSignupResidence.editText?.setText(viewModel.residence)
        tabGender.getTabAt(viewModel.gender)?.select()
    }


    private fun ActivitySignupBinding.signupValidationCheck(): Boolean {
        val firstName = if (tilSignupFirstName.editText?.text.toString().isNotEmpty()) true else {
            tilSignupFirstName.error = "Please enter your first name"
            false
        }

        val lastName = if (tilSignupLastName.editText?.text.toString().isNotEmpty()) true else {
            tilSignupLastName.error = "Please enter your last name"
            false
        }

        val emailAddress =
            if (tilSignupEmailAddress.editText?.text.toString().isNotEmpty()) true else {
                tilSignupEmailAddress.error = "Please enter your email name"
                false
            }

        val emailValid =
            if (tilSignupEmailAddress.editText?.text.toString().isEmailValid()) true else {
                tilSignupEmailAddress.error = "Please enter valid email address"
                false
            }

        val dob = if (tilSignupDob.editText?.text.toString().isNotEmpty()) true else {
            tilSignupDob.error = "Please enter your date of birth"
            false
        }

        val nationality =
            if (tilSignupNationality.editText?.text.toString().isNotEmpty()) true else {
                tilSignupNationality.error = "Please enter your nationality"
                false
            }

        val residence = if (tilSignupResidence.editText?.text.toString().isNotEmpty()) true else {
            tilSignupResidence.error = "Please enter your country of residence"
            false
        }

        return firstName && lastName && emailAddress && emailValid && dob && nationality && residence
    }

    private fun ActivitySignupBinding.inputBoxTextChangeListener() {
        tilSignupFirstName.editText?.afterTextChangedDelayed {
            tilSignupFirstName.error = null
        }
        tilSignupLastName.editText?.afterTextChangedDelayed {
            tilSignupLastName.error = null
        }
        tilSignupEmailAddress.editText?.afterTextChangedDelayed {
            tilSignupEmailAddress.error = null

        }
        tilSignupDob.editText?.afterTextChangedDelayed {
            tilSignupDob.error = null
        }
        tilSignupNationality.editText?.afterTextChangedDelayed {
            tilSignupNationality.error = null

        }
        tilSignupResidence.editText?.afterTextChangedDelayed {
            tilSignupResidence.error = null
        }
    }

}