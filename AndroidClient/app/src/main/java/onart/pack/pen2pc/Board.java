package onart.pack.pen2pc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Board extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        findViewById(R.id.pentoggle).setOnClickListener(v -> {
            boolean isOn=!v.isActivated();
            v.setActivated(isOn);
            if(isOn){
                ((Button)v).setText(R.string.off);
            }
            else{
                ((Button)v).setText(R.string.on);
            }
        });
        findViewById(R.id.eraser).setOnClickListener(v -> clearRecord());
        findViewById(R.id.eraser2).setOnClickListener(v -> Toast.makeText(getApplicationContext(),"예",Toast.LENGTH_SHORT).show());
        //1. PC 연결
        //2. 스레드 시작
        //3. 스레드 종료
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        if(y>1900f) return super.onTouchEvent(event);
        else x+=1;
        return super.onTouchEvent(event);
    }

    void clearRecord(){
        new DialogYN(R.string.sure2erase,R.id.eraser2,0).show(getSupportFragmentManager(),null);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        findViewById(R.id.eraser2).callOnClick();
        super.onConfigurationChanged(newConfig);
    }
}