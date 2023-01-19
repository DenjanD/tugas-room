package com.denjand.tugasroom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_pesan;
    private Button btn_history;
    private Button btn_perbarui;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        btn_pesan = (Button) findViewById(R.id.btn_pesan);
        btn_pesan.setOnClickListener(this);
        btn_history = (Button) findViewById(R.id.btn_history);
        btn_history.setOnClickListener(this);
        btn_perbarui = (Button) findViewById(R.id.btn_perbarui);
        btn_perbarui.setOnClickListener(this);

    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.btn_pesan:
                Intent i1 = new Intent(this, TransaksiActivity.class);
                startActivity(i1);
                break;
//            case R.id.btn_history:
//                Intent i2 = new Intent(this, RiwayatTransaksi.class);
//                startActivity(i2);
//                break;
            case R.id.btn_perbarui:
                Intent i3 = new Intent(this, ProductActivity.class);
                startActivity(i3);
                break;

        }
    }
}