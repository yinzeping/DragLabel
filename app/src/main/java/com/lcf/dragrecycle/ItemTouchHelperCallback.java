package com.lcf.dragrecycle;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * 该类工作与ItemTouchHelper和你的app之间，起一个桥梁的作用
 * 主要负责，定义用户drag和swipe的方向，以及当户产生了指定手势会收到相应的回调方法
 */
public class ItemTouchHelperCallback extends ItemTouchCustomHelper.Callback {
    private OnItemPositionChangeListener mListener;

    public ItemTouchHelperCallback(OnItemPositionChangeListener mListener) {
        this.mListener = mListener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final int dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT
                | ItemTouchHelper.UP | ItemTouchHelper.DOWN;


        //can be dragged, can not be swiped
        return makeMovementFlags(dragFlags, 0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        if(target!=null){
            int position = target.getLayoutPosition();
            if(getSelectNoNeedSwipeList()!=null && getSelectNoNeedSwipeList().contains(position)){
                return true;
            }
        }

        if (mListener != null) {
            return mListener.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        }
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    public List<Integer> getSelectNoNeedSwipeList() {
        return mListener!=null?mListener.getSelectNoNeedSwipeList():null;
    }

    public interface OnItemPositionChangeListener {
        boolean onItemMove(int fromPos, int toPos);

        List<Integer> getSelectNoNeedSwipeList();
    }
}
