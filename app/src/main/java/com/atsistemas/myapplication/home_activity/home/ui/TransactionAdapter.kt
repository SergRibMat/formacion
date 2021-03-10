package com.atsistemas.myapplication.home_activity.home.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.atsistemas.data.models.TransactionDTO
import com.atsistemas.myapplication.databinding.ItemTransactionBinding

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
class TransactionAdapter(private var mValues: List<TransactionDTO>?,
                         private val cellClickListener: CellClickListener
                            ): RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private lateinit var binding: ItemTransactionBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionAdapter.ViewHolder {
        binding = ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: TransactionAdapter.ViewHolder, position: Int) {
        mValues?.let {
            holder.tvDate.text = it[position].date
            if (it[position].description.isNullOrEmpty()){
                holder.tvDescription.text = ""
            }else{
                holder.tvDescription.text = it[position].description
            }
            holder.tvAmount.text = it[position].amount + "€"
            if (it[position].fee.isNullOrEmpty()){
                holder.tvFee.text = ""
                holder.tvFee.visibility = View.GONE
            } else {
                holder.tvFee.text = it[position].fee
                holder.tvFee.visibility = View.VISIBLE
            }
            if (it[position].amount.toDouble() > 0){
                holder.imgIn.visibility = View.VISIBLE
                holder.imgOut.visibility = View.GONE
            } else {
                holder.imgIn.visibility = View.GONE
                holder.imgOut.visibility = View.VISIBLE
            }
            holder.itemView.setOnClickListener { _ ->
                cellClickListener.onCellClickListener(it[position])
            }
        } ?: clearList()
    }

    override fun getItemCount(): Int {
        return mValues?.size ?: 0
    }

    inner class ViewHolder (mView: View): RecyclerView.ViewHolder(mView){
        val tvDate = binding.tvDate
        val tvDescription = binding.tvDescription
        val tvAmount = binding.tvAmount
        val tvFee = binding.tvFee
        var imgIn = binding.imgTransaction
        var imgOut = binding.imgTransactionOut

        override fun toString(): String {
            return super.toString() + " '" + tvDate.text + "'"
        }
    }

    private fun clearList() {
        val emptyList = listOf<TransactionDTO>()
        mValues = emptyList
        notifyItemRangeRemoved(0, 0)
    }
}

interface CellClickListener{
    fun onCellClickListener(transaction: TransactionDTO)
}