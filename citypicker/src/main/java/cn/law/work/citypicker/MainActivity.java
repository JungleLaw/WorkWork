package cn.law.work.citypicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private AreaPickerDialog mDialog;
    private AreaPickerDialog mNoCategoryDialog;

    public void showDialog(View view) {
        if (mDialog == null) {
            List<Item> mCategories = new ArrayList<>();

            for (int i = 0, s = 20; i < s; i++) {
                Item item = new Item();
                item.setName("Category " + i);
                item.setCode(i);
                mCategories.add(item);
            }
            mDialog = new AreaPickerDialog(this, mCategories, true);
        }

        mDialog.show();
    }

    public void showNoCategoryDialog(View view) {
        if (mNoCategoryDialog == null) {
            List<Item> mCategories = new ArrayList<>();
            Item item = new Item();
            item.setName("Category");
            mCategories.add(item);
            mNoCategoryDialog = new AreaPickerDialog(this, mCategories, false);
        }

        mNoCategoryDialog.show();
    }
}
