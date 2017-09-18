package kpu.ac.kr.hitokaradennwatwo.MainList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by angel on 2017-09-18.
 */

public class ListMainAdapter extends BaseAdapter {
    private Context mContext;

    private List<ListMainItem> mItems = new ArrayList<ListMainItem>();

    public ListMainAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItem(ListMainItem it) {
        mItems.add(it);
    }

    public void setListItems(List<ListMainItem> lit) {
        mItems = lit;
    }

    public int getCount() {
        return mItems.size();
    }

    public Object getItem(int position) {
        return mItems.get(position);
    }

    public boolean areAllItemsSelectable() {
        return false;
    }

    public boolean isSelectable(int position) {
        try {
            return mItems.get(position).isSelectable();
        } catch (IndexOutOfBoundsException ex) {
            return false;
        }
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ListMainView itemView;
        if (convertView == null) {
            itemView = new ListMainView(mContext, mItems.get(position));
        } else {
            itemView = (ListMainView) convertView;

            itemView.setIcon(mItems.get(position).getIcon());
            itemView.setText(0, mItems.get(position).getData(0));
            itemView.setText(1, mItems.get(position).getData(1));
        }

        return itemView;
    }
}