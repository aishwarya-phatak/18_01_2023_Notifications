package com.example.a18_01_2023_notificationsdemo

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
        inboxStyleNotification()
        bigPictureNotification()
        snackBarNotification()
        actionTextStyleNotification()
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
    fun bigPictureNotification(){
        binding.btnBigPictureStyleNotification.setOnClickListener {
            var builder = NotificationCompat.Builder(this,channelId)
            builder.setContentTitle("Android Batch")
            builder.setContentText("Batch at March 2023")
            builder.setSmallIcon(R.drawable.ic_launcher_background)
            builder.setColor(Color.RED)

            var bigPictureStyle = NotificationCompat.BigPictureStyle()
            bigPictureStyle.setSummaryText("Admissions open")
            bigPictureStyle.setBigContentTitle("Admissions open for java, web, android, iOS")

            var imageIcon = BitmapFactory.decodeResource(resources,R.drawable.nature)
            bigPictureStyle.bigPicture(imageIcon)
            builder.setStyle(bigPictureStyle)
            notificationManager.notify(30,builder.build())
        }
    }

    fun actionTextStyleNotification(){
        binding.btnActionTextStyleNotification.setOnClickListener {
            var notification = NotificationCompat.Builder(this,channelId)
            notification.setContentTitle("Android At Bitcode")
            notification.setContentText("Android Batch Jan 2023")
            notification.setSmallIcon(R.drawable.nature)
            var actionIntent = Intent(this,SecondActivity::class.java)
            var pendingIntent = PendingIntent.getActivity(
                this,
                1,
                actionIntent,
                PendingIntent.FLAG_IMMUTABLE
            )
            var actionTextStyle = NotificationCompat.Action(
                R.drawable.nature,
                "Admissions Open",
                pendingIntent
            )
            notification.addAction(actionTextStyle)
            notification.addAction(R.drawable.ic_launcher_background,"Share",pendingIntent)
            notification.addAction(R.drawable.nature,"Useful",pendingIntent)
            notificationManager.notify(40,notification.build())
        }
    }

    fun snackBarNotification(){
        binding.btnSnackBarNotification.setOnClickListener {
           var snackbar = Snackbar.make(
                binding.root,
                "Task Completed",
                Snackbar.LENGTH_LONG
            )
            snackbar.setTextColor(Color.RED)
            snackbar.setAction("This is SncakBar View") { Log.e("tag", "SnackBar Notification") } //imp - lambda syntax
                .show()
        }
    }
    
    fun inboxStyleNotification(){
        binding.btnInboxStyleNotification.setOnClickListener {
            var builder = NotificationCompat.Builder(this,channelId)
            builder.setContentTitle("Bitcode Technologies")
            builder.setContentText("Admissions For Android Batch March 2023")
            builder.setSmallIcon(R.mipmap.ic_launcher)
            var inboxStyle = NotificationCompat.InboxStyle()
            inboxStyle.addLine("Android")
            inboxStyle.addLine("Java")
            inboxStyle.addLine("iOS")
            inboxStyle.addLine("Web")
            builder.setStyle(inboxStyle)
            notificationManager.notify(20,builder.build())
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