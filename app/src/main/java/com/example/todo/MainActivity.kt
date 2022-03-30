package com.example.todo

import androidx.appcompat.app.AppCompatActivity
import com.example.todo.Adapter.TodoAdapter
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import com.example.todo.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.model.Todo
import com.google.android.material.textfield.TextInputEditText
import java.util.ArrayList

class MainActivity : AppCompatActivity() {
    private var todoAdapter: TodoAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())
        val recyclerView = findViewById<RecyclerView>(R.id.recylerview)
        recyclerView.adapter = todoAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val add : Button = findViewById(R.id.save)
        val delete : Button = findViewById(R.id.delete)
        val input : TextInputEditText = findViewById(R.id.tt)
        add.setOnClickListener{
            val title = input.text.toString()
            if(title.isEmpty()){
                val todo = Todo(title)
                todoAdapter!!.addTodo(todo)
                input.text?.clear()
            }
        }
        delete.setOnClickListener{
            todoAdapter!!.deleteDoneTodos()
        }

    }
}