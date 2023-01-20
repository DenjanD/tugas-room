package com.denjand.tugasroom;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.denjand.tugasroom.adapter.KasirAdapter;
import com.denjand.tugasroom.crud.kasir.AddKasirActivity;
import com.denjand.tugasroom.database.DatabaseClient;
import com.denjand.tugasroom.database.model.Kasir;

import java.util.List;

public class KasirActivity extends AppCompatActivity {
    private Button buttonAddTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasir);

        recyclerView = findViewById(R.id.rv_kasir);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        buttonAddTask = findViewById(R.id.button_add_kasir);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KasirActivity.this, AddKasirActivity.class);
                startActivity(intent);
            }
        });
        getKasir();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void getKasir() {
        class GetKasir extends AsyncTask<Void, Void, List<Kasir>> {

            @Override
            protected List<Kasir> doInBackground(Void... voids) {
                List<Kasir> kasirList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .kasirDao()
                        .getAll();
                return kasirList;
            }

            @Override
            protected void onPostExecute(List<Kasir> kasir) {
                super.onPostExecute(kasir);
                KasirAdapter adapter = new KasirAdapter(KasirActivity.this, kasir);
                recyclerView.setAdapter(adapter);
            }
        }

        GetKasir gp = new GetKasir();
        gp.execute();
    }
}
