package com.example.shemajamebeli5.ui

import android.widget.Toast
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shemajamebeli5.R
import com.example.shemajamebeli5.databinding.FragmentUserBinding
import com.google.android.material.textfield.TextInputLayout


class UserFragment : BaseFragment<UserViewModel, FragmentUserBinding>(
    FragmentUserBinding::inflate,
    UserViewModel::class.java
) {

    private val outerAdapter = OuterAdapter()
    private val userData = mutableMapOf<Int, String>()

    override fun init() {
        setRecycler()
        setListeners()
    }

    private fun setListeners() {
        binding.registerBtnView.setOnClickListener {
            if (saveToMap())
                findNavController().navigate(UserFragmentDirections.actionUserFragmentToRegisteredFragment())
            else
                Toast.makeText(requireContext(), "Map is Cleared", Toast.LENGTH_SHORT).show()
        }

        viewModel.userData.observe(viewLifecycleOwner) {
            outerAdapter.data = it
        }
    }

    private fun setRecycler() {
        binding.outerRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.outerRecyclerView.adapter = outerAdapter
    }

    private fun saveToMap(): Boolean {
        binding.outerRecyclerView.children.forEach { cardView ->
            cardView.findViewById<RecyclerView>(R.id.outerItemRecyclerView).children.forEach { inputView ->
                var item = inputView.findViewById<TextInputLayout>(R.id.txtInputLayoutView)
                if (item == null)
                    item = inputView.findViewById(R.id.chooserInputLayout)

                val id = item.getTag(R.string.id_key) as Int
                val required = item.getTag(R.string.required_key) as String
                val text = item.editText?.text.toString()
                val hint = item.editText?.hint.toString()

                if (checkValidity(text, required.toBoolean(), hint))
                    userData.set(key = id, value = text)
                else {
                    userData.clear()
                    return false
                }

            }
        }

        return true
    }

    private fun checkValidity(text: String, isRequired: Boolean, hint: String) =
        if (text.isEmpty() && isRequired) {
            Toast.makeText(requireContext(), "$hint Field is not Filled", Toast.LENGTH_SHORT).show()
            false
        } else
            true

}