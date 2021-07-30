package onart.pack.pen2pc;

import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfigPage2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigPage2 extends Fragment {

    Configuration parent;
    TextView tv;
    ImageView iv;

    public ConfigPage2() {
        // Required empty public constructor
    }

    public ConfigPage2(Configuration fa){
        parent=fa;
        parent.p2=this;
    }

    public static ConfigPage2 newInstance(Configuration fa) {
        ConfigPage2 fragment = new ConfigPage2(fa);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public void update(){
        switch (MainActivity.cf.getCMethod()){
            case 1: //WIRED
                iv.setImageResource(R.drawable.wired);
                break;
            case 2: //BLUE
                iv.setImageResource(R.drawable.blue);
                break;
            case 3: //INET
                iv.setImageResource(R.drawable.inet);
                break;
            case 4: //LAN
                iv.setImageResource(R.drawable.lan);
                break;
            default: //비정상

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);

        View v=inflater.inflate(R.layout.fragment_config_page2,container,false);
        tv=v.findViewById(R.id.description);
        iv=v.findViewById(R.id.settingImage);
        update();
        v.findViewById(R.id.confirm2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }
}