import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginProgram extends JFrame {
    private JButton loginButton, signUpButton, exitButton;
    
    public LoginProgram() {
        setTitle("Login Program");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel imagePanel = new JPanel();
        ImageIcon image1 = new ImageIcon("src/pic1.jpg");
        ImageIcon image2 = new ImageIcon("src/pic2.png");
        JLabel label1 = new JLabel(image1);
        JLabel label2 = new JLabel(image2);
        imagePanel.add(label1);
        imagePanel.add(label2);
        imagePanel.setBackground(Color.white);
        add(imagePanel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("로그인");
        exitButton = new JButton("프로그램 종료");
        buttonPanel.add(loginButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.CENTER);
        
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginSystem LS = new LoginSystem();
                LS.start();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(LoginProgram.this, "프로그램을 종료하시겠습니까?", "프로그램 종료", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginProgram();
            }
        });
    }
}
