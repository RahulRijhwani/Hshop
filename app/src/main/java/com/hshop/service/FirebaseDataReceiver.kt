package  com.tesuta.service

import android.app.*
import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.content.WakefulBroadcastReceiver
import android.util.Log
import android.widget.Toast
import com.tesuta.R
import com.tesuta.shopping.Order_details


/**
 * Created by rahul-3rddigital on 12/02/19.
 */
class FirebaseDataReceiver : WakefulBroadcastReceiver() {

    private val TAG = "FirebaseDataReceiver"
    var notificationManager: NotificationManager? =null
    val id1="com.tesuta.service"
    val NOTIFICATION_ID = 1

    override fun onReceive(context: Context, intent: Intent) {
        //Log.d(TAG, "I'm in!!!")
        val dataBundle = intent.extras
        var v1=dataBundle.get("name")

        //Log.d(TAG, dataBundle.toString())
        //Toast.makeText(context,"name ="+v1,Toast.LENGTH_LONG).show()
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           createNotificationChannel(
                    id1,
                    dataBundle.get("title").toString(),
                    dataBundle.get("body").toString())
        }
        sendnotification(dataBundle.get("title").toString(), dataBundle.get("body").toString(),context)
        var inp=Intent(context, Order_details::class.java)
//       inp.putExtra("name",v1.toString())
        //context.startActivity(inp)
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

    fun sendnotification(title : String,body : String,context: Context)
    {

        val intent = Intent(context, Order_details::class.java)
        intent.putExtra("title",title)
        intent.putExtra("body",body)
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP);
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        val snoozei=Intent(context,Order_details::class.java)
        val pendingsooz= PendingIntent.getActivity(context,0,snoozei,0)


        val builder = Notification.Builder(context)


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

        //builder.setSubText("Tap to view about activity")
        builder.setOngoing(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setChannelId(id1)
        }


        val notificationManager = context.getSystemService(
                Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(NOTIFICATION_ID, builder.build())

    }
}