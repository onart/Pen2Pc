package onart.pack.pen2pc;

import java.util.LinkedList;

public abstract class Communicator {

    protected LinkedList<Short> buf;

    Communicator(Object info){
        buf=new LinkedList<>();
    }

    public void accumulate(short x, short y){   //sync
        buf.add(x);
        buf.add(y);
    }

    int f2b(float f){
        return Float.floatToIntBits(f);
    }

    public abstract void send();    //sync
    public abstract void recv();
    public abstract void echo();
}
