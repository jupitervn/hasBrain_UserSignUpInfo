package com.hasbrain.usersignupinfo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep1Activity extends AppCompatActivity {
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
        if (savedInstanceState == null) {
            etEmail.setError("no email");
        }
    }

    @OnClick(R.id.bt_next)
    public void onNextClicked(View v) {
        Intent startActivity2 = new Intent(this, SignUpStep2Activity.class);
        startActivity(startActivity2);
    }


}
