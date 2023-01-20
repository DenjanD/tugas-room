package com.denjand.tugasroom.crud.kasir;

import android.os.Bundle;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.denjand.tugasroom.MainActivity;
import com.denjand.tugasroom.R;
import com.denjand.tugasroom.crud.kasir.UpdateKasirActivity;
import com.denjand.tugasroom.database.DatabaseClient;
import com.denjand.tugasroom.database.model.Kasir;
import com.denjand.tugasroom.database.model.Product;

public class UpdateKasirActivity extends AppCompatActivity {
    private EditText editTextName, editTextCity, editTextGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kasir);

        editTextName = findViewById(R.id.tie_nama_kasir_tp);
        editTextCity = findViewById(R.id.tie_city_tp);
        editTextGender = findViewById(R.id.tie_gender_tp);

        final Kasir kasir = (Kasir) getIntent().getSerializableExtra("kasir");

        loadKasir(kasir);

        findViewById(R.id.btn_simpan_kasir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updateTask(kasir);
            }
        });

        findViewById(R.id.btn_hapus_kasir).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateKasirActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(kasir);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                AlertDialog ad = builder.create();
                ad.show();
            }
        });
    }

    private void loadKasir(Kasir kasir) {
        editTextName.setText(kasir.getName());
        editTextCity.setText(String.valueOf(kasir.getCity()));
        editTextGender.setText(String.valueOf(kasir.getGender()));
    }

    private void updateTask(final Kasir kasir) {
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

        class UpdateKasir extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                kasir.setName(sName);
                kasir.setCity(sCity);
                kasir.setGender(sGender);
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .kasirDao()
                        .update(kasir);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateKasirActivity.this, MainActivity.class));
            }
        }

        UpdateKasir up = new UpdateKasir();
        up.execute();
    }

    private void deleteTask(final Kasir kasir) {
        class DeleteKasir extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .kasirDao()
                        .delete(kasir);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateKasirActivity.this, MainActivity.class));
            }
        }

        DeleteKasir dt = new DeleteKasir();
        dt.execute();

    }
}
