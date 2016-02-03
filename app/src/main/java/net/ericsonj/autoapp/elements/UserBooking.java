package net.ericsonj.autoapp.elements;

import java.util.Date;

/**
 * Created by ejoseph on 1/30/16.
 */
public class UserBooking {

    private long service_id;
    private String name;
    private String document;
    private String email;
    private String car_brand;
    private String car_dode;
    private Date date;
    private long availableHourId;

    public UserBooking() {
    }

    public UserBooking(long service_id, String name, String document, String email, String car_brand, String car_dode, Date date, long availableHourId) {
        this.service_id = service_id;
        this.name = name;
        this.document = document;
        this.email = email;
        this.car_brand = car_brand;
        this.car_dode = car_dode;
        this.date = date;
        this.availableHourId = availableHourId;
    }



    public long getService_id() {
        return service_id;
    }

    public void setService_id(long service_id) {
        this.service_id = service_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCar_brand() {
        return car_brand;
    }

    public void setCar_brand(String car_brand) {
        this.car_brand = car_brand;
    }

    public String getCar_dode() {
        return car_dode;
    }

    public void setCar_dode(String car_dode) {
        this.car_dode = car_dode;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getAvailableHourId() {
        return availableHourId;
    }

    public void setAvailableHourId(long availableHourId) {
        this.availableHourId = availableHourId;
    }


    @Override
    public String toString() {
        return "UserBooking{" +
                "service_id=" + service_id +
                ", name='" + name + '\'' +
                ", document='" + document + '\'' +
                ", email='" + email + '\'' +
                ", car_brand='" + car_brand + '\'' +
                ", car_dode='" + car_dode + '\'' +
                ", date=" + date +
                '}';
    }

}
