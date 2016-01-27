package com.hasbrain.usersignupinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpStep2Activity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener {

    public static final int SALARY_STEP = 100;
    @Bind(R.id.sp_jobs)
    Spinner spJobs;
    @Bind(R.id.sb_salary)
    SeekBar sbSalary;
    @Bind(R.id.tv_salary_label)
    TextView tvSalaryLabel;
    @Bind(R.id.tv_max_salary)
    TextView tvMaxSalaryLabel;

    public static final int MAX_SALARY = 100000;
    public static final int MIN_SALARY = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_step2);
        ButterKnife.bind(this);
        setTitle("Step 2");
        String[] jobs = getResources().getStringArray(R.array.jobs);
        spJobs.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, jobs));
        sbSalary.setMax(MAX_SALARY / SALARY_STEP);
        if (savedInstanceState == null) {
            onProgressChanged(sbSalary, 0, false);
        }
        sbSalary.setOnSeekBarChangeListener(this);
        tvMaxSalaryLabel.setText("$" + MAX_SALARY);
    }

    @OnClick(R.id.bt_done)
    public void onDoneClicked(View v) {
//        Intent startActivity3Intent = new Intent(this, SignUpStep3Activity.class);
//        startActivity(startActivity3Intent);
//        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_down);
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        tvSalaryLabel.setText(getString(R.string.salary_text_label, progress* SALARY_STEP));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
