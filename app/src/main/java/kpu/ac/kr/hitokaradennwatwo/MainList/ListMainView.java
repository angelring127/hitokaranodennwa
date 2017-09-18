package kpu.ac.kr.hitokaradennwatwo.MainList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import kpu.ac.kr.hitokaradennwatwo.R;

/**
 * Created by angel on 2017-09-18.
 */

public class ListMainView extends LinearLayout {

    //Icon
    private ImageView mIcon;
    //
    private TextView mText01;
    private TextView mText02;


    public ListMainView(Context context, ListMainItem listMainItem) {
        super(context);
        //layout inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.list_view_mainitem,this,true);

        //set icon
        mIcon = (ImageView) findViewById(R.id.imageItem);
        mIcon.setImageDrawable(listMainItem.getIcon());

        // Set Text 01
        mText01 = (TextView) findViewById(R.id.dataItem01);
        mText01.setText(listMainItem.getData(0));

        // Set Text 02
        mText02 = (TextView) findViewById(R.id.dataItem02);
        mText02.setText(listMainItem.getData(1));
    }

    public void setText(int index, String data) {
        if (index == 0) {
            mText01.setText(data);
        } else if (index == 1) {
            mText02.setText(data);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * set Icon
     *
     * @param icon
     */
    public void setIcon(Drawable icon) {
        mIcon.setImageDrawable(icon);
    }
}
