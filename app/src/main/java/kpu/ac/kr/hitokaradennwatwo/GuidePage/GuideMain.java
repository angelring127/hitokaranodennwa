package kpu.ac.kr.hitokaradennwatwo.GuidePage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import kpu.ac.kr.hitokaradennwatwo.R;

/**
 * Created by angel on 2017-09-18.
 */

public class GuideMain extends AppCompatActivity {
    int MAX_PAGE = 3;
    Fragment guide_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //noActionTitleBar

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.guide_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.gViewPager);
        viewPager.setAdapter(new adapter(getSupportFragmentManager()));



    }
    private class adapter extends FragmentPagerAdapter {                    //adapter클래스
        public adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if(position<0 || MAX_PAGE<=position)        //가리키는 페이지가 0 이하거나 MAX_PAGE보다 많을 시 null로 리턴
                return null;
            switch (position){              //포지션에 맞는 Fragment찾아서 cur_fragment변수에 대입
                case 0:
                    guide_fragment=new GuidePage1();
                    break;

                case 1:
                    guide_fragment=new GuidePage2();
                    break;

                case 2:
                    guide_fragment=new GuidePage3();
                    break;
            }

            return guide_fragment;
        }

        @Override
        public int getCount() {
            return MAX_PAGE;
        }
    }
}
