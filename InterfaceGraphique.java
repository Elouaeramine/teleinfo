import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfaceGraphique extends JFrame implements ActionListener {
    private Client client;
    private JButton boutonOK;
    private JTextField inputId;
    private JTextArea outputArea;
    private JLabel intro;
    private JLabel outro;

    public InterfaceGraphique(){
        client = new Client();
        this.setTitle("Student Data");
        this.setSize(320, 240);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        intro = new JLabel("Student Id: ");
        intro.setBounds(10, 20, 90, 21);
        add(intro);

        inputId = new JTextField();
        inputId.setBounds(10, 40, 90, 21);
        add(inputId);

        boutonOK = new JButton("OK");
        boutonOK.setBounds(110, 40, 60, 21);
        boutonOK.addActionListener(this);
        add(boutonOK);

        intro = new JLabel("Student's Information: ");
        intro.setBounds(10, 85, 290, 21);
        add(intro);

        outputArea = new JTextArea();
        outputArea.setBounds(10, 105, 290, 120);
        outputArea.setEditable(false);
        add(outputArea);

        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource().equals(boutonOK)) {
                int id = Integer.parseInt(inputId.getText());
                client.setNum(id);
                outputArea.setText("Id "+ id +": " + client.getReponse());
        }
    }

    public static void main(String[] args) {
        InterfaceGraphique ihm = new InterfaceGraphique();
    }
}