package ray.droid.com.droidinfoboot;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getBaseContext();

        try {

            Intent i = new Intent(context, DroidAlertDialogActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.putExtra("exibeAlert", false);
            context.startActivity(i);

        } catch (Exception ex) {
            Log.d("DroidInfoBoot", ex.getMessage());
        }
        finish();
    }

}


