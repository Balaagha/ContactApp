package com.vholodynskyi.assignment.ui.contactslist

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import com.vholodynskyi.assignment.databinding.ItemContactListBinding

class ContactAdapter (
    private val context: Context,
    private val onItemClicked: ItemClick
) : RecyclerView.Adapter<ViewHolder>() {

    var items: List<DbContact> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(
            ItemContactListBinding.inflate(LayoutInflater.from(context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        with(holder.binding) {
            text.text = items[position].firstName
            root.setOnClickListener {
                onItemClicked(item)
            }
        }
    }

    private fun onItemClicked(item: DbContact) {
        onItemClicked.invoke(item.id)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class ViewHolder (val binding: ItemContactListBinding) : RecyclerView.ViewHolder(binding.root)

typealias ItemClick = (Int) -> Unit