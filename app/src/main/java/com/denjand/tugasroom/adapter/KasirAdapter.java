package com.denjand.tugasroom.adapter;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denjand.tugasroom.R;
import com.denjand.tugasroom.crud.kasir.UpdateKasirActivity;
import com.denjand.tugasroom.database.model.Kasir;

import java.util.List;

public class KasirAdapter extends RecyclerView.Adapter<KasirAdapter.KasirViewHolder> {
    private Context mCtx;
    private List<Kasir> kasirList;

    public KasirAdapter(Context mCtx, List<Kasir> kasirList) {
        this.mCtx = mCtx;
        this.kasirList = kasirList;
    }

    @Override
    public KasirAdapter.KasirViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.rv_kasir, parent, false);
        return new KasirAdapter.KasirViewHolder(view);
    }

    @Override
    public void onBindViewHolder( KasirAdapter.KasirViewHolder holder, int position) {
        Kasir k = kasirList.get(position);
        holder.textViewName.setText(k.getName());
        holder.textViewCity.setText(k.getCity());
        holder.textViewGender.setText(k.getGender());
        holder.textViewStatus.setText("Info Data Kasir");
    }

    @Override
    public int getItemCount() {
        return kasirList.size();
    }

    class KasirViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewName, textViewCity, textViewGender, textViewStatus;

        public KasirViewHolder(View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewCity = itemView.findViewById(R.id.textViewCity);
            textViewGender = itemView.findViewById(R.id.textViewGender);
            textViewStatus = itemView.findViewById(R.id.textViewInfo);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Kasir kasir = kasirList.get(getAdapterPosition());

            Intent intent = new Intent(mCtx, UpdateKasirActivity.class);
            intent.putExtra("kasir", kasir);

            mCtx.startActivity(intent);
        }
    }
}
