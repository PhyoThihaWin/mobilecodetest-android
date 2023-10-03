package com.pthw.statedatamanagement.ui.welcomelogin

import android.os.Bundle
import com.pthw.sharebase.core.BaseActivity
import com.pthw.sharebase.extensions.inflater
import com.pthw.statedatamanagement.databinding.ActivityWelcomeLoginBinding
import com.pthw.statedatamanagement.ui.signup.SignupActivity

class WelcomeLoginActivity : BaseActivity<ActivityWelcomeLoginBinding>() {
    override val binding: ActivityWelcomeLoginBinding by lazy {
        ActivityWelcomeLoginBinding.inflate(inflater())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupUI()
    }

    private fun setupUI() {
        binding.btbCreateNewAccount.setOnClickListener {
            SignupActivity.newInstance(this)
        }
    }
}