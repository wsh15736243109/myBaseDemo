package test.com.basedemo;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends BaseActivity {
    @IsNeedClick
    Button txt1;
    Button txt2;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        txt1.setText("测试有注解的");
        txt2.setText("测试没有注解的");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txt1:
                Toast.makeText(this, "点击了", 0).show();
                break;
            case R.id.txt2:
                Toast.makeText(this, "点击了", 0).show();
                break;
        }
    }
}
