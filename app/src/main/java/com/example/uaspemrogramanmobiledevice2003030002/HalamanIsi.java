package com.example.uaspemrogramanmobiledevice2003030002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
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

import java.util.List;

public class HalamanIsi extends AppCompatActivity {

    Button apply;
    Spinner pilih;
    private TextView latitude, longitude, akurasi;
    private FusedLocationProviderClient locationProviderClient;
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_isi);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        latitude = findViewById(R.id. latitude);
        longitude = findViewById(R.id. longitude);
        akurasi = findViewById(R.id. akurasi);
        locationProviderClient = LocationServices.getFusedLocationProviderClient(HalamanIsi.this);

        apply = (Button) findViewById(R.id.btnya);
        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextapply();
            }
        });
        pilih = (Spinner) findViewById(R.id.spin);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 10) {
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) !=
            PackageManager.PERMISSION_GRANTED) {

            } else { nextapply(); }
        }
    }

    public void nextapply() {
        String Pilihmenu = pilih.getSelectedItem().toString();
        if (Pilihmenu.equals("Jelajahi")) {
            Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ump.ac.id"));
            startActivity(in);
            Toast.makeText(this.getBaseContext(), "Wait...", Toast.LENGTH_SHORT).show();
        } else if (Pilihmenu.equals("Hubungi")) {
            Intent ms = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:085724126979"));
            ms.putExtra("sms_body", "Pesan dari aplikasi Android");
            startActivity(ms);
            Toast.makeText(this, "Berhasil mengirim pesan", Toast.LENGTH_SHORT).show();
        } else if (Pilihmenu.equals("Baca Data")) {
            List<Sensor> deviceSensors = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
            AlertDialog.Builder kotakPesan =
                    new AlertDialog.Builder(HalamanIsi.this);
            kotakPesan.setMessage(
                            deviceSensors       +"\n" );
            kotakPesan.create().show();
        } else if (Pilihmenu.equals("Cek Posisi")) {

            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        android.Manifest.permission.ACCESS_COARSE_LOCATION}, 10);
            } else {
                locationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            latitude.setText(String.valueOf(location.getLatitude()));
                            longitude.setText(String.valueOf(location.getLongitude()));
                            akurasi.setText(location.getAccuracy() + "%");
                            Toast.makeText(getBaseContext(), "Lokasi Terbaca", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Meminta Akses Lokasi", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }
}