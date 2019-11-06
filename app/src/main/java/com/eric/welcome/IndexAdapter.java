package com.eric.welcome;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @功能:
 * @Creat 2019/11/6 15:09
 * @User Lmy
 * @Compony zaituvideo
 */
public class IndexAdapter extends RecyclerView.Adapter {
    private Context context;
    private int[] images;
    private int index;

    public IndexAdapter(Context context, int[] images, int index) {
        this.context = context;
        this.images = images;
        this.index = index;
    }

    public void setIndex(int index) {
        this.index = index;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.indexitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).index.setBackground(context.getResources().getDrawable(R.drawable.index_n));
        if (position == index) {
            ((ViewHolder) holder).index.setBackground(context.getResources().getDrawable(R.drawable.index_y));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView index;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            index = itemView.findViewById(R.id.index);
        }
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}
