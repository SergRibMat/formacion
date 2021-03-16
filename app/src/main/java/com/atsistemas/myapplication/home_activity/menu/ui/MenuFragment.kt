package com.atsistemas.myapplication.home_activity.menu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.atsistemas.myapplication.R
import com.atsistemas.myapplication.commons.BaseFragment
import com.atsistemas.myapplication.databinding.DetailFragmentBinding
import com.atsistemas.myapplication.databinding.MenuFragmentBinding

class MenuFragment: BaseFragment() {

    private var _binding: MenuFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = MenuFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    override fun loadObservers() {

    }

    override fun loadButtonListeners(){
        binding.btnToHome.setOnClickListener { view ->
            findNavController().navigate(R.id.action_MenuFragment_to_homeFragment)
        }

        binding.btnToProfile.setOnClickListener { view ->
            findNavController().navigate(R.id.action_MenuFragment_to_profileFragment)
        }


    }



}