package com.atsistemas.myapplication.home_activity.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.atsistemas.data.models.TransactionDTO
import com.atsistemas.myapplication.commons.BaseFragment
import com.atsistemas.myapplication.databinding.HomeFragmentBinding
import com.atsistemas.myapplication.home_activity.home.vm.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
class HomeFragment: BaseFragment(), CellClickListener {

    private val presenter: HomeViewModel by viewModel()

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: TransactionAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadObservers()
        presenter.fetchTransactions()
    }


    private fun loadObservers(){
        presenter.transactionsList.observe(viewLifecycleOwner, {
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = TransactionAdapter(it, this)
            binding.recyclerView.adapter = adapter
//            it?.let {
//                presenter.saveTransactions(it)
//            }
        })
    }


    override fun onCellClickListener(transaction: TransactionDTO) {
        Toast.makeText(activity, "clearing...", Toast.LENGTH_LONG).show()
        presenter.clearList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

