package com.example.froze.threadbasic_asynctest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView percent;
    Button btnDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        //progressBar.setMax(바이트로 쓰고 싶으면 바이트로);
        percent = (TextView)findViewById(R.id.textView);
        btnDownload = (Button)findViewById(R.id.buttonDownload);
        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // AsyncTask 동작함...
                new DownloadTask().execute(100);
            }
        });
    }
    //      1.    2.    3.
    class DownloadTask extends AsyncTask<Integer, Integer, String> {    //없으면 Void
        // 1. doinBackground의 parameter type
        // 2. onProgressUpdate의 parameter type
        // 3. onPostExecute의 parameter type

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Integer... params) {
            int max = params[0];
            try {
            for(int i=0;i<=max;i++){
                    publishProgress(i);
                    Thread.sleep(100);
                }
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Finish";
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(aVoid);
            Log.i("tag","Result :" + s);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            //super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
            percent.setText(values[0]+" %");
        }
    }
}
