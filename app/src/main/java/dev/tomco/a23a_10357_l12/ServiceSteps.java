package dev.tomco.a23a_10357_l12;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;

public class ServiceSteps extends Service {

    public static final String ACTION_STOP = "ACTION_STOP";
    public static final String ACTION_START = "ACTION_START";

    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.d("in ", "onStartCommand: ");
        String action = intent.getAction();
        if (action.equals(ACTION_START)) {
            if (!isPlaying) {
                Log.d("START", "onStartCommand ");
                startMusic();
            }
        } else if (action.equals(ACTION_STOP)) {
            if (isPlaying) {
                Log.d("STOP", "onStartCommand ");
                stopMusic();
            }
        }

        return START_STICKY;
    }

    private void stopMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        stopSelf();
    }

    private void startMusic() {
        isPlaying = true;
        mediaPlayer = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
