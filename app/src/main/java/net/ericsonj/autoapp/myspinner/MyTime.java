package net.ericsonj.autoapp.myspinner;

/**
 * Created by ejoseph on 1/23/16.
 */
public class MyTime {

    long Id;
    int time;

    public MyTime(long id, int time) {
        Id = id;
        this.time = time;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}

