package com.willvargas.deudoresapp.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.willvargas.deudoresapp.R
import com.willvargas.deudoresapp.data.entities.Debtor
import com.willvargas.deudoresapp.databinding.CardViewDebtorsItemBinding

class DebtorsAdapter(private val onItemClicked:(Debtor)->Unit,
):RecyclerView.Adapter<DebtorsAdapter.ViewHolder>() {

    private var listDebtor:MutableList<Debtor> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_view_debtors_item,parent,false)
        return ViewHolder(view)
     }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listDebtor[position])
        holder.itemView.setOnClickListener { onItemClicked(listDebtor[position]) }
    }

    override fun getItemCount(): Int {
        return listDebtor.size
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val binding = CardViewDebtorsItemBinding.bind(view)
        fun bind(debtor: Debtor){
            with(binding){
                nameTextView.text = debtor.name
                amountTextView.text = debtor.amount.toString()
            }

        }
    }

    fun appendItems(newItems: MutableList<Debtor>){
        listDebtor.clear()
        listDebtor.addAll(newItems)
        notifyDataSetChanged()
    }

}