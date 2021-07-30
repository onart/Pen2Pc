package onart.pack.pen2pc;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;

public class Configuration extends FragmentActivity {

    Configuration self;
    public ConfigPage2 p2;
    ConfigPageAdapter cpa;
    ViewPager2 vp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        self=this;
        setContentView(R.layout.activity_configuration);
        cpa=new ConfigPageAdapter(this);
        vp2=findViewById(R.id.vp2);
        vp2.setAdapter(cpa);
        vp2.setUserInputEnabled(false);
    }

    @Override
    public void onBackPressed() {
        int item=vp2.getCurrentItem();
        if(item==0) super.onBackPressed();
        else vp2.setCurrentItem(item-1);
    }

    public void toPage2(){
        vp2.setCurrentItem(1);
        if(p2!=null)p2.update();
    }


    private class ConfigPageAdapter extends FragmentStateAdapter{

        public ConfigPageAdapter(FragmentActivity fa){
            super(fa);
        }

        @NonNull
        @NotNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return ConfigPage1.newInstance(self);   //선택 페이지
                case 1:
                    return ConfigPage2.newInstance(self);   //안내 페이지 (PC 앱을 설치해야 합니다+세팅은 이렇게)
            }
            return new ConfigPage1();
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}