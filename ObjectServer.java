    import java.io.ObjectInputStream;
    import java.io.ObjectOutputStream;
    import java.io.PrintWriter;
    import java.net.ServerSocket;
    import java.net.Socket;
    
    public class ObjectServer {
    
        public static void main(String[] argv) throws Exception {
    // why 5000 ?
            ServerSocket s = new ServerSocket(5000);
            System.out.println("Server started");
            while (true) {
                Socket t = s.accept();// wait for client to connect
                System.out.println("server connected");
                ObjectInputStream b = new ObjectInputStream(t.getInputStream());
    //so il 3 ostor he4om need to change i dont know how but we nee to get those infos ml bd
                Student received = (Student) b.readObject();
                PrintWriter output = new PrintWriter(t.getOutputStream(), true);
                output.println("Name : " + received.getName()+ \n + " Age: "
                        + received.getAge() + \n +" Mark :"+ recieved.getMark);
                b.close();
                output.close();
                t.close();
            }
    
        }
    }
