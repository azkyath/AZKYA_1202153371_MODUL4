package com.example.android.azkya_1202153371_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //mengambil id button-button
        button1 = (Button)findViewById(R.id.button_lihat);
        button2 = (Button)findViewById(R.id.button_cari);
    }
    //untuk mengarahkan ke tampilan menu list mahasiswa
    public void menu_lihat (View view){
        Intent intent = new Intent(this,LihatActivity.class);
        startActivity(intent);
    }
    //untuk mengarahkan ke tampilan menu cari gambar
    public  void  menu_cari (View view){
        Intent intent = new Intent(this,CariActivity.class);
        startActivity(intent);
    }
}
