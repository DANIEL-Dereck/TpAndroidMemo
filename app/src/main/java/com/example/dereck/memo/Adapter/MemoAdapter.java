package com.example.dereck.memo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dereck.memo.DAO.MemoDao;
import com.example.dereck.memo.Entity.Memo;
import com.example.dereck.memo.R;
import com.example.dereck.memo.ViewHolder.MemoViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoAdapter extends RecyclerView.Adapter<MemoViewHolder>
{
    private MemoDao memoDao;
    List<Memo> memos;

    public MemoAdapter()
    {
        this.memos = new ArrayList<>();
        this.memoDao = new MemoDao();
    }

    public MemoAdapter(List<Memo> memoList)
    {
        this.memoDao = new MemoDao();
        this.memoDao.insert(memoList);
        this.memos = memoList;
    }

    @Override
    public MemoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewMemo = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_item, parent, false);
        return new MemoViewHolder(viewMemo);
    }

    @Override
    public void onBindViewHolder(MemoViewHolder holder, int position) {
//        holder.textViewLibelle.setText(this.memoDao.selectById(position + 1).getText());
        holder.textViewLibelle.setText(this.memos.get(position).getText());
    }

    @Override
    public int getItemCount()
    {
//        return this.memoDao.countAll();
        return  this.memos.size();
    }

    public boolean onItemMove(int positionDebut, int positionFin)
    {
//        Collections.swap(this.memoDao.selectAll(), positionDebut, positionFin);
        Collections.swap(this.memos, positionDebut, positionFin);

        this.notifyItemMoved(positionDebut, positionFin);
        return true;
    }

    public void onItemDismiss(int position)
    {
        if (position > -1)
        {
//            this.memoDao.deleteById(position + 1);
            this.memos.remove(position);
            this.notifyItemRemoved(position);
        }
    }
}
