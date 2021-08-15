package com.willvargas.deudoresapp.ui.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.willvargas.deudoresapp.DeudoresApp
import com.willvargas.deudoresapp.data.dao.DebtorDao
import com.willvargas.deudoresapp.data.entities.Debtor
import com.willvargas.deudoresapp.databinding.FragmentCreateBinding
import java.sql.Types.NULL

class CreateFragment : Fragment() {

    private lateinit var createViewModel: CreateViewModel
    private var _binding: FragmentCreateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createViewModel =
            ViewModelProvider(this).get(CreateViewModel::class.java)

        _binding = FragmentCreateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textDashboard
        createViewModel.text.observe(viewLifecycleOwner, Observer {
          //  textView.text = it
        })

        with(binding){
            createButton.setOnClickListener{
                val name = nameEditText.text.toString()
                val phone = phoneEditText.text.toString()
                val amount = amountEditText.text.toString().toLong()
                crearDeudor(name,phone,amount)
            }
        }

        return root
    }

    private fun crearDeudor(name: String, phone: String, amount: Long) {
        val debtor = Debtor(id= NULL,name=name,phone=phone,amount=amount)
        val debtorDao: DebtorDao = DeudoresApp.database.DebtorDao()
        debtorDao.createDebtor(debtor)
        cleanViews()
    }

    private fun cleanViews() {
        with(binding){
            nameEditText.setText("")
            phoneEditText.setText("")
            amountEditText.setText("")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}