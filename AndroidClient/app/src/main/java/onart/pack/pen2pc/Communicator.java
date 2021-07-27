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

    public abstract void send();    //보낼 정보: 터치 지점, 내부 영역
    public abstract void recv();    //받을 정보: 영향을 받을 window 영역
}
