/*
 * Copyright (C) 2014 Snowdream Mobile <yanghui1986527@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.snowdream.android.apps.jokes;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.nhaarman.listviewanimations.ArrayAdapter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by hui.yang on 2014/12/14.
 */
public class GoogleCardsAdapter extends ArrayAdapter<Joke> {
    private final Context mContext;

    GoogleCardsAdapter(final Context context) {
        mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.fragment_googlel_cards_item, parent, false);

            holder = new ViewHolder();
            holder.textView = (TextView) view.findViewById(R.id.textview_googlecards_card);
            holder.imageView = (ImageView) view.findViewById(R.id.imageview_googlecards_card);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Joke joke = getItem(position);
        if (joke != null) {
            String content = joke.getContent();
            if (!TextUtils.isEmpty(content)) {
                holder.textView.setText(content);
            } else {
                holder.textView.setText("");
            }

            List<String> images = joke.getImages();
            if (images != null && images.size()> 0){
                String imageUrl = images.get(0);

                if (!TextUtils.isEmpty(imageUrl)) {
                    holder.imageView.setVisibility(View.VISIBLE);
                    ImageLoader.getInstance().displayImage(imageUrl, holder.imageView);
                }else{
                    holder.imageView.setVisibility(View.GONE);
                }
            }else{
                holder.imageView.setVisibility(View.GONE);
            }
        }
        return view;
    }

    private static class ViewHolder {
        TextView textView;
        ImageView imageView;
    }
}
