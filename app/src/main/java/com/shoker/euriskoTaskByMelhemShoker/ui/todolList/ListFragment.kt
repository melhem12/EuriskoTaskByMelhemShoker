package com.shoker.euriskoTaskByMelhemShoker.ui.todolList

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.melhem.neotask.adapters.MyAdapter
import com.melhem.neotask.view_model.MyViewModelFactory
import com.shoker.euriskoTaskByMelhemShoker.R
import com.shoker.euriskoTaskByMelhemShoker.repositories.Repository
import com.shoker.euriskoTaskByMelhemShoker.service.RetrofitService


class ListFragment : Fragment() {
    lateinit var swipeContainer: SwipeRefreshLayout
    lateinit var viewModel: ListViewModel

    private val retrofitService = RetrofitService.getInstance()
    val adapter = MyAdapter()
    lateinit var   recycler : RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_todo_list, container, false)
        swipeContainer = root.findViewById(R.id.swipeContainer)
        recycler = root.findViewById(R.id.recyclerview)
        fetchTimelineAsync()

        swipeContainer.setOnRefreshListener {

            // Your code to refresh the list here.
            // Make sure you call swipeContainer.setRefreshing(false)
            // once the network request has completed successfully.
            fetchTimelineAsync()
        }





        return root
    }
    fun fetchTimelineAsync() {

        adapter.clear()
        viewModel = ViewModelProvider(
            this, MyViewModelFactory(
                Repository(
                    retrofitService
                )
            )
        ).get(ListViewModel::class.java)

        val llm = LinearLayoutManager(context)
        llm.orientation = LinearLayoutManager.VERTICAL
        recycler.setLayoutManager(llm)
        recycler.adapter = adapter

        viewModel.todoList.observe(viewLifecycleOwner, Observer {
            Log.d(ContentValues.TAG, "onCreate: $it")
            adapter.setTodoList(it)
            swipeContainer.setRefreshing(false)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {

        })
        viewModel.getAllTodos()
    }

}