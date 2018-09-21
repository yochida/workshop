package com.tracklocation.workshop;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.pixplicity.easyprefs.library.Prefs;

import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class GrideFragment extends Fragment {


    private RecyclerView recyclerView;
    private String url = "https://script.google.com/a/sskru.ac.th/macros/s/AKfycbxHkUt6ZQywEJefpMJY6hEiG_BdKoYx_iKqedm8gxg/dev";
    private List<ProductModel.ItemsBean> mDataArray;
    private int State = 0;

    public GrideFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gride, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        new FeedJson().execute();

        return view;
    }

    private class adapter extends RecyclerView.Adapter<ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_product, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            try {
                ProductModel.ItemsBean product = mDataArray.get(position);

                holder.mProductName.setText(product.getF_name());
                holder.mPrice.setText(String.valueOf(product.getF_price()));
                holder.mScore.setText(String.valueOf(product.getF_likeCount()));
                Glide.with(getActivity()).load(product.getF_img()).into(holder.mImageProduct);
                holder.mLike.setTag(product.getF_id());

                if (product.getF_like() == 0) {
                    holder.mLike.setImageResource(R.drawable.heart);
                } else {
                    holder.mLike.setImageResource(R.drawable.hearted);
                }
                holder.mScore.setTag(product.getF_like());

            } catch (Exception e) {

            }

        }

        @Override
        public int getItemCount() {

            return mDataArray.size();

        }

    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mImageProduct;
        private final TextView mProductName;
        private final TextView mPrice;
        private final TextView mScore;
        private final ImageView mLike;

        public ViewHolder(View itemView) {
            super(itemView);

            mImageProduct = (ImageView) itemView.findViewById(R.id.imageProduct);
            mProductName = (TextView) itemView.findViewById(R.id.productName);
            mPrice = (TextView) itemView.findViewById(R.id.price);
            mScore = (TextView) itemView.findViewById(R.id.score);
            mLike = (ImageView) itemView.findViewById(R.id.like);

            mLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String LikeStatus = String.valueOf(mScore.getTag());

                    if (LikeStatus.equals("0")) {
                        mLike.setImageResource(R.drawable.hearted);
                    } else {
                        mLike.setImageResource(R.drawable.heart);
                    }

                    new FeedLike().execute(String.valueOf(mLike.getTag()));
                }
            });

        }
    }

    private class FeedJson extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {

                // 1. connect server with okHttp
                OkHttpClient client = new OkHttpClient();

                /*RequestBody postData = new FormBody.Builder()
                        .add("username", "yochida")
                        .build();*/

                Request request = new Request.Builder()
                        .url("https://script.google.com/a/sskru.ac.th/macros/s/AKfycbxHkUt6ZQywEJefpMJY6hEiG_BdKoYx_iKqedm8gxg/dev?username=" + Prefs.getString("username", ""))
                        .build();

                // 3. transport request to server
                Response response = client.newCall(request).execute();
                String result = response.body().string();

                // parse json string with gson
                Gson gson = new Gson();
                ProductModel productModel = gson.fromJson(result, ProductModel.class);

                mDataArray = productModel.getItems();

                return result;

            } catch (Exception e) {
                Log.d("getData", "Error" + e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            recyclerView.setAdapter(new adapter());
            /*
            if (State == 0) {
                recyclerView.setAdapter(new adapter());
            } else {
                recyclerView.swapAdapter(new adapter(), false);
                State = 0;
            }*/
        }
    }

    private class FeedLike extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {

                // 1. connect server with okHttp
                OkHttpClient client = new OkHttpClient();

                RequestBody postData = new FormBody.Builder()
                        .add("f_id", strings[0])
                        .add("username", Prefs.getString("username", ""))
                        .build();

                Request request = new Request.Builder()
                        .url("https://script.google.com/a/sskru.ac.th/macros/s/AKfycbxHkUt6ZQywEJefpMJY6hEiG_BdKoYx_iKqedm8gxg/dev")
                        .post(postData)
                        .build();

                // 3. transport request to server
                Response response = client.newCall(request).execute();
                String result = response.body().string();
                Log.d("getData", result);

                // parse json string with gson
                Gson gson = new Gson();
                ProductModel productModel = gson.fromJson(result, ProductModel.class);

                mDataArray = productModel.getItems();

                return result;

            } catch (Exception e) {
                Log.d("getData", "Error" + e.toString());
                return null;
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            recyclerView.swapAdapter(new adapter(), false);
        }
    }
}

