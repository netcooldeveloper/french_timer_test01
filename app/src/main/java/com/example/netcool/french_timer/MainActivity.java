package com.example.netcool.french_timer;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends ActionBarActivity {
    private NumberPicker numPickerMM;
    private NumberPicker numPickerSS;
    private NumberPicker numPickerCount;

    private TextView txtViewWorktime;

    private Button btnStart;

    private Timer mainTimer;
    private MainTimerTask mainTimerTask;

    private long startTime;
    private long endTime;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findProc();
        initProc();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public class MainTimerTask extends TimerTask {
        @Override
        public void run() {
            long workTime;

            //ここに定周期で実行したい処理を記述します
            endTime = System.currentTimeMillis();

            workTime = endTime - startTime;

//            edTxtWorktime.setText(Long.toString(workTime));
            txtViewWorktime.setText("0");
        }
    }

    private void findProc(){
        numPickerMM = (NumberPicker)findViewById(R.id.numberPickerMM);
        numPickerSS = (NumberPicker)findViewById(R.id.numberPickerSS);
        numPickerCount = (NumberPicker)findViewById(R.id.numberPickerCount);

        txtViewWorktime = (TextView)findViewById(R.id.editTextWorktime);

        btnStart = (Button)findViewById((R.id.buttonStart));
    }

    private void initProc(){
        numPickerMM.setMaxValue(60);
        numPickerMM.setMinValue(0);
        numPickerMM.setValue(4);

        numPickerSS.setMaxValue(59);
        numPickerSS.setMinValue(0);
        numPickerSS.setMinValue(0);

        numPickerCount.setMaxValue(10);
        numPickerCount.setMinValue(0);
        numPickerCount.setValue(5);

        txtViewWorktime.setText("Test");
    }

    public void ButtonStartClick(View view){
//        this.mainTimer = new Timer();
//
//        this.mainTimerTask = new MainTimerTask();
//
//        this.mainTimer.schedule(mainTimerTask,0,1000);
//
//        startTime = System.currentTimeMillis();

        final MyCountDownTimer cdt = new MyCountDownTimer(180000, 1000);

        cdt.start();
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            // カウントダウン完了後に呼ばれる
            txtViewWorktime.setText("0");
            Toast.makeText(getApplicationContext(), "タイマー満了", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // インターバル(countDownInterval)毎に呼ばれる
            txtViewWorktime.setText(Long.toString(millisUntilFinished/1000/60) + ":" + Long.toString(millisUntilFinished/1000%60));
        }
    }
}
