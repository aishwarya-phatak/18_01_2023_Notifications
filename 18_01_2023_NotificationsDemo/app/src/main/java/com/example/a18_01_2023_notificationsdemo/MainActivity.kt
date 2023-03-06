package com.example.a18_01_2023_notificationsdemo

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.a18_01_2023_notificationsdemo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var notificationManager: NotificationManagerCompat
    private var channelId = "bitcode_channel"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        notificationManager = NotificationManagerCompat.from(this)

        simpleNotification()
        createNotificationChannel()
    }
    fun simpleNotification(){
        binding.btnSimpleNotification.setOnClickListener {
            var notificationBuilder = NotificationCompat.Builder(this, channelId)

            notificationBuilder.setContentTitle("Android Batch Jan '23")
            notificationBuilder.setContentText("Students are ready for placement")
            notificationBuilder.setLights(Color.RED, 200, 200)
            notificationBuilder.setVisibility(NotificationCompat.VISIBILITY_SECRET)
            notificationBuilder.setOngoing(true)
            notificationBuilder.setSmallIcon(R.mipmap.ic_launcher)

            var actionIntent = Intent(this, SecondActivity::class.java)
            var actionPendingIntent = PendingIntent.getActivity(
                this,
                1,
                actionIntent,
                PendingIntent.FLAG_MUTABLE
            )
            notificationBuilder.setContentIntent(actionPendingIntent)
            var notification = notificationBuilder.build()
            notificationManager.notify(10, notification)
        }
    }

    fun createNotificationChannel() {
        var channelBitcodeBuilder = NotificationChannelCompat.Builder(
            channelId,
            NotificationManager.IMPORTANCE_HIGH
        )
        channelBitcodeBuilder.setName("Bitcode Channel")
        channelBitcodeBuilder.setDescription("Bitcode Notification Channel")
        channelBitcodeBuilder.setShowBadge(true)

        notificationManager.createNotificationChannel(channelBitcodeBuilder.build())
    }
}