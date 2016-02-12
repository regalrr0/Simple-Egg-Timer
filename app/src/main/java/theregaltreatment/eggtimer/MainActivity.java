package theregaltreatment.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String time;
    String initialTime = "5:00";
    String endTime = "0:00";
    SeekBar bar;
    Button stop, go;
    int instance;
    int minutes;
    int sec;
    MediaPlayer mediaPlayer;
    TextView timer;
    CountDownTimer ctimer;

    public void stop(View view){
        ctimer.cancel();
        timer.setText(initialTime);
        bar.setProgress(300000);
        go.setEnabled(true);
        go.setVisibility(View.VISIBLE);
        stop.setEnabled(false);
        stop.setVisibility(View.INVISIBLE);
        bar.setEnabled(true);
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
    }
    public void go (View view){
        instance = bar.getProgress();
        bar.setEnabled(false);
        ctimer = new CountDownTimer(instance, 1000){

            public void onTick (long millis){
                double number= millis/1000.0;
                minutes = (int) number/60;
                sec = (int) number % 60;
                if(sec < 10){
                    time = minutes + ":" + "0" + sec;
                }
                else {
                    time = minutes + ":" + sec;
                }
                timer.setText(time);

                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
            }
            public void onFinish(){
                mediaPlayer.start();
                timer.setText(endTime);
            }
        }.start();
        go.setEnabled(false);
        go.setVisibility(View.INVISIBLE);
        stop.setEnabled(true);
        stop.setVisibility(View.VISIBLE);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go = (Button) findViewById(R.id.go);
        stop = (Button) findViewById(R.id.stop);
        stop.setEnabled(false);
        bar = (SeekBar) findViewById(R.id.seekBar);
        timer = (TextView) findViewById(R.id.timer);
        mediaPlayer = MediaPlayer.create(this, R.raw.threwground);
        bar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


                double number= progress/1000.0;
                minutes = (int) number/60;
                sec = (int) number % 60;
                if(sec < 10){
                    time = minutes + ":" + "0" + sec;
                }
                else {
                    time = minutes + ":" + sec;
                }
                timer.setText(time);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

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
}
