package com.example.todo.Adapter

import android.annotation.SuppressLint
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.model.Todo

class TodoAdapter(private val todos:MutableList<Todo>):RecyclerView.Adapter<TodoAdapter.TodoViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        var todo = todos[position]
        holder.textView.text = todo.title
        holder.check.isChecked = todo.isChecked
        toggleStrikeThrough(holder.textView,todo.isChecked)
        holder.check.setOnCheckedChangeListener { _, isCheckeds ->

            toggleStrikeThrough(holder.textView,isCheckeds)
            todo.isChecked = !todo.isChecked
        }
    }

    fun addTodo(todo: Todo){
        todos.add(todo)
        notifyItemChanged(todos.size -1)
    }

    fun deleteDoneTodos(){
        todos.removeAll { todo ->
                todo.isChecked
        }

        notifyDataSetChanged()
    }

    override fun getItemCount() = todos.size

    private fun toggleStrikeThrough(tvTodoTitle:TextView,isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        }else{
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG.inv()

        }
    }

    class TodoViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val textView: TextView = itemView.findViewById(R.id.todoTitle)
        val check : CheckBox = itemView.findViewById(R.id.done)
    }

}