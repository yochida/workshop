package com.tracklocation.workshop;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.thefinestartist.ytpa.utils.YouTubeApp;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {


    private RecyclerView recyclerView;
    private String url = "https://script.googleusercontent.com/macros/echo?user_content_key=9tF9VhMrAYAOcPy-PLTJhj9_su33gt5PNObBabiu_IiFmdlvPg4ngTu63_UpKJFdh3pTGfl3dGs5QqcWiFz8A9K-Nb0UK7uwm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_BQYdAJ18ZOTnNO5fZ7GoZtXIpf5m9CgHnaYeW_ilaew8M2yeDwxSEqfSqbsXiSHEoZN-p3htWAJvMqQf4qkIZpCaSyCsagVahlgo3jS_cH4&lib=MzajnA5J25hMFRYl170ZDmSiwFzvFrgAq";
    private List<YoutubeModel.ItemsBean> mDataArray;

    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        new FeedJson().execute(url);

        return view;
    }

    private class adapter extends RecyclerView.Adapter<ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_listview, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

            try {
                YoutubeModel.ItemsBean youtube = mDataArray.get(position);

                holder.titleTextView.setText(youtube.getTitle());
                holder.subtitleTextView.setText(youtube.getSubtitle());
                Glide.with(getActivity()).load(youtube.getIcon())
                        .bitmapTransform(new CropCircleTransformation(getActivity()))
                        .into(holder.Icon);
                Glide.with(getActivity()).load(youtube.getYoutubeImgage()).into(holder.youtubeImageView);
                holder.youtubeImageView.setTag(youtube.getYoutubeID());
            } catch (Exception e) {

            }


        }

        @Override
        public int getItemCount() {
            return mDataArray.size();
        }
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleTextView;
        ImageView Icon;
        ImageView youtubeImageView;
        TextView subtitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = (TextView) itemView.findViewById(R.id.item_listview_title);
            subtitleTextView = (TextView) itemView.findViewById(R.id.item_listview_subtitle);
            youtubeImageView = (ImageView) itemView.findViewById(R.id.item_listview_youtube_image);
            Icon = (ImageView) itemView.findViewById(R.id.item_listview_Icon);

            youtubeImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Id = String.valueOf(youtubeImageView.getTag());
                    Toast.makeText(getContext(), Id, Toast.LENGTH_SHORT).show();
                    YouTubeApp.startVideo(getActivity(), Id);
                }
            });
        }
    }

    private class FeedJson extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {

                OkHttpClient client = new OkHttpClient();

                String url = strings[0];
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                Response response = client.newCall(request).execute();
                String result = response.body().string();
                //Log.d("getData", "Data" + result);

                Gson gson = new Gson();
                YoutubeModel youtubes = gson.fromJson(result, YoutubeModel.class);

                mDataArray = youtubes.getItems();
                //Log.i("getData", String.valueOf(mDataArray.get(0).getSubtitle()));

                return result;

            } catch (Exception e) {
                Log.d("Error", e.toString());
                return e.toString();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            recyclerView.setAdapter(new adapter());
        }
    }
}
