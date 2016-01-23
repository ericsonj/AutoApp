package net.ericsonj.autoapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import net.ericsonj.autoapp.myspinner.MyItemSpinner;
import net.ericsonj.autoapp.myspinner.MySpinnerAdapter;

import java.util.LinkedList;

public class ScheduleActivity extends AppCompatActivity {


    LinkedList<MyItemSpinner> services = new LinkedList<>();

    private Spinner sService;
    private EditText etName;
    private EditText etId;
    private EditText etEmail;
    private EditText etCar;
    private EditText etCarId;
    private TextView tvDate;
    private Spinner sTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.titleSchedule);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        sService = (Spinner)findViewById(R.id.spinner_service);
        etName = (EditText)findViewById(R.id.editText_name);
        etId = (EditText)findViewById(R.id.editText_id);
        etEmail = (EditText)findViewById(R.id.editText_email);
        etCar = (EditText)findViewById(R.id.editText_car);
        etCarId = (EditText)findViewById(R.id.editText_carId);
        tvDate = (TextView)findViewById(R.id.textView_date);
        sTime = (Spinner)findViewById(R.id.spinner_time);

        services.add(new MyItemSpinner(R.drawable.ic_gears,"Servicio General"));
        services.add(new MyItemSpinner(R.drawable.ic_oil,"Cambio de Aceite"));
        services.add(new MyItemSpinner(R.drawable.ic_braket,"Revisión de frenos"));
        services.add(new MyItemSpinner(R.drawable.ic_suspe,"Revisión de suspención"));
        services.add(new MyItemSpinner(R.drawable.ic_motor,"Revisión de motor"));
        services.add(new MyItemSpinner(R.drawable.ic_light,"Revisión de luces"));
        services.add(new MyItemSpinner(R.drawable.ic_direction,"Revisión de dirección"));
        services.add(new MyItemSpinner(R.drawable.ic_neumatic,"Revisión de neumaticos"));
        loadAdapterMySpinner();

    }

    private void loadAdapterMySpinner(){
        MySpinnerAdapter adapter = new MySpinnerAdapter(this, services);
        sService.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_schedule, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
