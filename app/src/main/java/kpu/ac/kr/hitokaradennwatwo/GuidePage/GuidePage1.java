package kpu.ac.kr.hitokaradennwatwo.GuidePage;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import kpu.ac.kr.hitokaradennwatwo.R;

/**
 * Created by angel on 2017-09-18.
 */

public class GuidePage1 extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        LinearLayout linearLayout=(LinearLayout)inflater.inflate(R.layout.guide_page,container,false);

        LinearLayout background=(LinearLayout)linearLayout.findViewById(R.id.background);
        TextView page_num=(TextView)linearLayout.findViewById(R.id.page_num);
        page_num.setText(String.valueOf(1));
        background.setBackground(new ColorDrawable(0xff6dc6d2));

        return linearLayout;
    }
}
