package test.com.basedemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by wu shun hui on 2017/5/16.
 * 2017/5/16 添加自定义注解，修复activty中一旦定义某个View便自动生成点击事件（某些控件我们不想要点击），影响点击
 * 2017/5/16 添加透明状态状态栏
 */

public abstract class BaseActivity extends FragmentActivity implements View.OnClickListener {
    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        initWindow();
        initData();
    }

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
    protected abstract int getLayoutId();

    protected abstract void initData();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        smartInject();// 自动获取控件
    }

    private void smartInject() {

        try {
            Class<? extends Activity> clz = getClass();

            while (clz != BaseActivity.class) {

                Field[] fs = clz.getDeclaredFields();
                Resources res = getResources();
                String packageName = getPackageName();
                for (Field field : fs) {
                    if (!View.class.isAssignableFrom(field.getType())) {
                        continue;
                    }
                    int viewId = res.getIdentifier(field.getName(), "id",
                            packageName);
                    if (viewId == 0)
                        continue;
                    field.setAccessible(true);
                    try {
                        View v = findViewById(viewId);
                        field.set(this, v);
                        Class<?> c = field.getType();
                        //判断是否有注解
                        if (field.getAnnotations() != null) {
                            if (field.isAnnotationPresent(IsNeedClick.class)) {//如果属于这个注解
                                //为这个控件设置属性
                                field.setAccessible(true);//允许修改反射属性
                                IsNeedClick inject = field.getAnnotation(IsNeedClick.class);
//                                int value=inject.value();//得到注解的值
//                                if(value==-1){
//
//                                }
                            }else{
                                Method m = c.getMethod("setOnClickListener",
                                        View.OnClickListener.class);
                                m.invoke(v, this);
                            }
                        }else {
//                            Method m = c.getMethod("setOnClickListener",
//                                    View.OnClickListener.class);
//                            m.invoke(v, this);
                        }
                    } catch (Throwable e) {
                    }
                    field.setAccessible(false);

                }

                clz = (Class<? extends Activity>) clz.getSuperclass();

            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
