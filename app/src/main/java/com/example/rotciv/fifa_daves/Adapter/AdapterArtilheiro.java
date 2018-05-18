package com.example.rotciv.fifa_daves.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rotciv.fifa_daves.R;
import com.example.rotciv.fifa_daves.model.Confronto;
import com.example.rotciv.fifa_daves.model.Jogador;

import java.util.List;

public class AdapterArtilheiro extends BaseAdapter {
    List<Jogador> listArtilheiro;
    LayoutInflater layout;

    public AdapterArtilheiro(Context context, List<Jogador> listArtilheiro){
        this.listArtilheiro =listArtilheiro;
        layout = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return listArtilheiro.size();
    }

    @Override
    public Object getItem(int position) {
        return listArtilheiro.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listArtilheiro.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layout.inflate(R.layout.linha_artilheiro,parent,false);

        TextView txvJogador = convertView.findViewById(R.id.txvJogador);
        TextView txvTime = convertView.findViewById(R.id.txvTime);

        Jogador jogador = (Jogador)getItem(position);

        txvJogador.setText(jogador.getNome());
        txvTime.setText(jogador.getTime().getNome());

        return convertView;
    }
}