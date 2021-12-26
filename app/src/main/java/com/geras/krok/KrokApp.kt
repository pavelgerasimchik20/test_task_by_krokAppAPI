package com.geras.krok

import android.app.Application
import com.geras.krok.data.Repository

class KrokApp : Application() {

    val repository by lazy { Repository() }
}