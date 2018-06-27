package com.example.dereck.memo.Helper;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.dereck.memo.Adapter.MemoAdapter;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback
{
    private MemoAdapter memoAdapter;

    public ItemTouchHelperCallback()
    {

    }

    public ItemTouchHelperCallback(MemoAdapter memoAdapter)
    {
        this.memoAdapter = memoAdapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlagsUpDown = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int dragFlagsLeftRight = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlagsUpDown, dragFlagsLeftRight);    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        this.memoAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        this.memoAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
