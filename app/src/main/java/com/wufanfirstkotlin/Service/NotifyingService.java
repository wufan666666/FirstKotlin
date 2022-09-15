package com.wufanfirstkotlin.Service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.ConditionVariable;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

import com.wufanfirstkotlin.R;
import com.wufanfirstkotlin.himalaya.utils.L;

import java.util.Calendar;
import java.util.TimeZone;

public class NotifyingService extends Service {


    private NotificationManager manager;
    private Notification notification;
    /**
     * Android给我们提供ConditionVariable类，用于线程同步。提供了三个方法block()、open()、close()。 void
     * block() 阻塞当前线程，直到条件为open 。 void block(long timeout)阻塞当前线程，直到条件为open或超时
     * void open()释放所有阻塞的线程 void close() 将条件重置为close。
     */
    private ConditionVariable mCondition;

    public NotifyingService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return null;
    }

    @Override
    public void onCreate() {
        // 启动一个新个线程执行任务，因Service也是运行在主线程，不能用来执行耗时操作
//        Thread notifyingThread = new Thread(null, mTask, "NotifyingService");
//        mCondition = new ConditionVariable(false);
//        notifyingThread.start();
        // 前台保活
        L.e("service","service open success");
        startForeground(100, showForeGroundNotification()); // id设置为0前台不会显示
    }

    @Override
    public void onDestroy() {
        // 取消通知功能
//        manager.cancel(NOTIFICATION_ID);
        // 移除前台通知
        stopForeground(true);
        // 停止线程进一步生成通知
//        mCondition.open();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        Thread notifyingThread = new Thread(mTask);
//        notifyingThread.start(); // 开启定时通知线程
        return START_REDELIVER_INTENT; //这里为了提高优先级，选择START_REDELIVER_INTENT 没那么容易被内存清理时杀死
    }



    // 创建定时器管理器
    public AlarmManager CreateAlarmManager() {
        return (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    // 定时器设置时间
    public Calendar CreateCalendar(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();  // 设置时间
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));  // 设置时区,不然会有8小时时差,东八区
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        return calendar;
    }

    private Notification showForeGroundNotification() {
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  //操作系统的版本号 > 8.0
            NotificationChannel channel = new NotificationChannel("channel", "前台服务", NotificationManager.IMPORTANCE_HIGH);
            manager.createNotificationChannel(channel);
        }
        notification = new NotificationCompat.Builder(this, "channel")
//                .setContentTitle("")
                .setContentText(getApplicationContext().getResources().getString(R.string.app_name))
                .setSmallIcon(R.mipmap.iconfont)
                .setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .build();
        return notification;
    }



}