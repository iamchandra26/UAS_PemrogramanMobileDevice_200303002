package com.example.uaspemrogramanmobiledevice2003030002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

public class HalamanIsi extends AppCompatActivity {

    Button apply;
    Spinner pilih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_isi);

        apply = (Button) findViewById(R.id.btnya);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextapply();
            }
        });
        pilih = (Spinner) findViewById(R.id.spin);
    }

    public void nextapply() {
        String Pilihmenu = pilih.getSelectedItem().toString();
        if (Pilihmenu.equals("Jelajahi")) {
            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://wwww.ump.ac.id"));
            startActivity(in);
            Toast.makeText(this.getBaseContext(), "Wait...", Toast.LENGTH_SHORT).show();
        } else if (Pilihmenu.equals("Hubungi")) {
            Intent ms = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:085742587734"));
            ms.putExtra("sms_body", "Pesan dari aplikasi Android");
            startActivity(ms);
            Toast.makeText(this, "Berhasil mengirim pesan", Toast.LENGTH_SHORT).show();
        } else if (Pilihmenu.equals("Baca Data")) {

        } else if (Pilihmenu.equals("Cek Posisi")) {

        }
    }
}