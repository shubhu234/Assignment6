/**
 * This class is used to list the name and the id of the user
 */
package com.example.studentmanagement.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.example.studentmanagement.R;
import com.example.studentmanagement.model.UserDetails;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewHolder> {
    private ArrayList<UserDetails> mStudentArrayList;
    private ItemClickListener itemClickListener;

    public void setOnclickListener(ItemClickListener itemClickListener){
        this.itemClickListener=itemClickListener;
    }
    public UserAdapter(ArrayList<UserDetails> mStudentArrayList)
    {
     this.mStudentArrayList=mStudentArrayList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvUserName, tvUserId;

        public MyViewHolder(View view) {
            super(view);
            tvUserName = view.findViewById(R.id.tv_userName);
            tvUserId= view.findViewById(R.id.tv_userId);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemClickListener!=null){
                        int po=getAdapterPosition();
                        if(po!=RecyclerView.NO_POSITION){
                            itemClickListener.onItemClickListener(po);
                        }
                    }
                }
            });
        }

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        UserDetails userDetails = mStudentArrayList.get(myViewHolder.getAdapterPosition());
        myViewHolder.tvUserName.setText(userDetails.getmName());
        myViewHolder.tvUserId.setText(userDetails.getId());
    }

    @Override
    public int getItemCount() {
        if(mStudentArrayList!=null) {
            return mStudentArrayList.size();
        }
        return 0;
    }

    public interface ItemClickListener{
         void onItemClickListener(int position);
    }


}

