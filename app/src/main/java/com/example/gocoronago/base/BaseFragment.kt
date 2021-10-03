package com.example.gocoronago.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<out B : ViewDataBinding> : Fragment() {
    private lateinit var viewDataBinding: B

    val binding: B
        get() = viewDataBinding

    @LayoutRes
    protected abstract fun layoutResourceId(): Int
    protected abstract fun initViewCreated()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutResourceId(), container, false)
        viewDataBinding.lifecycleOwner = this
        viewDataBinding.executePendingBindings()
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewCreated()
    }

    protected fun hideKeyboard() {
        requireActivity().currentFocus.let {
            (requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
                .hideSoftInputFromWindow(it?.windowToken, 0)
        }
    }
}