package com.jsontodos.jsonplaceholdertodos.presentation.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jsontodos.jsonplaceholdertodos.R
import com.jsontodos.jsonplaceholdertodos.databinding.ItemTodosBinding
import com.jsontodos.jsonplaceholdertodos.models.TodosData

class HomeTodoAdapter(
    val context: Context,
    val onItemClick: (todosData: TodosData) -> Unit,
) :
    ListAdapter<TodosData, RecyclerView.ViewHolder>(DiffCallback) {

    inner class HomeGridViewHolder(private val binding: ItemTodosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(todosData: TodosData, position: Int) {

            binding.txtTitle.text = todosData.title
            binding.txtCompleted.text =
                if (todosData.completed.toString().equals("true")) {
                    "Completed"
                } else "Pending"

            if (todosData.completed) {
                binding.mainCard.setCardBackgroundColor(context.resources.getColor(R.color.colorCompleted))
            } else {
                binding.mainCard.setCardBackgroundColor(context.resources.getColor(R.color.colorPending))
            }

            binding.root.setOnClickListener {
                onItemClick(todosData)
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<TodosData>() {
        override fun areItemsTheSame(oldItem: TodosData, newItem: TodosData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodosData, newItem: TodosData): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            ItemTodosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeGridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val adjustViewHolder = holder as HomeGridViewHolder
        val cast = getItem(position)

        cast.let {
            adjustViewHolder.onBind(it, position)
        }
    }
}