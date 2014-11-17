package com.example.neyrojas.pmm.Adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.neyrojas.pmm.Modelos.Pais;
import com.example.neyrojas.pmm.R;

import java.util.ArrayList;
/**
 * Created by curso on 14/11/2014.
 */
public class paisAdapter extends ArrayAdapter<Pais> {
    ArrayList<Pais> paisesList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public paisAdapter(Context context, int resource, ArrayList<Pais> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        paisesList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.tvNombre = (TextView) v.findViewById(R.id.nombrePais);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.tvNombre.setText(paisesList.get(position).getNombre().toString());
        return v;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.tvNombre = (TextView) v.findViewById(R.id.nombrePais);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.tvNombre.setText(paisesList.get(position).getNombre().toString());
        return v;
    }

    static class ViewHolder {
        public TextView tvNombre;
    }
}
