package com.example.lenovo.mobilbasagrisi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       TextView baslik=findViewById(R.id.basliktxt);
       Button agri_ekle=findViewById(R.id.agriekle_btn);
       Button kayıt_btn=findViewById(R.id.kayit_btn);



       agri_ekle.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(MainActivity.this,AgriEkle.class);
               startActivity(intent);
           }
       });
        kayıt_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Kayitlar.class);
                startActivity(intent);
            }
        });



    }
}
