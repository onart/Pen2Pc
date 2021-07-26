package onart.pack.pen2pc;

import android.content.SharedPreferences;

public class Config {
    public enum connection {NOTSET,WIRED,BLUE,INET,P2P}
    String address;
    int cMethod;

    int getCMethod(){
        return cMethod;
    }

    void setcMethod(connection c){
        cMethod=c.ordinal();
    }

    public void save(SharedPreferences sharedPref){
        SharedPreferences.Editor editor=sharedPref.edit();
        editor.putInt("connection",cMethod);
        editor.apply();
    }

    public void load(SharedPreferences sharedPref){
        cMethod=sharedPref.getInt("NecTConIoN", connection.NOTSET.ordinal());
    }
}
