package com.shoker.euriskoTaskByMelhemShoker.repositories

import com.shoker.euriskoTaskByMelhemShoker.service.RetrofitService

class Repository constructor(private val retrofitService: RetrofitService) {

    fun getAllTodos() = retrofitService.getAllTodos()
}