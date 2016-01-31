package net.ericsonj.autoapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.ericsonj.autoapp.elements.ListService;
import net.ericsonj.autoapp.elements.RequestMessage;
import net.ericsonj.autoapp.elements.Service;
import net.ericsonj.autoapp.myitemlist.MyItemListService;
import net.ericsonj.autoapp.mypreferences.SettingsActivity;

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
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private SharedPreferences preferences;
    private String name= "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = preferences.getString(getResources().getString(R.string.key_pref_name), "");
                if(name.isEmpty()){
                    Intent runActivity = new Intent(MainActivity.this, SettingsActivity.class);
                    startActivity(runActivity);
                }else{
                    Intent runActivity = new Intent(MainActivity.this, ListDateActivity.class);
                    startActivity(runActivity);
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RequestMessage message = new RequestMessage("OK","getServices");
        AsyncTackREST rest = new AsyncTackREST();
        rest.execute(message);

        ServerData.getInstance().loadcars();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            startActivity(new Intent(this, SettingsActivity.class));
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_about) {
            Intent runActivity = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(runActivity);
        } else if (id == R.id.nav_schedule) {
            Intent runActivity = new Intent(MainActivity.this, ListDateActivity.class);
            startActivity(runActivity);
        } else if (id == R.id.nav_exit) {
            this.finish();
        } else if (id == R.id.nav_info) {
            Intent runActivity = new Intent(MainActivity.this, InfoActivity.class);
            startActivity(runActivity);
        } else if (id == R.id.nav_service) {
            Intent runActivity = new Intent(MainActivity.this, ServiceActivity.class);
            startActivity(runActivity);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void updateList(ListService s){

        try{
            LinkedList<Service> services = s.getList();
            LinkedList<MyItemListService> itemService = new LinkedList<>();
            for(Service serv : services){
                itemService.add(new MyItemListService(ServerData.getInstance().getIcon(serv.getNameService()),serv.getNameService(),String.valueOf(serv.getId())));
            }
            ServerData.getInstance().loadServices(itemService);
        }catch (Exception e){
            ServerData.getInstance().loadService();
        }

    }

    public class AsyncTackREST extends AsyncTask<RequestMessage,String,ListService> {

        @Override
        protected ListService doInBackground(RequestMessage... params) {
            ListService result = null;
            try {
                result =  connect(ServerData.SERVER_URL_GETSERVICES, params[0], RequestMessage.class, ListService.class);
            } catch (IOException e) {
                e.printStackTrace();
                Log.v(TAG, e.toString());
            }
            return result;
        }

        @Override
        protected void onPostExecute(ListService s) {
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
