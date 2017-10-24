package ray.droid.com.droidinfoboot;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.prefs.Preferences;

/**
 * Created by Robson on 22/09/2017.
 */


public class DroidAlertDialogActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    private Context context;
    private EditText lv_boots;
    private Button btnFechar;
    private Button btnConfigurar;
    private boolean exibeAlert;


    private void AlertDialog() {
        try {
            Calendar calendar = Calendar.getInstance();
            String diaAtual = DateFormat.format("dd/MM/yyyy", calendar.getTime()).toString();
            String horaAtual = DateFormat.format("kk:mm:ss", calendar.getTime()).toString();
            String titulo = "O dispositivo foi reiniciado\n";
            String dataHoraAtual = " dia " + diaAtual + " às " + horaAtual + "\n";
            String dadosBoots = DroidPreferences.GetString(context, "dataHoraAtual");
            DroidPreferences.SetString(context, "dataHoraAtual", dataHoraAtual + dadosBoots);

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("ATENÇÃO");
            builder.setMessage(titulo + dataHoraAtual);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface arg0, int arg1) {
                    alertDialog.dismiss();
                }
            });
            alertDialog = builder.create();
            alertDialog.show();
            lv_boots.setText(ListaDadosBoots());

        } catch (Exception ex) {
            Log.d("DroidInfoBoot", ex.getMessage());
        }
    }

    private void Inicializacao() {
        //
        lv_boots = (EditText) findViewById(R.id.lv_boots);
        lv_boots.setText(ListaDadosBoots());

        lv_boots.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(view.getContext());
                //
                alerta.setTitle("Limpar os dados")
                        .setMessage("Deseja realmente limpar os dados ?");
                alerta.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        DroidPreferences.SetString(context, "dataHoraAtual", "");
                        lv_boots.setText(ListaDadosBoots());
                    }
                });
                //
                alerta.setNegativeButton("Cancelar", null);
                //
                alerta.show();

                return false;
            }
        });



        btnFechar = (Button) findViewById(R.id.btnFechar);
        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnConfigurar = (Button) findViewById(R.id.btnConfigurar);

        btnConfigurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, DroidPreferenceActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alerta);
        context = getBaseContext();

        exibeAlert = getIntent().getBooleanExtra("exibeAlert", true);

        Inicializacao();
        if (exibeAlert) {
            exibeAlert = false;
            AlertDialog();
        }
    }


    private String ListaDadosBoots() {
        return DroidPreferences.GetString(context, "dataHoraAtual");
    }


}
