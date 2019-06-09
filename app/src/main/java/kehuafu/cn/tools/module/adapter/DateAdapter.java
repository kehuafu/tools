package kehuafu.cn.tools.module.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import kehuafu.cn.tools.util.SPUtils;
import khf.edu.mytools.R;
import khf.edu.mytools.module.bean.LeaveDate;

import static khf.edu.mytools.module.dialog.FragmentDialog.flag_fragment;

public class DateAdapter extends RecyclerView.Adapter implements RecyclerView.OnClickListener {

    private Context context;
    private List<LeaveDate> list;
    private List<String> date;
    private List<String> week;
    private ViewHolder holder;
    private OnclickDate onclickDate;

    public DateAdapter(Context context, List<String> date, List<String> week,
                       List<LeaveDate> list, OnclickDate onclickDate) {
        this.context = context;
        this.date = date;
        this.week = week;
        this.list = list;
        this.onclickDate = onclickDate;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_date,
                viewGroup, false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        holder = (ViewHolder) viewHolder;
        holder.date_tv.setText(date.get(i));
        holder.week_tv.setText(week.get(i));
        holder.date_rl.setBackgroundResource(list.get(i).getDateBgColor());
        holder.date_tv.setTextColor(list.get(i).getDateColor());
        holder.week_tv.setTextColor(list.get(i).getWeekColor());
        holder.date_rl.setTag(i);
        if (flag_fragment==5){
            holder.date_rl.setClickable(false);
            holder.date_rl.setEnabled(false);
        }
        holder.date_rl.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return date == null ? 0 : date.size();
    }

    /**
     * item点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        onRefresh(SPUtils.instance().get("date", 0), position);
        SPUtils.instance().save("date", position);
        onclickDate.onClickDate(position);
    }

    /**
     * 刷新RecyclerView的UI界面
     */
    public void onRefresh(int presentItem, int currentItem) {

        Log.i("TTTTTTTTTT","前："+presentItem+"后"+currentItem);
        if (currentItem != presentItem) {
            list.get(currentItem).setDateBgColor(R.drawable.online_date_bg);
            list.get(currentItem).setDateColor(context.getResources().getColor(R.color.colorWhite));
            list.get(currentItem).setWeekColor(context.getResources().getColor(R.color.colorWhite));
            list.get(presentItem).setDateBgColor(R.drawable.online_date_normal_bg);
            list.get(presentItem).setDateColor(context.getResources().getColor(R.color.textColor));
            list.get(presentItem).setWeekColor(context.getResources().getColor(R.color.weekColor));
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout date_rl;
        TextView date_tv;
        TextView week_tv;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            date_rl = itemView.findViewById(R.id.date_rl);
            date_tv = itemView.findViewById(R.id.date_tv);
            week_tv = itemView.findViewById(R.id.week_tv);
        }
    }

    /**
     * 定义一个接口，用于回调item点击后，切换到课程页面对应viewPager的item
     */
    public interface OnclickDate {
        void onClickDate(int position);
    }
}
