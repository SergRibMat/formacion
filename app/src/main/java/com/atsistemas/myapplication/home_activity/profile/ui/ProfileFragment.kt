package com.atsistemas.myapplication.home_activity.profile.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.atsistemas.myapplication.commons.BaseFragment
import com.atsistemas.myapplication.commons.checkIfEmptyText
import com.atsistemas.myapplication.databinding.ProfileFragmentBinding
import com.atsistemas.myapplication.home_activity.profile.vm.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment: BaseFragment() {

    private val presenter: ProfileViewModel by viewModel()

    private var _binding: ProfileFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ProfileFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun loadObservers() {

        presenter.profileData.observe(viewLifecycleOwner, {
            if (it != null) {
                binding.tvName.text = "Name: ${it.name}"
                binding.tvSurname.text = "Surname: ${it.surname}"
                Toast.makeText(context, "${it.name}", Toast.LENGTH_SHORT).show()
            }

        })
    }

    override fun loadButtonListeners() {

        binding.btnSave.setOnClickListener{
            presenter.saveProfileData(getName(), getSurname())
        }


    }



    private fun getName(): String = binding.etName.text.toString().checkIfEmptyText()

    private fun getSurname(): String = binding.etSurname.text.toString().checkIfEmptyText()



    override fun onStop() {
        super.onStop()
        presenter.saveProfileData(getName(), getSurname())
    }


}