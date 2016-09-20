package bo.edu.ucbcba.videoclub.view;

import bo.edu.ucbcba.videoclub.controller.Session;
import bo.edu.ucbcba.videoclub.controller.UserController;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.metamodel.Metamodel;
import javax.swing.*;
import javax.swing.text.html.parser.Entity;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by privado on 01/06/2016.
 */
public class ChangePasswordAdmin extends JFrame {
    private JPasswordField oldpasswordfield;
    private JPasswordField newpasswordfield;
    private JPasswordField confirmnewpasswordfield;
    private JPanel rootPane;
    private JButton saveChangesButton;
    private JButton cancelButton;
    private UserController userController;
    private EntityManager entityManager;

    public ChangePasswordAdmin() {
        super("Changing Password");
        setContentPane(rootPane);
        setSize(700, 500);
        this.setLocationRelativeTo(null);
        //setSize(600, 400);
        setResizable(false);
        launchImage();
        userController = new UserController();
        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateChanges()) {
                    cancel();
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });

    }

    private boolean validateChanges() {
        String opswd = oldpasswordfield.getText();
        String npswd = newpasswordfield.getText();
        String cpswd = confirmnewpasswordfield.getText();

        if (!opswd.isEmpty()) {
            if (!npswd.isEmpty()) {
                if (!cpswd.isEmpty()) {
                    if (opswd.length() <= 20) {
                        if (npswd.length() > 20) {
                            JOptionPane.showMessageDialog(this, "New Password is too long, must have less than 20 characters", "Error", JOptionPane.INFORMATION_MESSAGE);
                            return false;
                        } else {
                            if (cpswd.length() > 20) {
                                JOptionPane.showMessageDialog(this, "Confirm Password is too long, must have less than 20 characters", "Error", JOptionPane.INFORMATION_MESSAGE);
                                return false;
                            } else {
                                if (npswd.length() < 6) {
                                    JOptionPane.showMessageDialog(this, "New Password is too short, must have more than 6 characters", "Error", JOptionPane.INFORMATION_MESSAGE);
                                    return false;
                                } else {
                                    if (cpswd.length() < 6) {
                                        JOptionPane.showMessageDialog(this, "Confirm Password is too short, must have more than 20 characters", "Error", JOptionPane.INFORMATION_MESSAGE);
                                        return false;
                                    } else {
                                        if (npswd.equals(cpswd)) {
                                            if (userController.validateUser(Session.getSession().getUsername(), opswd)) {
                                                userController.ChangePassword(Session.getSession().getUsername(), npswd);
                                                JOptionPane.showMessageDialog(this, "Password changed successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                return true;
                                            } else {
                                                JOptionPane.showMessageDialog(this, "Old Password incorrect", "Fail", JOptionPane.INFORMATION_MESSAGE);
                                                return false;
                                            }
                                        } else {
                                            JOptionPane.showMessageDialog(this, "New password and Confirm Password are not equal", "Fail", JOptionPane.INFORMATION_MESSAGE);
                                            return false;
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Old Password is too long, must have less than 20 characters", "Error", JOptionPane.INFORMATION_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Confirm Password can't be blank", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            } else {
                JOptionPane.showMessageDialog(this, "New Password can't be blank", "Error", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(this, "Old Password can't be blank", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
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
        rootPane = new JPanel();
        rootPane.setLayout(new GridLayoutManager(6, 3, new Insets(20, 40, 20, 40), -1, -1));
        rootPane.setBackground(new Color(-3090213));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font("Courier New", Font.BOLD, 30));
        label1.setForeground(new Color(-4477584));
        label1.setText("Changing Password");
        rootPane.add(label1, new GridConstraints(0, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font("Courier New", Font.BOLD, 18));
        label2.setForeground(new Color(-4486332));
        label2.setText("Old Password:");
        rootPane.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        oldpasswordfield = new JPasswordField();
        rootPane.add(oldpasswordfield, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 35), new Dimension(150, 35), new Dimension(-1, 35), 0, false));
        newpasswordfield = new JPasswordField();
        rootPane.add(newpasswordfield, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 35), new Dimension(150, 35), new Dimension(-1, 35), 0, false));
        confirmnewpasswordfield = new JPasswordField();
        rootPane.add(confirmnewpasswordfield, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(-1, 35), new Dimension(150, 35), new Dimension(-1, 35), 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font("Courier New", Font.BOLD, 18));
        label3.setForeground(new Color(-4486332));
        label3.setText("New Password:");
        rootPane.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font("Courier New", Font.BOLD, 18));
        label4.setForeground(new Color(-4486332));
        label4.setText("Repeat New Password:");
        rootPane.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveChangesButton = new JButton();
        saveChangesButton.setBackground(new Color(-12828863));
        saveChangesButton.setFont(new Font("Courier New", saveChangesButton.getFont().getStyle(), 18));
        saveChangesButton.setForeground(new Color(-4486332));
        saveChangesButton.setText("Save Changes");
        rootPane.add(saveChangesButton, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-12828863));
        cancelButton.setFont(new Font("Courier New", cancelButton.getFont().getStyle(), 18));
        cancelButton.setForeground(new Color(-4486332));
        cancelButton.setText("Cancel");
        rootPane.add(cancelButton, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPane;
    }
}
