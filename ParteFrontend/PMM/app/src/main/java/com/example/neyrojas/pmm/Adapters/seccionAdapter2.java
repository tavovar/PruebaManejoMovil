package com.example.neyrojas.pmm.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.neyrojas.pmm.Modelos.SeccionesManual;
import com.example.neyrojas.pmm.R;

import java.util.ArrayList;

/**
 * Created by curso on 12/11/2014.
 */
public class seccionAdapter2 extends ArrayAdapter<SeccionesManual> {
    ArrayList<SeccionesManual> List;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public seccionAdapter2(Context context, int resource, ArrayList<SeccionesManual> objects) {
        super(context, resource, objects);
        vi = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        List = objects;
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
        holder.tvNombre.setText("Capitulo " + List.get(position).getIndice());
        return v;
    }

    static class ViewHolder {
        public TextView tvNombre;
    }
}
