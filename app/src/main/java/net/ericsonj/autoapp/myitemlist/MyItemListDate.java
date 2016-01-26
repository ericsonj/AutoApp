package net.ericsonj.autoapp.myitemlist;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by ejoseph on 1/23/16.
 */
public class MyItemListDate extends MyItemList{

    private int imgId;
    private String titleService;
    private String name;
    private String id;
    private String email;
    private String car;
    private String carId;
    private Date date;

    public MyItemListDate() {
    }

    public MyItemListDate(int imgId, String titleService, String name, String id, String email, String car, String carId, Date date) {
        this.imgId = imgId;
        this.titleService = titleService;
        this.name = name;
        this.id = id;
        this.email = email;
        this.car = car;
        this.carId = carId;
        this.date = date;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitleService() {
        return titleService;
    }

    public void setTitleService(String titleService) {
        this.titleService = titleService;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int getImgId() {
        return imgId;
    }

    @Override
    public String getItemName() {
        return titleService;
    }

    @Override
    public String getItemDetail() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMMM-yyyy, HH:mm:ss", new Locale("es","ES"));
        return carId + "    "+formatter.format(date);
    }


}
