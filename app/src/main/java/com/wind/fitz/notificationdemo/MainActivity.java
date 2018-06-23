package com.wind.fitz.notificationdemo;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.wind.fitz.notificationdemo.manager.NotificationChannelManager;
import com.wind.fitz.notificationdemo.manager.NotificationChannelManager.CHANNEL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private String TAG = this.getLocalClassName();

    @BindView(R.id.textView)   TextView textView;
    @BindView(R.id.button)     Button   button;
    @BindView(R.id.textView2)  TextView textView2;
    @BindView(R.id.button2)    Button   button2;
    @BindView(R.id.textView3)  TextView textView3;
    @BindView(R.id.button3)    Button   button3;
    @BindView(R.id.textView4)  TextView textView4;
    @BindView(R.id.button4)    Button   button4;
    @BindView(R.id.textView5)  TextView textView5;
    @BindView(R.id.button5)    Button   button5;
    @BindView(R.id.textView6)  TextView textView6;
    @BindView(R.id.button6)    Button   button6;
    @BindView(R.id.textView7)  TextView textView7;
    @BindView(R.id.button7)    Button   button7;
    @BindView(R.id.textView8)  TextView textView8;
    @BindView(R.id.button8)    Button   button8;
    @BindView(R.id.textView9)  TextView textView9;
    @BindView(R.id.button9)    Button   button9;
    @BindView(R.id.textView10) TextView textView10;
    @BindView(R.id.button10)   Button   button10;
    @BindView(R.id.textView11) TextView textView11;
    @BindView(R.id.button11)   Button   button11;

    private NotificationParameter np;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);
        ButterKnife.bind(this);
        np = NotificationParameter.getInstance();
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10, R.id.button11})

    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                np.setContentText(button.getText());
                np.setContentTitle("高级别通知");
                np.setSmallIcon(R.mipmap.ic_launcher);
                np.setChannelLevel(CHANNEL.HIGH_NOTIFICATION);
                np.setNotifyID(R.id.button);
                createNotification(np);
                break;
            case R.id.button2:
                np.setContentText(button2.getText());
                np.setContentTitle("默认级别通知");
                np.setSmallIcon(R.mipmap.ic_launcher);
                np.setChannelLevel(CHANNEL.DEFAULT_NOTIFICATION);
                np.setNotifyID(R.id.button2);
                createNotification(np);
                break;
            case R.id.button3:
                np.setContentText(button3.getText());
                np.setContentTitle("低级别通知");
                np.setSmallIcon(R.mipmap.ic_launcher);
                np.setChannelLevel(CHANNEL.LOW_NOTIFICATION);
                np.setNotifyID(R.id.button3);
                createNotification(np);
                break;
            case R.id.button4:
                np.setContentText(button4.getText());
                np.setContentTitle("最低级别通知");
                np.setSmallIcon(R.mipmap.ic_launcher);
                np.setChannelLevel(CHANNEL.MIN_NOTIFICATION);
                np.setNotifyID(R.id.button4);
                createNotification(np);
                break;
            case R.id.button5:

                break;
            case R.id.button6:
                break;
            case R.id.button7:
                break;
            case R.id.button8:
                break;
            case R.id.button9:
                break;
            case R.id.button10:
                break;
            case R.id.button11:
                break;
        }
    }

    public void createNotification(NotificationParameter np) {
        if(NotificationParameter.getInstance().checknull()){
            //有参数为空或未设置，无法发出通知
            Log.e(TAG,"null np");
            return;
        }
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this, np.getChannelLevel())
                .setSmallIcon(np.getSmallIcon())
                .setContentTitle(np.getContentTitle())
                .setContentText(np.getContentText());   //(1)builder
        Notification        notification = mBuilder.build();//（2）notification
        NotificationChannel channel      = NotificationChannelManager.getInstance().getChannel(this, np.getChannelLevel());//(3)设置channel等级
        NotificationManager nm           = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);//（4）notificationmanager
        nm.createNotificationChannel(channel);//(5) 设置channel
        channel.setDescription(np.getDescription());   //这里可以对channel设置属性
        //channel.setSound(sound, null);
        //channel.setGroup(channelId);
        //channel.setLightColor(1234);
        //channel.setLockscreenVisibility(1);
        //channel.setVibrationPattern(new long[111]);
        //channel.setLockscreenVisibility(lockScreen);
        nm.notify(np.getNotifyID(), notification);//（6）发出通知
    }
}
