package com.freshappbooks.movies.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.freshappbooks.movies.R;
import com.freshappbooks.movies.data.Review;

import java.util.ArrayList;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {

    private ArrayList<Review> reviews; // List of reviews

    @NonNull
    @Override
    public ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_item, parent, false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewViewHolder holder, int position) {
        Review review = reviews.get(position);
        holder.textViewReview.setText(review.getContent());
        holder.textViewAuthor.setText(review.getContent());

    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(ArrayList<Review> reviews) {
        this.reviews = reviews;
        notifyDataSetChanged();  // hey, adapter! Values changes! - Got it.
    }

    class ReviewViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewAuthor;
        private TextView textViewReview;

        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewAuthor = itemView.findViewById(R.id.textView_author);
            textViewReview = itemView.findViewById(R.id.textView_review);
        }
    }
}
