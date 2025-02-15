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




//package com.example.mybank
//
//import android.os.Bundle
//import androidx.fragment.app.Fragment
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import com.example.bankapp.R
//
//// TODO: Rename parameter arguments, choose names that match
//// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"
//
///**
// * A simple [Fragment] subclass.
// * Use the [AccountsFragment.newInstance] factory method to
// * create an instance of this fragment.
// */
//class AccountsFragment : Fragment() {
//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_accounts, container, false)
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment AccountsFragment.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            AccountsFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
//}