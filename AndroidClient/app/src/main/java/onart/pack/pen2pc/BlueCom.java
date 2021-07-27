package onart.pack.pen2pc;

import android.bluetooth.BluetoothSocket;

import java.io.InputStream;
import java.io.OutputStream;

public class BlueCom extends Communicator {

    InputStream inChannel;
    OutputStream outChannel;

    BlueCom(Object info){
        super(null);

    }

    @Override
    public void send() {
        buf.clear();
    }

    @Override
    public void recv(){

    }
}
