package com.example.uaspemrogramanmobiledevice2003030002;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class HalamanIsi extends AppCompatActivity {

    Button apply;
    Spinner pilih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_isi);

        apply = (Button) findViewById(R.id. btnya);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextapply();
            }
        });

        Spinner pilih = (Spinner) findViewById(R.id. spin);
        String[] Pilihanmenu = {
                "Jelajahi" , "Hubungi" , "Baca Data" , "Cek Posisi" };
        ArrayAdapter<String> arrayAdapterbahasa = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, Pilihanmenu );
        pilih.setAdapter(arrayAdapterbahasa);
    }

    public void nextapply() {

    }
}