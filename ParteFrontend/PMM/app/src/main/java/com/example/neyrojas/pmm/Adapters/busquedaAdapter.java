package com.example.neyrojas.pmm.Adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.neyrojas.pmm.Modelos.Lugares;
import com.example.neyrojas.pmm.R;

public class busquedaAdapter extends ArrayAdapter<Lugares> {
    ArrayList<Lugares> lugaresList;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public busquedaAdapter(Context context, int resource, ArrayList<Lugares> objects) {
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
            holder.tvNombre = (TextView) v.findViewById(R.id.nombreManual);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.tvNombre.setText(lugaresList.get(position).getNombre());
        return v;
    }

    static class ViewHolder {
        public TextView tvNombre;
    }
}
