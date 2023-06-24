import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.HashMap;

public class LoginSystem extends JFrame implements ActionListener {
    private HashMap<String, String> users;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel loginPanel;
    private JPanel registerPanel;
    private CardLayout cardLayout;

    public LoginSystem() {
        users = new HashMap<>();

        setTitle("로그인 시스템");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        setLayout(cardLayout);

        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("아이디:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("비밀번호:");
        passwordField = new JPasswordField();
        loginButton = new JButton("로그인");
        registerButton = new JButton("회원가입");

        loginButton.addActionListener(this);
        registerButton.addActionListener(this);

        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);

        registerPanel = new JPanel();
        registerPanel.setLayout(new GridLayout(3, 2));

        JLabel registerUsernameLabel = new JLabel("아이디:");
        JTextField registerUsernameField = new JTextField();
        JLabel registerPasswordLabel = new JLabel("비밀번호:");
        JPasswordField registerPasswordField = new JPasswordField();
        JButton registerConfirmButton = new JButton("가입하기");
        JButton registerCancelButton = new JButton("취소");

        registerConfirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = registerUsernameField.getText();

                if (users.containsKey(username)) {
                    JOptionPane.showMessageDialog(LoginSystem.this, "이미 존재하는 아이디입니다.");
                } else {
                    String password = new String(registerPasswordField.getPassword());
                    users.put(username, password);
                    JOptionPane.showMessageDialog(LoginSystem.this, "회원가입이 완료되었습니다.");
                    cardLayout.show(LoginSystem.this.getContentPane(), "loginPanel");
                }
            }
        });

        registerCancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(LoginSystem.this.getContentPane(), "loginPanel");
            }
        });

        registerPanel.add(registerUsernameLabel);
        registerPanel.add(registerUsernameField);
        registerPanel.add(registerPasswordLabel);
        registerPanel.add(registerPasswordField);
        registerPanel.add(registerConfirmButton);
        registerPanel.add(registerCancelButton);

        add(loginPanel, "loginPanel");
        add(registerPanel, "registerPanel");

        loadUsersFromFile();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                saveUsersToFile();
            }
        });

        setVisible(true);
    }

    private void saveUsersToFile() {
        try {
            FileOutputStream fileOut = new FileOutputStream("users.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(users);
            out.close();
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadUsersFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("users.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            users = (HashMap<String, String>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException | ClassNotFoundException e) {
            users = new HashMap<>();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("로그인")) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (users.containsKey(username) && users.get(username).equals(password)) {
                JOptionPane.showMessageDialog(this, "로그인 성공!");
        		BlockGame a = new BlockGame();
        		a.startGame();

            } else {
                JOptionPane.showMessageDialog(this, "아이디 또는 비밀번호가 잘못되었습니다.");
            }
        } else if (e.getActionCommand().equals("회원가입")) {
            cardLayout.show(this.getContentPane(), "registerPanel");
        }
    }

    public void start() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginSystem();
            }
        });
    }
    
}
