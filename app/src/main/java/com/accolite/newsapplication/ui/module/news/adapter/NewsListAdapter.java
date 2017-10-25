package com.accolite.newsapplication.ui.module.news.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.accolite.newsapplication.R;
import com.accolite.newsapplication.model.news.Hit;
import com.accolite.newsapplication.utils.NewsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<Hit> mItems;
    private Context context;

    public NewsListAdapter(Context context, List<Hit> data, OnItemClickListener listener) {
        this.mItems = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }

    public NewsListAdapter updateItems(List<Hit> data){
        mItems.clear();
        if(data != null) {
            mItems.addAll(data);
        }
        return this;
    }

    public NewsListAdapter addItems(List<Hit> data){
        mItems.addAll(data);
        return this;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(mItems.get(position), listener, position);
    }


    @Override
    public int getItemCount() {
        return mItems == null? 0 : mItems.size();
    }


    public interface OnItemClickListener {
        void onClick(Hit item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView mTitle;
        @BindView(R.id.author)
        TextView mAuthor;
        @BindView(R.id.forward)
        View mForward;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void bind(final Hit hit, final OnItemClickListener listener, int position) {
            if(hit!=null) {
                mTitle.setText(NewsUtils.getReadableString(hit.getTitle()));
                mAuthor.setText(NewsUtils.getAuthorString(hit.getAuthor()));
                if(!TextUtils.isEmpty(hit.getUrl())) {
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onClick(hit);
                        }
                    });
                    mForward.setVisibility(View.VISIBLE);
                } else {
                    itemView.setOnClickListener(null);
                    mForward.setVisibility(View.GONE);
                }
            } else {
                mTitle.setText(NewsUtils.getReadableString(""));
                mAuthor.setText(NewsUtils.getAuthorString(""));
                itemView.setOnClickListener(null);
            }
        }


    }


}
