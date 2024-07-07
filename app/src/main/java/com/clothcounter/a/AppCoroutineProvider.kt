package com.clothcounter.a

import kotlinx.coroutines.CoroutineScope

interface AppCoroutineProvider{
    val backgroundCoroutineScope: CoroutineScope
}

