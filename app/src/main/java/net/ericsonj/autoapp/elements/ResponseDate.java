package net.ericsonj.autoapp.elements;

import java.util.Date;

/**
 * Created by ejoseph on 1/31/16.
 */
public class ResponseDate {

    private long id;
    private String service_name;
    private String car_code;
    private Date date;
    private String state;

    public ResponseDate() {
    }

    public ResponseDate(long id, String service_name, String car_code, Date date, String state) {
        this.id = id;
        this.service_name = service_name;
        this.car_code = car_code;
        this.date = date;
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getCar_code() {
        return car_code;
    }

    public void setCar_code(String car_code) {
        this.car_code = car_code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "ResponseDate{" + "id=" + id + ", service_name=" + service_name + ", car_code=" + car_code + ", date=" + date + ", state=" + state + '}';
    }

}
