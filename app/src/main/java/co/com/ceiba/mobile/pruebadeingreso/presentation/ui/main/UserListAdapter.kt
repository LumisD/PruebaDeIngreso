package co.com.ceiba.mobile.pruebadeingreso.presentation.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import co.com.ceiba.mobile.pruebadeingreso.common.util.isClickedSingle
import co.com.ceiba.mobile.pruebadeingreso.databinding.UserListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.model.UserView

class UserListAdapter(
    private val itemClickListener: OnUserClickListener,
    private val emptyListListener: OnEmptyListListener
) :
    ListAdapter<UserView, UserListAdapter.ViewHolder>(UserDiffCallback()) {

    private var unfilteredList = listOf<UserView>()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserView, clickListener: OnUserClickListener) {
            binding.name.text = item.name
            binding.phone.text = item.phone
            binding.email.text = item.email

            binding.btnViewPost.setOnClickListener {
                if (isClickedSingle()) return@setOnClickListener
                clickListener.onItemClicked(item.id)
            }
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = UserListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun modifyList(list: List<UserView>) {
        unfilteredList = list
        submitList(list)
        if (list.isEmpty()) emptyListListener.onEmptyList() else emptyListListener.onNotEmptyList()
    }

    fun filter(query: CharSequence?) {
        val list = mutableListOf<UserView>()

        if (!query.isNullOrEmpty()) {
            list.addAll(unfilteredList.filter {
                it.name.lowercase(Locale.getDefault())
                    .contains(query.toString().lowercase(Locale.getDefault()))
            })
        } else {
            list.addAll(unfilteredList)
        }

        submitList(list)
        if (list.isEmpty()) emptyListListener.onEmptyList() else emptyListListener.onNotEmptyList()
    }
}

interface OnUserClickListener {

    fun onItemClicked(userId: Int)

}

interface OnEmptyListListener {

    fun onEmptyList()

    fun onNotEmptyList()

}

class UserDiffCallback : DiffUtil.ItemCallback<UserView>() {
    override fun areItemsTheSame(oldItem: UserView, newItem: UserView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UserView, newItem: UserView): Boolean {
        return oldItem == newItem
    }
}