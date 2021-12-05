package com.shoker.euriskoTaskByMelhemShoker.ui.todolList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shoker.euriskoTaskByMelhemShoker.models.Todo
import com.shoker.euriskoTaskByMelhemShoker.repositories.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListViewModel  constructor(private val repository: Repository)  : ViewModel() {

    val todoList = MutableLiveData<List<Todo>>()
    val errorMessage = MutableLiveData<String>()
    fun getAllTodos() {

        val response = repository.getAllTodos()
        response.enqueue(object : Callback<List<Todo>> {
            override fun onResponse(call: Call<List<Todo>>, response: Response<List<Todo>>) {
                todoList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Todo>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}