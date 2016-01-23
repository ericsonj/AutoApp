package net.ericsonj.autoapp.myspinner;

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
 * Created by ejoseph on 1/22/16.
 */
public class MySpinnerAdapter extends ArrayAdapter<MyItemSpinner>{

    private List<MyItemSpinner> items;
    private Activity context;

    public MySpinnerAdapter(Activity context, List<MyItemSpinner> objects) {
        super(context, R.layout.spinner_row, objects);
        this.items = objects;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.spinner_row, parent, false);

        TextView textViewItemTitle = (TextView)rowView.findViewById(R.id.textView_title);
        ImageView imageViewImgItem =  (ImageView)rowView.findViewById(R.id.imageView_icon);

        textViewItemTitle.setText(items.get(position).getTitle());
        imageViewImgItem.setImageResource(items.get(position).getImgId());

        return rowView;
    }
}
