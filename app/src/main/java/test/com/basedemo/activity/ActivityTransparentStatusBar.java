package test.com.basedemo.activity;

import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import test.com.basedemo.BaseActivity;
import test.com.basedemo.R;
import test.com.basedemo.widget.TranslucentActionBar;
import test.com.basedemo.widget.TranslucentScrollView;

public class ActivityTransparentStatusBar extends BaseActivity implements TranslucentScrollView.TranslucentChangedListener {


    private TranslucentActionBar actionBar;//可渐变的标题栏
    private TranslucentScrollView pullzoom_scrollview;//添加滑动监听的滑动组件
    Button btn_zoom;
    RelativeLayout lay_actionbar_left;
    RelativeLayout lay_actionbar_right;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_transparent_status_bar;
    }

    @Override
    protected void initData() {
        //初始actionBar
        actionBar.setData("个人中心", 0, "左边", R.mipmap.ic_right_gray, "右边", null);
        //开启渐变
        actionBar.setNeedTranslucent();
        //设置状态栏高度
        actionBar.setStatusBarHeight(getStatusBarHeight());

        //设置透明度变化监听
        pullzoom_scrollview.setTranslucentChangedListener(this);
        //关联需要渐变的视图
        pullzoom_scrollview.setTransView(actionBar);

        //关联伸缩的视图
        pullzoom_scrollview.setPullZoomView(btn_zoom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lay_actionbar_right:
                Toast.makeText(this, "right", Toast.LENGTH_SHORT).show();
                break;
            case R.id.lay_actionbar_left:
                Toast.makeText(this, "left", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onTranslucentChanged(int transAlpha) {
        actionBar.tvTitle.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
    }
}
