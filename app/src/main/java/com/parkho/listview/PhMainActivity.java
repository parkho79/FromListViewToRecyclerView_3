package com.parkho.listview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PhMainActivity extends AppCompatActivity {

    public interface ExtraKey {
        String IMAGE_RES_ID = "ImageResourceId";
    }

    // 이미지 클릭 이벤트를 위한 listener
    public interface ImageItemClickListener {
        void onImageItemClick(int a_imageResId) ;
    }

    // List item
    private List<PhCountryItem> mItemList;

    // ListView adapter
    private PhCountryArrayAdapter mCountryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindList();
    }

    private void bindList() {
        mItemList = new ArrayList<>();
        mItemList.add(new PhCountryItem(R.drawable.ico_southkorea, "Korea"));
        mItemList.add(new PhCountryItem(R.drawable.ico_china, "China"));
        mItemList.add(new PhCountryItem(R.drawable.ico_japan, "Japan"));
        mItemList.add(new PhCountryItem(R.drawable.ico_unitedstates, "America"));
        mItemList.add(new PhCountryItem(R.drawable.ico_newzealand, "NewZealand"));

        mCountryAdapter = new PhCountryArrayAdapter(this, mItemList);

        // List item 중 image view click event 처리
        mCountryAdapter.setImageItemClickListener(new ImageItemClickListener() {
            @Override
            public void onImageItemClick(int a_imageResId) {
                Intent intent = new Intent(PhMainActivity.this, PhImageActivity.class);
                intent.putExtra(ExtraKey.IMAGE_RES_ID, a_imageResId);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(mCountryAdapter);

        // List item click event 처리
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                final PhCountryItem item = (PhCountryItem) mCountryAdapter.getItem(a_position);
                Toast.makeText(PhMainActivity.this, item.getCountry() + " Click event", Toast.LENGTH_SHORT).show();
            }
        });

        // List item long click event 처리
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> a_parent, View a_view, int a_position, long a_id) {
                final PhCountryItem item = (PhCountryItem) mCountryAdapter.getItem(a_position);
                Toast.makeText(PhMainActivity.this, item.getCountry() + " Long click event", Toast.LENGTH_SHORT).show();

                // False 로 return 할 경우 long click event 후 click event 발생
                return true;
            }
        });
    }
}