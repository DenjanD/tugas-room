package com.denjand.tugasroom.crud.kasir;

import android.content.Intent;
import android.os.AsyncTask;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.denjand.tugasroom.MainActivity;
import com.denjand.tugasroom.R;
import com.denjand.tugasroom.database.DatabaseClient;
import com.denjand.tugasroom.database.model.Kasir;

public class AddKasirActivity extends AppCompatActivity {
    private EditText editTextName, editTextCity, editTextGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_kasir);

        editTextName = findViewById(R.id.tie_nama_kasir_tp);
        editTextCity = findViewById(R.id.tie_city_tp);
        editTextGender = findViewById(R.id.tie_gender_tp);

        findViewById(R.id.btn_simpan_kasir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        final String sName = editTextName.getText().toString().trim();
        final String sCity = editTextCity.getText().toString().trim();
        final String sGender = editTextGender.getText().toString().trim();

        if (sName.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }

        if (sCity.isEmpty()) {
            editTextCity.setError("City required");
            editTextCity.requestFocus();
            return;
        }

        if (sGender.isEmpty()) {
            editTextGender.setError("Gender required");
            editTextGender.requestFocus();
            return;
        }

        class SaveKasir extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Kasir kasir = new Kasir();
                kasir.setName(sName);
                kasir.setCity(sCity);
                kasir.setGender(sGender);

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .kasirDao()
                        .insert(kasir);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                finish();
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
            }
        }
        SaveKasir st = new SaveKasir();
        st.execute();
    }
}
