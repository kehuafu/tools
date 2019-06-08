package khf.edu.mytools.module.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import java.util.LinkedList;
import khf.edu.mytools.R;
import khf.edu.mytools.module.bean.LeaveBeanShell;

public class LeaveAdapter extends BaseAdapter {

    private LinkedList<LeaveBeanShell.CourseBean> mData;
    private Context context;
    private View view;
    private ViewHolder holder;
    private int i=0;

    public LeaveAdapter(){
        super();
    }
    public LeaveAdapter(LinkedList<LeaveBeanShell.CourseBean>mData , Context context){
        this.context = context;
        this.mData = mData;

    }
    /**
     * 刷新ListView的UI界面
     */
    public void onRefresh(LinkedList<LeaveBeanShell.CourseBean> mData){
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
            holder.sectionTime_tv = convertView.findViewById(R.id.sectionTime_tv);
            holder.selected_cb = convertView.findViewById(R.id.selected_iv);
            holder.online_courseName_tv = convertView.findViewById(R.id.online_courseName_tv);
            convertView.setTag(holder);//将holder存储到convertView
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.sectionTime_tv.setText(mData.get(position).getSectionTime());
        holder.selected_cb.setChecked(mData.get(position).isChecked());
        holder.online_courseName_tv.setText(mData.get(position).getName());
        holder.online_courseName_tv.setTextColor(mData.get(position).getColor());
        view = convertView;
        return view;
    }
    class ViewHolder{
        TextView sectionTime_tv;
        CheckBox selected_cb;
        TextView online_courseName_tv;
    }
}
