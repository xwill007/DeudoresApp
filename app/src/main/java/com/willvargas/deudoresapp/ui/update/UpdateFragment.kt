package com.willvargas.deudoresapp.ui.update

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
import com.willvargas.deudoresapp.databinding.FragmentUpdateBinding

class UpdateFragment : Fragment() {

    companion object {
        fun newInstance() = UpdateFragment()
    }

    private lateinit var viewModel: UpdateViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() =_binding!!

    private var isSearching = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)
        val root: View = binding.root

        var idDebtor =0
        binding.updateButton.setOnClickListener {
            val debtorDao: DebtorDao = DeudoresApp.database.DebtorDao()
            val name = binding.nameEditText.text.toString()

            if(isSearching){  //Buscando
                val debtor: Debtor= debtorDao.readDebtor(name)
                if(debtor != null){
                    idDebtor = debtor.id
                    binding.amountEditText.setText(debtor.amount.toString())
                    binding.phoneEditText.setText(debtor.phone)
                    binding.updateButton.text = getString(R.string.title_update)
                    isSearching = false
                }else {
                    Toast.makeText(requireContext(), getString(R.string.dont_exist), Toast.LENGTH_LONG).show()
                    cleanWidgets()
                }
            }else{    //actualizando
                val debtor = Debtor(
                    id = idDebtor,
                    name = binding.nameEditText.text.toString(),
                    amount = binding.amountEditText.text.toString().toLong(),
                    phone = binding.phoneEditText.text.toString()
                )
                debtorDao.updateDebtor(debtor)
                binding.updateButton.text = getString(R.string.title_read)
                isSearching = true
                cleanWidgets()
                Toast.makeText(requireContext(), getString(R.string.debtor_updated), Toast.LENGTH_LONG).show()
            }
        }

        return root
    }

    private fun cleanWidgets() {
        with(binding) {
            nameEditText.setText("")
            phoneEditText.setText("")
            amountEditText.setText("")
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(UpdateViewModel::class.java)
        // TODO: Use the ViewModel
    }

}