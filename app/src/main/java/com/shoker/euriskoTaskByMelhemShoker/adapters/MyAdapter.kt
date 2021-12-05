package com.melhem.neotask.adapters

import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shoker.euriskoTaskByMelhemShoker.R
import com.shoker.euriskoTaskByMelhemShoker.Show
import com.shoker.euriskoTaskByMelhemShoker.models.Todo
import com.shoker.euriskoTaskByMelhemShoker.databinding.AdapterTodoBinding

class MyAdapter: RecyclerView.Adapter<MainViewHolder> ()  {

    var todos = mutableListOf<Todo>()

    fun setTodoList(todos: List<Todo>) {
        this.todos = todos.toMutableList()
        notifyDataSetChanged()
    }
    fun clear() {
        todos.clear()
        notifyDataSetChanged()
    }

    // Add a list of items -- change to type used




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterTodoBinding.inflate(inflater, parent, false)

        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val todo = todos[position]
        if(todo.completed){
       holder.itemView.setBackgroundColor(Color.LTGRAY)
        }else{
            holder.itemView.setBackgroundColor(Color.WHITE)

        }

        holder.binding.title.text = todo.title
        holder.itemView.setOnClickListener{
            val intent = Intent(holder.itemView.context, Show::class.java)
            intent.putExtra("title",todo.title)
            intent.putExtra("id",todo.id.toString())

            intent.putExtra("userId",todo.userId.toString())
            intent.putExtra("completed",todo.completed.toString())

            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return  todos.size
    }


}

class MainViewHolder(val binding: AdapterTodoBinding) : RecyclerView.ViewHolder(binding.root) {

}

