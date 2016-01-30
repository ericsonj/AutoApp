package net.ericsonj.autoapp.elements;

import java.util.LinkedList;

/**
 * Created by ejoseph on 1/30/16.
 */
public interface ListObjects<T> {

    public LinkedList<T> getList();

    public void setList(LinkedList<T> list);

}
