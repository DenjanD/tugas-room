package com.denjand.tugasroom.crud.product;

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
import com.denjand.tugasroom.database.model.Product;

public class AddProductActivity extends AppCompatActivity {

    private EditText editTextName, editTextStock, editTextPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        editTextName = findViewById(R.id.tie_nama_produk_tp);
        editTextStock = findViewById(R.id.tie_stock_tp);
        editTextPrice = findViewById(R.id.tie_harga_tp);

        findViewById(R.id.btn_simpan_produk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTask();
            }
        });
    }

    private void saveTask() {
        final String sName = editTextName.getText().toString().trim();
        final String sStock = editTextStock.getText().toString().trim();
        final String sPrice = editTextPrice.getText().toString().trim();

        if (sName.isEmpty()) {
            editTextName.setError("Name required");
            editTextName.requestFocus();
            return;
        }

        if (sStock.isEmpty()) {
            editTextStock.setError("Stock required");
            editTextStock.requestFocus();
            return;
        }

        if (sPrice.isEmpty()) {
            editTextPrice.setError("Price required");
            editTextPrice.requestFocus();
            return;
        }

        class SaveProduct extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {

                //creating a task
                Product product = new Product();
                product.setName(sName);
                product.setStock(Integer.parseInt(sStock));
                product.setPrice(Integer.parseInt(sPrice));

                //adding to database
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .productDao()
                        .insert(product);
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

        SaveProduct st = new SaveProduct();
        st.execute();
    }

}