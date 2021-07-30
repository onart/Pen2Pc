package onart.pack.pen2pc;

import android.content.SharedPreferences;

public class Config {
    public enum connection {NOTSET,WIRED,BLUE,INET,P2P}
    SharedPreferences sp;
    String address;
    int cMethod;

    int getCMethod(){
        return cMethod;
    }

    public Config(SharedPreferences sp){
        this.sp=sp;
        load();
    }

    void setcMethod(connection c){
        cMethod=c.ordinal();
        save();
    }

    void save(){
        SharedPreferences.Editor editor=sp.edit();
        editor.putInt("NecTConIoN",cMethod);
        editor.apply();
    }

    void load(){
        cMethod=sp.getInt("NecTConIoN", connection.NOTSET.ordinal());
    }
}
