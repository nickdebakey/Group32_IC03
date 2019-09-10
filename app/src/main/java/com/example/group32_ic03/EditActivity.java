package com.example.group32_ic03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import static com.example.group32_ic03.MainActivity.TAG_IMAGE;

public class EditActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    RadioButton rb_male;
    RadioButton rb_female;
    ImageView imageView;
    Button button_save;
    EditText et_firstName_edit;
    EditText et_lastName_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        setTitle("My Profile");

        radioGroup = findViewById(R.id.rg_gender_edit);
        rb_male = findViewById(R.id.rb_male_edit);
        rb_female = findViewById(R.id.rb_female_edit);
        imageView = findViewById(R.id.imageView_edit);
        button_save = findViewById(R.id.button_save_edit);
        et_firstName_edit = findViewById(R.id.et_firstName_edit);
        et_lastName_edit = findViewById(R.id.et_lastName_edit);


        final Bundle extrasFromDisplay = getIntent().getExtras().getBundle("toEdit");
        User user = (User) extrasFromDisplay.getSerializable("bundleEdit");

        // setting received data
        if(user!=null){
            et_firstName_edit.setText(user.getFirstName());
            et_lastName_edit.setText(user.getLastName());
            if(user.getGender().equals("male")){
                rb_male.setChecked(true);
                imageView.setImageDrawable(getDrawable(R.drawable.male));
            } else {
                Log.d("demo", "OnEdit User: " + user.getGender());
                rb_female.setChecked(true);
                imageView.setImageDrawable(getDrawable(R.drawable.female));
            }
        }

        // setting the flag on click
        final String[] flag_image = {""};
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.rb_female_edit:
                        imageView.setImageDrawable(getDrawable(R.drawable.female));
                        flag_image[0] = "female";
                        break;
                    case R.id.rb_male_edit:
                        imageView.setImageDrawable(getDrawable(R.drawable.male));
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
                final String firstName = et_firstName_edit.getText().toString();
                final String lastName = et_lastName_edit.getText().toString();
                User user = new User(flag_image[0], firstName, lastName);

                Bundle sendData = new Bundle();
                sendData.putSerializable("userToDisplay", user);
                Intent intent = new Intent(EditActivity.this, DisplayActivity.class);
                intent.putExtra(TAG_IMAGE, sendData);

                // setting results
                setResult(EditActivity.RESULT_OK, intent);
                finish();
            }
        });

    }
}
