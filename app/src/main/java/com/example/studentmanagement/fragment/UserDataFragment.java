/**
 * UserDataFragment for displaying the name,image and and of the user when clicked on the particular item of the list
 */
package com.example.studentmanagement.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.studentmanagement.R;
import com.example.studentmanagement.activity.UserPostActivity;
import com.example.studentmanagement.constant.Constant;

public class UserDataFragment extends Fragment {
    private View view;
    private Context mContext;
    private TextView tvId;
    private TextView tvName;
    private ImageView ivUser;
    private Button btnPostUser;
    public UserDataFragment()
    {
        //empty constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user_data, container, false);
        init();
        btnPostUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, UserPostActivity.class);
                intent.putExtra(Constant.USER_ID,tvId.getText().toString().trim());
                startActivity(intent);
            }
        });
        return view;
    }
    //method init for initializing the variables
    private void init(){
        ivUser=view.findViewById(R.id.iv_userimage);
        tvId=view.findViewById(R.id.tv_id);
        tvName=view.findViewById(R.id.tv_name);
        btnPostUser=view.findViewById(R.id.btn_post);
        btnPostUser.setVisibility(View.GONE);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext=context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * method updateDetails to set the details of the user
     * @param bundle for passing of the data
     */
    public void updateDetails(Bundle bundle) {
        ivUser.setImageResource(R.mipmap.ic_launcher);
        tvName.setText(bundle.getString(Constant.USER_NAME));
        tvId.setText(bundle.getString(Constant.USER_ID));

        if(btnPostUser.getVisibility()==View.GONE){
            btnPostUser.setVisibility(View.VISIBLE);
        }
    }
}
