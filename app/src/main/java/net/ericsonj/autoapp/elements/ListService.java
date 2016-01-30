package net.ericsonj.autoapp.elements;

import java.util.LinkedList;

/**
 * Created by ejoseph on 1/30/16.
 */
public class ListService implements ListObjects<Service>{

    private LinkedList<Service> list= new LinkedList<>();

    @Override
    public LinkedList<Service> getList() {
        return list;
    }

    @Override
    public void setList(LinkedList<Service> list) {
        this.list = list;
    }

}