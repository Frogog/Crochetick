package com.example.crochetick.notifications

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotificationWorker(
    private val context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        val title = inputData.getString("title") ?: "Напоминание"
        val message = inputData.getString("message") ?: "Пора!"
        val notificationId = inputData.getLong("notificationId",-1)

        NotificationHelper(context).showNotification(notificationId, title, message)
        return Result.success()
    }
}