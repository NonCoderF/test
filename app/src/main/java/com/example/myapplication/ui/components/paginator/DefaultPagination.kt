package com.example.myapplication.ui.components.paginator

class DefaultPagination<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean, Key) -> Unit,
    private inline val onRequest: suspend (optionalArgs: Any?, nextKey: Key) -> Result<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit,
    private inline val onError: (Throwable) -> Unit

) : Pagination<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems(vararg optionalArgs: Any) {
        if (isMakingRequest) return
        isMakingRequest = true

        onLoadUpdated(true, currentKey)

        val result = onRequest(optionalArgs, currentKey)
        isMakingRequest = false

        val items = result.getOrElse {
            onError(it)
            onLoadUpdated(false, currentKey)
            return
        }

        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false, currentKey)
    }

    override fun reset() {
        onLoadUpdated(false, initialKey)
        isMakingRequest = false
        currentKey = initialKey
    }
}