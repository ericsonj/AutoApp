package net.ericsonj.autoapp.elements;

import java.util.LinkedList;

/**
 * Created by ejoseph on 1/31/16.
 */
public class ListResponseDate implements ListObjects<ResponseDate>{

    LinkedList<ResponseDate> list = new LinkedList<>();

    @Override
    public LinkedList<ResponseDate> getList() {
        return list;
    }

    @Override
    public void setList(LinkedList<ResponseDate> list) {
        this.list = list;
    }

}
