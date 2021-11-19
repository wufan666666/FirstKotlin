package com.wf.lifecycle.navgation.deeplink;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.wf.lifecycle.R;

/**
 * @author wf
 */
public class HomeFragment extends Fragment {


    private int notificationId;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deeplink_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button = getView().findViewById(R.id.home);
        button.setOnClickListener((v) -> {
            sendNotification();
        });

    }

    private void sendNotification() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(getActivity().getPackageName(), "myChannel", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("my NotificationChannel");
            NotificationManager manager = getActivity().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        Notification notification = new NotificationCompat.Builder(getActivity(), getActivity().getPackageName())
                .setSmallIcon(R.mipmap.cat1)
                .setContentTitle("紧急悬赏通知：抓捕通缉犯邓文豪")
                .setContentText("成都市金牛区公安局悬赏8.6W元发布，希望群众踊跃举报")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(getPendingIntent())
                .build();

        NotificationManagerCompat from = NotificationManagerCompat.from(getActivity());
        from.notify(notificationId++,notification);
    }

    private PendingIntent getPendingIntent() {
        return Navigation.findNavController(getActivity(),R.id.home)
                .createDeepLink()
                .setGraph(R.navigation.my_nav_graph)
                .setDestination(R.id.detailFragment)
                .createPendingIntent();
    }
}