from socket import *
import threading

class Pilot:
    def __init__(self, connection, sin, q):
        self.channel=socket.socket(AF_INET, SOCK_STREAM)
        self.bind()
        self.sin=sin
        self.q=q
        try:
            self.channel.connect(connection)
            threading.Thread(target=self.giveTake,daemon=True).start()
        except:
            print('연결 실패')
    
    def giveTake(self):
        while True:
            self.q.put(self.channel.recv(1392))
            self.sin.emit()
    
    def send(self, obj):
        cont=(str(obj.width())+str(obj.height())).encode()
        self.channel.send(cont)