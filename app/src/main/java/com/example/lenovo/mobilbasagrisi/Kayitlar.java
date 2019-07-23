package com.example.lenovo.mobilbasagrisi;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lenovo.mobilbasagrisi.Adapter.KayitAdapter;

import java.util.ArrayList;

public class Kayitlar extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kayitlar);

        final DatabaseHelper db = new DatabaseHelper(this);
        final ListView kayitlist = findViewById(R.id.kayitlist);

        KayitAdapter adapter = new KayitAdapter(getApplicationContext(),db.getKayit());
        kayitlist.setAdapter(adapter);

    }


}
