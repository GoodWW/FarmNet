/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cdzp.farmnet.contract.map;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.Poi;
import com.cdzp.farmnet.R;
import com.cdzp.farmnet.base.BaseAdapter;

import java.util.List;


/**
 * Created by YOLANDA on 2016/7/22.
 */
public class MapRecyclerAdapter extends BaseAdapter<MapRecyclerAdapter.ViewHolder,Poi> {

    private  List<Poi> mDataList;

    public MapRecyclerAdapter(Context context) {
        super(context);
    }

    public void notifyDataSetChanged( List<Poi> dataList) {
        this.mDataList = dataList;
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e("", "      getItemViewType:       " + viewType);
            return new ViewHolder(getInflater().inflate(R.layout.item_map_recycler, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.setName(mDataList.get(position).getName());
            holder.setAddress(mDataList.get(position).getAddr());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName;
        TextView tvAddress;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvAddress = itemView.findViewById(R.id.tvAddress);
        }

        public void setName(String title) {
            this.tvName.setText(title);
        }
        public void setAddress(String tvAddress) {
            this.tvAddress.setText(tvAddress);
        }
    }

}