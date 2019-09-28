package ChatRoom;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;

public class ChatServer {
        boolean started  = false;
        ServerSocket ss = null;
    public static void main(String[] args) {
        new ChatServer().start();
    }
    public void start()
    {
        try {
            ss = new ServerSocket(8888);
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        try{
            while(started)
            {
                Socket s = ss.accept();
                Client c = new Client(s);
                System.out.println("client connected");
                new Thread(c).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ss.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    class Client implements Runnable{
        private Socket s;
        private DataInputStream dis = null;
        private boolean bConnected = false;

        public Client(Socket s)
        {
            this.s = s;
            try{
                dis = new DataInputStream(s.getInputStream());
                bConnected = true;
            }catch (Exception e)
            {
                e.printStackTrace();
            }finally {
                try {
                    if(dis!= null)
                    {
                        dis.close();
                    }
                    if(s != null)
                    {
                        s.close();
                    }
                }catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

        public void run() {

        }
    }

}
