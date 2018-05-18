package com.example.rotciv.fifa_daves.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rotciv.fifa_daves.R;
import com.example.rotciv.fifa_daves.model.Usuario;

import java.util.List;

public class AdapterRanking extends BaseAdapter {
    List<Usuario> listUsuario;
    LayoutInflater layout;

    public AdapterRanking(Context context, List<Usuario> listUsuario){
        this.listUsuario =listUsuario;
        layout = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return listUsuario.size();
    }

    @Override
    public Object getItem(int position) {
        return listUsuario.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  listUsuario.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layout.inflate(R.layout.linha_ranking,parent,false);

        TextView txvAmigos = convertView.findViewById(R.id.txvAmigos);
        TextView txvPontos = convertView.findViewById(R.id.txvPontos);

        Usuario usuario = (Usuario) getItem(position);

        txvAmigos.setText(usuario.getNome());
        txvPontos.setText(String.valueOf(usuario.getPonto()));

        return convertView;
    }
}