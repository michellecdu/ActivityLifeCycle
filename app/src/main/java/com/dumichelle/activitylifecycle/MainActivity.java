package com.dumichelle.activitylifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    EditText edText;
    int seekValue;
    private static final String TAG = "MAIN_ACTIVITY";
    private static final String COUNT_STORAGE_KEY_ON_CREATE = "stored_count";
    private static final String COUNT_STORAGE_KEY_ON_START = "stored_count";
    private static final String COUNT_STORAGE_KEY_ON_RESUME = "stored_count";
    private static final String COUNT_STORAGE_KEY_ON_PAUSE = "stored_count";
    private static final String COUNT_STORAGE_KEY_ON_STOP = "stored_count";
    private static final String COUNT_STORAGE_KEY_ON_RESTART = "stored_count";
    private static final String COUNT_STORAGE_KEY_ON_DESTROY = "stored_count";

    Button topleftbutton;
    Button toprightbutton;
    Button bottomleftbutton;
    Button bottomrightbutton;
    SeekBar seekBar;
    int countOnCreate = 0;
    int countOnStart = 0;
    int countOnResume = 0;
    int countOnPause = 0;
    int countOnStop = 0;
    int countOnRestart = 0;
    int countOnDestroy = 0;
    int countOnCreateSP = 0;
    int countOnStartSP = 0;
    int countOnResumeSP = 0;
    int countOnPauseSP = 0;
    int countOnStopSP = 0;
    int countOnRestartSP = 0;
    int countOnDestroySP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        countOnCreate++;
        topleftbutton = findViewById(R.id.topleftbutton);
        bottomleftbutton = findViewById(R.id.bottomleftbutton);
        toprightbutton = findViewById(R.id.toprightbutton);
        bottomrightbutton = findViewById(R.id.bottomrightbutton);
        edText = findViewById(R.id.editText);
        seekBar = findViewById(R.id.seeker);
        topleftbutton.setOnClickListener(this);
        bottomleftbutton.setOnClickListener(this);
        toprightbutton.setOnClickListener(this);
        bottomrightbutton.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                topleftbutton.setTextSize(seekValue);
                toprightbutton.setTextSize(seekValue);
                bottomleftbutton.setTextSize(seekValue);
                bottomrightbutton.setTextSize(seekValue);
            }
        });
        sharedPreferences = getSharedPreferences("Values", MODE_PRIVATE);
        countOnCreateSP = sharedPreferences.getInt(COUNT_STORAGE_KEY_ON_CREATE, 0);
        countOnCreateSP++;
        editor = sharedPreferences.edit();
        editor.putInt(COUNT_STORAGE_KEY_ON_CREATE, countOnCreateSP);
        editor.apply();

        setInitialValues();
    }

    public void resetClick(View v) {
        countOnCreate = 0;
        countOnStart = 0;
        countOnResume = 0;
        countOnPause = 0;
        countOnStop = 0;
        countOnRestart = 0;
        countOnDestroy = 0;
        topleftbutton.setText("0");
        toprightbutton.setText("0");
        bottomleftbutton.setText("0");
        bottomrightbutton.setText("0");
        storeValues();
    }

    private void updateTextSize(int progress) {
        Toast.makeText(getApplicationContext(), "seekbar progress: " + progress, Toast.LENGTH_LONG);
    }

    public void onClick(View v) {
        TextView x = (TextView) v;
        x.setText(""+(Integer.parseInt(x.getText().toString())+1));
        storeValues();
    }

    private void setInitialValues() {
        topleftbutton.setText("" + sharedPreferences.getInt("topleft", 0));
        toprightbutton.setText("" + sharedPreferences.getInt("topright", 0));
        bottomleftbutton.setText("" + sharedPreferences.getInt("bottomleft", 0));
        bottomrightbutton.setText("" + sharedPreferences.getInt("bottomright", 0));
        countOnCreate=sharedPreferences.getInt("onCreate", 0);
        countOnStart=sharedPreferences.getInt("onStart", 0);
        countOnResume=sharedPreferences.getInt("onResume", 0);
        countOnPause=sharedPreferences.getInt("onPause", 0);
        countOnStop=sharedPreferences.getInt("onStop", 0);
        countOnRestart=sharedPreferences.getInt("onRestart", 0);
        countOnDestroy=sharedPreferences.getInt("onDestroy", 0);

        countOnCreateSP=sharedPreferences.getInt("onCreate since App Installation", countOnCreateSP);
        countOnStartSP=sharedPreferences.getInt("onStart since App Installation", countOnStartSP);
        countOnResumeSP=sharedPreferences.getInt("onResume since App Installation", countOnResumeSP);
        countOnPauseSP=sharedPreferences.getInt("onPause since App Installation", countOnPauseSP);
        countOnStopSP=sharedPreferences.getInt("onStop since App Installation", countOnStopSP);
        countOnRestartSP=sharedPreferences.getInt("onRestart since App Installation", countOnRestartSP);
        countOnDestroySP=sharedPreferences.getInt("onDestroy since App Installation", countOnDestroySP);
    }

    private void storeValues() {
        editor.putInt("topleft", Integer.parseInt(topleftbutton.getText().toString()));
        editor.putInt("topright", Integer.parseInt(toprightbutton.getText().toString()));
        editor.putInt("bottomleft", Integer.parseInt(bottomleftbutton.getText().toString()));
        editor.putInt("bottomright", Integer.parseInt(bottomrightbutton.getText().toString()));
        editor.putInt("onCreate", countOnCreate);
        editor.putInt("onStart", countOnStart);
        editor.putInt("onResume", countOnResume);
        editor.putInt("onPause", countOnPause);
        editor.putInt("onStop", countOnStop);
        editor.putInt("onRestart", countOnRestart);
        editor.putInt("onDestroy", countOnDestroy);
        editor.apply();
        System.out.println("Values - Data Set 1 ------------------------------------------");
        System.out.println("Values onCreate: " + countOnCreate);
        System.out.println("Values onStart: " + countOnStart);
        System.out.println("Values onResume: " + countOnResume);
        System.out.println("Values onPause: " + countOnPause);
        System.out.println("Values onStop " + countOnStop);
        System.out.println("Values onRestart: " + countOnRestart);
        System.out.println("Values onDestroy: " + countOnDestroy);
        System.out.println("Values Since App Installation - Data Set 2 --------------------");
        System.out.println("Values onCreate: " + countOnCreateSP);
        System.out.println("Values onStart: " + countOnStartSP);
        System.out.println("Values onResume: " + countOnResumeSP);
        System.out.println("Values onPause: " + countOnPauseSP);
        System.out.println("Values onStop: " + countOnStopSP);
        System.out.println("Values onRestart: " + countOnRestartSP);
        System.out.println("Values onDestroy: " + countOnDestroySP);
        System.out.println("===============================================================");

    }
    private void erase() {
        editor.remove("Values").commit();
    }


    @Override
    protected void onStart() {
        super.onStart();
        countOnStart++;
        storeValues();
        sharedPreferences = getSharedPreferences("Values", MODE_PRIVATE);
        countOnStartSP = sharedPreferences.getInt(COUNT_STORAGE_KEY_ON_START, 0);
        countOnStartSP++;
        editor = sharedPreferences.edit();
        editor.putInt(COUNT_STORAGE_KEY_ON_START, countOnStartSP);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        countOnResume++;
        storeValues();
        sharedPreferences = getSharedPreferences("Values", MODE_PRIVATE);
        countOnResumeSP = sharedPreferences.getInt(COUNT_STORAGE_KEY_ON_RESUME, 0);
        countOnResumeSP++;
        editor = sharedPreferences.edit();
        editor.putInt(COUNT_STORAGE_KEY_ON_RESUME, countOnResumeSP);
    }

    @Override
    protected void onPause() {
        super.onPause();
        countOnPause++;
        storeValues();
        sharedPreferences = getSharedPreferences("Values", MODE_PRIVATE);
        countOnPauseSP = sharedPreferences.getInt(COUNT_STORAGE_KEY_ON_PAUSE, 0);
        countOnPauseSP++;
        editor = sharedPreferences.edit();
        editor.putInt(COUNT_STORAGE_KEY_ON_PAUSE, countOnPauseSP);
        editor.apply();
    }

    @Override
    protected void onStop() {
        super.onStop();
        countOnStop++;
        storeValues();
        sharedPreferences = getSharedPreferences("Values", MODE_PRIVATE);
        countOnStopSP = sharedPreferences.getInt(COUNT_STORAGE_KEY_ON_STOP, 0);
        countOnStopSP++;
        editor = sharedPreferences.edit();
        editor.putInt(COUNT_STORAGE_KEY_ON_STOP, countOnStopSP);
        editor.apply();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        countOnRestart++;
        storeValues();
        sharedPreferences = getSharedPreferences("Values", MODE_PRIVATE);
        countOnRestartSP = sharedPreferences.getInt(COUNT_STORAGE_KEY_ON_RESTART, 0);
        countOnRestartSP++;
        editor = sharedPreferences.edit();
        editor.putInt(COUNT_STORAGE_KEY_ON_RESTART, countOnRestartSP);
        editor.apply();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countOnDestroy++;
        storeValues();
        sharedPreferences = getSharedPreferences("Values", MODE_PRIVATE);
        countOnDestroySP = sharedPreferences.getInt(COUNT_STORAGE_KEY_ON_DESTROY, 0);
        countOnDestroySP++;
        editor = sharedPreferences.edit();
        editor.putInt(COUNT_STORAGE_KEY_ON_DESTROY, countOnDestroySP);
        editor.apply();
    }
}