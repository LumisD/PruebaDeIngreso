package co.com.ceiba.mobile.pruebadeingreso.presentation.ui.post

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import co.com.ceiba.mobile.pruebadeingreso.databinding.PostListItemBinding
import co.com.ceiba.mobile.pruebadeingreso.presentation.ui.model.PostView

class PostListAdapter(private val emptyListListener: PostActivity) :
    ListAdapter<PostView, PostListAdapter.ViewHolder>(PostDiffCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: PostListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PostView) {
            binding.title.text = item.title
            binding.body.text = item.body
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PostListItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }

    fun modifyList(list: List<PostView>) {
        submitList(list)
        if (list.isEmpty()) emptyListListener.onEmptyList() else emptyListListener.onNotEmptyList()
    }

}

interface OnEmptyListListener {

    fun onEmptyList()

    fun onNotEmptyList()

}

class PostDiffCallback : DiffUtil.ItemCallback<PostView>() {
    override fun areItemsTheSame(oldItem: PostView, newItem: PostView): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PostView, newItem: PostView): Boolean {
        return oldItem == newItem
    }
}