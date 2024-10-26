package MenuDialog;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import MenuSetUp.MyButton;
import res.LoadResource;

public class NoticeDialog extends JDialog {
    public NoticeDialog(JFrame parent) {
        super(parent);
        setSize(400, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);


        addMessage();

        MyButton okButton = new MyButton("yes");
        getContentPane().add(okButton, BorderLayout.SOUTH);
        okButton.addActionListener(e -> setVisible(false));
    }

    void addMessage() {
        JLabel message = new JLabel();
        message.setText("Please enter Player's name ");
        message.setHorizontalAlignment(SwingConstants.CENTER);
        message.setVerticalAlignment(SwingConstants.CENTER);
        message.setFont(LoadResource.Courier_New_Bold_20);
        getContentPane().add(message);
    }
}
