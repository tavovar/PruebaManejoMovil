package com.example.neyrojas.pmm.Adapters;


import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.neyrojas.pmm.Modelos.Lugares;
import com.example.neyrojas.pmm.R;

/**
 * Created by curso on 31/10/2014.
 */
public class lugarAdapter extends ArrayAdapter<Lugares> {
    ArrayList<Lugares> lugaresList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public lugarAdapter(Context context, int resource, ArrayList<Lugares> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        lugaresList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);
            holder.tvNombre = (TextView) v.findViewById(R.id.nombreLugar);
            holder.tvDescripcion = (TextView) v.findViewById(R.id.descripcionLugar);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.tvNombre.setText(lugaresList.get(position).getNombre());
        holder.tvDescripcion.setText(lugaresList.get(position).getLugar());
        return v;
    }

    static class ViewHolder {
        public TextView tvNombre;
        public TextView tvDescripcion;
    }
}
