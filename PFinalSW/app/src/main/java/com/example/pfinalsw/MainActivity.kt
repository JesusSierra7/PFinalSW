package com.example.pfinalsw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.e
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.pfinalsw.di.AppModule
import com.example.pfinalsw.di.NetworkModule
import com.example.pfinalsw.presentation.view.DescriptionFragment
import com.example.pfinalsw.presentation.viewmodel.VMDesc
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import androidx.multidex.MultiDex


class MainActivity : AppCompatActivity() {

    lateinit var viewModel : VMDesc

    override fun onCreate(savedInstanceState: Bundle?) {
        MultiDex.install(this)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainActivity)
            modules(listOf(AppModule, NetworkModule))
        }

        try {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            viewModel = ViewModelProvider(this).get(VMDesc::class.java)
            viewModel.position.observe(this) {
                supportFragmentManager.beginTransaction().replace(
                        R.id.frag,
                        DescriptionFragment(), null
                ).addToBackStack(null).commit()
            }
        } catch(e : Exception) {
            e("MainActivity", "onCreate", e)
            throw e
        }
    }
}