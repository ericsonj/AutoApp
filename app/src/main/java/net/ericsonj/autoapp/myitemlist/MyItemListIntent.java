package net.ericsonj.autoapp.myitemlist;

import android.content.Intent;
import android.util.Log;

import net.ericsonj.autoapp.ScheduleActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by ejoseph on 1/22/16.
 */

public class MyItemListIntent {

    private static final String TAG = ScheduleActivity.class.getSimpleName();

    private SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private Intent intent;
    private MyItemListDate itemList;

    public MyItemListIntent(MyItemListDate itemList) {
        this.itemList = itemList;
        this.intent = new Intent();
        putData();
    }

    public MyItemListIntent(Intent intent) {
        this.intent = intent;
        itemList = new MyItemListDate();
        getData();
    }

    public Intent getIntent() {
        return intent;
    }

    public MyItemListDate getItemList() {
        return itemList;
    }

    public void setItemList(MyItemListDate itemList) {
        this.itemList = itemList;
    }

    private void putData() {

        intent.putExtra("imgId", itemList.getImgId());
        intent.putExtra("titleService", itemList.getTitleService());
        intent.putExtra("name", itemList.getName());
        intent.putExtra("id", itemList.getId());
        intent.putExtra("email", itemList.getEmail());
        intent.putExtra("car", itemList.getCar());
        intent.putExtra("carId", itemList.getCarId());
        intent.putExtra("date", formatter.format(itemList.getDate()));


    }

    private void getData() {
        itemList.setImgId(intent.getExtras().getInt("imgId"));
        itemList.setTitleService(intent.getExtras().getString("titleService"));
        itemList.setName(intent.getExtras().getString("name"));
        itemList.setId(intent.getExtras().getString("id"));
        itemList.setEmail(intent.getExtras().getString("email"));
        itemList.setCar(intent.getExtras().getString("car"));
        itemList.setCarId(intent.getExtras().getString("carId"));
        try {
            itemList.setDate(formatter.parse(intent.getExtras().getString("date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}


