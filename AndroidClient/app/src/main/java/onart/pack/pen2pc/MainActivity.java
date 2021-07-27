package onart.pack.pen2pc;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private long timeStamp=0;
    public static Config cf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        cf.load(getPreferences(Context.MODE_PRIVATE));

        /*
        findViewById(R.id.term).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        람다 표현식 사용 전*/
        findViewById(R.id.term).setOnClickListener(v -> finish());
        findViewById(R.id.start).setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Board.class)));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        long now=System.currentTimeMillis();
        if(now-timeStamp>2000) {
            Toast.makeText(this,"한 번 더 누르면 종료합니다.",Toast.LENGTH_LONG).show();
            timeStamp=now;
        }
        else {
            finish();
        }
    }
}