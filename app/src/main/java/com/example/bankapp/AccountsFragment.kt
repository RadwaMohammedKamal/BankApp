package com.example.mybank

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bankapp.AccountsClass
import com.example.bankapp.AccountsItem
import com.example.bankapp.databinding.FragmentAccountsBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder

class AccountsFragment : Fragment() {
    private var _binding: FragmentAccountsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 🔹 إنشاء قائمة الحسابات
        val accountsList = listOf(
            AccountsClass("Bank Maser"),
            AccountsClass("Bank Al Ahly"),
            AccountsClass("Bank CIB")
        )

        // 🔹 إعداد المحول (Adapter) باستخدام Groupie
        val adapter = GroupAdapter<GroupieViewHolder>().apply {
            accountsList.forEach { add(AccountsItem(it)) }
        }

        // 🔹 إعداد RecyclerView
        binding.accountsRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = adapter

            // 🔹 إضافة مسافة قبل أول عنصر فقط
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    if (parent.getChildAdapterPosition(view) == 0) {
                        outRect.top = 30 // تعديل الرقم حسب الحاجة
                    }
                }
            })
        }
        binding.accountsRV.isFocusable = false
        binding.accountsRV.isFocusableInTouchMode = false

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}