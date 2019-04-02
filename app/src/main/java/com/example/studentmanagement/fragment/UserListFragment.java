/**
 * UserListFragment class showing the list of the users having the name and  id
 */
package com.example.studentmanagement.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.studentmanagement.R;
import com.example.studentmanagement.adapter.UserAdapter;
import com.example.studentmanagement.constant.Constant;
import com.example.studentmanagement.listener.FragmentCommunication;
import com.example.studentmanagement.model.UserDetails;
import com.example.studentmanagement.retrofit.RetrofitImplementation;

import java.util.ArrayList;

public class UserListFragment extends Fragment implements RetrofitImplementation.SendData {
    private View view;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private UserAdapter mUserAdapter;
    private ArrayList<UserDetails> mUserDetailsArrayList;
    private FragmentCommunication mListener;

    private ProgressBar pbUserList;

    public UserListFragment() {
        //empty constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_list, container, false);
        pbUserList = view.findViewById(R.id.pb_userlist);
        RetrofitImplementation ret = new RetrofitImplementation(this);
        ret.retrofitDataFetch();
        return view;
    }

    /**
     * method onClickRecycle-getting the name and id
     * @param position-for taking the position on which item we have clicked
     */
    private void onClickRecycle(int position) {
        Bundle mBundle = new Bundle();
        mBundle.putString(Constant.USER_ID, mUserDetailsArrayList.get(position).getId());
        mBundle.putString(Constant.USER_NAME, mUserDetailsArrayList.get(position).getmName());
        mListener.communicate(mBundle);
    }
    // method init for initializing the variables
    private void init() {
        mRecyclerView = view.findViewById(R.id.rv_user_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mUserAdapter = new UserAdapter(mUserDetailsArrayList);
        mRecyclerView.setAdapter(mUserAdapter);
        mUserAdapter.setOnclickListener(new UserAdapter.ItemClickListener() {
            @Override
            public void onItemClickListener(int position) {
                onClickRecycle(position);
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentCommunication) {
            mListener = (FragmentCommunication) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void callBack(Bundle bundle) {
        if (bundle.getString(Constant.TYPE_FROM_MAIN).equals(Constant.SUCCESS)) {
            pbUserList.setVisibility(View.GONE);
            mUserDetailsArrayList = (ArrayList<UserDetails>) bundle.getSerializable(Constant.USER_ARRAYLIST);
            init();
        }
    }
}
