package ika.test.runningapp.settings;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import ika.test.runningapp.R;
import ika.test.runningapp.databinding.ActivitySettingsBinding;

public class SettingsActivity extends AppCompatActivity {

    private SettingViewModel settingsViewModel;

    private ActivitySettingsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        settingsViewModel = new ViewModelProvider(this).get(SettingViewModel.class);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {

            String[] settings = getResources().getStringArray(R.array.setting_array);

            int cnt = 0;
            for (String settingText: settings) {
                RadioButton rb = new RadioButton(this);
                rb.setText(settingText);
                rb.setId(cnt);
                binding.radioGroupSrc.addView(rb);

                RadioButton rb2 = new RadioButton(this);
                rb2.setText(settingText);
                rb2.setId(cnt + 50);
                binding.radioGroupDst.addView(rb2);

                cnt++;
            }


            if(settingsViewModel.getSrcIndex().getValue()!=null){
                binding.radioGroupSrc.check(settingsViewModel.getSrcIndex().getValue());
            }

            if(settingsViewModel.getDstIndex().getValue()!=null){
                binding.radioGroupDst.check(settingsViewModel.getDstIndex().getValue());
            }

            binding.radioGroupSrc.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    settingsViewModel.setSrcIndex(checkedId);
                    Log.d("testt","SRC: " + checkedId);
                }
            });

            binding.radioGroupDst.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    settingsViewModel.setDstIndex(checkedId);
                    Log.d("testt","DST " + checkedId);
                }
            });

        } else {

            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.setting_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinnerSrc.setAdapter(adapter);
            binding.spinnerDst.setAdapter(adapter);

            if(settingsViewModel.getSrcSpinnerIndex().getValue() != null){
                binding.spinnerSrc.setSelection(settingsViewModel.getSrcSpinnerIndex().getValue());
            }

            if(settingsViewModel.getDstSpinnerIndex().getValue() != null){
                binding.spinnerDst.setSelection(settingsViewModel.getDstSpinnerIndex().getValue());
            }

            binding.spinnerSrc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    settingsViewModel.setSrcSpinnerIndex(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            binding.spinnerDst.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    settingsViewModel.setDstSpinnerIndex(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



        }



    }
}