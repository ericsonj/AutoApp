package net.ericsonj.autoapp.myspinner;

import java.util.Objects;

/**
 * Created by ejoseph on 1/22/16.
 */
public class MyItemSpinner {

    private int imgId;
    private String title;
    private Object object;

    public MyItemSpinner(int imgId, String title) {
        this.imgId = imgId;
        this.title = title;
    }

    public MyItemSpinner(int imgId, String title, Object object) {
        this.imgId = imgId;
        this.title = title;
        this.object = object;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
