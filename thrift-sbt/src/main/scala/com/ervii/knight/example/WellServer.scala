package com.ervii.knight.example
import com.ervii.knight.primitives.thriftjava.WellService
import org.apache.thrift.server.{TServer, TThreadPoolServer}
import org.apache.thrift.transport.TServerSocket


/**
 * http://thrift-tutorial.readthedocs.org/en/latest/usage-example.html
 * https://chamibuddhika.wordpress.com/2011/10/02/apache-thrift-quickstart-tutorial/
 *
 * public class Server {

    private void start() {
        try {
            TServerSocket serverTransport = new TServerSocket(7911);

            ArithmeticService.Processor processor = new ArithmeticService.Processor(new ArithmeticServiceImpl());

            TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport).
                    processor(processor));
            System.out.println("Starting server on port 7911 ...");
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server srv = new Server();
        srv.start();
    }

}
 */
object WellServer extends App {
  println("Server UP!")

  val serverTransport = new TServerSocket(7911)
  val processor = new WellService.Processor(new WellServiceImpl()) // https://issues.apache.org/jira/browse/THRIFT-3035

  val tArgs = new TThreadPoolServer.Args(serverTransport)
  val server: TServer = new TThreadPoolServer(tArgs.processor(processor))

  System.out.println("Starting the simple server...")
  server.serve()

}




class WellServiceImpl extends WellService.Iface {
  override def add(n1: Int, n2: Int): Int = n1 + n2
}