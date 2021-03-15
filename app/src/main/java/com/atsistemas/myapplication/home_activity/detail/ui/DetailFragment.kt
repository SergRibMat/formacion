package com.atsistemas.myapplication.home_activity.detail.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.atsistemas.myapplication.commons.BaseFragment
import com.atsistemas.myapplication.databinding.DetailFragmentBinding
import com.atsistemas.myapplication.utils.SharedTransactionVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Created by Juan Manuel Rincón on 3/15/21.
 */
class DetailFragment: BaseFragment() {

    private val sharedTransactionVM: SharedTransactionVM by sharedViewModel()


    private var _binding: DetailFragmentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val transaction = sharedTransactionVM.transaction.value
        transaction?.let {
            binding.detailTV.text =
                "${transaction.id} ${transaction.description} ${transaction.date} ${transaction.amount} " +
                        "${transaction.fee} ${transaction.total}"
        }
    }


    override fun loadObservers() {
    }


}