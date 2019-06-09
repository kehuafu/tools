package khf.edu.mytools.module.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import khf.edu.mytools.R;

public class DateAdapter extends RecyclerView.Adapter {

    private Context context;
    private List<String> date;
    private List<String> week;
    private ViewHolder holder;

    public DateAdapter(Context context,List<String> date,List<String> week){
        this.context = context;
        this.date = date;
        this.week = week;
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
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class  ViewHolder  extends RecyclerView.ViewHolder{

        RelativeLayout date_rl;
        TextView date_tv;
        TextView week_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
