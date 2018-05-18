package com.example.rotciv.fifa_daves.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rotciv.fifa_daves.Dao.TimeDAO;
import com.example.rotciv.fifa_daves.Dao.UsuarioDAO;
import com.example.rotciv.fifa_daves.R;
import com.example.rotciv.fifa_daves.model.Confronto;
import com.example.rotciv.fifa_daves.model.Time;

import java.util.List;

public class AdapterConfronto extends BaseAdapter {
    List<Confronto> listConfronto;
    LayoutInflater layout;

    public AdapterConfronto(Context context, List<Confronto> listConfronto){
        this.listConfronto =listConfronto;
        layout = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return listConfronto.size();
    }

    @Override
    public Object getItem(int position) {
        return listConfronto.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listConfronto.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layout.inflate(R.layout.linha_confronto,parent,false);

        TextView txtTime1 = convertView.findViewById(R.id.txtTime1);
        TextView txtTime2 = convertView.findViewById(R.id.txtTime2);

        Confronto confronto = (Confronto)getItem(position);

        txtTime1.setText(confronto.getUsuario1().getTime().getNome());
        txtTime2.setText(confronto.getUsuario2().getTime().getNome());


        return convertView;

    }
}