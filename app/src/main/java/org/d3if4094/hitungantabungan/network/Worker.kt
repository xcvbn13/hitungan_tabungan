package org.d3if4094.hitungantabungan.network

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import org.d3if4094.hitungantabungan.MainActivity
import org.d3if4094.hitungantabungan.R

class Worker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    private val notifId = 44

    override fun doWork(): Result {
        val intent = Intent(applicationContext, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(applicationContext,0,intent,0)

        val builder = NotificationCompat.Builder(
            applicationContext,
            MainActivity.CHANNEL_ID
        )

            .setSmallIcon(R.mipmap.ic_launcher_round)
            .setContentTitle(applicationContext.getString(R.string.notif_title))
            .setContentText(applicationContext.getString(R.string.notif_text))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        val manager = NotificationManagerCompat.from(applicationContext)
            .notify(notifId,builder.build())


        return Result.success()
    }
}