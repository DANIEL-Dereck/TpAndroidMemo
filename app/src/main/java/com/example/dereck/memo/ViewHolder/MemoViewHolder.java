package com.example.dereck.memo.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.dereck.memo.R;

public class MemoViewHolder extends RecyclerView.ViewHolder
{
    public TextView textViewLibelle;

    public MemoViewHolder(View itemView)
    {
        super(itemView);
        this.textViewLibelle = itemView.findViewById(R.id.libelle_item);
    }
}
