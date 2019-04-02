/**
 * UserPostActivity to fetch the posts of the particular user from the Api call using retrofit
 */
package com.example.studentmanagement.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.studentmanagement.R;
import com.example.studentmanagement.adapter.UserPostAdapter;
import com.example.studentmanagement.constant.Constant;
import com.example.studentmanagement.listener.Api;
import com.example.studentmanagement.model.UserPost;
import com.example.studentmanagement.retrofit.RetrofitImplementation;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserPostActivity extends AppCompatActivity implements RetrofitImplementation.SendData {
    private RecyclerView mRecyclerViewPost;
    private ArrayList<UserPost> mPostArrayList;
    private UserPostAdapter mUserPostAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_post);
        init();
        RetrofitImplementation retrofitImplementation=new RetrofitImplementation(this);
        retrofitImplementation.retrofitPostFetch(getIntent().getStringExtra(Constant.USER_ID));
    }

    public void callBack(Bundle bundle) {

        if(bundle.getString(Constant.TYPE_FROM_MAIN).equals(Constant.SUCCESS)) {
            mPostArrayList = (ArrayList<UserPost>) bundle.getSerializable(Constant.USER_POST_LIST);
            mUserPostAdapter = new UserPostAdapter(mPostArrayList);
            mRecyclerViewPost.setAdapter(mUserPostAdapter);
        }
        else{
            Toast.makeText(this,getString(R.string.error_data_not_received),Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void init() {
        mRecyclerViewPost=findViewById(R.id.rv_user__post_list);
        mRecyclerViewPost.setLayoutManager(new LinearLayoutManager(this));
    }


}
