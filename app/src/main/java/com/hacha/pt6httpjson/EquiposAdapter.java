package com.hacha.pt6httpjson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EquiposAdapter extends RecyclerView.Adapter<EquiposAdapter.ViewHolder> {

    ArrayList<String> lista;

    public EquiposAdapter(ArrayList<String> lista) {
        this.lista = lista;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder h, int pos) {
        h.texto.setText(lista.get(pos));
    }

    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView texto;
        ViewHolder(View v) {
            super(v);
            texto = v.findViewById(android.R.id.text1);
        }
    }
}
