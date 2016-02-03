package net.ericsonj.autoapp.elements;

import java.util.LinkedList;

/**
 * Created by ejoseph on 2/2/16.
 */
public class ListResponseAHour implements ListObjects<ResponseAvailableHour>{

    LinkedList<ResponseAvailableHour> list = new LinkedList<>();

    @Override
    public LinkedList<ResponseAvailableHour> getList() {
        return list;
    }

    @Override
    public void setList(LinkedList<ResponseAvailableHour> list) {
        this.list = list;
    }


}
