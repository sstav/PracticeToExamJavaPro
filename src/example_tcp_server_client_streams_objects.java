import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

class Data implements Serializable{
    public int num;
    public String Pnum;


    public int getNum(){
        return this.num;
    }

    public String getBinaryString(){
        return this.Pnum;
    }

    public Data(int num){
        this.num = num;
    }

    public Data(){
        this.num = 0;
    }

    public void setBinaryNum(String pnum){
        this.Pnum = pnum;
    }

    public void setNum(int num){
        this.num = num;
    }
}

class ClientThread extends Thread {
    private int port;
    private String ip;
    private Data num;

    public ClientThread(String ip,Data num,int port) {
        this.num = num;
        this.port = port;
        this.ip = ip;
    }

    @Override
    public void run() {
        super.run();
        try {
           handleReadAndWrite();
        }catch (Exception e){
            e.printStackTrace();
        }
        
    }

    private void handleReadAndWrite() throws IOException {
        Socket s = new Socket(ip,port);

        OutputStream outputStream = s.getOutputStream();
        ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);

        InputStream inputStream = s.getInputStream();
        ObjectInputStream objInputStream = new ObjectInputStream(inputStream);


        // Write the Data to Server
        objOutputStream.writeObject(new Data(this.num.getNum()));


        // Get updated Data From Server
        Data n = new Data();
        try {
            n = (Data)objInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Send the Data From Server
        System.out.println("The num is: " + n.getNum() + " & BinaryNum: " + n.getBinaryString());

        // Close Objects
        outputStream.close();
        objOutputStream.close();
        inputStream.close();
        objInputStream.close();
        s.close();
    }
}


class Client implements Serializable {
    public static void main(String args[]) {
        Data n = new Data(5);
        ClientThread s = new ClientThread("127.0.0.1",n,7777);
        s.start();
        try {
            s.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ServerThread extends Thread implements Serializable{
    Socket s = null;

    public ServerThread(Socket s) {
        this.s = s;
    }

    private void handleReadAndWrite() throws IOException, ClassNotFoundException {
        OutputStream outputStream = s.getOutputStream();
        ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
        InputStream inputStream = s.getInputStream();
        ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
        // get Data from Client
        Data n;
        n = (Data)objInputStream.readObject();
        // Process the number and send Data to client
        n.setBinaryNum(Integer.toBinaryString(n.getNum()));
        objOutputStream.writeObject(n);
        objInputStream.close();
        inputStream.close();
        objOutputStream.close();
        outputStream.close();
        s.close();
    }

    @Override
    public void run() {
        super.run();
        try{
            handleReadAndWrite();
        }catch (Exception e){}
    }

}

class Server {
    public Server(String ip, int port) {
        ServerSocket sc = null;
        Socket s = null;
        // Initialize Socket
        try {
            sc = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Accept Socket
        try {
            while (true) {
                s = sc.accept();
                System.out.println("New Client Connected..." + s.getRemoteSocketAddress());
                new ServerThread(s).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Client Connected!
        System.out.println("Done..." + s.getRemoteSocketAddress());
        //CLose Socket
        try {
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        Server s;
        while(true){
            s = new Server("127.0.0.1",7777);
        }
    }
}

public class example_tcp_server_client_streams_objects {

    public void main() {

    }
}
