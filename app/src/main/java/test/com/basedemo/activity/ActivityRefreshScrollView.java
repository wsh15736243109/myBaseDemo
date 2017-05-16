package test.com.basedemo.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import test.com.basedemo.BaseActivity;
import test.com.basedemo.R;
import test.com.basedemo.pullrefreshscrollview.PullToRefreshBase;
import test.com.basedemo.pullrefreshscrollview.PullToRefreshScrollView;

public class ActivityRefreshScrollView extends BaseActivity implements PullToRefreshBase.OnRefreshListener<ScrollView> {

    PullToRefreshScrollView scrollView;
    ScrollView mScrollView;
    @Override
    protected void initData() {
        // 上拉加载不可用
        scrollView.setPullLoadEnabled(true);
//        scrollView.set;
        // 滚动到底自动加载可用
        scrollView.setScrollLoadEnabled(true);
        //进入即自动刷新
        scrollView.doPullRefreshing(true,100);
        scrollView.setOnRefreshListener(this);
        mScrollView=scrollView.getRefreshableView();
        mScrollView.addView(createTextView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_refresh_scroll_view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        stopLoad();
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
        stopLoad();
    }

    private TextView createTextView() {
        TextView textView = new TextView(this);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 200; ++i) {
            sb.append(String.format(" %03d", i)).append("\n");
        }

        textView.setText(sb.toString());
        textView.setTextColor(Color.WHITE);
        textView.setTextColor(Color.BLACK);
        textView.setTextSize(18);

        return textView;
    }
    //停止加载
    public void stopLoad(){
        scrollView.onPullDownRefreshComplete();
        scrollView.onPullUpRefreshComplete();
    }
}
