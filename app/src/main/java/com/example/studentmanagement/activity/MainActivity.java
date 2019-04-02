package com.example.studentmanagement.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.studentmanagement.R;
import com.example.studentmanagement.fragment.UserDataFragment;
import com.example.studentmanagement.fragment.UserListFragment;
import com.example.studentmanagement.listener.FragmentCommunication;

public class MainActivity extends AppCompatActivity implements FragmentCommunication {


    private UserDataFragment userDataFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDataFragment=(UserDataFragment)getSupportFragmentManager().findFragmentById(R.id.ll_data_fragment);
    }

    @Override
    public void communicate(Bundle bundle) {
        userDataFragment.updateDetails(bundle);
    }
}
