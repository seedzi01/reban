package com.erban.module.user;

import android.os.Bundle;

import com.erban.AbstractActivity;
import com.erban.R;
import com.erban.module.user.center.control.UserCenterControl;

public class UserInfoModify4BirthdayActivity extends AbstractActivity<UserCenterControl> {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info_modify_brith_layout);
	}
}
