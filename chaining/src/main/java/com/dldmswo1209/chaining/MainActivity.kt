package com.dldmswo1209.chaining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

// WorkManager Chaining : 작업의 순서를 관리함

// A -> 순서 상관 없이 실행되도 괜찮음
// B -> 순서 상관 없이 실행되도 괜찮음
// C -> 마지막에 실행 되어야 함

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val workManagerA = OneTimeWorkRequestBuilder<WorkManagerA>().build()
        val workManagerB = OneTimeWorkRequestBuilder<WorkManagerB>().build()
        val workManagerC = OneTimeWorkRequestBuilder<WorkManagerC>().build()
//        WorkManager.getInstance(this).enqueue(workManagerA)
//        WorkManager.getInstance(this).enqueue(workManagerB)
//        WorkManager.getInstance(this).enqueue(workManagerC)

        WorkManager.getInstance(this)
            .beginWith(listOf(workManagerA, workManagerB))
            .then(workManagerC)
            .enqueue()

    }
}