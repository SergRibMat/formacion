package com.atsistemas.myapplication.home_activity.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.atsistemas.data.models.TransactionDTO
import com.atsistemas.myapplication.R
import com.atsistemas.myapplication.commons.BaseFragment
import com.atsistemas.myapplication.commons.uicomponents.ErrorDialog
import com.atsistemas.myapplication.databinding.HomeFragmentBinding
import com.atsistemas.myapplication.home_activity.home.vm.HomeViewModel
import com.atsistemas.myapplication.utils.SharedTransactionVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
class HomeFragment: BaseFragment(), CellClickListener {

    private val presenter: HomeViewModel by sharedViewModel()
    private val sharedTransactionVM: SharedTransactionVM by sharedViewModel()


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
    }


    override fun loadObservers(){
        presenter.transactionsList.observe(viewLifecycleOwner, {
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            adapter = TransactionAdapter(it, this)
            binding.recyclerView.adapter = adapter
        })

        presenter.showMessage.observe(viewLifecycleOwner, {
            Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
        })

        presenter.showError.observe(viewLifecycleOwner,{
            errorDialog = activity?.let { activity ->
                ErrorDialog(
                        activity,
                        getString(R.string.alert),
                        it,
                        getString(R.string.close)
                ) {
                    errorDialog?.dismiss()
                }
            }
            errorDialog!!.setCancelable(false)
            errorDialog!!.show()
        })
        presenter.isLoading.observe(viewLifecycleOwner,{
            if (it){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })
    }


    override fun onCellClickListener(transaction: TransactionDTO) {
        Toast.makeText(activity,"Cell: ${transaction.description}", Toast.LENGTH_SHORT).show()
        sharedTransactionVM.setTransaction(transaction)
        findNavController().navigate(R.id.action_homeFragment_to_detailFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

