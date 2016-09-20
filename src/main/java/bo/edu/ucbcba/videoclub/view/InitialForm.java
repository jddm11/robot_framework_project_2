package bo.edu.ucbcba.videoclub.view;

import bo.edu.ucbcba.videoclub.controller.Session;
import bo.edu.ucbcba.videoclub.controller.UserController;
import com.intellij.uiDesigner.core.DimensionInfo;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class InitialForm extends JFrame {
    private JPanel rootPanel;
    private JButton MoviesButton;
    private JButton CustomersButton;
    private JButton GamesButton;
    private JButton logoutButton;
    private JButton changePasswordAdminButton;
    private JButton addUserButton;
    private JButton viewUsersButton;
    private UserController userController;

    //Creo que es necesario que esta clase tenga un contructor propio para cada ventana, o almenos asi es como logre hacer
    //si logran hacerlo de una mejor forma y mas limpia le meten no mas
    //HomeGamesForm parent
    public InitialForm() {
        super("Welcome");
        setContentPane(rootPanel);
        setSize(700, 500);
        //setSize(600, 400);
        this.setLocationRelativeTo(null);
        setResizable(false);
        userController = new UserController();
        MoviesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MoviesZone();
            }
        });
        CustomersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchClientsForm();
            }
        });
        launchImage();
        GamesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchGames();
            }
        });
        //InitialForm form = new InitialForm();

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchLogout();
            }
        });
        launchImage();
        if (Session.getSession().getUtype() == 2) {
            addUserButton.setVisible(false);
            viewUsersButton.setVisible(false);
        } else {
            addUserButton.setVisible(true);
            viewUsersButton.setVisible(true);
        }
        changePasswordAdminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchChangePasswordAdmin();
            }
        });

        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchUserRegister();
            }
        });

        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                launchViewUsers();
            }
        });
    }

    private void launchUserRegister() {
        //this.setVisible(false);
        UserRegister form = new UserRegister(this);
        form.setVisible(true);
        //form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void launchViewUsers() {
        ViewUsersForm form = new ViewUsersForm(this);
        form.setVisible(true);
    }

    private void launchLogout() {
        this.setVisible(false);
        Session.getSession().Logout();
        LogoutForm form = new LogoutForm();
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void launchGames() {
        this.setVisible(false);
        HomeGamesForm form = new HomeGamesForm(this);
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void launchClientsForm() {
        this.setVisible(false);
        ClientsForm form = new ClientsForm(this);
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void MoviesZone() {
        this.setVisible(false);
        HomeMoviesForm form = new HomeMoviesForm(this);
        form.setVisible(true);
        form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void launchChangePasswordAdmin() {
        //this.setVisible(false);
        ChangePasswordAdmin form = new ChangePasswordAdmin();
        form.setVisible(true);
        //form.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void launchImage() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("probando2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel(new ImageIcon(myPicture));
        ((JPanel) getContentPane()).setOpaque(false);
        getLayeredPane().add(label, JLayeredPane.FRAME_CONTENT_LAYER);
        label.setBounds(0, 0, myPicture.getWidth(), myPicture.getHeight());

    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridLayoutManager(4, 5, new Insets(40, 10, 40, 10), -1, -1));
        rootPanel.setBackground(new Color(-3090213));
        rootPanel.setEnabled(true);
        rootPanel.setFont(new Font(rootPanel.getFont().getName(), rootPanel.getFont().getStyle(), rootPanel.getFont().getSize()));
        rootPanel.setForeground(new Color(-3090213));
        rootPanel.putClientProperty("html.disable", Boolean.FALSE);
        GamesButton = new JButton();
        GamesButton.setBackground(new Color(-12828863));
        GamesButton.setFont(new Font("Courier New", GamesButton.getFont().getStyle(), 18));
        GamesButton.setForeground(new Color(-4486332));
        GamesButton.setHideActionText(false);
        GamesButton.setText("Games");
        rootPanel.add(GamesButton, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(220, 35), new Dimension(220, 35), new Dimension(220, 35), 0, false));
        MoviesButton = new JButton();
        MoviesButton.setBackground(new Color(-12828863));
        MoviesButton.setFont(new Font("Courier New", MoviesButton.getFont().getStyle(), 18));
        MoviesButton.setForeground(new Color(-4486332));
        MoviesButton.setHorizontalTextPosition(11);
        MoviesButton.setText("Movies");
        rootPanel.add(MoviesButton, new GridConstraints(3, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(220, 35), new Dimension(220, 35), new Dimension(220, 35), 0, false));
        CustomersButton = new JButton();
        CustomersButton.setBackground(new Color(-12828863));
        CustomersButton.setEnabled(true);
        CustomersButton.setFont(new Font("Courier New", CustomersButton.getFont().getStyle(), 18));
        CustomersButton.setForeground(new Color(-4486332));
        CustomersButton.setHideActionText(false);
        CustomersButton.setText("Clients");
        rootPanel.add(CustomersButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(220, 35), new Dimension(220, 35), new Dimension(220, 35), 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-12828863));
        label1.setEnabled(true);
        label1.setFont(new Font("Courier New", Font.BOLD, 36));
        label1.setForeground(new Color(-4486332));
        label1.setText("SAKILA 2.0");
        rootPanel.add(label1, new GridConstraints(2, 0, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        logoutButton = new JButton();
        logoutButton.setBackground(new Color(-12828863));
        logoutButton.setFont(new Font("Courier New", Font.BOLD, 18));
        logoutButton.setForeground(new Color(-4486332));
        logoutButton.setText("Logout");
        rootPanel.add(logoutButton, new GridConstraints(0, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 35), new Dimension(200, 35), new Dimension(200, 35), 0, false));
        changePasswordAdminButton = new JButton();
        changePasswordAdminButton.setBackground(new Color(-12828863));
        changePasswordAdminButton.setFont(new Font("Courier New", changePasswordAdminButton.getFont().getStyle(), 18));
        changePasswordAdminButton.setForeground(new Color(-4486332));
        changePasswordAdminButton.setText("Change Password");
        rootPanel.add(changePasswordAdminButton, new GridConstraints(1, 2, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 30), new Dimension(200, 30), new Dimension(200, 30), 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        addUserButton = new JButton();
        addUserButton.setBackground(new Color(-12828863));
        addUserButton.setFont(new Font("Courier New", addUserButton.getFont().getStyle(), 18));
        addUserButton.setForeground(new Color(-4486332));
        addUserButton.setText("Add User");
        rootPanel.add(addUserButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 30), new Dimension(200, 30), new Dimension(200, 30), 0, false));
        viewUsersButton = new JButton();
        viewUsersButton.setBackground(new Color(-12828863));
        viewUsersButton.setFont(new Font("Courier New", viewUsersButton.getFont().getStyle(), 18));
        viewUsersButton.setForeground(new Color(-4486332));
        viewUsersButton.setText("View Users");
        rootPanel.add(viewUsersButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(200, 30), new Dimension(200, 30), new Dimension(200, 30), 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
