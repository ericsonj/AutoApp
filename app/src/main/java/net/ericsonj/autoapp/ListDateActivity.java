package net.ericsonj.autoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import net.ericsonj.autoapp.myitemlist.MyItemListDate;
import net.ericsonj.autoapp.myitemlist.MyItemListIntent;
import net.ericsonj.autoapp.myitemlist.MyListAdapter;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class ListDateActivity extends AppCompatActivity {

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public static final int REQUEST_CODE = 48795;
    private LinkedList<MyItemListDate> list = new LinkedList<>();
    private ListView listDate;

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
                Intent intentAddItem = new Intent(ListDateActivity.this , ScheduleActivity.class);
                startActivityForResult(intentAddItem,REQUEST_CODE);
            }
        });

        listDate = (ListView)findViewById(R.id.listView);
        loadItemToList();

    }

    public void loadItemToList(){
        MyListAdapter adapter = new MyListAdapter(this, list);
        listDate.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE){
            MyItemListIntent intent = new MyItemListIntent(data);
            MyItemListDate itemDate = intent.getItemList();
            list.add(itemDate);
            loadItemToList();
            Toast.makeText(this, "Servicio Agendado ", Toast.LENGTH_LONG).show();
        }

    }
}
