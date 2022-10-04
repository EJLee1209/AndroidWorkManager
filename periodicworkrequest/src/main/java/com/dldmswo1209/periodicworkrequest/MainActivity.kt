package com.dldmswo1209.periodicworkrequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

// WorkManager 주기적 실행
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workManager = PeriodicWorkRequestBuilder<WorkManager1>(15, TimeUnit.MINUTES).build() // 15분마다 주기적으로 실행
        // Minimum 값이 15분이므로 1분으로 설정해도 15분에 한번씩 실행됨

        WorkManager.getInstance(this).enqueue(workManager)

    }
}