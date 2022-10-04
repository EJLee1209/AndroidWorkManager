package com.dldmswo1209.workmanagertest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.work.Data
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf

// WorkManager 데이터 주고 받기

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val workManagerA = OneTimeWorkRequestBuilder<WorkManagerA>().build()
//        WorkManager.getInstance(this).enqueue(workManagerA)

        val myData: Data = workDataOf(
            "a" to 10,
            "b" to 20
        )

        val workManagerB = OneTimeWorkRequestBuilder<WorkManagerB>().setInputData(myData).build()
        WorkManager.getInstance(this).enqueue(workManagerB)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workManagerB.id)
            .observe(this, Observer { info->
                if(info != null && info.state.isFinished){
                    val result = info.outputData.getInt("result", 10000)
                    val result2 = info.outputData.getInt("result2", -1)
                    Log.d("testt", result.toString())
                    Log.d("testt", result2.toString())
                }
            })

    }
}

