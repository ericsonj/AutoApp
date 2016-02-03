package net.ericsonj.autoapp.elements;

import java.util.Date;

/**
 * Created by ejoseph on 2/2/16.
 */
public class ResponseAvailableHour {

    private long id;
    private Date datehour;

    public ResponseAvailableHour() {
    }

    public ResponseAvailableHour(long id, Date datehour) {
        this.id = id;
        this.datehour = datehour;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDatehour() {
        return datehour;
    }

    public void setDatehour(Date datehour) {
        this.datehour = datehour;
    }

    @Override
    public String toString() {
        return "ResponseAvailableHour{" + "id=" + id + ", datehour=" + datehour + '}';
    }

}
