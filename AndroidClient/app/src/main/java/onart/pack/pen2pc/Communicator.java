package onart.pack.pen2pc;

import java.util.LinkedList;

public abstract class Communicator {

    protected LinkedList<Short> buf;

    Communicator(Object info){
        buf=new LinkedList<>();
    }

    public void accumulate(short x, short y){
        buf.add(x);
        buf.add(y);
    }

    public abstract void send();
}
