package tw.workshop.services;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class StandupReminderReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       //alarm manager. new PendingINte(

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 10);
        PendingIntent sender = PendingIntent.getBroadcast(context, 192837, new Intent(context, ReminderService.class), PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager am = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        am.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 10000, sender);

//        context.startService(new Intent(context, ReminderService.class));
    }
}
