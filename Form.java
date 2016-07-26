import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


/**
 * Created by dex on 7/18/16.
 */
public class Form extends JFrame  {

    FormInterface fi;
    private JButton btn = new JButton();
    private JButton btn1 = new JButton();
    private JPanel panel2 = new JPanel();
    private String s;
    Client cl;

    private JPanel panel1 = new JPanel();
    JTextArea textArea = new JTextArea();
    JTextArea textArea1 = new JTextArea();

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Form(Client cl) {
        this.cl=cl;
        FileWork.fileGen();
        setTitle("Chat Window");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 800);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        add(panel1);
        initPanel1(panel1,textArea);

        add(panel2);
        initPanel2(panel2,btn,btn1,textArea1);

        action();
    }

    public void initPanel1 (JPanel panel, JTextArea jta){

        panel.setBounds(10,10,580,580);
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.white);

        jta.setEditable(false);
        jta.setCaretPosition(jta.getDocument().getLength());

        JScrollPane jsp = new JScrollPane(jta);
        panel.add(jsp);


    }

    public void initPanel2 (JPanel panel, JButton enter, JButton close,JTextArea jta){

        panel.setBounds(10,600,580,150);
        panel.setLayout(null);
        panel.setBackground(Color.white);

        JScrollPane jsp = new JScrollPane(jta);
        jsp.setBounds(0,0,580,80);
        jsp.setBackground(Color.blue);
        panel.add(jsp);

        enter.setBackground(Color.white);
        enter.setBounds(450,100, 100, 40);
        enter.setText("ENTER");
        enter.setVisible(true);
        panel.add(enter);

        close.setBackground(Color.white);
        close.setBounds(30,100, 100, 40);
        close.setText("CLOSE");
        close.setVisible(true);
        panel.add(close);

    }

    public void action(){
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea.append("ME:"+textArea1.getText() + "\n");
              //  textArea.append(s + "\n");
                FileWork.append("ME:"+textArea1.getText());
                cl.sendMsg(textArea1.getText());
                textArea1.setText(null);
            }
        });

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        textArea1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if((e.isControlDown() == false) && (e.getKeyText(e.getKeyCode())=="Enter")) {
                    textArea.append("ME:"+textArea1.getText());
                   // textArea.append(s);
                    FileWork.append("ME:"+textArea1.getText());
                    cl.sendMsg(textArea1.getText());

                    textArea1.setText(null);
                }
                if((e.isControlDown() == true) && (e.getKeyText(e.getKeyCode())=="Enter")){
                    textArea1.append("\n");
                }
            }

        });

    }


}

