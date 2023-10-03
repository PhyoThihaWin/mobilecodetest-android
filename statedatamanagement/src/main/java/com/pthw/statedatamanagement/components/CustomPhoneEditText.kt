package com.pthw.statedatamanagement.components

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.hbb20.CountryCodePicker
import com.pthw.sharebase.extensions.afterTextChangedDelayed
import com.pthw.sharebase.extensions.gone
import com.pthw.sharebase.extensions.hide
import com.pthw.sharebase.extensions.show
import com.pthw.statedatamanagement.R
import com.pthw.statedatamanagement.databinding.CustomPhoneEditTextBinding

class CustomPhoneEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var binding: CustomPhoneEditTextBinding
    private lateinit var ccp: CountryCodePicker

    init {
        binding = CustomPhoneEditTextBinding.inflate(LayoutInflater.from(context), this, false)
        addView(binding.root)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()

        ccp = CountryCodePicker(context)

        binding.etPhone.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus || binding.etPhone.length() > 0) {
                binding.tvHint.show()
                binding.etPhone.hint = ""
                binding.viewUnderline.setBackgroundResource(R.color.color_primary)
            } else {
                binding.tvHint.hide()
                binding.etPhone.hint = context.getString(R.string.signup_hint_mobile_number)
                binding.viewUnderline.setBackgroundResource(R.color.color_grey)
            }
        }

        binding.etPhone.afterTextChangedDelayed {
            setError(null)
        }

        binding.tvCountryCode.setOnClickListener {
            ccp.launchCountrySelectionDialog()
        }

    }


    fun fullMobileNumber(): String {
        return ccp.selectedCountryCode + binding.etPhone.text.toString()
    }


    fun setError(msg: String?, borderRed: Boolean = true) {
        if (msg == null) {
            binding.llTextInput.backgroundTintList =
                ColorStateList.valueOf(context.getColor(R.color.color_black))
            binding.tvError.gone()
        } else {
            binding.llTextInput.backgroundTintList =
                ColorStateList.valueOf(context.getColor(R.color.color_black))
            binding.tvError.text = msg
            binding.tvError.show()
        }
    }

    fun getEditText() = binding.etPhone
}
