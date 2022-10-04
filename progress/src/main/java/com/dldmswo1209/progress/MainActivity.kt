package com.dldmswo1209.progress

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.dldmswo1209.progress.WorkManagerTest.Companion.HOW_MUCH

// WorkManager -> 진행상태 검토??
// WorkManager -> 오래걸리는 작업 (60초) -> 몇 퍼센트 정도 진행되었는가?

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workManagerTest = OneTimeWorkRequestBuilder<WorkManagerTest>().build()
        WorkManager.getInstance(this).enqueue(workManagerTest)

        WorkManager.getInstance(this)
            .getWorkInfoByIdLiveData(workManagerTest.id).observe(this, Observer { workInfo ->
                val progress = workInfo?.progress
                val value = progress?.getInt(HOW_MUCH, 0)
                if(value != 0){
                    Log.d("testt", value.toString())
                }
                if(value == 100){
                    Log.d("testt", "이제 끝!")
                }
            })
    }
}