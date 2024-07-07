package com.clothcounter.a

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject

class AppCoroutineProviderImp @Inject constructor() : AppCoroutineProvider {
    override val backgroundCoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
}
