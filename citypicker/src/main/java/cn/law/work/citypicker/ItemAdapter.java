package cn.law.work.citypicker;

import android.content.Context;
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
public class ItemAdapter extends BaseAdapter {
    private Context mContext;
    private List<Item> mItems;

    private OnItemClickCallback mCallback;

    public ItemAdapter(Context mContext, List<Item> mItems) {
        this.mContext = mContext;
        this.mItems = mItems;
    }

    public void setCallback(OnItemClickCallback callback) {
        this.mCallback = callback;
    }

    @Override
    public int getCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mItems == null ? 0 : mItems.get(i
        );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Item item = mItems.get(i);
        ViewHolder mHolder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.adapter_item, viewGroup, false);
            mHolder = new ViewHolder(view);
            view.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) view.getTag();
        }

        mHolder.mTvName.setText(item.getName());
        mHolder.mTvName.setSelected(item.isSelected());

        if (mCallback != null) {
            mHolder.mTvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onItemClick(item);
                }
            });
        }
        return view;
    }

    public void refresh(List<Item> items) {
        this.mItems = items;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        private TextView mTvName;

        private ViewHolder(View itemView) {
            mTvName = itemView.findViewById(R.id.tv_item);
        }
    }

    public interface OnItemClickCallback {
        void onItemClick(Item item);
    }
}
