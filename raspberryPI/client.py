#!/usr/bin/python
from twisted.internet.protocol import Protocol, ClientFactory
from sys import stdout
from twisted.internet import defer

class Echo(Protocol):
    def dataReceived(self, data):
	print data
	return
    def sendMessage(self, msg):
	self.transport.write(msg)
	self.transport.flush()
	return

class EchoClientFactory(ClientFactory):
    def startedConnecting(self, connector):
        print "Started to connect."

    def buildProtocol(self, addr):
        print "Connected."
        return Echo()
				
    def clientConnectionLost(self, connector, reason):
        print "Lost connection.  Reason:", reason

    def clientConnectionFailed(self, connector, reason):
        print "Connection failed. Reason:", reason


def gotProtocol(p):
    while(True):
        var = raw_input("Say something: ")
   	if(var=="quit"):
		break
	p.sendMessage(var)

from twisted.internet import reactor
from twisted.internet.endpoints import TCP4ClientEndpoint

point = TCP4ClientEndpoint(reactor, "localhost", 2345)
d = point.connect(EchoClientFactory())
d.addCallback(gotProtocol)
reactor.run()
