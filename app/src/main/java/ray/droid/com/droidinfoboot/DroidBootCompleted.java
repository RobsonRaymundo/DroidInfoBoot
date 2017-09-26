package ray.droid.com.droidinfoboot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Robson on 03/05/2017.
 */

public class DroidBootCompleted extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, DroidAlertDialogActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

        Intent intentTTS = new Intent(context, DroidTTS.class);
        context.startService(intentTTS);
    }
}
