package com.dldmswo1209.uniquework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.dldmswo1209.uniquework.databinding.ActivityMainBinding

// WorkManager 고유 작업
class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 버튼 클릭시 WorkManager 의 작업을 실행하는 경우
        // 같은 작업이 중복되는 경우가 생길 수 있음
        // 그럴 경우 uniqueWork 를 사용해서 이미 존재하는 작업에 대한 작업을 처리해줘야함
        binding.testBtn.setOnClickListener {
            // 기존 방식
            val testWorkManager = OneTimeWorkRequestBuilder<TestWorkManager>().build()
//            WorkManager.getInstance(this).enqueue(testWorkManager)

            // 새로운 방식
            WorkManager.getInstance(this).enqueueUniqueWork("test", ExistingWorkPolicy.REPLACE, testWorkManager)
        }
    }
}