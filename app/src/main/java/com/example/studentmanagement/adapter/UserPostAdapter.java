/**
 * UserPostAdapter class uses the recycler view for getting the various details of the related to the id of particular user
 */
package com.example.studentmanagement.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.studentmanagement.R;
import com.example.studentmanagement.model.UserPost;

import java.util.ArrayList;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.MyViewHolder> {
    private ArrayList<UserPost> postArrayList;

    public UserPostAdapter(ArrayList<UserPost> postArrayList) {
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_user_post,viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        UserPost userPost=postArrayList.get(myViewHolder.getAdapterPosition());
        ((MyViewHolder)myViewHolder).tv_user_id.setText(userPost.getUserId());
        ((MyViewHolder)myViewHolder).tv_post_id.setText(userPost.getId());
        ((MyViewHolder)myViewHolder).tv_post_title.setText(userPost.getTitle());
        ((MyViewHolder)myViewHolder).tv_post.setText(userPost.getBody());

    }

    @Override
    public int getItemCount() {
        if(postArrayList!=null){
            return  postArrayList.size();
        }
        return 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_post_id, tv_post_title,tv_post,tv_user_id;

        public MyViewHolder(View view) {
            super(view);
            tv_user_id = view.findViewById(R.id.tv_userId2);
            tv_post_id= view.findViewById(R.id.tv_id2);
            tv_post_title=view.findViewById(R.id.tv_title);
            tv_post=view.findViewById(R.id.tv_body);
        }

    }
}
