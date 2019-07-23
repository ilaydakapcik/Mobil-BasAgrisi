package com.example.lenovo.mobilbasagrisi.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lenovo.mobilbasagrisi.DbModel.KayitModel;
import com.example.lenovo.mobilbasagrisi.R;

import java.util.ArrayList;
import java.util.List;

public class KayitAdapter extends ArrayAdapter<KayitModel> {

    private final LayoutInflater inflater;
    private final Context context;
    private ViewHolder holder;
    private List<KayitModel> items;

    public KayitAdapter(Context context, List<KayitModel> items) {
        super(context, 0, items);
        this.context = context;
        this.items = items;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.itemkayit, null);
            holder = new ViewHolder();
            holder.textBasTarih = (TextView) convertView.findViewById(R.id.basTarih);
            holder.textBitTarih = (TextView) convertView.findViewById(R.id.bitTarih);
            holder.siddet = (TextView) convertView.findViewById(R.id.siddet);
            holder.tetikleyici = (TextView) convertView.findViewById(R.id.tetikleyici);
            holder.ilac = (TextView) convertView.findViewById(R.id.ilac);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder)convertView.getTag();
        }

        KayitModel model = items.get(position);
        if(model != null){
            holder.textBasTarih.setText(model.getBasTarih());
            holder.textBitTarih.setText(model.getSonTarih());
            holder.siddet.setText(model.getSiddet());
            holder.ilac.setText(model.getIlac());
            holder.tetikleyici.setText(model.getTetikleyici());
        }
        return convertView;
    }
    private static class ViewHolder{
        TextView textBasTarih;
        TextView textBitTarih;
        TextView siddet;
        TextView tetikleyici;
        TextView ilac;
    }

    @Override
    public int getCount() {
        return items.size();
    }
    @Override
    public KayitModel getItem(int position) {
        return items.get(position);
    }

}