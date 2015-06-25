package com.erban.levelone;

import com.erban.R;
import com.erban.bean.User;
import com.erban.levelone.control.UserControl;
import com.erban.module.user.LoginAndRegisterActivity;
import com.erban.module.user.UserInfoActivity;
import com.erban.module.user.center.FavActivity;
import com.erban.module.user.center.MessageActivity;
import com.erban.util.UserUtil;
import com.erban.widget.TitleBar;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yuekuapp.BaseFragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class UserFragment extends BaseFragment<UserControl> implements View.OnClickListener{
	
	private ViewGroup mRootView;
	
	private TitleBar mTitleBar;
	
	private ViewGroup line1,line2,line3,line4,line5,line6,line7;
	
	private ImageView mUserIconIv;
	
	private TextView mUserNameTv;
	
	private TextView mUserGradeTv;
	
	private TextView mLoginButton;
	
	private TextView mRegisterButton;
	
	private View mNoLoginLayout;
	
	private View mloginLayout;
	
	private TextView mNickNameTv;
	
	private TextView mIdTv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_user_center_layout, null);
		setUpView();
	    initData();
		return mRootView;
	}

	@Override
	public void onResume() {
	    super.onResume();
	    initData();
	}
	
	private void setUpView(){
		mRootView.findViewById(R.id.back).setVisibility(View.GONE);
		mRootView.findViewById(R.id.user_icon).setOnClickListener(this);;
		mTitleBar = (TitleBar) mRootView.findViewById(R.id.titlebar);
		mTitleBar.setTitle("个人中心");
		
		line1 = (ViewGroup) mRootView.findViewById(R.id.line1);
		setUpUserItem(line1, "我的积分", this,R.drawable.user_integral_icon);
		line2 = (ViewGroup) mRootView.findViewById(R.id.line2);
		setUpUserItem(line2, "我的优惠", this,R.drawable.user_sale_icon);
		line3 = (ViewGroup) mRootView.findViewById(R.id.line3);
		setUpUserItem(line3, "我的会员", this,R.drawable.user_vip_icon);
		line4 = (ViewGroup) mRootView.findViewById(R.id.line4);
		setUpUserItem(line4, "我的消息", this,R.drawable.user_message_icon);
		line5 = (ViewGroup) mRootView.findViewById(R.id.line5);
		setUpUserItem(line5, "我的收藏", this,R.drawable.user_fav_icon);
		line6 = (ViewGroup) mRootView.findViewById(R.id.line6);
		setUpUserItem(line6, "关于我们", this,R.drawable.user_about_icon);
		
		mUserIconIv = (ImageView) mRootView.findViewById(R.id.user_icon);
		
		mLoginButton = (TextView) mRootView.findViewById(R.id.login_button);
		mRegisterButton = (TextView) mRootView.findViewById(R.id.register_button);
		mNoLoginLayout = mRootView.findViewById(R.id.no_login_layout);
		
		mLoginButton.setOnClickListener(this);
		mRegisterButton.setOnClickListener(this);
		
		mloginLayout = mRootView.findViewById(R.id.login_layout);
		mNickNameTv = (TextView) mRootView.findViewById(R.id.nickname);
		mIdTv = (TextView) mRootView.findViewById(R.id.id);
	}
	
	private void initData(){
		User user = UserUtil.getUser();
		if(user == null){
		    mNoLoginLayout.setVisibility(View.VISIBLE);
		    mloginLayout.setVisibility(View.GONE);
			return;
		}else{
			mNoLoginLayout.setVisibility(View.GONE);
			mloginLayout.setVisibility(View.VISIBLE);
		}
	    try {
	        User.UserInfo userInfo = UserUtil.getUser().getUserInfo();
	        if(!TextUtils.isEmpty(userInfo.getAvatar()))
	            ImageLoader.getInstance().displayImage(userInfo.getAvatar(), mUserIconIv);
	        mNickNameTv.setText(TextUtils.isEmpty(userInfo.getNickname())?"未命名":userInfo.getNickname());
	        mIdTv.setText("ID:"+userInfo.getUserid());
        } catch (Exception e) {
        }
	}
	
	private void setUpUserItem(ViewGroup rootView,String title,View.OnClickListener clickListener,int drawableRes){
		ImageView iv = (ImageView) rootView.findViewById(R.id.icon);
		iv.setImageResource(drawableRes);
		TextView tv = (TextView) rootView.findViewById(R.id.title);
		tv.setText(title);
		rootView.setOnClickListener(clickListener);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.user_icon:
		    UserInfoActivity.startActivity(getActivity());
		    break;
		case R.id.login_button://登录
			LoginAndRegisterActivity.startActivity(getActivity(), true);
			break;
		case R.id.register_button://注册
			LoginAndRegisterActivity.startActivity(getActivity(), false);
			break;
		case R.id.line1:  //我的订单
//			UserOrderListActivity.startActivity(getActivity());
			break;
		case R.id.line2: 
//			MessageActivity.startActivity(getActivity());
			break;
		case R.id.line3: //我的收藏
//			CollectionActivity.startActivity(getActivity());
			break;
		case R.id.line4: //我的消息
			MessageActivity.startActivity(getActivity());
			break;
		case R.id.line5: //我的收藏
			FavActivity.startActivity(getActivity());
			break;
		case R.id.line6: //关于我们
//			AboutActivity.startActivity(getActivity());
			break;
//		case R.id.line7: 
//			showProgressDialog();
//			break;
//		case R.id.user_info_layout:
//			UserInfoActivity.startActivity(getActivity());
//			break;
		default:
			break;
		}
	}
	
	private void showProgressDialog(){
        AlertDialog.Builder builder = new Builder(getActivity());
        builder.setTitle("400-667-0190");
        builder.setPositiveButton("呼叫", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            	Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:4006670190")); 
            	startActivity(intent);  
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create().show();
	}

}
