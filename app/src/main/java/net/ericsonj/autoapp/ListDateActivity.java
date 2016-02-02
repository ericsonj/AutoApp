package net.ericsonj.autoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ericsonj.autoapp.elements.ListResponseDate;
import net.ericsonj.autoapp.elements.RequestMessage;
import net.ericsonj.autoapp.elements.ResponseDate;
import net.ericsonj.autoapp.myitemlist.MyItemListDate;
import net.ericsonj.autoapp.myitemlist.MyItemListIntent;
import net.ericsonj.autoapp.myitemlist.MyListAdapterDate;

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
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class ListDateActivity extends AppCompatActivity {

    private static final String TAG = ListDateActivity.class.getSimpleName();
    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public static final int REQUEST_CODE = 48795;
    private LinkedList<MyItemListDate> list = new LinkedList<>();
    private ListView listDate;
    private SharedPreferences preferences;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_date);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.titlelistDate);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentAddItem = new Intent(ListDateActivity.this, ScheduleActivity.class);
                startActivityForResult(intentAddItem, REQUEST_CODE);
            }
        });

        listDate = (ListView)findViewById(R.id.listView);
        loadItemToList();

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        name = preferences.getString(getResources().getString(R.string.key_pref_name), "");
        if(!name.isEmpty()){
            RequestMessage message = new RequestMessage("OK",name);
            AsyncTackREST rest = new AsyncTackREST();
            rest.execute(message);
        }

    }

    public void loadItemToList(){
        MyListAdapterDate adapter = new MyListAdapterDate(this, list);
        listDate.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            MyItemListIntent intent = new MyItemListIntent(data);
            MyItemListDate itemDate = intent.getItemList();
//            list.add(itemDate);
//            loadItemToList();
            RequestMessage message = new RequestMessage("OK",name);
            AsyncTackREST rest = new AsyncTackREST();
            rest.execute(message);
        }

    }

    public void updateList(ListResponseDate s){
        if(s == null){
            return;
        }
        LinkedList<ResponseDate> listDate = s.getList();
        list.clear();
        for (ResponseDate rd : listDate){
            list.add(new MyItemListDate(ServerData.getInstance().getIcon(rd.getService_name()),rd.getService_name(),"","","","",rd.getCar_code(),rd.getDate(),rd.getState()));
        }
        loadItemToList();
    }

    public class AsyncTackREST extends AsyncTask<RequestMessage,String,ListResponseDate> {

        @Override
        protected ListResponseDate doInBackground(RequestMessage... params) {
            ListResponseDate result = null;
            try {
                result =  connect(ServerData.SERVER_URL_REQUESTDATE, params[0], RequestMessage.class, ListResponseDate.class);
            } catch (IOException e) {
                e.printStackTrace();
                Log.v(TAG, e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(ListResponseDate s) {
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

}
