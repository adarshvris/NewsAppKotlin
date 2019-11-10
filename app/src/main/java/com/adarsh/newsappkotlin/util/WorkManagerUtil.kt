package com.adarsh.newsappkotlin.util

import androidx.lifecycle.LiveData
import androidx.work.*
import com.adarsh.newsappkotlin.newsApplicationInstance

inline fun <reified T : ListenableWorker> startOneTimeWorkManager(
    workName: String,
    data: Map<String, Any>? = null
): LiveData<WorkInfo> {
    val oneTimeWorkRequest: OneTimeWorkRequest = getOneTimeWorkRequest<T>(data)

    WorkManager.getInstance(newsApplicationInstance)
        .enqueueUniqueWork(workName, ExistingWorkPolicy.KEEP, oneTimeWorkRequest)

    return WorkManager.getInstance(newsApplicationInstance).getWorkInfoByIdLiveData(oneTimeWorkRequest.id)

}

inline fun <reified T : ListenableWorker> getOneTimeWorkRequest(data: Map<String, Any>? = null): OneTimeWorkRequest {

    val oneTimeWorkRequest = OneTimeWorkRequestBuilder<T>()
    data?.let {
        val dataBuilder = Data.Builder()
        for (dataVal in data.entries) {
            dataBuilder.put(dataVal.key, dataVal.value)
        }
        oneTimeWorkRequest.setInputData(dataBuilder.build())
    }

    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)

    oneTimeWorkRequest.setConstraints(constraints.build())

    return oneTimeWorkRequest.build()
}