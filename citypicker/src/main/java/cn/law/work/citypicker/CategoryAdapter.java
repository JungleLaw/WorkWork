package cn.law.work.citypicker;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: Jungle Law
 * Date: 2018/12/16
 * Time: 18:44
 */
public class CategoryAdapter extends BaseAdapter {
    private Context mContext;
    private List<Item> mCategories;

    CategoryAdapter(Context mContext, List<Item> mCategories) {
        this.mContext = mContext;
        this.mCategories = mCategories;
    }

    @Override
    public int getCount() {
        return mCategories == null ? 0 : mCategories.size();
    }

    @Override
    public Object getItem(int i) {
        return mCategories == null ? 0 : i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Item category = mCategories.get(i);
        ViewHolder mHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_category, viewGroup, false);
            mHolder = new ViewHolder(view);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }
        mHolder.mTvItem.setText(category.getName());
        if (category.isSelected()) {
            mHolder.mIndicator.setVisibility(View.VISIBLE);
        } else {
            mHolder.mIndicator.setVisibility(View.GONE);
        }
        return view;
    }

    public void refresh(List<Item> categories) {
        this.mCategories = categories;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        private TextView mTvItem;

        private View mIndicator;

        private ViewHolder(@NonNull View itemView) {
            mTvItem = itemView.findViewById(R.id.tv_item);
            mIndicator = itemView.findViewById(R.id.v_indicator);
        }
    }
}
