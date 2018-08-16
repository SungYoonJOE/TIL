package com.example.hahaj.studylogin;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.kakao.auth.AuthType;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.network.ErrorResult;
import com.kakao.usermgmt.LoginButton;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.MeV2ResponseCallback;
import com.kakao.usermgmt.response.MeV2Response;
import com.kakao.util.exception.KakaoException;
import com.kakao.util.helper.log.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    private SessionCallback callback;
    Button loginbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginbtn = (Button)findViewById(R.id.loginbtn);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Session session = Session.getCurrentSession();
                session.addCallback(new SessionCallback());
                session.open(AuthType.KAKAO_LOGIN_ALL, LoginActivity.this);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)){
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Session.getCurrentSession().removeCallback(callback);
    }

    public class SessionCallback implements ISessionCallback {

        //로그인에 성공한 상태
        @Override
        public void onSessionOpened() {
            requestMe();
            //redirectSignupActivity();
        }

        //로그인에 실패한 상태
        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            if(exception != null) Logger.d(exception);
        }

        //사용자정보 요청
        private void requestMe(){
            List<String> keys = new ArrayList<>();
            keys.add("propertis.nickname");
            keys.add("propertis.profile_image");

            //사용자정보 요청결과에 대한 callback
            UserManagement.getInstance().me(keys, new MeV2ResponseCallback() {

                //사용자정보 요청 실패
                @Override
                public void onFailure(ErrorResult errorResult) {
                    String message = "failed to get user info. msg=" + errorResult;
                    Logger.d(message);
                }

                //세션 오픈 실패, 세션이 삭제된 경우
                @Override
                public void onSessionClosed(ErrorResult errorResult) {
                    Log.e("SessionCallback::", "onSessionClosed"+errorResult.getErrorMessage());
                }

                //사용자정보 요청에 성공한 경우
                @Override
                public void onSuccess(MeV2Response result) {
                    long pid = result.getId();
                    String nickname = result.getNickname();
                    String profileImagePath = result.getProfileImagePath();
                    String thumnailPath = result.getThumbnailImagePath();
                    String email = result.getKakaoAccount().getEmail();
                    String bday = result.getKakaoAccount().getBirthday();
                    Log.e("Profile pid: ", pid + "");
                    Log.e("Profile nickname: ", nickname + "");
                    Log.e("ProfileImgPath: ", profileImagePath  + "");
                    Log.e("Profile thumnailPath: ", thumnailPath + "");
                    Log.e("Profile email : ", email + "");
                    Log.e("Profile birthday: ", bday + "");
                    redirectMainActivity();
                }
            });
        }
        //사용자 정보 저장
        private void requestUpdateProfile(){
            final Map<String, String> properties = new HashMap<String, String>();

        }
    }
    //메인으로 이동
    protected void redirectMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
