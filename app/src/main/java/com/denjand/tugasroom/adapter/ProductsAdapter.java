package com.denjand.tugasroom.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denjand.tugasroom.R;
import com.denjand.tugasroom.crud.product.UpdateProductActivity;
import com.denjand.tugasroom.database.model.Product;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {

    private Context mCtx;
    private List<Product> productList;

    public ProductsAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recyclerview_tasks, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsViewHolder holder, int position) {
        Product p = productList.get(position);
        holder.textViewName.setText(p.getName());
        holder.textViewStock.setText(String.valueOf(p.getStock()));
        holder.textViewPrice.setText(String.valueOf(p.getPrice()));
        holder.textViewStatus.setText("Info Produk");
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewName, textViewStock, textViewPrice, textViewStatus;

        public ProductsViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewStock = itemView.findViewById(R.id.textViewStock);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            textViewStatus = itemView.findViewById(R.id.textViewInfo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Product product = productList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateProductActivity.class);
            intent.putExtra("product", product);

            mCtx.startActivity(intent);
        }
    }
}