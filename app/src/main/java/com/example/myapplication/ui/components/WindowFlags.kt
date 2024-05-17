package com.example.myapplication.ui.components

class WindowFlags(
    val onAdded: (Int) -> Unit = {},
    val onRemoved: (Int) -> Unit = {}
) {
    fun add(flag: Int): WindowFlags = this.apply { onAdded(flag) }
    fun remove(flag: Int): WindowFlags = this.apply { onRemoved(flag) }
}