package ray.droid.com.droidinfoboot;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.format.DateFormat;
import android.util.Log;
import android.util.TypedValue;
import android.widget.RemoteViews;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Robson on 04/08/2017.
 */

public class DroidCommon {


    public static void TimeSleep(Integer seg) {
        try {
            Thread.sleep(seg);
        } catch (Exception ex) {
        }
    }

    public static void Vibrar(Context context, int valor) {
        try {
            TimeSleep(100);
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(valor);
            TimeSleep(100);
        } catch (Exception ex) {
            Log.d("DroidBattery", "Vibrar: " + ex.getMessage());
        }
    }

    public static boolean NaoPertube(Context context) {

        boolean informarBateriaCarregada = true;
        try {
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
            boolean naoPertubeAtivado = sp.getBoolean("quiet", false);

            if (naoPertubeAtivado) {
                String startTime = sp.getString("startTime", "23:00");
                String stopTime = sp.getString("stopTime", "09:00");

                SimpleDateFormat sdfDate = new SimpleDateFormat("H:mm");
                String currentTimeStamp = sdfDate.format(new Date());
                int currentHour = Integer.parseInt(currentTimeStamp.split("[:]+")[0]);
                int currentMinute = Integer.parseInt(currentTimeStamp.split("[:]+")[1]);

                int startHour = Integer.parseInt(startTime.split("[:]+")[0]);
                int startMinute = Integer.parseInt(startTime.split("[:]+")[1]);

                int stopHour = Integer.parseInt(stopTime.split("[:]+")[0]);
                int stopMinute = Integer.parseInt(stopTime.split("[:]+")[1]);

                if (startHour < stopHour && currentHour > startHour && currentHour < stopHour)
                    informarBateriaCarregada = false;
                else if (startHour > stopHour && (currentHour > startHour || currentHour < stopHour))
                    informarBateriaCarregada = false;
                else if (currentHour == startHour && currentMinute >= startMinute)
                    informarBateriaCarregada = false;
                else if (currentHour == stopHour && currentMinute <= stopMinute)
                    informarBateriaCarregada = false;

                return informarBateriaCarregada;
            }
        } catch (Exception ex) {
            Log.d("DroidInfoBattery", ex.getMessage());
        }
        return informarBateriaCarregada;
    }



}
