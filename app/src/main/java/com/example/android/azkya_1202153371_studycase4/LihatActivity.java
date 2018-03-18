package com.example.android.azkya_1202153371_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class LihatActivity extends AppCompatActivity {
    //menyimpan data dari list nama-nama mahasiswa yang ingin ditampilkan
    private String[] mahasiswa = {
            "Kim Jong Un",
            "Lee Min Ho",
            "Kim Tae Hun",
            "Choi Tae Jun",
            "Jung Il Woo",
            "Azkya",
            "Joy",
            "Irene",
            "Dadang Konelo",
            "Mimi Peri",
            "Zayn Malik",
            "Ely Sugigi",
            "Cak Lontong",
            "Uvuvwevwe",
            "To Ming Tse"};
    private ListView listView;
    private Button btnStart;
    private ProgressBar mProgressBar;
    private MyTask mytask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat);
        //mengambil id dari layout
        listView = (ListView) findViewById(R.id.list);
        btnStart = (Button) findViewById(R.id.button);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        listView.setVisibility(View.GONE);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mytask = new MyTask();
                mytask.execute();
            }
        });
    }

    public class MyTask extends AsyncTask<Void, String, Void> {
        private ArrayAdapter<String> mAdapter;
        private int counter = 1;
        ProgressDialog mProgressDialog = new ProgressDialog(LihatActivity.this);

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) listView.getAdapter();
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Please wait....");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int which) {
                    mytask.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialogInterface.dismiss();
                }
            });
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : mahasiswa) {
                publishProgress(item);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (isCancelled()) {
                    mytask.cancel(true);
                }
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);
            Integer current_status = (int) ((counter / (float) mahasiswa.length) * 100);
            mProgressBar.setProgress(current_status);
            mProgressDialog.setProgress(current_status);
            mProgressDialog.setMessage(String.valueOf(current_status + "%"));
            counter++;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressBar.setVisibility(View.GONE);
            //remove progress dialog
            mProgressDialog.dismiss();
            listView.setVisibility(View.VISIBLE);
        }
    }

}
