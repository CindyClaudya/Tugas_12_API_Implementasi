package com.e.uts_aplikasi_krs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // dataDosenService = RetrofitClient.getRetrofitInstance().create(DataDosenService.class); // ambil data

        this.getSupportActionBar().hide();

        Button btnSignIn1 = (Button)findViewById(R.id.btnLogin);
        btnSignIn1.setOnClickListener(myBtnLoginClick);
    }




    private View.OnClickListener myBtnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SharedPreferences prefs = MainActivity.this.getSharedPreferences("prefs_file",MODE_PRIVATE);

            String statusLogin = prefs.getString("isLogin",null);
            SharedPreferences.Editor edit = prefs.edit();

            TextView emailText = findViewById(R.id.editTextid);
            if (emailText.getText().toString().contains("@si.ukdw.ac.id")){
                edit.putString("isLogin" , "Mahasiswa");
                edit.commit();
                Intent intent = new Intent(MainActivity.this, Dosen.class);
                startActivity(intent);
            }else if(emailText.getText().toString().contains("@staff.ukdw.ac.id")){
                edit.putString("isLogin","Admin");
                edit.commit();
                Intent intent = new Intent(MainActivity.this, Admin.class);
                startActivity(intent);
            }else {
                Toast toast = Toast.makeText(getApplicationContext(),"Bukan Email UKDW",Toast.LENGTH_SHORT);
                toast.setMargin(100,100);
                toast.show();
            }

        }
    };



}