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
           // l fonction he4i l9itha online mnaarfesh esh tnayeek supposee 
           //7aja marbouta bl base de donne bla bla mnaarfsh naamlha
           //5000 he4i why ???
            Socket s = new Socket("localhost", 5000);
            ObjectOutputStream p = new ObjectOutputStream(s.getOutputStream());
              int num = Integer.parseInt(tNum.getText());
            // so basically il star he4a lzmna naamlou new student fl bd dont know how
            // so it needs change
              p.writeObject(new Student(num)); // houni tetbadel haja
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