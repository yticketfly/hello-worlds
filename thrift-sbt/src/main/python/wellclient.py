#!/usr/bin/env python
# sudo easy_install thrift==0.9.2
# cd thrift-sbt/src/main/python/ && thrift --gen py ../thrift/wellservice.thrift
# http://themayesfamily.com/blogs/b/2013/01/how-to-get-a-python-thrift-client-running-on-mac-os/
import sys
sys.path.append('./gen-py')

from thriftpython import WellService
from thriftpython.ttypes import *

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TBinaryProtocol

try:

  # Make socket
  transport = TSocket.TSocket('localhost', 7911)

  # Buffering is critical. Raw sockets are very slow
  transport = TTransport.TBufferedTransport(transport)

  # Wrap in a protocol
  protocol = TBinaryProtocol.TBinaryProtocol(transport)

  # Create a client to use the protocol encoder
  client = WellService.Client(protocol)

  # Connect!
  transport.open()

  product = client.add(4,5)
  print '4*5=%d' % (product)

  # Close!
  transport.close()

except Thrift.TException, tx:
  print '%s' % (tx.message)