package com.willvargas.deudoresapp.ui.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.willvargas.deudoresapp.DeudoresApp
import com.willvargas.deudoresapp.R
import com.willvargas.deudoresapp.data.dao.DebtorDao
import com.willvargas.deudoresapp.data.entities.Debtor
import com.willvargas.deudoresapp.databinding.FragmentReadBinding

class ReadFragment : Fragment() {

    private lateinit var readViewModel: ReadViewModel
    private var _binding: FragmentReadBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        readViewModel =
            ViewModelProvider(this).get(ReadViewModel::class.java)

        _binding = FragmentReadBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // val textView: TextView = binding.textNotifications
        readViewModel.text.observe(viewLifecycleOwner, Observer {
        // textView.text = it
        })

        binding.readButton.setOnClickListener {
            readDebtors(binding.nameEditText.text.toString())
        }

        return root
    }

    private fun readDebtors(name: String) {
        val debtorDao: DebtorDao = DeudoresApp.database.DebtorDao()
        val debtor: Debtor = debtorDao.readDebtor(name)
        if(debtor != null){
            with(binding){
                phoneTextView.text = getString(R.string.phone_value,debtor.phone)
                amountTextView.text = getString(R.string.amount_value,debtor.amount.toString())
            }
        }else {
            Toast.makeText(requireContext(), getString(R.string.dont_exist), Toast.LENGTH_LONG).show()
            with(binding) {
                phoneTextView.text = ""
                amountTextView.text = ""
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}