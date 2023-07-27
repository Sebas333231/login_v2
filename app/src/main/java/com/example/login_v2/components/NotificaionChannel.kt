package com.example.login_v2.components

import android.Manifest
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.login_v2.MainActivity
import com.example.login_v2.R
import com.example.login_v2.components.NotificacionProgramada.Companion.NOTIFICATION_ID


fun CreateChannelNotification(
    idChannel: String,
    context: Context,
    name: String,
    descriptionText: String
){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        //Definicion del Canal
        val channel = NotificationChannel(
            idChannel,
            name,
            importance
        ).apply {
            description = descriptionText
        }

        //Definimos del Gestor de Notificaciones
        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


        //Creacion del Canal
        notificationManager.createNotificationChannel(channel)
    }
}


fun notificationSencille(
    context: Context,
    idChannel: String,
    idNotification: Int,
    textTitle: String,
    textContent: String,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
){
    val intent = Intent(
        context,
        MainActivity::class.java
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(
        context,
        idChannel
    )
        .setSmallIcon(R.drawable.logo)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setPriority(priority)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    with(NotificationManagerCompat.from(context)){
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ){
            return
        }
        notify(idNotification, builder)
    }
}


fun notificationExtensa(
    context: Context,
    idChannel: String,
    idNotification: Int,
    textTitle: String,
    textContent: String,
    bigIcono: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
){
    var builder = NotificationCompat.Builder(context, idChannel)
        .setSmallIcon(R.drawable.logo)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(bigIcono)
        .setStyle(
            NotificationCompat.BigTextStyle()
                .bigText(textContent)
        )
        .setPriority(priority)
        .build()

    with(NotificationManagerCompat.from(context)){
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS,
            ) != PackageManager.PERMISSION_GRANTED
        ){
            return
        }
        notify(idNotification, builder)
    }
}

fun notificacionImagen(
    context: Context,
    idChannel: String,
    idNotification: Int,
    textTitle: String,
    textContent: String,
    bigIcono: Bitmap,
    bigImagen: Bitmap,
    priority: Int = NotificationCompat.PRIORITY_DEFAULT
){
    val intent = Intent(
        context,
        MainActivity::class.java
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    val pendingIntent: PendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    val builder = NotificationCompat.Builder(
        context,
        idChannel
    )
        .setSmallIcon(R.drawable.logo)
        .setContentTitle(textTitle)
        .setContentText(textContent)
        .setLargeIcon(bigIcono)
        .setStyle(
            NotificationCompat
                .BigPictureStyle()
                .bigPicture(bigImagen)
                .setBigContentTitle("Tienda Sena CBA")
        )
        .setPriority(priority)
        .setContentIntent(pendingIntent)
        .setAutoCancel(true)
        .build()

    with(NotificationManagerCompat.from(context)){
        if(ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ){
            return
        }
        notify(idNotification, builder)
    }
}

fun notificacionProgramada(context: Context){


    //Intent describe la actividad que se debe iniciar y continuar los datos necesarios para ellos
    val intent = Intent(
        context,
        NotificacionProgramada::class.java
    ).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    /* Un pendingIntent es una referencia a un token que mantiene el sistema,
    La aplicacion A puede pasar un PendingIntent a la aplicacion B para permitir
    que la aplicacion B ejecute acciones predefinidas en nombre de la aplicacion A,
     independientemente de si esta sigue activa
    */

    val pendingIntent: PendingIntent = PendingIntent.getBroadcast(
        context,
        NOTIFICATION_ID,
        intent,
        PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
    )

    //alarmManager: programa la notification
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setExact(
        AlarmManager.RTC_WAKEUP,
        Calendar.getInstance().timeInMillis + 10000,
        pendingIntent
    )
}