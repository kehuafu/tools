package khf.edu.mytools.module.waterfall.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import imageloader.libin.com.images.config.ScaleMode;
import imageloader.libin.com.images.loader.ImageLoader;
import kehuafu.cn.tools.custom.RoundImageView;
import khf.edu.mytools.R;

public class WaterFallAdapter extends RecyclerView.Adapter<WaterFallAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> mList;
    private List<Integer> mHeight;
    private List<Integer> mColor;
    private OnItemClickListener onItemClickListener;
    public WaterFallAdapter(Context context,List<String> list,List<Integer> mHeight,List<Integer> mColor) {
        this.mContext = context;
        this.mList = list;
        this.mHeight = mHeight;
        this.mColor = mColor;
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
        ImageLoader.with(mContext)
                .url("https://tse1-mm.cn.bing.net/th/id/OET.d4e9c1e02e214fc68b20f610121dc3e0?w=272&h=135&c=7&rs=1&o=5&pid=1.9")
                .placeHolder(R.mipmap.user_unload)
                .rectRoundCorner(30)
                .into(holder.cardUserIv);
        ViewGroup.LayoutParams layoutParams =holder.itemIv.getLayoutParams();
        layoutParams.height = mHeight.get(i);
        holder.itemIv.setLayoutParams(layoutParams);
        ImageLoader.with(mContext)
                .url("https://tse1-mm.cn.bing.net/th/id/OET.d4e9c1e02e214fc68b20f610121dc3e0?w=272&h=135&c=7&rs=1&o=5&pid=1.9")
                .placeHolder(mColor.get(i))
                .scale(ScaleMode.FIT_CENTER)
                .into(holder.itemIv);
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
        RoundImageView cardUserIv;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemIv = itemView.findViewById(R.id.iv_item);
            cardView = itemView.findViewById(R.id.card_view);
            cardUserIv = itemView.findViewById(R.id.card_user_iv);
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
