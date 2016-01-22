package com.hasbrain.usersignupinfo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step3);
        setTitle("Step 3");
        ButterKnife.bind(this);
    }
    @OnClick(R.id.bt_send_email)
    public void onSendEmailClicked(View v) {
        Intent sendEmailIntent = new Intent(Intent.ACTION_SENDTO);
        sendEmailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"test@hasbrain.com"});
        sendEmailIntent.putExtra(Intent.EXTRA_SUBJECT, "User's info result");
        sendEmailIntent.setData(Uri.parse("mailto:"));
        if (sendEmailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(sendEmailIntent);
        } else {
            openPlaystoreForGmail();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    private void openPlaystoreForGmail() {
        Intent openPlayStoreForGmail =new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.gm"));
        if (openPlayStoreForGmail.resolveActivity(getPackageManager()) != null) {
            startActivity(openPlayStoreForGmail);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.gm&hl=en")));
        }
    }
}
