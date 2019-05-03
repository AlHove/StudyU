package com.example.GonkDroids.StudyU;

//Created by Kaleb 05/03/19

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//Alarm receiver class
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    //Method for receiving intent
    public void onReceive(Context context, Intent intent) {

        //Create intent with notification activity and pass into TaskStackBuilder
        //Thus, whenever the notification is clicked it will pass the intent into a pending intent
        Intent notificationIntent = new Intent(context, NotificationActivity.class);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(NotificationActivity.class);
        stackBuilder.addNextIntent(notificationIntent);

        //create pending intent from TaskStackBuilder and pass into notification
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        //build notification
        Notification.Builder builder = new Notification.Builder(context);

        //show notification
        Notification notification = builder.setContentTitle("Upcoming Assignments/Exams")
                .setContentText("Unfinished Assignments/Exams")
                .setTicker("New Message Alert!")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent).build();

        //Notification manager will notify us with notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notification);
    }

}
