package test.com.basedemo.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import test.com.basedemo.BaseActivity;
import test.com.basedemo.R;
import test.com.basedemo.adapter.ModeAdapter;
import test.com.basedemo.adapter.baseadapter.GridSpacingItemDecoration;
import test.com.basedemo.refreshrecyvlerview.PullToRefreshRecyclerView;
import test.com.basedemo.refreshrecyvlerview.callback.PullToRefreshListener;

/**
 * Created by Administrator on 2017/5/16.
 */

public class ActivityRefreshRecyclerView extends BaseActivity implements PullToRefreshListener {
    private PullToRefreshRecyclerView recyclerView;
    private List<String> data;
    private ModeAdapter adapter;
    String type;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recyclerview;
    }

    @Override
    protected void initData() {
        type=getIntent().getStringExtra("type");
        data = new ArrayList<>();
        data.add("0000");
        data.add("1111");
        data.add("2222");
        data.add("3333");
        data.add("4444");
        //添加HeaderView
//        recyclerView.addHeaderView(headView);
        //添加FooterView
//        recyclerView.addFooterView(footerView);
        //设置EmptyView
//        View emptyView = View.inflate(this, R.layout.layout_empty_view, null);
//        emptyView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                ViewGroup.LayoutParams.MATCH_PARENT));
//        recyclerView.setEmptyView(emptyView);
        if (type.equals("gridview")) {
            final GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
            recyclerView.addItemDecoration(new GridSpacingItemDecoration(3, getResources().getDimensionPixelSize(R.dimen.padding_middle), true));
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
        }else {
            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        }
        adapter = new ModeAdapter(this, data);
        recyclerView.setAdapter(adapter);
        //设置是否开启上拉加载
        recyclerView.setLoadingMoreEnabled(true);
        //设置是否开启下拉刷新
        recyclerView.setPullRefreshEnabled(true);
        //设置是否显示上次刷新的时间
        recyclerView.displayLastRefreshTime(true);
        //设置刷新回调
        recyclerView.setPullToRefreshListener(this);
        //主动触发下拉刷新操作
        recyclerView.onRefresh();
    }

    @Override
    public void onRefresh() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                recyclerView.setRefreshComplete();
                //模拟没有数据的情况
                data.clear();
                adapter.notifyDataSetChanged();
            }
        }, 3000);
    }

    int i = 0;
    @Override
    public void onLoadMore() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                i++;
                if(i>=4){
                    recyclerView.setLoadMoreFail();
                    return;
                }
                recyclerView.setLoadMoreComplete();
                //模拟加载数据的情况
                int size = data.size();
                for (int i = size; i < size + 4; i++) {
                    data.add("" + i + i + i + i);
                }
                adapter.notifyDataSetChanged();

            }
        }, 3000);
    }

    @Override
    public void onClick(View v) {

    }
}
