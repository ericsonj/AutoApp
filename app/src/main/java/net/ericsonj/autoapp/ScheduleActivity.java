package net.ericsonj.autoapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ericsonj.autoapp.elements.ListResponseAHour;
import net.ericsonj.autoapp.elements.ListService;
import net.ericsonj.autoapp.elements.RequestMessage;
import net.ericsonj.autoapp.elements.ResponseAvailableHour;
import net.ericsonj.autoapp.elements.ResponseMessage;
import net.ericsonj.autoapp.elements.Service;
import net.ericsonj.autoapp.elements.UserBooking;
import net.ericsonj.autoapp.myitemlist.MyItemListDate;
import net.ericsonj.autoapp.myitemlist.MyItemListIntent;
import net.ericsonj.autoapp.myitemlist.MyItemListService;
import net.ericsonj.autoapp.myspinner.MyItemSpinner;
import net.ericsonj.autoapp.myspinner.MySpinnerAdapter;
import net.ericsonj.autoapp.myspinner.MyTime;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

public class ScheduleActivity extends AppCompatActivity {

    private static final String TAG = ScheduleActivity.class.getSimpleName();
    public static final int DATE_DIALOG_ID = 999;
    private SharedPreferences preferences;
    private SharedPreferences prefCar;
    private DatePickerDialog.OnDateSetListener datePickerListener;
    private LinkedList<MyItemSpinner> services = new LinkedList<>();
    private LinkedList<MyItemSpinner> timeOptions = new LinkedList<>();
    private LinkedList<MyItemSpinner> carOptions = new LinkedList<>();
    private LinearLayout linearLayoutDate;
    private Spinner sService;
    private Spinner sCar;
    private TextView etName;
    private TextView etId;
    private TextView etEmail;
    private EditText etCarId;
    private TextView tvDate;
    private Spinner sTime;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
    private SimpleDateFormat formatHour = new SimpleDateFormat("HH:mm:ss");
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
//        loadTimeOptiones();
        loadCarOptions();
        loadCurrentCalendar();

        sService = (Spinner) findViewById(R.id.spinner_service);
        etName = (TextView) findViewById(R.id.editText_name);
        etId = (TextView) findViewById(R.id.editText_id);
        etEmail = (TextView) findViewById(R.id.editText_email);
        sCar = (Spinner) findViewById(R.id.spinner_car);
        etCarId = (EditText) findViewById(R.id.editText_carId);
        tvDate = (TextView) findViewById(R.id.textView_date);
        tvDate.setText(dateEs);
        sTime = (Spinner) findViewById(R.id.spinner_time);
        linearLayoutDate = (LinearLayout) findViewById(R.id.linearLayout_date);

//        Load Preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        etName.setText(preferences.getString(getResources().getString(R.string.key_pref_name), ""));
        etId.setText(preferences.getString(getResources().getString(R.string.key_pref_id), ""));
        etEmail.setText(preferences.getString(getResources().getString(R.string.key_pref_email), ""));


        loadAdapterMySpinner();
        loadadapterMySpinnerTime();
        loadadapterMySpinnerCar();
        addLayoutDateOnClick();
        addDatePicketListener();

        prefCar = getSharedPreferences("car",MODE_PRIVATE);
        int idListCar = prefCar.getInt("carItemPosition",0);
        String sCarId = prefCar.getString("carId","");
        sCar.setSelection(idListCar);
        etCarId.setText(sCarId);
        findAvailableHour();

    }

    public void loadServicesOptions() {
        for (MyItemListService s : ServerData.getInstance().getServices()) {
            services.add(new MyItemSpinner(s.getImgId(), s.getItemName(),s.getItemDetail()));
        }
    }

    public void loadTimeOptiones() {
        timeOptions.clear();
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 8 + ":00   -- 24HS", new MyTime(8)));
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 9 + ":00   -- 24HS", new MyTime(9)));
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 10 + ":00   -- 24HS", new MyTime(10)));
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 11 + ":00   -- 24HS", new MyTime(11)));
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 12 + ":00   -- 24HS", new MyTime(12)));
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 13 + ":00   -- 24HS", new MyTime(13)));
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 14 + ":00   -- 24HS", new MyTime(14)));
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 15 + ":00   -- 24HS", new MyTime(15)));
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 16 + ":00   -- 24HS", new MyTime(16)));
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 17 + ":00   -- 24HS", new MyTime(17)));
//        timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj, 18 + ":00   -- 24HS", new MyTime(18)));
    }

    public void loadCarOptions() {
        carOptions.clear();
        for(MyItemSpinner s : ServerData.getInstance().getCars()){
            carOptions.add(s);
        }
    }

    private void loadadapterMySpinnerCar() {
        MySpinnerAdapter adapter = new MySpinnerAdapter(this, carOptions);
        sCar.setAdapter(adapter);
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
                findAvailableHour();

            }
        };
    }

    public void findAvailableHour(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        RequestMessage message = new RequestMessage("OK",dateFormat.format(selectedDay));
        AsyncTackRESTHour restHour = new AsyncTackRESTHour();
        restHour.execute(message);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                // set date picker as current date
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, datePickerListener, year, month, day){
                    @Override
                    public void onDateChanged(DatePicker view, int y, int m, int d) {
                        if (y < year)
                            view.updateDate(year, month, day);

                        if (m < month && y == year)
                            view.updateDate(year, month, day);

                        if (d < day && y == year && m == month)
                            view.updateDate(year, month, day);
                    }
                };
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
        if (id == R.id.action_send) {
            sendData();
        }
        if (id == R.id.action_delete) {
            sService.setSelection(0);
//            etName.setText("");
//            etId.setText("");
//            etEmail.setText("");
//            etCarId.setText("");
//            tvDate.setText(dateEs);
            sTime.setSelection(0);
        }

        return super.onOptionsItemSelected(item);
    }

    public void loadCurrentCalendar() {
        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);
        dateEs = formatDate.format(c.getTime());
        selectedDay = c.getTime();
        selectedDay.setHours(0);
        selectedDay.setMinutes(0);
        selectedDay.setSeconds(0);
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

    private void sendData() {

        if (!validateFields()) {
            return;
        }
        int imgId = services.get(sService.getSelectedItemPosition()).getImgId();
        String title = services.get(sService.getSelectedItemPosition()).getTitle();
        long service_id = Long.parseLong((String)services.get(sService.getSelectedItemPosition()).getObject());
        String name = etName.getText().toString();
        String idName = etId.getText().toString();
        String email = etEmail.getText().toString();
        String car = (String)carOptions.get(sCar.getSelectedItemPosition()).getObject();
        String carId = etCarId.getText().toString();
        MyTime hour = (MyTime) timeOptions.get(sTime.getSelectedItemPosition()).getObject();
        Date date = addHour(selectedDay, hour.getTime());
        MyItemListDate itemDate = new MyItemListDate(imgId, title, name, idName, email, car, carId, date, "");
        MyItemListIntent data3 = new MyItemListIntent(itemDate);
        setResult(RESULT_OK, data3.getIntent());

        //save carSettings
        SharedPreferences.Editor editor = prefCar.edit();
        editor.putInt("carItemPosition", sCar.getSelectedItemPosition());
        editor.putString("carId", etCarId.getText().toString());
        editor.commit();
        Toast.makeText(this, "Información de auto guardada ",Toast.LENGTH_SHORT).show();

        UserBooking userBooking = new UserBooking(service_id,name,idName,email,car,carId,date,hour.getId());
        AsyncTackREST rest = new AsyncTackREST();
        rest.execute(userBooking);

//        finish();

    }

    public boolean validateFields() {
        if (etName.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Debe ingresar su Nombre.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (etId.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Debe ingresar su numero de Cedula.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (etEmail.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Debe ingresar un Correo.", Toast.LENGTH_LONG).show();
            return false;
        }

        if (etCarId.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "Debe ingresar la placa del auto.", Toast.LENGTH_LONG).show();
            return false;
        }
        if (timeOptions.isEmpty()){
            Toast.makeText(this, "Debe ingresar una fecha", Toast.LENGTH_LONG).show();
            return false;
        }


        return true;
    }

    public Date addHour(Date date, int hour) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR, (int)hour);
        return cal.getTime();
    }

    public void updateList(ResponseMessage s){
        if(s != null){
            if(s.isIsSuccessful()){
                Toast.makeText(this, "Servicio Agendado ", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "No agendado, revise que cuente con una conexion de Internet", Toast.LENGTH_LONG).show();
        }
        finish();
    }

    public class AsyncTackREST extends AsyncTask<UserBooking,String,ResponseMessage> {

        @Override
        protected ResponseMessage doInBackground(UserBooking... params) {
            ResponseMessage result = null;
            try {
                result =  connect(ServerData.SERVER_URL_BOOKING, params[0], UserBooking.class, ResponseMessage.class);
            } catch (IOException e) {
                e.printStackTrace();
                Log.v(TAG, e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(ResponseMessage s) {
            Log.v(TAG, "DESPUÉS de CANCELAR la descarga. Se han descarcado " + s + " imágenes. Hilo PRINCIPAL");
            updateList(s);
        }

        public <Q, A> A connect(String url, Q query, Class<Q> qClazz, Class<A> aClazz) throws IOException {

            ObjectMapper mapper = new ObjectMapper();
            HttpParams htpp = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(htpp, 30000);
            HttpConnectionParams.setSoTimeout(htpp, 30000);
            HttpClient httpClient = new DefaultHttpClient(htpp);
            HttpPost post;
            post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            StringEntity entity = new StringEntity(mapper.writeValueAsString(query), "UTF-8");
            post.setEntity(entity);
            HttpResponse resp = httpClient.execute(post);
            InputStream is = resp.getEntity().getContent();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte array[] = new byte[1024];
            int readId;
            while ((readId = is.read(array)) > 0) {
                bos.write(array, 0, readId);
            }
            String strResponse = new String(bos.toByteArray());
            A respValue = mapper.readValue(strResponse, aClazz);
            return respValue;

        }

    }

    public void updateListHour(ListResponseAHour s){

        if(s == null){
            return;
        }
        if(s.getList().isEmpty()){
            Toast.makeText(this, "Sin horas disponibles para esta fecha.",Toast.LENGTH_LONG).show();
        }
        timeOptions.clear();
        for(ResponseAvailableHour aHour : s.getList()){
            timeOptions.add(new MyItemSpinner(R.drawable.ic_reloj,formatHour.format(aHour.getDatehour()),new MyTime(aHour.getId(),aHour.getDatehour().getHours())));
        }
        loadadapterMySpinnerTime();

    }

    public class AsyncTackRESTHour extends AsyncTask<RequestMessage,String,ListResponseAHour> {

        @Override
        protected ListResponseAHour doInBackground(RequestMessage... params) {
            ListResponseAHour result = null;
            try {
                result =  connect(ServerData.SERVER_URL_REQUESTHOUR, params[0], RequestMessage.class, ListResponseAHour.class);
            } catch (IOException e) {
                e.printStackTrace();
                Log.v(TAG, e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(ListResponseAHour s) {
            Log.v(TAG, "DESPUÉS de CANCELAR la descarga. Se han descarcado " + s + " imágenes. Hilo PRINCIPAL");
            updateListHour(s);
        }

        public <Q, A> A connect(String url, Q query, Class<Q> qClazz, Class<A> aClazz) throws IOException {

            ObjectMapper mapper = new ObjectMapper();
            HttpParams htpp = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(htpp, 30000);
            HttpConnectionParams.setSoTimeout(htpp, 30000);
            HttpClient httpClient = new DefaultHttpClient(htpp);
            HttpPost post;
            post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            post.setHeader("Accept", "application/json");
            StringEntity entity = new StringEntity(mapper.writeValueAsString(query), "UTF-8");
            post.setEntity(entity);
            HttpResponse resp = httpClient.execute(post);
            InputStream is = resp.getEntity().getContent();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte array[] = new byte[1024];
            int readId;
            while ((readId = is.read(array)) > 0) {
                bos.write(array, 0, readId);
            }
            String strResponse = new String(bos.toByteArray());
            A respValue = mapper.readValue(strResponse, aClazz);
            return respValue;

        }

    }

}
