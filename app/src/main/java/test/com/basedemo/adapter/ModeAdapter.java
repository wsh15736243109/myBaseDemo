package test.com.basedemo.adapter;

import android.content.Context;

import java.util.List;

import test.com.basedemo.R;
import test.com.basedemo.adapter.baseadapter.BaseAdapter;
import test.com.basedemo.adapter.baseadapter.ViewHolder;


/**
 * Created by Kun on 2016/12/14.
 * GitHub: https://github.com/AndroidKun
 * CSDN: http://blog.csdn.net/a1533588867
 * Description:模版
 */

public class ModeAdapter extends BaseAdapter {

    public ModeAdapter(Context context, List datas) {
        super(context, R.layout.item_mode, datas);
    }

    @Override
    public void convert(ViewHolder holder, Object o) {
        holder.setText(R.id.textView,(String)o);
    }
}
