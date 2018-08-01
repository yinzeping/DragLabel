package com.lcf.dragrecycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecycleSelected;
    private RecyclerView mRecycleUnSelected;
    private List<String> mSelectedDatas;
    private List<String> mUnselectedDatas;
    private SelectedRecycleAdapter mSelectedAdatper;
    private UnSelectedRecycleAdapter mUnSelectedAdatper;
    private ItemTouchCustomHelper mItemTouchHelper;

    private TextView mFinishedText;

    private ArrayList<Integer> mSelectNoNeedSwipeList;

    private boolean isAddIconsShow = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecycleSelected = (RecyclerView) findViewById(R.id.recycle_selected);
        mRecycleUnSelected = (RecyclerView) findViewById(R.id.recycle_unselected);
        mFinishedText = (TextView) findViewById(R.id.tv_finish);

        initData();
        initView();
        initEvent();
    }

    private void initView() {
        mSelectNoNeedSwipeList = new ArrayList<Integer>();
        mSelectNoNeedSwipeList.add(0);
        mSelectNoNeedSwipeList.add(1);
        mSelectNoNeedSwipeList.add(2);

        mRecycleSelected.setLayoutManager(new GridLayoutManager(this, 4));
        mSelectedAdatper = new SelectedRecycleAdapter(this, mSelectedDatas);
        mSelectedAdatper.setSelectNoNeedSwipeList(mSelectNoNeedSwipeList);
        mRecycleSelected.setAdapter(mSelectedAdatper);
        mRecycleSelected.addItemDecoration(new SpaceItemDecoration(8));

        mRecycleUnSelected.setLayoutManager(new GridLayoutManager(this, 4));
        mUnSelectedAdatper = new UnSelectedRecycleAdapter(this, mUnselectedDatas);
        mRecycleUnSelected.setAdapter(mUnSelectedAdatper);
        mRecycleUnSelected.addItemDecoration(new SpaceItemDecoration(8));

    }

    private void initEvent() {
        //初始化ItemTouchHelper实例
        ItemTouchHelperCallback callback = new ItemTouchHelperCallback(mSelectedAdatper);
        mItemTouchHelper = new ItemTouchCustomHelper(callback);
        //mItemTouchHelper关联RecyclerView
        mItemTouchHelper.attachToRecyclerView(mRecycleSelected);

        mRecycleSelected.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        mSelectedAdatper.setOnItemClickListener(new SelectedRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClickListener(SelectedRecycleAdapter.MyViewHolder viewHolder, int pos) {
                if (!mSelectedAdatper.isDeleteIconsShow()) {
                    Toast.makeText(MainActivity.this, mSelectedDatas.get(pos), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onItemLongClickListener(SelectedRecycleAdapter.MyViewHolder viewHolder, int pos) {
                if (!mSelectedAdatper.isDeleteIconsShow()) {
//                    showAllDeleteIcons();
                    mSelectedAdatper.setDeleteIconsShow(true);
                    mSelectedAdatper.notifyDataSetChanged();

                    mFinishedText.setVisibility(View.VISIBLE);
                }
            }
        });

        mSelectedAdatper.setOnDeleteIconClickListener(new SelectedRecycleAdapter.OnDeleteIconClickListener() {
            @Override
            public void onDeleteIconClick(int pos) {
                mUnSelectedAdatper.addData(mSelectedDatas.get(pos), mUnselectedDatas.size());
                mSelectedAdatper.removeData(pos);
            }
        });


        mFinishedText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSelectedAdatper.setDeleteIconsShow(false);
                mSelectedAdatper.notifyDataSetChanged();
//                hideAllDeleteIcons();
                mFinishedText.setVisibility(View.INVISIBLE);
            }
        });


        mUnSelectedAdatper.setOnItemClickListener(new UnSelectedRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(UnSelectedRecycleAdapter.MyViewHolder holder, int pos) {
                mSelectedAdatper.addData(mUnselectedDatas.get(pos), mSelectedDatas.size());
                mUnSelectedAdatper.removeData(pos);
            }

            @Override
            public void onItemLongClickListener(UnSelectedRecycleAdapter.MyViewHolder viewHolder, int pos) {
            }
        });

    }

    private void initData() {
        mSelectedDatas = new ArrayList<String>();
        mSelectedDatas.add("头条");
        mSelectedDatas.add("娱乐");
        mSelectedDatas.add("精选");
        mSelectedDatas.add("热点");
        mSelectedDatas.add("体育");
        mSelectedDatas.add("网易号");
        mSelectedDatas.add("直播");
        mSelectedDatas.add("财经");
        mSelectedDatas.add("科技");
        mSelectedDatas.add("房产");
        mSelectedDatas.add("汽车");
        mSelectedDatas.add("轻松一刻");
        mSelectedDatas.add("跟帖");
        mSelectedDatas.add("图片");
        mSelectedDatas.add("段子");
        mSelectedDatas.add("家具");
        mSelectedDatas.add("游戏");
        mSelectedDatas.add("健康");
        mSelectedDatas.add("政务");
        mSelectedDatas.add("漫画");
        mSelectedDatas.add("中国足球");
        mSelectedDatas.add("数码");
        mSelectedDatas.add("趣闻");

        mUnselectedDatas = new ArrayList<String>();
        mUnselectedDatas.add("NBA");
        mUnselectedDatas.add("社会");
        mUnselectedDatas.add("军事");
        mUnselectedDatas.add("欧洲杯");
        mUnselectedDatas.add("CBA");
        mUnselectedDatas.add("跑步");
        mUnselectedDatas.add("移动互联");
        mUnselectedDatas.add("云课堂");
        mUnselectedDatas.add("房产");
        mUnselectedDatas.add("旅游");
        mUnselectedDatas.add("读书");
        mUnselectedDatas.add("酒香");
        mUnselectedDatas.add("教育");
        mUnselectedDatas.add("亲子");
        mUnselectedDatas.add("暴雪游戏");
        mUnselectedDatas.add("态度营销");
        mUnselectedDatas.add("时尚");
        mUnselectedDatas.add("情感");
        mUnselectedDatas.add("艺术");
        mUnselectedDatas.add("海外");
        mUnselectedDatas.add("博客");
        mUnselectedDatas.add("论坛");
        mUnselectedDatas.add("型男");
        mUnselectedDatas.add("萌宠");
    }

//    /**
//     * 显示出所有的删除图标
//     */
//    private void showAllDeleteIcons() {
//        int count = mRecycleSelected.getChildCount();
//        for (int i = 0; i < count; i++) {
//            View child = mRecycleSelected.getChildAt(i);
//            ImageView delete = (ImageView) child.findViewById(R.id.delelte);
//            delete.setVisibility(View.VISIBLE);
//        }
//
//        isDeleteIconsShow = true;
//    }
//
//    /**
//     * 隐藏所有的删除图标
//     */
//    private void hideAllDeleteIcons() {
//        int count = mRecycleSelected.getChildCount();
//        for (int i = 0; i < count; i++) {
//            View child = mRecycleSelected.getChildAt(i);
//            ImageView delete = (ImageView) child.findViewById(R.id.delelte);
//            delete.setVisibility(View.INVISIBLE);
//        }
//
//        isDeleteIconsShow = false;
//    }
}
