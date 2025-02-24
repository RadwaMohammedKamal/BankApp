package com.example.mybank

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import android.graphics.Color
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Locale
import com.example.bankapp.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import java.util.Date

class AddaccountsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_addaccounts, container, false)

        val spinner: Spinner = view.findViewById(R.id.spinner_banks)
        val banksList = listOf(
            "Select Bank", "Banque Misr", "National Bank of Egypt", "Egyptian Arab Land Bank",
            "Agricultural Bank of Egypt", "Industrial Development Bank", "Banque Du Caire",
            "The United Bank", "Bank of Alexandria", "MIDBank S.A. E",
            "Qatar National Bank Alahli S.A.E", "Commercial International Bank (CIB)",
            "Attijariwafa bank Egypt S.A.E", "Societe Arabe Internationale de Banque",
            "Credit Agricole Egypt S.A.E", "Emirates National Bank of Dubai S.A.E.",
            "Suez Canal Bank", "Arab Investment Bank", "AL Ahli Bank of Kuwait",
            "First Abu Dhabi Bank - Misr", "Ahli United Bank", "Faisal Islamic Bank of Egypt",
            "Housing and Development Bank", "Al Baraka Bank of Egypt S.A.E",
            "National Bank of Kuwait (NBK)", "Abu Dhabi Islamic Bank",
            "ABU DHABI COMMERCIAL BANK", "Egyptian Gulf Bank", "Arab African International Bank",
            "HSBC Bank Egypt S.A.E", "Arab Banking Corporation", "Export Development Bank of Egypt",
            "Arab International Bank", "Citi Bank N A / Egypt", "Arab Bank PLC",
            "Mashreq Bank", "National Bank of Greece", "Standard Chartered Bank"
        )

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, banksList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        val btnPickStartDate: Button = view.findViewById(R.id.btnPickStartDate)
        val tvStartDate: TextView = view.findViewById(R.id.tvStartDate)
        val btnPickEndDate: Button = view.findViewById(R.id.btnPickEndDate)
        val tvEndDate: TextView = view.findViewById(R.id.tvEndDate)

        btnPickStartDate.setOnClickListener {
            showMaterialDatePicker { selectedDate ->
                tvStartDate.text = "Start Date: $selectedDate"
                tvStartDate.tag = selectedDate
            }
        }

        btnPickEndDate.setOnClickListener {
            showMaterialDatePicker { date ->
                val startDateText = tvStartDate.tag as? String
                if (startDateText != null) {
                    try {
                        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        val startDateParsed = sdf.parse(startDateText)
                        val endDateParsed = sdf.parse(date)

                        if (startDateParsed != null && endDateParsed != null && endDateParsed.before(startDateParsed)) {
                            tvEndDate.text = "End Date must be after Start Date"
                            tvEndDate.setTextColor(Color.RED)
                            tvEndDate.tag = null
                        } else {
                            tvEndDate.text = "End Date: $date"
                            tvEndDate.setTextColor(Color.BLACK)
                            tvEndDate.tag = date
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    tvEndDate.text = "End Date: $date"
                    tvEndDate.setTextColor(Color.BLACK)
                    tvEndDate.tag = date
                }
            }
        }

        val btnSubmit: Button = view.findViewById(R.id.btnSubmit)
        btnSubmit.setOnClickListener {
            btnSubmit.isEnabled = false
            val amount = view.findViewById<EditText>(R.id.amountEditText).text.toString().trim()
            val percentage = view.findViewById<EditText>(R.id.percentageEditText).text.toString().trim()
            val selectedBank = spinner.selectedItem.toString()
            val startDateText = tvStartDate.tag as? String
            val endDateText = tvEndDate.tag as? String

            var isValid = true

            val amountInputLayout = view.findViewById<TextInputLayout>(R.id.amountInputLayout)
            if (amount.isEmpty()) {
                amountInputLayout.error = "This field is required"
                isValid = false
            } else {
                amountInputLayout.error = null
            }

            val percentageInputLayout = view.findViewById<TextInputLayout>(R.id.percentageInputLayout)
            if (percentage.isEmpty()) {
                percentageInputLayout.error = "This field is required"
                isValid = false
            } else {
                percentageInputLayout.error = null
            }

            if (selectedBank == "Select Bank") {
                Toast.makeText(requireContext(), "Please select a Bank", Toast.LENGTH_SHORT).show()
                isValid = false
            }

            if (startDateText == null) {
                tvStartDate.setTextColor(Color.RED)
                tvStartDate.text = "You must select Start Date"
                isValid = false
            } else {
                tvStartDate.setTextColor(Color.BLACK)
            }

            if (endDateText == null) {
                tvEndDate.setTextColor(Color.RED)
                tvEndDate.text = "You must select End Date"
                isValid = false
            } else {
                tvEndDate.setTextColor(Color.BLACK)
            }

            if (isValid && startDateText != null && endDateText != null) {
                try {
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    val startDateParsed = sdf.parse(startDateText)
                    val endDateParsed = sdf.parse(endDateText)

                    if (startDateParsed != null && endDateParsed != null && endDateParsed.before(startDateParsed)) {
                        tvEndDate.setTextColor(Color.RED)
                        tvEndDate.text = "End Date must be after Start Date"
                        isValid = false
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            if (!isValid) {
                btnSubmit.isEnabled = true
                return@setOnClickListener
            }

            val bottomNavigationView = requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            bottomNavigationView.selectedItemId = R.id.account
        }

        return view
    }

    private fun showMaterialDatePicker(onDateSelected: (String) -> Unit) {
        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .build()

        datePicker.addOnPositiveButtonClickListener { selection ->
            val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(selection))
            onDateSelected(formattedDate)
        }

        datePicker.show(parentFragmentManager, "MATERIAL_DATE_PICKER")
    }
}