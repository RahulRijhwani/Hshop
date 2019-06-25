package com.tesuta.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.util.Log
import android.widget.Toast
import com.google.firebase.messaging.RemoteMessage
import com.google.firebase.messaging.FirebaseMessagingService
import com.tesuta.R
import com.tesuta.shopping.Check


class MyFirebaseMessageService : FirebaseMessagingService() {
    var notificationManager: NotificationManager? =null
    val id1="com.tesuta.service"
    val NOTIFICATION_ID = 1
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        // TODO: Handle FCM messages here.
        // If the application is in the foreground handle both data and notification messages here.
        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated.
      //  var n1=remoteMessage.toString()
       // Log.d(TAG, "From: " + remoteMessage!!.from!!)
        //Toast.makeText(applicationContext,"From:" + remoteMessage!!.from!!,Toast.LENGTH_LONG).show()
        //Log.d(TAG, "Notification Message Body: " + remoteMessage.data)
        //Toast.makeText(baseContext,"Notification Message Body:" + remoteMessage.notification!!.body!!,Toast.LENGTH_LONG).show()
        notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(
                    id1,
                    "title from msgrcv",
                    "dec from msg rcv")
        }
        sendnotification("title from msgrcv",
                "dec from msg rcv")

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(id: String, name: String,
                                          description: String) {

        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(id, name, importance)

        channel.description = description

        channel.enableLights(true)
        channel.lightColor = Color.RED
        channel.enableVibration(true)
        channel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
        notificationManager?.createNotificationChannel(channel)
    }

    fun sendnotification(title : String,body : String)
    {

        val intent = Intent(applicationContext, Check::class.java)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, 0)
        val snoozei=Intent(applicationContext,Check::class.java)
        val pendingsooz= PendingIntent.getActivity(applicationContext,0,snoozei,0)


        val builder = Notification.Builder(applicationContext)


        builder.setSmallIcon(R.drawable.logo)

        builder.setContentIntent(pendingIntent)


        builder.setAutoCancel(true)



        //val drawable: Drawable = ResourcesCompat.getDrawable(resources, R.drawable.ic_menu_camera, null) as Drawable
        //  builder.setLargeIcon(convertToBitmap(drawable,5,5))


        builder.setDefaults(Notification.DEFAULT_SOUND or Notification.DEFAULT_VIBRATE or Notification.DEFAULT_LIGHTS)

        //var b1 =convertToBitmap(resources.getDrawable(R.drawable.ic_menu_camera),30,30)
        //builder.setLargeIcon(b1)
        builder.setPriority(Notification.PRIORITY_MAX)
        //builder.addAction(R.drawable.ic_camera_alt_black_24dp,"dismiss text",pendingIntent)
        //builder.addAction(R.drawable.ic_camera_alt_black_24dp,"Snooz text",pendingsooz)
        builder.setContentTitle(title)
        builder.setContentText(body)


        builder.setOngoing(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(id1)
        }




        val notificationManager = applicationContext.getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())

    }
    companion object {
        private val TAG = "FCM Service"
    }
}