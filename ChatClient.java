package ChatRoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatClient extends JFrame {
    Socket s = null;
    DataOutputStream dos = null;

    TextField tfTxt = new TextField();
    TextArea taContent = new TextArea();

    public static void main(String[] args) {
        new ChatClient().launchFrame();
    }

    public void launchFrame(){
        setLocation(400, 300);
        this.setSize(300, 300);
        add(tfTxt, BorderLayout.SOUTH);
        add(taContent, BorderLayout.NORTH);
        pack();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                disconnect();
                System.exit(0);
            }
        });
        tfTxt.addActionListener(new TFListener());
        setVisible(true);
        connect();
    }
    private class TFListener implements ActionListener{

        public void actionPerformed(ActionEvent e) {
            String str = tfTxt.getText().trim();

            taContent.setText(str);
            tfTxt.setText("");
            try {
                dos.writeUTF(str);
                dos.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void connect()
    {
        try {
            s = new Socket("127.0.0.1", 8888);
            dos = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void disconnect(){
        try {
            dos.close();
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
