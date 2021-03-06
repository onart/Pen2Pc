package onart.pack.pen2pc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentContainerView;

import android.content.res.Configuration;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class Board extends AppCompatActivity {

    private DefaultPaint paintSheet;
    private boolean isLand;
    private Thread com;
    public static int xmaxp=0, ymaxp=0, xmaxl=0, ymaxl=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);
        findViewById(R.id.pentoggle).setOnClickListener(v -> {
            boolean isOn=!v.isActivated();
            v.setActivated(isOn);
            if(isOn){
                ((Button)v).setText(R.string.off);
                paintSheet.on();
            }
            else{
                ((Button)v).setText(R.string.on);
                paintSheet.off();
            }
        });
        findViewById(R.id.eraser).setOnClickListener(v -> clearRecord());
        findViewById(R.id.eraser2).setOnClickListener(v -> paintSheet.erase());
        paintSheet=findViewById(R.id.paintSheet);
        //1. PC 연결
        //2. 스레드 시작
        //3. 스레드 종료
    }

    void clearRecord(){
        new DialogYN(R.string.sure2erase,R.id.eraser2,0).show(getSupportFragmentManager(),null);
    }

    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        findViewById(R.id.eraser2).callOnClick();
        isLand=newConfig.orientation==Configuration.ORIENTATION_LANDSCAPE;
        super.onConfigurationChanged(newConfig);
    }

    void getMax(){
        if(xmaxl>0)return;
        if(getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            xmaxl=paintSheet.getWidth();
            ymaxl=paintSheet.getHeight();
            xmaxp=ymaxl;
            ymaxp=xmaxl;
            isLand=true;
        }
        else{
            xmaxp=paintSheet.getWidth();
            ymaxp=paintSheet.getHeight();
            xmaxl=ymaxp;
            ymaxl=xmaxp;
            isLand=false;
        }
    }

    void setRatio(int x, int y){
        ViewGroup.LayoutParams lp=paintSheet.getLayoutParams();
        getMax();
        if(x==0){
            lp.width=ViewGroup.LayoutParams.MATCH_PARENT;
            lp.height=ViewGroup.LayoutParams.MATCH_PARENT;
        }
        else if(x>y){
            double yOverX=(double)y/x;
            lp.width=ViewGroup.LayoutParams.MATCH_PARENT;
            if(isLand){
                lp.height=(int) (yOverX*xmaxl);
            }
            else{
                lp.height=(int) (yOverX*xmaxp);
            }
        }
        else{
            lp.height=ViewGroup.LayoutParams.MATCH_PARENT;
            double xOverY=(double)x/y;
            if(isLand){
                lp.width=(int) (xOverY*ymaxl);
            }
            else{
                lp.width=(int) (xOverY*ymaxp);
            }
        }
        paintSheet.setLayoutParams(lp);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}