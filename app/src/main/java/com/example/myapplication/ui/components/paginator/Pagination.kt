package com.example.myapplication.ui.components.paginator

interface Pagination<Key, T> {
    suspend fun loadNextItems(vararg optionalArgs : Any)
    fun reset()
}