package kpu.ac.kr.hitokaradennwatwo.CallingPage;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import kpu.ac.kr.hitokaradennwatwo.R;

/**
 * Created by angel on 2017-09-18.
 */

public class CallingPageView extends AppCompatActivity {


    //오디오 데이터 위치
    static final String AUDIO_URL = "http://musicisvfr.com/sounds/se/pre/Cell_Phone-Ringtone02-1L.mp3";
    //비디오 데이터 위치
    private String videoUrl ;
    static final String KEY_FATH_DATA = "data";
    //미디어플레이어 변수 선언
    private MediaPlayer mediaPlayer;
    private int playbackPosition;
    private ImageView callImage;
    private VideoView videoView;


    //광고설정
    private InterstitialAd mInterstitialAd;

    //버튼 선언
    private Button btn_ok;
    private Button btn_cancel;
    private Button btn_stop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.calling_page);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-5555878466921311/3757981802");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());





        //인텐트에서 값을 가져온다
        Intent intent = getIntent();
        videoUrl = intent.getStringExtra(KEY_FATH_DATA);

        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        callImage = (ImageView) findViewById(R.id.callImage);
        videoView = (VideoView) findViewById(R.id.videoView);
        mediaPlayer = MediaPlayer.create(this,R.raw.cell_phone);
        mediaPlayer.setLooping(true);

        //MediaController mc = new MediaController(this);
        videoView.setMediaController(null);
        videoView.setVideoURI(Uri.parse(videoUrl));
        videoView.requestFocus();




        if(mediaPlayer != null){
            isPlaying();
        } else{
            mediaPlayer = MediaPlayer.create(this,R.raw.cell_phone);
            isPlaying();
        }


        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //영상 시작 버튼
                //이미지뷰 감추고 영상뷰 올림
                callImage.setVisibility(v.INVISIBLE);
                videoView.setVisibility(v.VISIBLE);
                btn_ok.setVisibility(v.INVISIBLE);
                btn_cancel.setVisibility(v.INVISIBLE);
                btn_stop.setVisibility(v.VISIBLE);
                //전화벨 종료하고 영상 실행
                mediaPlayer.stop();
                mediaPlayer.release();

                videoView.seekTo(0);
                videoView.start();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent resultIntent = new Intent();

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

               // setResult(RESULT_OK, resultIntent);
                finish();
            }
        });

        btn_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent resultIntent = new Intent();

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }

               // setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    private void isPlaying(){
        if(!mediaPlayer.isPlaying()){

            mediaPlayer.start();
        } else{
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        mediaPlayer = new MediaPlayer();
        mediaPlayer.setDataSource(url);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
