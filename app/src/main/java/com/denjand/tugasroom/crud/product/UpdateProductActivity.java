package com.denjand.tugasroom.crud.product;

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
import com.denjand.tugasroom.database.DatabaseClient;
import com.denjand.tugasroom.database.model.Product;

public class UpdateProductActivity extends AppCompatActivity {

    private EditText editTextName, editTextStock, editTextPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        editTextName = findViewById(R.id.tie_nama_produk_tp);
        editTextStock = findViewById(R.id.tie_stock_tp);
        editTextPrice = findViewById(R.id.tie_harga_tp);

        final Product product = (Product) getIntent().getSerializableExtra("product");

        loadProduct(product);

        findViewById(R.id.btn_simpan_produk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Clicked", Toast.LENGTH_LONG).show();
                updateTask(product);
            }
        });

        findViewById(R.id.btn_hapus_produk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(UpdateProductActivity.this);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteTask(product);
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

    private void loadProduct(Product product) {
        editTextName.setText(product.getName());
        editTextStock.setText(String.valueOf(product.getStock()));
        editTextPrice.setText(String.valueOf(product.getPrice()));
    }

    private void updateTask(final Product product) {
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

        class UpdateProduct extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                product.setName(sName);
                product.setStock(Integer.parseInt(sStock));
                product.setPrice(Integer.parseInt(sPrice));
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .productDao()
                        .update(product);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateProductActivity.this, MainActivity.class));
            }
        }

        UpdateProduct up = new UpdateProduct();
        up.execute();
    }


    private void deleteTask(final Product product) {
        class DeleteProduct extends AsyncTask<Void, Void, Void> {

            @Override
            protected Void doInBackground(Void... voids) {
                DatabaseClient.getInstance(getApplicationContext()).getAppDatabase()
                        .productDao()
                        .delete(product);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_LONG).show();
                finish();
                startActivity(new Intent(UpdateProductActivity.this, MainActivity.class));
            }
        }

        DeleteProduct dt = new DeleteProduct();
        dt.execute();

    }

}