package cn.law.work.citypicker;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Jungle Law
 * Date: 2018/12/16
 * Time: 17:58
 */
public class AreaPickerDialog extends Dialog {

    private ListView mLvCategories;
    private GridView mGvItems;
    private ProgressBar mProgressBar;

    private List<Item> mCategories;
    private HashMap<String, List<Item>> mItemsMap;

    private CategoryAdapter mCategoryAdapter;
    private ItemAdapter mItemAdapter;

    private List<Item> items = new ArrayList<>();

    private int mCategorySelectedIndex = 0;

    AreaPickerDialog(Context context, final List<Item> categories, boolean hasCategory) {
        super(context);
        this.mCategories = categories;
        mItemsMap = new HashMap();

        setContentView(R.layout.layout_dialog_area_picker);

        mLvCategories = findViewById(R.id.rv_category);
        mGvItems = findViewById(R.id.rv_items);
        mProgressBar = findViewById(R.id.progress);


        if (hasCategory) {
            mLvCategories.setVisibility(View.VISIBLE);
            Item all = new Item();
            all.setName("全部");
            all.setSelected(true);
            mCategories.add(0, all);
            List<Item> allList = new ArrayList<>();
            Item allItem = new Item();
            allItem.setName("全部");
            allItem.setSelected(true);
            allList.add(allItem);
            mItemsMap.put(all.getName(), allList);
        } else
            mLvCategories.setVisibility(View.GONE);

        mCategoryAdapter = new CategoryAdapter(getContext(), mCategories);
        mLvCategories.setAdapter(mCategoryAdapter);

        mItemAdapter = new ItemAdapter(getContext(), items);
        mGvItems.setAdapter(mItemAdapter);

        mLvCategories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == mCategorySelectedIndex) {
                    return;
                }
                try {
                    for (Item category : mCategories) {
                        category.setSelected(false);
                    }
                    mCategories.get(i).setSelected(true);
                    mCategoryAdapter.refresh(mCategories);
                    mCategorySelectedIndex = i;
                    loadItems(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        mItemAdapter.setCallback(new ItemAdapter.OnItemClickCallback() {
            @Override
            public void onItemClick(Item item) {
                if (item.isSelected()) {
                    return;
                }
                for (Map.Entry<String, List<Item>> entry : mItemsMap.entrySet()) {
                    for (Item i : entry.getValue()) {
                        i.setSelected(false);
                    }
                }
                item.setSelected(true);
                Toast.makeText(getContext(), item.getName(), Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        try {
            loadItems(mCategorySelectedIndex);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void loadItems(final int position) throws InterruptedException {
        if (!mItemsMap.containsKey(mCategories.get(position).getName())) {
            mProgressBar.setVisibility(View.VISIBLE);
            mItemAdapter.refresh(null);
            mGvItems.postDelayed(new Runnable() {
                @Override
                public void run() {
                    items = new ArrayList<>();
                    Item allItem = new Item();
                    allItem.setName("全部 " + position);
                    if (mCategories.size() != 1) {
                        allItem.setSelected(false);
                    } else {
                        allItem.setSelected(true);
                    }
                    items.add(allItem);

                    for (int i = 0, s = 20; i < s; i++) {
                        Item item = new Item();
                        item.setName("category " + position + ",item " + i);
                        item.setCode(i);
                        items.add(item);
                    }
                    mItemsMap.put(mCategories.get(position).getName(), items);

                    if (mCategorySelectedIndex == position) {
                        mProgressBar.setVisibility(View.GONE);
                        mItemAdapter.refresh(items);
                    }
                    mGvItems.setSelection(0);
                }
            }, 3000);
        } else {
            items = mItemsMap.get(mCategories.get(position).getName());
            if (mCategorySelectedIndex == position) {
                mProgressBar.setVisibility(View.GONE);
                mItemAdapter.refresh(items);
            }
            mGvItems.setSelection(0);
        }

//        mItemAdapter = new ItemAdapter(getContext(), items);
//        mGvItems.setAdapter(mItemAdapter);

    }


}
