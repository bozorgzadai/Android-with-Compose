package io.github.bozorgzadai.bookshelf

import android.app.Application
import io.github.bozorgzadai.bookshelf.data.AppContainer
import io.github.bozorgzadai.bookshelf.data.DefaultAppContainer


class BooksApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
