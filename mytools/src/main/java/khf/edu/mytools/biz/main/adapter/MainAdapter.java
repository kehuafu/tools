package khf.edu.mytools.biz.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import khf.edu.mytools.R;
import khf.edu.mytools.common.util.GoToNextPageUtil;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.VH> {

    private List<String> mList;

    public MainAdapter(List<String> list) {
        this.mList = list;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH vh = new MainAdapter.VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_item_main, parent, false));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        holder.itemIv.setText(mList.get(position));
        holder.itemCl.setTag(position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class VH extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView itemIv;
        ConstraintLayout itemCl;

        VH(@NonNull View itemView) {
            super(itemView);
            itemIv = itemView.findViewById(R.id.tv_item);
            itemCl = itemView.findViewById(R.id.cl_item);
            itemCl.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            GoToNextPageUtil.getInstance().goToActivity(v, (Integer) v.getTag());
        }
    }
}
