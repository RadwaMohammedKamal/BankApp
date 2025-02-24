package com.example.bankapp

import android.content.Intent
import com.example.bankapp.databinding.AccountsItemsBinding
import com.xwray.groupie.Item
import com.xwray.groupie.GroupieViewHolder

class AccountsItem(private val accountsName: AccountsClass) : Item<GroupieViewHolder>() {

    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        val binding = AccountsItemsBinding.bind(viewHolder.itemView)
        binding.title.text = accountsName.bankName  // تعيين النص داخل TextView

        // اجعل البطاقة قابلة للنقر لفتح شاشة جديدة
        binding.cardView.setOnClickListener {
            val context = it.context
            val intent = Intent(context, AccountDetailsActivity::class.java)
            intent.putExtra("BANK_NAME", accountsName.bankName) // تمرير اسم البنك إلى الشاشة الجديدة
            context.startActivity(intent)
        }
    }

    override fun getLayout(): Int = R.layout.accounts_items
}