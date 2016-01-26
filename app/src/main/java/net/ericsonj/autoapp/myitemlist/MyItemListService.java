package net.ericsonj.autoapp.myitemlist;

/**
 * Created by ejoseph on 1/25/16.
 */
public class MyItemListService extends MyItemList{

    private int imgId;
    private String itemName;
    private String itemDetail;

    public MyItemListService(int imgId, String itemName, String itemDetail) {
        this.imgId = imgId;
        this.itemName = itemName;
        this.itemDetail = itemDetail;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setItemDetail(String itemDetail) {
        this.itemDetail = itemDetail;
    }

    @Override
    public int getImgId() {
        return imgId;
    }

    @Override
    public String getItemName() {
        return itemName;
    }

    @Override
    public String getItemDetail() {
        return itemDetail;
    }
}
