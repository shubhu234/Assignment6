/**
 * RetroFitImplementation class for fetching the data
 */
package com.example.studentmanagement.retrofit;


import com.example.studentmanagement.constant.Constant;
import com.example.studentmanagement.listener.Api;
import com.example.studentmanagement.model.UserDetails;
import com.example.studentmanagement.model.UserPost;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitImplementation {
    private SendData sendData;

    public RetrofitImplementation(SendData sendData) {
        this.sendData = sendData;
    }

    /**
     * retrofitDataFetch- method to fetch the name and id of the user
     */
    public void retrofitDataFetch(){
        final Bundle bundle=new Bundle();
                Retrofit mRetrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=mRetrofit.create(Api.class);
        final Intent intent=new Intent();
        Call<ArrayList<UserDetails>> call=api.getUserDetails();
        call.enqueue(new Callback<ArrayList<UserDetails>>() {
            @Override
            public void onResponse(Call<ArrayList<UserDetails>> call, Response<ArrayList<UserDetails>> response) {

                ArrayList<UserDetails> list=new ArrayList<UserDetails>();
                for(int i=0;i<response.body().size();i++){
                    list.add(response.body().get(i));
                }

               bundle.putSerializable(Constant.USER_ARRAYLIST,list);
                bundle.putString(Constant.TYPE_FROM_MAIN,Constant.SUCCESS);
                sendData.callBack(bundle);
            }

            @Override
            public void onFailure(Call<ArrayList<UserDetails>> call, Throwable t) {
                bundle.putString(Constant.TYPE_FROM_MAIN,Constant.FAILURE);
               sendData.callBack(bundle);
            }

        });

    }
    //retrofitPostFetch- method to fetch the details belonging to the particular user like body,title
    public void retrofitPostFetch(String userId){
        final Bundle bundle=new Bundle();

        Retrofit mRetrofit=new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api=mRetrofit.create(Api.class);

        Call<ArrayList<UserPost>> call=api.getUserPost(Integer.parseInt(userId));
        call.enqueue(new Callback<ArrayList<UserPost>>(){


            @Override
            public void onResponse(Call<ArrayList<UserPost>> call, Response<ArrayList<UserPost>> response) {
                ArrayList<UserPost> userlist=new ArrayList<UserPost>();
                for(int i=0;i<response.body().size();i++){
                    userlist.add(response.body().get(i));
                }

                bundle.putSerializable(Constant.USER_POST_LIST,userlist);
                bundle.putString(Constant.TYPE_FROM_MAIN,Constant.SUCCESS);
                sendData.callBack(bundle);
            }

            @Override
            public void onFailure(Call<ArrayList<UserPost>> call, Throwable t) {
                bundle.putString(Constant.TYPE_FROM_MAIN,Constant.FAILURE);
                sendData.callBack(bundle);
            }

        });
    }

    public interface SendData{
        public void callBack(Bundle bundle);
    }

}
