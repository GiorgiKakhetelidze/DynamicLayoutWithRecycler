package com.example.shemajamebeli5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.shemajamebeli5.databinding.FragmentRegisteredBinding
import com.example.shemajamebeli5.databinding.FragmentUserBinding


class RegisteredFragment : Fragment() {

    private var _binding: FragmentRegisteredBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisteredBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}