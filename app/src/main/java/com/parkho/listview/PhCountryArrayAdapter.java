package com.parkho.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.parkho.listview.PhMainActivity.ImageItemClickListener;

import java.util.List;

public class PhCountryArrayAdapter extends ArrayAdapter<PhCountryItem> {
    private static final int LAYOUT_RESOURCE_ID = R.layout.content_list_item;

    private Context mContext;
    private List<PhCountryItem> mItemList;

    private ImageItemClickListener mImageItemClickListener;

    public PhCountryArrayAdapter(Context a_context, List<PhCountryItem> a_itemList) {
        super(a_context, LAYOUT_RESOURCE_ID, a_itemList);

        mContext = a_context;
        mItemList = a_itemList;
    }

    @Override
    public View getView(int a_position, View a_convertView, ViewGroup a_parent) {
        PhCountryItemViewHolder viewHolder;
        if (a_convertView == null) {
            a_convertView = LayoutInflater.from(mContext).inflate(LAYOUT_RESOURCE_ID, a_parent, false);

            viewHolder = new PhCountryItemViewHolder(a_convertView);
            a_convertView.setTag(viewHolder);
        } else {
            viewHolder = (PhCountryItemViewHolder) a_convertView.getTag();
        }

        final PhCountryItem countryItem = mItemList.get(a_position);

        // Icon 설정
        viewHolder.ivIcon.setImageResource(countryItem.getImageResId());
        viewHolder.ivIcon.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View a_view) {
                if (mImageItemClickListener != null) {
                    mImageItemClickListener.onImageItemClick(countryItem.getImageResId());
                }
            }
        });

        // Country 설정
        viewHolder.tvCountry.setText(countryItem.getCountry());

        return a_convertView;
    }

    public void setImageItemClickListener(ImageItemClickListener a_listener) {
        mImageItemClickListener = a_listener;
    }
}