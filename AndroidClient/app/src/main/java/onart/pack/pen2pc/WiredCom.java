package onart.pack.pen2pc;

import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbManager;

public class WiredCom extends Communicator{

    WiredCom(Object info){
        super(null);
        assert info instanceof Intent;
        UsbAccessory accessory=((Intent)info).getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
    }

    @Override
    public void send() {

    }
    @Override
    public void recv() {

    }

    @Override
    public void echo() {

    }
}
