package net.ericsonj.autoapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import net.ericsonj.autoapp.myspinner.MyItemSpinner;
import net.ericsonj.autoapp.myspinner.MySpinnerAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class ScheduleActivity extends AppCompatActivity {


    public static final int DATE_DIALOG_ID = 999;

    private DatePickerDialog.OnDateSetListener datePickerListener;
    private LinkedList<MyItemSpinner> services = new LinkedList<>();
    private LinkedList<MyItemSpinner> timeOptions = new LinkedList<>();
    private LinearLayout linearLayoutDate;
    private Spinner sService;
    private EditText etName;
    private EditText etId;
    private EditText etEmail;
    private EditText etCar;
    private EditText etCarId;
    private TextView tvDate;
    private Spinner sTime;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
    private int year;
    private int month;
    private int day;
    private String dateEs;
    private Date selectedDay;

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

        loadServicesOptions();
        loadTimeOptiones();

        loadCurrentCalendar();


        sService = (Spinner) findViewById(R.id.spinner_service);
        etName = (EditText) findViewById(R.id.editText_name);
        etId = (EditText) findViewById(R.id.editText_id);
        etEmail = (EditText) findViewById(R.id.editText_email);
        etCar = (EditText) findViewById(R.id.editText_car);
        etCarId = (EditText) findViewById(R.id.editText_carId);
        tvDate = (TextView) findViewById(R.id.textView_date);
        tvDate.setText(dateEs);
        sTime = (Spinner) findViewById(R.id.spinner_time);
        linearLayoutDate = (LinearLayout) findViewById(R.id.linearLayout_date);

        loadAdapterMySpinner();
        loadadapterMySpinnerTime();
        addLayoutDateOnClick();
        addDatePicketListener();

    }

    public void loadServicesOptions() {
        services.add(new MyItemSpinner(R.drawable.ic_gears, "Servicio General"));
        services.add(new MyItemSpinner(R.drawable.ic_oil, "Cambio de Aceite"));
        services.add(new MyItemSpinner(R.drawable.ic_braket, "Revisión de frenos"));
        services.add(new MyItemSpinner(R.drawable.ic_suspe, "Revisión de suspención"));
        services.add(new MyItemSpinner(R.drawable.ic_motor, "Revisión de motor"));
        services.add(new MyItemSpinner(R.drawable.ic_light, "Revisión de luces"));
        services.add(new MyItemSpinner(R.drawable.ic_direction, "Revisión de dirección"));
        services.add(new MyItemSpinner(R.drawable.ic_neumatic, "Revisión de neumaticos"));
    }

    public void loadTimeOptiones() {
        timeOptions.clear();
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 8 + ":00   -- 24HS", 8));
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 9 + ":00   -- 24HS", 9));
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 10 + ":00   -- 24HS", 10));
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 11 + ":00   -- 24HS", 11));
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 12 + ":00   -- 24HS", 12));
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 13 + ":00   -- 24HS", 13));
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 14 + ":00   -- 24HS", 14));
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 15 + ":00   -- 24HS", 15));
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 16 + ":00   -- 24HS", 16));
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 17 + ":00   -- 24HS", 17));
        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 18 + ":00   -- 24HS", 18));
    }

    private void addLayoutDateOnClick() {
        linearLayoutDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
    }

    private void addDatePicketListener() {
        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                selectedDay = getDate(year, monthOfYear + 1, dayOfMonth);
                Toast.makeText(ScheduleActivity.this, formatDate.format(selectedDay), Toast.LENGTH_SHORT).show();
                tvDate.setText(formatDate.format(selectedDay));
            }
        };
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePickerListener, year, month, day);
                return datePickerDialog;
        }
        return null;
    }

    private void loadAdapterMySpinner() {
        MySpinnerAdapter adapter = new MySpinnerAdapter(this, services);
        sService.setAdapter(adapter);
    }

    private void loadadapterMySpinnerTime() {
        MySpinnerAdapter adapter = new MySpinnerAdapter(this, timeOptions);
        sTime.setAdapter(adapter);
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

    public void loadCurrentCalendar() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        dateEs = formatDate.format(c.getTime());
    }


    private Date getDate(int year, int month, int day) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = day + "-" + month + "-" + year;
        try {
            date = formatter.parse(dateInString);
            System.out.println(date);
            System.out.println(formatter.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
