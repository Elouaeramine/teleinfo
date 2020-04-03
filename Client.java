import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;
    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.ObjectInputStream;
    import java.io.ObjectOutputStream;
    import java.net.Socket;
    import java.net.UnknownHostException;
    
    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JTextArea;
    import javax.swing.JTextField;
    
    public class Client extends JFrame implements ActionListener {
        /**
         *
         */
        private static final long serialVersionUID = -7929505850812237390L;
        JLabel lNum;
        JTextField tNum;
        JButton bInfo;
        JTextArea txtS;
    
        public Client() {
            this.setTitle("Teleinformatique");
            this.setSize(320, 240);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            getContentPane().setLayout(null);
    
            lNum = new JLabel("Student id: ");
            lNum.setBounds(10, 10, 90, 21);
            add(lNum);
    
            tNum = new JTextField();
            tNum.setBounds(105, 10, 90, 21);
            add(tNum);
    
            bInfo = new JButton("Next");
            bInfo.setBounds(200, 40, 90, 21);
            bInfo.addActionListener(this);
            add(bInfo);
    
            txtS = new JTextArea();
            txtS.setBounds(10, 85, 290, 120);
            add(txtS);
    
            this.setVisible(true);
        }
    
        public static void main(String[] args) {
            new Client();
        }
    
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource().equals(bInfo)) {
                try {
                    Information();
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    
       public void Information() throws UnknownHostException, IOException {
           // This is the function eli ki nenzlou aal button tekhdem 
           // Tache Mtaa l function is to send data lel server hasb ma fhemt 
           //7aja marbouta bl base de donne bla bla mnaarfsh naamlha
            Socket s = new Socket("localhost", 5000);
            ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());
            int num = Integer.parseInt(tNum.getText());
            // so basically il star he4a lzmna naamlou new student fl bd dont know how
            // so it needs change
              p.writeInt(num); // Sending just the id not a Class
              p.flush();
              // Here we read the details from server
              BufferedReader response = new BufferedReader(new InputStreamReader(
                      s.getInputStream()));
              txtS.setText("Id "+ num +": " + response.readLine());
              p.close();
              response.close();
              s.close();
          
        }

    }