package khf.edu.mytools.module.waterfall.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import khf.edu.mytools.R;

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mList;
    private List<Integer> mHeight;
    private OnItemClickListener onItemClickListener;
    public WaterFallAdapter(Context context,List<String> list,List<Integer> mHeight) {
        this.mContext = context;
        this.mList = list;
        this.mHeight = mHeight;
    }

    public void removeData(int position){
        mList.remove(position);
        notifyItemRemoved(position);
    }
    /**
     * 加载条目布局
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_recyclerview,viewGroup,false));
        return holder;
    }

    /**
     * 将视图与数据进行绑定
     * @param holder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int i) {
        //holder.itemIv.setText(mList.get(i));
        ViewGroup.LayoutParams layoutParams =holder.itemIv.getLayoutParams();
        layoutParams.height = mHeight.get(i);
        holder.itemIv.setLayoutParams(layoutParams);
        if (onItemClickListener!=null){
            holder.cardView.setOnClickListener(v -> {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemClick(holder.cardView,pos);
            });
            holder.cardView.setOnLongClickListener(v -> {
                int pos = holder.getLayoutPosition();
                onItemClickListener.onItemLongClick(holder.cardView,pos);
                return false;
            });
        }
    }

    /**
     * 获取数据的长度
     * @return
     */
    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView itemIv;
        CardView cardView;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIv = itemView.findViewById(R.id.iv_item);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
