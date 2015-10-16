package com.hasbrain.usersignupinfo;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep1Activity extends AppCompatActivity {

    private static final int REQUEST_TO_TAKE_PICTURE = 0x1;
    @Bind(R.id.et_email)
    EditText etEmail;
    @Bind(R.id.et_first_name)
    EditText etFirstName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step1);
        setTitle("Step 1");
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_next)
    public void onNextClicked(View v) {
        Intent startActivity2 = new Intent(this, SignUpStep2Activity.class);
        startActivity(startActivity2);
    }
    @OnClick(R.id.iv_avatar)
    public void onAvatarClicked(View v) {
        Intent openCameraToTakePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(openCameraToTakePicture, REQUEST_TO_TAKE_PICTURE);
    }
}
