package com.example.group32_ic03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_IMAGE = "photoProf";
    RadioGroup rg_gender;
    RadioButton rb_female;
    RadioButton rb_male;
    ImageView iv_gender;
    Button button_save;
    EditText et_firstName;
    EditText et_lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("My Profile");

        rg_gender = findViewById(R.id.rg_gender);
        rb_female = findViewById(R.id.rb_female);
        rb_male = findViewById(R.id.rb_male);
        iv_gender = findViewById(R.id.imageView);
        button_save = findViewById(R.id.button_save);
        final String[] flag_image = {""};
        et_firstName = findViewById(R.id.et_firstName);
        et_lastName = findViewById(R.id.et_lastName);

        rg_gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb_female:
                        iv_gender.setImageDrawable(getDrawable(R.drawable.female));
                        flag_image[0] = "female";
                        break;
                    case R.id.rb_male:
                        iv_gender.setImageDrawable(getDrawable(R.drawable.male));
                        flag_image[0] = "male";
                        break;
                    default:
                        break;
                }
            }
        });

        button_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);

                final String firstName = et_firstName.getText().toString();
                final String lastName = et_lastName.getText().toString();
                User user = new User(flag_image[0], firstName, lastName);

                Bundle sentData = new Bundle();
                sentData.putSerializable("user",user);

                intent.putExtra(TAG_IMAGE, sentData);

                startActivity(intent);
                finish();
            }
        });
    }
}
