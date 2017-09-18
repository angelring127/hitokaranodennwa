package kpu.ac.kr.hitokaradennwatwo.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import kpu.ac.kr.hitokaradennwatwo.CallingPage.CallingPageView;
import kpu.ac.kr.hitokaradennwatwo.GuidePage.GuideMain;
import kpu.ac.kr.hitokaradennwatwo.MainList.ListMainAdapter;
import kpu.ac.kr.hitokaradennwatwo.MainList.ListMainItem;
import kpu.ac.kr.hitokaradennwatwo.R;

/**
 * Created by angel on 2017-09-18.
 */

public class MainFragment extends Fragment {

    ListView listView;
    ListMainAdapter adapter;

    private ProgressDialog progressBar;

    static final int PERMISSION_REQUEST_CODE = 1;
    public static final int REQUEST_CODE_ANOTHER = 1001;
    static final String KEY_FATH_DATA = "data";
    String[] PERMISSIONS = {"android.permission.READ_EXTERNAL_STORAGE","android.permission.WRITE_EXTERNAL_STORAGE"};
    private File outputFile; //파일명까지 포함한 경로
    private File path;//디렉토리경로
    private int result; // 다운로드 확인


    public MainFragment(){

    }
    private LinearLayout mainLeft;

    private boolean hasPermissions(String[] permissions) {
        int res = 0;
        //스트링 배열에 있는 퍼미션들의 허가 상태 여부 확인
        for (String perms : permissions){
            res = getContext().checkCallingOrSelfPermission(perms);
            if (!(res == PackageManager.PERMISSION_GRANTED)){
                //퍼미션 허가 안된 경우
                return false;
            }

        }
        //퍼미션이 허가된 경우
        return true;
    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        mainLeft = (LinearLayout) rootView.findViewById(R.id.mainLeft);
        TabHost tabHost1 = (TabHost) rootView.findViewById(R.id.tabHost);

        //listview setup
        listView = (ListView) rootView.findViewById(R.id.listView01);

        adapter = new ListMainAdapter(getContext());

        if (!hasPermissions(PERMISSIONS)) { //퍼미션 허가를 했었는지 여부를 확인
            requestNecessaryPermissions(PERMISSIONS);//퍼미션 허가안되어 있다면 사용자에게 요청
        } else {
            //이미 사용자에게 퍼미션 허가를 받음.
        }

        progressBar=new ProgressDialog(getContext());
        progressBar.setMessage("다운로드중");
        progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressBar.setIndeterminate(true);
        progressBar.setCancelable(true);

        // 아이템 데이터 만들기
        Resources res = getResources();
        adapter.addItem(new ListMainItem(res.getDrawable(R.drawable.icon05), "お兄ちゃん", "30,000 다운로드"));
        adapter.addItem(new ListMainItem(res.getDrawable(R.drawable.icon05), "お兄ちゃん", "26,000 다운로드"));
        adapter.addItem(new ListMainItem(res.getDrawable(R.drawable.icon05), "お兄ちゃん", "300,000 다운로드"));
        adapter.addItem(new ListMainItem(res.getDrawable(R.drawable.icon05), "友達探し (Friends Seeker)", "300,000 다운로드"));
        adapter.addItem(new ListMainItem(res.getDrawable(R.drawable.icon05), "友達探し (Friends Seeker)", "300,000 다운로드"));
        adapter.addItem(new ListMainItem(res.getDrawable(R.drawable.icon05), "友達探し (Friends Seeker)", "300,000 다운로드"));



        tabHost1.setup();

        // 리스트뷰에 어댑터 설정
        listView.setAdapter(adapter);

        // 새로 정의한 리스너로 객체를 만들어 설정
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListMainItem curItem = (ListMainItem) adapter.getItem(position);
                String[] curData = curItem.getData();


                final String fileURL = "http://hellonabi.tistory.com/attachment/cfile25.uf@99EE5C3359BE77D7353BEA.mp4";

                path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                outputFile= new File(path, "Aright.avi"); //파일명까지 포함함 경로의 File 객체 생성

                if (outputFile.exists() && result == 100) { //이미 다운로드 되어 있는 경우
                    Intent intent = new Intent(getContext(), CallingPageView.class);

                    intent.putExtra(KEY_FATH_DATA,outputFile.toString());

                    startActivity(intent);
                } else {//새로 받는 경우
                    final DownloadFilesTask downloadTask = new DownloadFilesTask(getContext());
                    downloadTask.execute(fileURL);

                    progressBar.setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialog) {
                            downloadTask.cancel(true);
                        }
                    });

                }
            }

        });



        mainLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(rootView.getContext(), GuideMain.class);
                startActivity(intent);

            }
        });

        // 첫 번째 Tab. (탭 표시 텍스트:"TAB 1"), (페이지 뷰:"content1")
        TabHost.TabSpec ts1 = tabHost1.newTabSpec("TabSpec 1");
        ts1.setContent(R.id.content1);
        ts1.setIndicator("おすすめ");
        tabHost1.addTab(ts1);

        TabHost.TabSpec ts2 = tabHost1.newTabSpec("TabSpec 1");
        ts2.setContent(R.id.content2);
        ts2.setIndicator("しかる");
        tabHost1.addTab(ts2);

        TabHost.TabSpec ts3 = tabHost1.newTabSpec("TabSpec 1");
        ts3.setContent(R.id.content3);
        ts3.setIndicator("ほめる");
        tabHost1.addTab(ts3);
        return rootView;
    }


    private void requestNecessaryPermissions(String[] permissions) {
        //마시멜로( API 23 )이상에서 런타임 퍼미션(Runtime Permission) 요청
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, PERMISSION_REQUEST_CODE);
        }
    }


    private class DownloadFilesTask extends AsyncTask<String, String, Long> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;

        public DownloadFilesTask(Context context) {
            this.context = context;
        }


        //파일 다운로드를 시작하기 전에 프로그레스바를 화면에 보여줍니다.
        @Override
        protected void onPreExecute() { //2
            super.onPreExecute();

            //사용자가 다운로드 중 파워 버튼을 누르더라도 CPU가 잠들지 않도록 해서
            //다시 파워버튼 누르면 그동안 다운로드가 진행되고 있게 됩니다.
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
            mWakeLock.acquire();

            progressBar.show();
        }


        //파일 다운로드를 진행합니다.
        @Override
        protected Long doInBackground(String... string_url) { //3
            int count;
            long FileSize = -1;
            InputStream input = null;
            OutputStream output = null;
            URLConnection connection = null;

            try {
                URL url = new URL(string_url[0]);
                connection = url.openConnection();
                connection.connect();


                //파일 크기를 가져옴
                FileSize = connection.getContentLength();

                //URL 주소로부터 파일다운로드하기 위한 input stream
                input = new BufferedInputStream(url.openStream(), 8192);

                path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                outputFile= new File(path, "Alight.avi"); //파일명까지 포함함 경로의 File 객체 생성

                // SD카드에 저장하기 위한 Output stream
                output = new FileOutputStream(outputFile);


                byte data[] = new byte[1024];
                long downloadedSize = 0;
                while ((count = input.read(data)) != -1) {
                    //사용자가 BACK 버튼 누르면 취소가능
                    if (isCancelled()) {
                        input.close();
                        return Long.valueOf(-1);
                    }

                    downloadedSize += count;

                    if (FileSize > 0) {
                        float per = ((float)downloadedSize/FileSize) * 100;
                        String str = "Downloaded " + downloadedSize + "KB / " + FileSize + "KB (" + (int)per + "%)";
                        publishProgress("" + (int) ((downloadedSize * 100) / FileSize) , str);

                    }

                    //파일에 데이터를 기록합니다.
                    output.write(data, 0, count);
                }
                // Flush output
                output.flush();

                // Close streams
                output.close();
                input.close();


            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                mWakeLock.release();

            }
            return FileSize;
        }


        //다운로드 중 프로그레스바 업데이트
        @Override
        protected void onProgressUpdate(String... progress) { //4
            super.onProgressUpdate(progress);

            // if we get here, length is known, now set indeterminate to false
            progressBar.setIndeterminate(false);
            progressBar.setMax(100);
            progressBar.setProgress(Integer.parseInt(progress[0]));
            progressBar.setMessage(progress[1]);
            result=Integer.parseInt(progress[0]);

        }

        //파일 다운로드 완료 후
        @Override
        protected void onPostExecute(Long size) { //5
            super.onPostExecute(size);

            progressBar.dismiss();




            if ( size > 0 && result == 100) {
                Intent intent = new Intent(getContext(), CallingPageView.class);
                intent.putExtra(KEY_FATH_DATA,outputFile.toString());
                startActivity(intent);
            }
            else
                Toast.makeText(getContext(), "다운로드 에러", Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int permsRequestCode, String[] permissions, int[] grantResults){
        switch(permsRequestCode){

            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    boolean readAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean writeAccepted = grantResults[1] == PackageManager.PERMISSION_GRANTED;

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                        if ( !readAccepted || !writeAccepted  )
                        {
                            showDialogforPermission("앱을 실행하려면 퍼미션을 허가하셔야합니다.");
                            return;
                        }
                    }
                }
                break;
        }
    }

    private void showDialogforPermission(String msg) {

        final AlertDialog.Builder myDialog = new AlertDialog.Builder(  getContext());
        myDialog.setTitle("알림");
        myDialog.setMessage(msg);
        myDialog.setCancelable(false);
        myDialog.setPositiveButton("예", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(PERMISSIONS, PERMISSION_REQUEST_CODE);
                }

            }
        });
        myDialog.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });
        myDialog.show();
    }



    public void setAdapter(){

    }
}
