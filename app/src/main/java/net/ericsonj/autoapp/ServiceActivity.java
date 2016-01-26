package net.ericsonj.autoapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import net.ericsonj.autoapp.myitemlist.MyItemListService;
import net.ericsonj.autoapp.myitemlist.MyListAdapterService;

import java.util.LinkedList;

public class ServiceActivity extends AppCompatActivity {

    private ListView listViewService;
    private LinkedList<MyItemListService> listService = new LinkedList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.titleService);
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listViewService = (ListView)findViewById(R.id.listView_service);
        for(MyItemListService s : ServerData.getInstance().getServices()){
            listService.add(s);
        }
        loadItemToList();
    }

    public void loadItemToList(){
        MyListAdapterService adapter = new MyListAdapterService(this, listService);
        listViewService.setAdapter(adapter);
    }

}
