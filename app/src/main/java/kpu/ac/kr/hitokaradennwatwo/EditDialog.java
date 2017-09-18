package kpu.ac.kr.hitokaradennwatwo;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by angel on 2017-09-18.
 */

public class EditDialog extends Dialog {
    public EditDialog(Context context) {
        super(context);
    }
    ImageView btn_timer_10;
    ImageView btn_timer_30;
    ImageView btn_timer_50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);

        btn_timer_10 = (ImageView) findViewById(R.id.btn_10);
        btn_timer_30 = (ImageView) findViewById(R.id.btn_30);
        btn_timer_50 = (ImageView) findViewById(R.id.btn_50);

        btn_timer_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_timer_30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_timer_50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
