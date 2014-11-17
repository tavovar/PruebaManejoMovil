package com.example.gustavovargas.movilv20.Adater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gustavovargas.movilv20.Modelos.Resultado;
import com.example.gustavovargas.movilv20.R;

import java.util.ArrayList;

/**
 * Created by gustavovargas on 15/11/14.
 */
public class ResultadoAdapter extends ArrayAdapter<Resultado> {

        ArrayList<Resultado> resultadosList;
        LayoutInflater vi;
        int Resource;
        ViewHolder holder;

        public ResultadoAdapter(Context context, int resource, ArrayList<Resultado> objects) {
            super(context, resource, objects);
            vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Resource = resource;
            resultadosList = objects;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // convert view = design
            View v = convertView;
            if (v == null) {
                holder = new ViewHolder();
                v = vi.inflate(Resource, null);
                holder.correctas = (TextView) v.findViewById(R.id.lbl_correctoAdapter);
                holder.incorrectas = (TextView) v.findViewById(R.id.lbl_incorrectoAdapter);
                holder.dia = (TextView) v.findViewById(R.id.lbl_diaAdapter);
                holder.tipoTest = (TextView) v.findViewById(R.id.lbl_tipoTestAdapter);
                holder.nota = (TextView) v.findViewById(R.id.lbl_notaAdapter);
                v.setTag(holder);
            } else {
                holder = (ViewHolder) v.getTag();
            }
            holder.nota.setText(resultadosList.get(position).nota);
            holder.tipoTest.setText(resultadosList.get(position).tipoTest);
            holder.dia.setText(resultadosList.get(position).fecha.substring(0,10));
            holder.incorrectas.setText(resultadosList.get(position).incorrectas);
            holder.correctas.setText(resultadosList.get(position).correctas);
            return v;
        }

        static class ViewHolder {
            public TextView nota;
            public TextView tipoTest;
            public TextView dia;
            public TextView correctas;
            public TextView incorrectas;
        }

}
