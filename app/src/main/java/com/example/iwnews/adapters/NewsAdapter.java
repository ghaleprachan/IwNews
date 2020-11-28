package com.example.iwnews.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.iwnews.R;
import com.example.iwnews.activities.detailedNews.DetailedNews;
import com.example.iwnews.models.NewsModel;
import com.example.iwnews.services.DateParser;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final Context mContext;
    private final NewsModel news;

    public NewsAdapter(Context mContext, NewsModel news) {
        this.mContext = mContext;
        this.news = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.news_layout_design, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.newsTitle.setText(news.getArticles().get(position).getTitle());
        if (news.getArticles().get(position).getAuthor() == null) {
            holder.newsAuthor.setText("Unknown");
        } else {
            holder.newsAuthor.setText(news.getArticles().get(position).getAuthor());
        }
        holder.newsDate.setText(DateParser.formatDate(
                news.getArticles().get(position).getPublishedAt(),
                "MMM dd, yyyy 'at' h:ss a"));
        RequestOptions defaultOptions = new RequestOptions()
                .placeholder(R.drawable.progress_animation)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .priority(Priority.HIGH)
                .dontAnimate()
                .dontTransform();
        Glide.with(mContext)
                .setDefaultRequestOptions(defaultOptions)
                .load(news.getArticles().get(position).getUrlToImage())
                .into(holder.newsImage);
        onNewsParentClick(holder.newsParent, position);
    }

    private void onNewsParentClick(RelativeLayout newsParent, int position) {
        newsParent.setOnClickListener(v -> {
            Intent intent = new Intent(mContext, DetailedNews.class);
            intent.putExtra("selectedNews", position);
            mContext.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return news.getArticles().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView newsTitle, newsAuthor, newsDate;
        private ImageView newsImage;
        private RelativeLayout newsParent;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            UiInit(itemView);
        }

        private void UiInit(View view) {
            newsTitle = view.findViewById(R.id.newsTitle);
            newsImage = view.findViewById(R.id.newsImage);
            newsAuthor = view.findViewById(R.id.newsAuthor);
            newsDate = view.findViewById(R.id.newsDate);
            newsParent = view.findViewById(R.id.newsParent);
        }
    }
}
