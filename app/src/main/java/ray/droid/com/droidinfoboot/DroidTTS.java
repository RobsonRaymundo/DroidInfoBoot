package ray.droid.com.droidinfoboot;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.speech.tts.TextToSpeech;
import android.support.annotation.Nullable;

import java.util.Locale;

/**
 * Created by Robson on 22/09/2017.
 */

public class DroidTTS extends Service implements TextToSpeech.OnInitListener {
    public static boolean VozDispositivoReiniciado;
    private TextToSpeech tts;
    private Context context;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onInit(int i) {
        if (!DroidCommon.NaoPertube(context)) {
            VozDispositivoReiniciado();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();
        tts = new TextToSpeech(context, this);
        tts.setLanguage(Locale.getDefault());
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    public void VozDispositivoReiniciado() {
        VozDispositivoReiniciado = false;
        if (DroidCommon.PreferenceSinteseVoz(context)) {
            tts.speak("O dispositivo foi reiniciado!", TextToSpeech.QUEUE_FLUSH, null);
            DroidCommon.TimeSleep(4000);
        }
        stopSelf();
    }

}
