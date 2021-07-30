package onart.pack.pen2pc;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfigPage1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfigPage1 extends Fragment {

    Configuration parent;

    public ConfigPage1() {
        // Required empty public constructor
    }

    public ConfigPage1(Configuration fa){
        parent=fa;
    }

    public static ConfigPage1 newInstance(Configuration fa) {
        ConfigPage1 fragment = new ConfigPage1(fa);
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
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

        View v=inflater.inflate(R.layout.fragment_config_page1,container,false);
        v.findViewById(R.id.select_wired).setOnClickListener(v1 -> {
            MainActivity.cf.setcMethod(Config.connection.WIRED);
            parent.toPage2();
        });
        v.findViewById(R.id.select_blue).setOnClickListener(v1 -> {
            MainActivity.cf.setcMethod(Config.connection.BLUE);
            parent.toPage2();
        });
        v.findViewById(R.id.select_inet).setOnClickListener(v1 -> {
            MainActivity.cf.setcMethod(Config.connection.INET);
            parent.toPage2();
        });
        v.findViewById(R.id.select_lan).setOnClickListener(v1 -> {
            MainActivity.cf.setcMethod(Config.connection.P2P);
            parent.toPage2();
        });
        return v;
    }
}