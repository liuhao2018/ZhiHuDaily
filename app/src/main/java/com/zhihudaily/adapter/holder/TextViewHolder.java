package com.zhihudaily.adapter.holder;


import android.view.View;
import android.widget.TextView;


import com.zhihudaily.R;
import com.zhihudaily.model.TextItem;

import butterknife.Bind;

/**
 * @author Wayne
 */
public class TextViewHolder extends BaseViewHolder<TextItem> {

    @Bind(R.id.text_view)
    TextView mTextView;

    public TextViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void onBind(int position, TextItem iItem) {
        mTextView.setText(String.format("%s - %s", iItem.getText(), position));
    }
}
