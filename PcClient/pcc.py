from PyQt5 import QtGui, QtCore, QtWidgets
from comm import Pilot
import sys, queue
import pyautogui as pag

class ReadSig(QtCore.QObject):
    sin=QtCore.pyqtSignal()

class TabletFrame(QtWidgets.QWidget):

    def __init__(self):
        super().__init__()
        self.setupUi()
        self.borderBrush=QtGui.QPen(QtGui.QColorConstants.Black,3)
        self.setAttribute(QtCore.Qt.WA_TranslucentBackground)
        self.__mousePressPos=None
        self.__mouseMovePos=None
        self.sin=ReadSig()
        self.sin.sin.connect(self.onRead)
        self.q=queue.Queue()
        self.isDown=False

    def paintEvent(self, a0: QtGui.QPaintEvent):
        sz=self.size()
        qp=QtGui.QPainter()
        qp.begin(self)
        qp.setRenderHint(QtGui.QPainter.Antialiasing, True)
        qp.setBrush(QtGui.QColorConstants.Black)
        qp.drawRect(0,0,100,12)
        QtCore.QPoint(0,0)
        qp.setPen(self.borderBrush)
        bord=QtGui.QPolygon((QtCore.QPoint(0,12),QtCore.QPoint(sz.width(),12),QtCore.QPoint(sz.width(),sz.height()),QtCore.QPoint(0,sz.height()),QtCore.QPoint(0,0)))
        qp.drawPolyline(bord)
        qp.setPen(QtGui.QColorConstants.White)
        qp.drawText(a0.rect(), QtCore.Qt.AlignTop, '프레임: {0}x{1}'.format(sz.width(),sz.height()-12))
        qp.end()
        #return super().paintEvent(a0)
    
    def setupUi(self):
        self.setWindowFlags(QtCore.Qt.FramelessWindowHint | QtCore.Qt.WindowStaysOnTopHint)
        layout=QtWidgets.QVBoxLayout(self)
        sg=QtWidgets.QSizeGrip(self)
        sg.mouseReleaseEvent=self.mouseReleaseEvent
        layout.addWidget(sg,0,QtCore.Qt.AlignBottom|QtCore.Qt.AlignRight)
        self.setLayout(layout)
        self.resize(200,200)
    
    def mousePressEvent(self, a0: QtGui.QMouseEvent) -> None:
        lp=a0.localPos()
        if a0.button()==QtCore.Qt.LeftButton and lp.x()<100 and lp.y()<12:
            self.__mousePressPos = a0.globalPos()                # global
            self.__mouseMovePos = a0.globalPos() - self.pos()    # local
        return super().mousePressEvent(a0)
    
    def mouseMoveEvent(self, a0: QtGui.QMouseEvent) -> None:
        if self.__mousePressPos==None:
            return
        if QtCore.Qt.LeftButton:
            globalPos=a0.globalPos()
            moved=globalPos-self.__mousePressPos
            if moved.manhattanLength() > 3:
                diff=globalPos-self.__mouseMovePos
                self.move(diff)
                self.__mouseMovePos=globalPos-self.pos()
                return
    
    def mouseReleaseEvent(self, a0: QtGui.QMouseEvent) -> None:
        self.channel.send(self.size())
        if self.__mousePressPos is not None:
            if a0.button() == QtCore.Qt.LeftButton:
                moved = a0.globalPos() - self.__mousePressPos
                if moved.manhattanLength() > 3:
                    # do not call click a0 or so on
                    a0.ignore()
                self.__mousePressPos = None

        return super().mouseReleaseEvent(a0)


    def mouseDown(self, posx, posy):
        self.isDown=True
        sz=self.size()
        self.bx=sz.width()/10000
        self.by=sz.height()/10000
        pag.mouseDown(self.bx*posx, self.by*posy)
    
    def mouseMove(self, posx, posy):
        pag.move(self.bx*posx,self.by*posy)

    def mouseUp(self):
        pag.mouseUp()
        self.isDown=False
    
    def setCom(self, connection=''):
        self.channel=Pilot(connection, self.sin, self.q)
    
    def onRead(self):
        cont=self.q.get().decode()
        for i in range(0,len(cont),8):
            if cont[i]=='.':
                self.mouseUp()
            elif self.isDown:
                self.mouseMove(int(cont[i:i+4]),int(cont[i+4:i+8]))
            else:
                self.mouseDown(int(cont[i:i+4]),int(cont[i+4:i+8]))
        

if __name__ == '__main__':
    app = QtWidgets.QApplication(sys.argv)
    myWindow = TabletFrame()
    myWindow.show()
    app.exec_()