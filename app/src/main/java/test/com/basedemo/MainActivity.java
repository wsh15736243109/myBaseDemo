package test.com.basedemo;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import test.com.basedemo.activity.ActivityRefreshRecyclerView;
import test.com.basedemo.activity.ActivityRefreshScrollView;
import test.com.basedemo.activity.ActivityTransparentStatusBar;
import test.com.basedemo.widget.TranslucentActionBar;


public class MainActivity extends BaseActivity {
    //申明了就没有了点击事件
    @IsNeedClick
    Button txt1;


    Button btn_refreshRecyclerView;
    Button btn_refreshgridView;
    Button btn_refreshscrollView;
    Button btn_jianbian;
    private TranslucentActionBar actionBar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        actionBar = (TranslucentActionBar) findViewById(R.id.actionbar);
        //设置状态栏高度
        actionBar.setStatusBarHeight(getStatusBarHeight());
        txt1.setText("测试有注解的");
        btn_refreshRecyclerView.setText("测试没有注解的");
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.txt1:

                Toast.makeText(this, "点击了1", 0).show();
                break;
            case R.id.btn_refreshRecyclerView:
                intent = new Intent(this, ActivityRefreshRecyclerView.class);
                intent.putExtra("type","recyclerview");
                startActivity(intent);
                Toast.makeText(this, "点击了2", 0).show();
                break;
            case R.id.btn_refreshgridView:
                intent = new Intent(this, ActivityRefreshRecyclerView.class);
                intent.putExtra("type","gridview");
                startActivity(intent);
                break;
            case R.id.btn_refreshscrollView:
                intent = new Intent(this, ActivityRefreshScrollView.class);
//                intent.putExtra("type","gridview");
                startActivity(intent);
                break;
            case R.id.btn_jianbian:
                intent = new Intent(this, ActivityTransparentStatusBar.class);
//                intent.putExtra("type","gridview");
                startActivity(intent);
                break;
        }
    }
}
