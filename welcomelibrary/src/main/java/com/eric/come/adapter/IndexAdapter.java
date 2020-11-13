package com.eric.come.adapter;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.eric.come.R;
import com.eric.come.utils.GuideAttributes;

import java.util.List;

/**
 * @功能: 引导页适配器
 * @Creat 2019/11/6 15:09
 * @User Lmy
 * @Compony zaituvideo
 */
public class IndexAdapter extends RecyclerView.Adapter {
    private Context context;
    private List<Integer> images;
    private int index;
    private GuideAttributes guideAttributes;

    public IndexAdapter(Context context, List<Integer> images, GuideAttributes guideAttributes, int index) {
        this.context = context;
        this.images = images;
        this.index = index;
        this.guideAttributes = guideAttributes;
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
        ((ViewHolder) holder).ivIndex.setBackground(context.getResources().getDrawable(R.drawable.index_n));
        GradientDrawable myShape = (GradientDrawable) ((ViewHolder) holder).ivIndex.getBackground();
        myShape.setColor(guideAttributes.getIndicatorUnSelectColor() == 0 ? context.getResources().getColor(R.color.skipBgColor) : context.getResources().getColor(guideAttributes.getIndicatorUnSelectColor()));
        if (position == index) {
            ((ViewHolder) holder).ivIndex.setBackground(context.getResources().getDrawable(R.drawable.index_y));
            GradientDrawable myShapes = (GradientDrawable) ((ViewHolder) holder).ivIndex.getBackground();
            myShapes.setColor(guideAttributes.getIndicatorSelectColor() == 0 ? context.getResources().getColor(R.color.colorAccent) : context.getResources().getColor(guideAttributes.getIndicatorSelectColor()));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivIndex;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIndex = itemView.findViewById(R.id.iv_index);
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}
