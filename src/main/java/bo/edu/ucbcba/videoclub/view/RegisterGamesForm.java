package bo.edu.ucbcba.videoclub.view;

import bo.edu.ucbcba.videoclub.controller.CompanyController;
import bo.edu.ucbcba.videoclub.controller.GameController;
import bo.edu.ucbcba.videoclub.controller.MovieController;
import bo.edu.ucbcba.videoclub.exceptions.ValidationException;
import bo.edu.ucbcba.videoclub.model.Company;
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
import java.util.List;

public class RegisterGamesForm extends JDialog {
    private JPanel rootPanel;
    private JTextField title;

    private JTextField releaseYear;
    private JTextArea description;
    private JRadioButton rating1;
    private JRadioButton rating2;
    private JRadioButton rating3;
    private JRadioButton rating4;
    private JRadioButton rating5;
    private JButton saveButton;
    private JButton cancelButton;
    private JTextField price;
    private JComboBox CompanycomboBox;
    private int rating = 5;
    private GameController controller;
    private final CompanyController companyController;

    RegisterGamesForm(HomeGamesForm parent) {
        super(parent, "Register Game", true);
        setContentPane(rootPanel);
        setSize(1000, 600);
        setResizable(false);
        this.setLocationRelativeTo(null);
        this.setLocationRelativeTo(null);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        ActionListener ratingListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rating = Integer.parseInt(((JRadioButton) e.getSource()).getText());
            }
        };
        rating1.addActionListener(ratingListener);
        rating2.addActionListener(ratingListener);
        rating3.addActionListener(ratingListener);
        rating4.addActionListener(ratingListener);
        rating5.addActionListener(ratingListener);
        controller = new GameController();
        companyController = new CompanyController();
        populateComboBox();
        launchImage();
    }

    RegisterGamesForm(GamesForm parent) {
        super(parent, "Register Game", true);
        setContentPane(rootPanel);
        setSize(1000, 600);
        setResizable(false);
        this.setLocationRelativeTo(null);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUser();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        ActionListener ratingListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rating = Integer.parseInt(((JRadioButton) e.getSource()).getText());
            }
        };
        rating1.addActionListener(ratingListener);
        rating2.addActionListener(ratingListener);
        rating3.addActionListener(ratingListener);
        rating4.addActionListener(ratingListener);
        rating5.addActionListener(ratingListener);
        controller = new GameController();
        companyController = new CompanyController();
        populateComboBox();
        launchImage();
    }

    private void populateComboBox() {
        List<Company> companies = companyController.getAllCompanies();
        for (Company c : companies) {
            CompanycomboBox.addItem(c);
        }
    }

    private void saveUser() {
        try {
            Company c = (Company) CompanycomboBox.getSelectedItem();
            controller.create(title.getText(),
                    description.getText(),
                    releaseYear.getText(),
                    rating,
                    price.getText(), c);
            JOptionPane.showMessageDialog(this, "Game created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            cancel();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void launchImage() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("fondo.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel label = new JLabel(new ImageIcon(myPicture));
        ((JPanel) getContentPane()).setOpaque(false);
        getLayeredPane().add(label, JLayeredPane.FRAME_CONTENT_LAYER);
        label.setBounds(0, 0, myPicture.getWidth(), myPicture.getHeight());

    }

    private void cancel() {
        setVisible(false);
        dispose();
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
        rootPanel.setLayout(new GridLayoutManager(13, 12, new Insets(0, 0, 0, 0), -1, -1));
        rootPanel.setBackground(new Color(-3090213));
        rootPanel.setForeground(new Color(-3090213));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font("Courier New", label1.getFont().getStyle(), 18));
        label1.setForeground(new Color(-4486332));
        label1.setText("Title");
        rootPanel.add(label1, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font("Courier New", label2.getFont().getStyle(), 18));
        label2.setForeground(new Color(-4486332));
        label2.setText("Company");
        rootPanel.add(label2, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        title = new JTextField();
        title.setText("");
        rootPanel.add(title, new GridConstraints(3, 2, 1, 9, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font("Courier New", label3.getFont().getStyle(), 18));
        label3.setForeground(new Color(-4486332));
        label3.setText("Rating");
        rootPanel.add(label3, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rating1 = new JRadioButton();
        rating1.setBackground(new Color(-3090213));
        rating1.setForeground(new Color(-4486332));
        rating1.setOpaque(false);
        rating1.setText("1");
        rootPanel.add(rating1, new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rating2 = new JRadioButton();
        rating2.setBackground(new Color(-3090213));
        rating2.setForeground(new Color(-4486332));
        rating2.setOpaque(false);
        rating2.setText("2");
        rootPanel.add(rating2, new GridConstraints(10, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rating3 = new JRadioButton();
        rating3.setBackground(new Color(-3090213));
        rating3.setForeground(new Color(-4486332));
        rating3.setOpaque(false);
        rating3.setText("3");
        rootPanel.add(rating3, new GridConstraints(10, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rating4 = new JRadioButton();
        rating4.setBackground(new Color(-3090213));
        rating4.setForeground(new Color(-4486332));
        rating4.setOpaque(false);
        rating4.setText("4");
        rootPanel.add(rating4, new GridConstraints(10, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rating5 = new JRadioButton();
        rating5.setBackground(new Color(-3090213));
        rating5.setEnabled(true);
        rating5.setForeground(new Color(-4486332));
        rating5.setOpaque(false);
        rating5.setSelected(true);
        rating5.setText("5");
        rootPanel.add(rating5, new GridConstraints(10, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setBackground(new Color(-12828863));
        saveButton.setFont(new Font("Courier New", saveButton.getFont().getStyle(), 18));
        saveButton.setForeground(new Color(-4486332));
        saveButton.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
        saveButton.setText("Save");
        rootPanel.add(saveButton, new GridConstraints(11, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font("Courier New", Font.BOLD, 20));
        label4.setForeground(new Color(-4486332));
        label4.setText("Register Game");
        rootPanel.add(label4, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setFont(new Font("Courier New", label5.getFont().getStyle(), 18));
        label5.setForeground(new Color(-4486332));
        label5.setText("Release Year");
        rootPanel.add(label5, new GridConstraints(8, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 1, false));
        releaseYear = new JTextField();
        rootPanel.add(releaseYear, new GridConstraints(8, 7, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-12828863));
        cancelButton.setFont(new Font("Courier New", cancelButton.getFont().getStyle(), 18));
        cancelButton.setForeground(new Color(-4486332));
        cancelButton.setIcon(new ImageIcon(getClass().getResource("/icons/remove-symbol.png")));
        cancelButton.setText("Cancel");
        rootPanel.add(cancelButton, new GridConstraints(11, 10, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(12, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        price = new JTextField();
        price.setText("");
        rootPanel.add(price, new GridConstraints(9, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setFont(new Font("Courier New", label6.getFont().getStyle(), 18));
        label6.setForeground(new Color(-4486332));
        label6.setText("Price");
        rootPanel.add(label6, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CompanycomboBox = new JComboBox();
        rootPanel.add(CompanycomboBox, new GridConstraints(8, 2, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(1, 9, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        rootPanel.add(spacer3, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        rootPanel.add(spacer4, new GridConstraints(6, 11, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        rootPanel.add(spacer5, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        rootPanel.add(spacer6, new GridConstraints(2, 6, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setFont(new Font("Courier New", label7.getFont().getStyle(), 18));
        label7.setForeground(new Color(-4486332));
        label7.setText("Description");
        rootPanel.add(label7, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        description = new JTextArea();
        description.setWrapStyleWord(false);
        rootPanel.add(description, new GridConstraints(5, 2, 3, 9, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(600, 50), new Dimension(600, 50), new Dimension(600, 50), 0, false));
        final Spacer spacer7 = new Spacer();
        rootPanel.add(spacer7, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer8 = new Spacer();
        rootPanel.add(spacer8, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer9 = new Spacer();
        rootPanel.add(spacer9, new GridConstraints(4, 5, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rating1);
        buttonGroup.add(rating2);
        buttonGroup.add(rating3);
        buttonGroup.add(rating4);
        buttonGroup.add(rating5);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
