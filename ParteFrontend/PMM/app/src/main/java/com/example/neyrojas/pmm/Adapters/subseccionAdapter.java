package com.example.neyrojas.pmm.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.neyrojas.pmm.Modelos.SubseccionesManual;
import com.example.neyrojas.pmm.R;

import java.util.ArrayList;

/**
 * Created by Ney Rojas on 15/11/2014.
 */
public class subseccionAdapter extends ArrayAdapter<SubseccionesManual> {
    ArrayList<SubseccionesManual> List;
    LayoutInflater vi;
    int Resource;
    ViewHolder holder;

    public subseccionAdapter(Context context, int resource, ArrayList<SubseccionesManual> objects) {
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
            holder.tvNombre1 = (TextView) v.findViewById(R.id.nombreSubseccion);
            holder.tvTexto = (TextView) v.findViewById(R.id.textoSubseccion);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        //
        holder.tvTexto.setText(List.get(position).getTexto());
        holder.tvNombre1.setText(List.get(position).getNombre());
        return v;
    }

    static class ViewHolder {
        public TextView tvNombre1;
        public TextView tvTexto;
    }
}
