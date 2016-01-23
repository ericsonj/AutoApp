package net.ericsonj.autoapp.myspinner;

/**
 * Created by ejoseph on 1/22/16.
 */
public class MyItemSpinner {

    private int imgId;
    private String title;

    public MyItemSpinner(int imgId, String title) {
        this.imgId = imgId;
        this.title = title;
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
}
