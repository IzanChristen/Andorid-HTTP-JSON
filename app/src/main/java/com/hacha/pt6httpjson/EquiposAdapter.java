package com.hacha.pt6httpjson;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;


public class EquiposAdapter extends RecyclerView.Adapter<EquiposAdapter.ViewHolder> {


    ArrayList<String> nombres, codigos;
    String liga;
    Context context;


    public EquiposAdapter(ArrayList<String> n, ArrayList<String> c, String l, Context ctx) {
        nombres = n;
        codigos = c;
        liga = l;
        context = ctx;
    }


    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(v);
    }


    public void onBindViewHolder(ViewHolder h, int pos) {
        h.texto.setText(nombres.get(pos));
        h.itemView.setOnClickListener(v -> {
            Intent i = new Intent(context, DetalleEquipoActivity.class);
            i.putExtra("liga", liga);
            i.putExtra("codigo", codigos.get(pos));
            context.startActivity(i);
        });
    }


    public int getItemCount() {
        return nombres.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView texto;
        ViewHolder(View v) {
            super(v);
            texto = v.findViewById(android.R.id.text1);
        }
    }
}