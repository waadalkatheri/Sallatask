package com.example.task

import android.app.Application
import com.example.task.di.networkModule
import com.example.task.di.repositoriesModule
import com.example.task.di.viewModelsModules
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // initialize koin modules
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                listOf(networkModule, repositoriesModule, viewModelsModules)
            )
        }

        // initialize Calligraphy for custom fonts
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/Text-Regular.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )

    }
}