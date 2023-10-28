package com.reza.auth.ui.register

import android.content.Context
import androidx.core.widget.doAfterTextChanged
import com.jakewharton.rxbinding4.view.clicks
import com.jakewharton.rxbinding4.widget.textChanges
import com.reza.auth.databinding.FragmentRegisterBinding
import com.reza.auth.ui.AuthActivity
import com.reza.core.ui.base.BaseFragment
import com.reza.core.util.extensions.popBackStack
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.subjects.PublishSubject
import javax.inject.Inject

class RegisterFragment : BaseFragment<FragmentRegisterBinding>() {

    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @Inject
    lateinit var registerPresenter: RegisterPresenter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? AuthActivity)?.authComponent?.inject(this)
    }

    override fun setupUi() {

    }

    override fun registerView() {

    }

    override fun setupListeners() {
        binding.apply {
            imgBack.setOnClickListener {
                (requireActivity() as? AuthActivity)?.popBackStack()
            }

            edtEmail.textChanges()
                .subscribe {
                    // TODO validate input passing to
                }
                .addTo(compositeDisposable)


        }
    }

    override fun getViewBinding(): FragmentRegisterBinding {
        return FragmentRegisterBinding.inflate(layoutInflater)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
    }
}