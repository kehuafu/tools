package khf.edu.mytools.module.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import khf.edu.mytools.R;
import khf.edu.mytools.module.bean.SchoolBeanShell;

public class SchoolAdapter extends BaseAdapter {

    private LinkedList<SchoolBeanShell.SchoolBean> mData;
    private Context context;
    private View view;
    private ViewHolder holder;
    private int i=0;

    public SchoolAdapter(){
        super();
    }
    public SchoolAdapter(LinkedList<SchoolBeanShell.SchoolBean>mData ,Context context){
        this.context = context;
        this.mData = mData;

    }

    /**
     * 刷新ListView的UI界面
     */
    public void onDataChange(LinkedList<SchoolBeanShell.SchoolBean> mData){
        this.mData = mData;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder=null;
        if (convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.list_item,null);
            holder = new ViewHolder();
            holder.name_tv = convertView.findViewById(R.id.name_tv);
            convertView.setTag(holder);//将holder存储到convertView
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name_tv.setText(mData.get(position).getName());
        holder.name_tv.setTextColor(mData.get(position).getColor());
        view = convertView;
        return view;
    }
    class ViewHolder{
        TextView name_tv;
    }
}
