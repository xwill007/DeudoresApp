package com.willvargas.deudoresapp.ui.delete

import android.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.willvargas.deudoresapp.DeudoresApp
import com.willvargas.deudoresapp.R
import com.willvargas.deudoresapp.data.dao.DebtorDao
import com.willvargas.deudoresapp.data.entities.Debtor
import com.willvargas.deudoresapp.databinding.FragmentDeleteBinding

class DeleteFragment : Fragment() {

    companion object {
        fun newInstance() = DeleteFragment()
    }

    private lateinit var viewModel: DeleteViewModel
    private var _binding: FragmentDeleteBinding? = null
    private val binding get() =_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDeleteBinding.inflate(inflater,container,false)
        val root: View = binding.root

        binding.deleteButton.setOnClickListener{
            deleteDebtor(binding.nameEditText.text.toString())
        }

        return root
    }

    private fun deleteDebtor(name: String){
        val debtorDao: DebtorDao = DeudoresApp.database.DebtorDao()
        val debtor: Debtor = debtorDao.readDebtor(name)
        if(debtor != null){
            val alertDialog: AlertDialog? = activity?.let{
                val builder = AlertDialog.Builder(it)
                builder.apply{
                    setMessage(getString(R.string.want_to_eliminate)+debtor.name +getString(R.string.your_debt_is)+debtor.amount.toString() + "?")
                    setPositiveButton(R.string.accept){dialog, id ->
                        debtorDao.deleteDebtor(debtor)
                        Toast.makeText(requireContext(), getString(R.string.debtor_eliminated), Toast.LENGTH_LONG).show()
                        binding.nameEditText.setText("")
                    }
                    setNegativeButton(R.string.cancel){dialog, id ->
                    }
                }
                builder.create()
            }
            alertDialog?.show()

        }else{
            Toast.makeText(requireContext(), getString(R.string.dont_exist), Toast.LENGTH_LONG).show()
        }

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeleteViewModel::class.java)
        // TODO: Use the ViewModel
    }

}