package net.ericsonj.autoapp.myitemlist;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import net.ericsonj.autoapp.R;

import java.util.List;


/**
 * Created by ejoseph on 1/21/16.
 */
public class MyListAdapter extends ArrayAdapter<MyItemListDate> {

    private List<MyItemListDate> items;
    private Activity context;

    public MyListAdapter(Activity context, List<MyItemListDate> objects) {
        super(context, R.layout.my_item_list, objects);
        this.items = objects;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.my_item_list, null, true);

        TextView textViewItemName = (TextView)rowView.findViewById(R.id.textView_itemName);
        ImageView imageViewImgItem =  (ImageView)rowView.findViewById(R.id.imageView_icon);
        TextView textViewItemDetail = (TextView)rowView.findViewById(R.id.textView_itemDetail);

        textViewItemName.setText(items.get(position).getItemName());
        imageViewImgItem.setImageResource(items.get(position).getImgId());
        textViewItemDetail.setText(items.get(position).getItemDetail());

        return rowView;
    }
}
