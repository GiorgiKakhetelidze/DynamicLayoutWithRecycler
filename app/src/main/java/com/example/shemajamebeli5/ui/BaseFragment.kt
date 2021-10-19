package com.example.shemajamebeli5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import kotlin.reflect.KClass


typealias inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VM : ViewModel, VB : ViewBinding>(
    private val inflate: inflate<VB>, viewModelClass : Class<VM>
) :
    Fragment() {

    private var _binding: VB? = null
    protected val binding get() = _binding!!
    abstract fun init()

    protected val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        init()
        return binding.root
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}