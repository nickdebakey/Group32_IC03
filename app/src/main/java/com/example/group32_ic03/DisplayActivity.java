package com.example.group32_ic03;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    public static final String TAG_IMAGE = "photoProf";
    private static final int REQ_CODE = 5;
    private ImageView iv_profile;
    private Button button_edit_text;
    private TextView tv_firstName_display;
    private TextView tv_lastName_display;
    private TextView tv_gender_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        setTitle("My Profile");

        iv_profile = findViewById(R.id.imageView2);
        button_edit_text = findViewById(R.id.button_edit);
        tv_firstName_display = findViewById(R.id.tv_firstName_display);
        tv_lastName_display = findViewById(R.id.tv_lastName_display);
        tv_gender_display = findViewById(R.id.tv_gender_display);

        final Bundle extrasFromMain = getIntent().getExtras().getBundle(MainActivity.TAG_IMAGE);
        final User user = (User) extrasFromMain.getSerializable("user");

        user.getFirstName();

        if (user!=null){
            tv_firstName_display.setText(user.getFirstName());
            tv_lastName_display.setText(user.getLastName());
            if (user.getGender().equals("male")){
                iv_profile.setImageDrawable(getDrawable(R.drawable.male));
                tv_gender_display.setText(user.getGender());
            } else {
                iv_profile.setImageDrawable(getDrawable(R.drawable.female));
                tv_gender_display.setText(user.getGender());
            }
        }

        button_edit_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEdit = new Intent(DisplayActivity.this, EditActivity.class);
                Bundle  bundle = new Bundle();
                bundle.putSerializable("bundleEdit", user);
                toEdit.putExtra("toEdit", bundle);
                startActivityForResult(toEdit, REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQ_CODE){
            if (resultCode==RESULT_OK){
                final Bundle extrasFromMain = data.getExtras().getBundle(TAG_IMAGE);
                final User user = (User) extrasFromMain.getSerializable("userToDisplay");

                if (user!=null) {
                    if (user.getGender().equals("male")) {
                        iv_profile.setImageDrawable(getDrawable(R.drawable.male));
                        tv_firstName_display.setText(user.getFirstName());
                        tv_lastName_display.setText(user.getLastName());
                        tv_gender_display.setText(user.getGender());
                    }
                    else {
                        iv_profile.setImageDrawable(getDrawable(R.drawable.female));
                        tv_firstName_display.setText(user.getFirstName());
                        tv_lastName_display.setText(user.getLastName());
                        tv_gender_display.setText(user.getGender());
                    }
                }
            }
        }
    }
}
