package onart.pack.pen2pc;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.util.Log;

public class WiredCom extends Communicator{

    UsbDevice device;
    private static final String ACTION_USB_PERMISSION="onart.pack.pen2pc.USB_PERMISSION";

    private final BroadcastReceiver usbReceiver=new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action= intent.getAction();
            if(ACTION_USB_PERMISSION.equals(action)){
                synchronized (this){
                    device=intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if(intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED,false)){
                        if(device!=null){

                        }
                    }
                    else{
                        Log.d("PermissionDenied","Permission denied for device "+device);
                    }
                }
            }
        }
    };

    WiredCom(Activity act){
        super(null);
        UsbManager usbManager=(UsbManager)act.getSystemService(Context.USB_SERVICE);
        PendingIntent permissionIntent= PendingIntent.getBroadcast(act, 0, new Intent(ACTION_USB_PERMISSION), 0);
        IntentFilter filter=new IntentFilter(ACTION_USB_PERMISSION);
        act.registerReceiver(usbReceiver, filter);
        usbManager.requestPermission(device, permissionIntent);
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
