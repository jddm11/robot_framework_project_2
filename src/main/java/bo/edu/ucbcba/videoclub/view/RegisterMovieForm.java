package bo.edu.ucbcba.videoclub.view;

import bo.edu.ucbcba.videoclub.controller.DirectorController;
import bo.edu.ucbcba.videoclub.controller.MovieController;
import bo.edu.ucbcba.videoclub.exceptions.ValidationException;
import bo.edu.ucbcba.videoclub.model.Director;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class RegisterMovieForm extends JFrame {
    private JPanel rootPanel;
    private JTextField title;
    private JTextArea description;
    private JTextField hoursLength;
    private JTextField minutesLength;
    private JTextField releaseYear;
    private JButton saveButton;
    private JButton cancelButton;
    private JRadioButton rating1;
    private JRadioButton rating2;
    private JRadioButton rating3;
    private JRadioButton rating4;
    private JRadioButton rating5;
    private int rating = 5;
    private MovieController controller;
    private JButton selectButton;
    private JTextField imageText;
    private JLabel CoverFilm;
    private JTextField price;
    private JComboBox directorComboBox;
    private final DirectorController directorController;

    RegisterMovieForm() {
        super("Register Movie");
        setContentPane(rootPanel);
        setSize(1000, 600);
        this.setLocationRelativeTo(null);
        imageText.setEditable(false);
        setResizable(false);
        launchImage();
        selectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadImage();
            }
        });
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
        controller = new MovieController();
        directorController = new DirectorController();
        populateComboBox();
    }

    private void populateComboBox() {
        List<Director> directors = directorController.getAlldirectors();
        for (Director d : directors) {
            directorComboBox.addItem(d);
        }
    }

    private void loadImage() {

        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        chooser.setFileFilter(filtroImagen);
        int search = chooser.showOpenDialog(null);
        if (search == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            imageText.setText(String.valueOf(file));
            Image image = getToolkit().createImage(imageText.getText());
            image = image.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
            CoverFilm.setIcon(new ImageIcon(image));
        }
    }

    private void saveUser() {
        try {
            Director d = (Director) directorComboBox.getSelectedItem();
            controller.create(title.getText(),
                    description.getText(),
                    releaseYear.getText(),
                    rating,
                    hoursLength.getText(),
                    minutesLength.getText(),
                    price.getText(),
                    imageText.getText(), d);
            JOptionPane.showMessageDialog(this, "Movie created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            cancel();
        } catch (ValidationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Format error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void cancel() {
        setVisible(false);
        dispose();
    }

    private void launchImage() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("fondoM.jpg"));
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
        rootPanel.setLayout(new GridLayoutManager(10, 13, new Insets(20, 20, 20, 20), -1, -1));
        rootPanel.setBackground(new Color(-3090213));
        rootPanel.setEnabled(true);
        rootPanel.setForeground(new Color(-3090213));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font("Courier New", label1.getFont().getStyle(), 18));
        label1.setForeground(new Color(-4486332));
        label1.setText("Title");
        rootPanel.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        title = new JTextField();
        title.setFont(new Font("Courier New", title.getFont().getStyle(), 16));
        rootPanel.add(title, new GridConstraints(1, 1, 1, 11, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        rootPanel.add(spacer1, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font("Courier New", label2.getFont().getStyle(), 18));
        label2.setForeground(new Color(-4486332));
        label2.setText("Description");
        rootPanel.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTHWEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font("Courier New", label3.getFont().getStyle(), 18));
        label3.setForeground(new Color(-4486332));
        label3.setText("Length");
        rootPanel.add(label3, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        description = new JTextArea();
        description.setAutoscrolls(false);
        description.setEditable(true);
        description.setEnabled(true);
        description.setFocusTraversalPolicyProvider(false);
        description.setFont(new Font("Courier New", description.getFont().getStyle(), 16));
        description.setLineWrap(false);
        description.setRows(5);
        rootPanel.add(description, new GridConstraints(2, 1, 1, 11, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, new Dimension(700, 50), new Dimension(700, 50), new Dimension(700, 50), 0, false));
        hoursLength = new JTextField();
        hoursLength.setColumns(2);
        hoursLength.setFont(new Font("Courier New", hoursLength.getFont().getStyle(), 16));
        rootPanel.add(hoursLength, new GridConstraints(3, 1, 1, 4, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font("Courier New", label4.getFont().getStyle(), 18));
        label4.setForeground(new Color(-4486332));
        label4.setText("Release Year");
        rootPanel.add(label4, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        releaseYear = new JTextField();
        releaseYear.setFont(new Font("Courier New", releaseYear.getFont().getStyle(), 16));
        rootPanel.add(releaseYear, new GridConstraints(4, 1, 1, 11, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        rootPanel.add(spacer2, new GridConstraints(3, 12, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setFont(new Font("Courier New", label5.getFont().getStyle(), 18));
        label5.setForeground(new Color(-4486332));
        label5.setText("Rating");
        rootPanel.add(label5, new GridConstraints(8, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        price = new JTextField();
        price.setFont(new Font("Courier New", price.getFont().getStyle(), 16));
        price.setText("");
        rootPanel.add(price, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setFont(new Font("Courier New", label6.getFont().getStyle(), 18));
        label6.setForeground(new Color(-4486332));
        label6.setText("Price");
        rootPanel.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        imageText = new JTextField();
        imageText.setFont(new Font("Courier New", imageText.getFont().getStyle(), 16));
        rootPanel.add(imageText, new GridConstraints(6, 4, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        selectButton = new JButton();
        selectButton.setBackground(new Color(-12828863));
        selectButton.setFont(new Font("Courier New", selectButton.getFont().getStyle(), 18));
        selectButton.setForeground(new Color(-4486332));
        selectButton.setHideActionText(false);
        selectButton.setIcon(new ImageIcon(getClass().getResource("/icons/magnifier.png")));
        selectButton.setText(" Select");
        rootPanel.add(selectButton, new GridConstraints(6, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setFont(new Font("Courier New", label7.getFont().getStyle(), 18));
        label7.setForeground(new Color(-4486332));
        label7.setText("Image");
        rootPanel.add(label7, new GridConstraints(6, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setBackground(new Color(-12828863));
        saveButton.setFont(new Font("Courier New", saveButton.getFont().getStyle(), 18));
        saveButton.setForeground(new Color(-4486332));
        saveButton.setHideActionText(false);
        saveButton.setIcon(new ImageIcon(getClass().getResource("/icons/add.png")));
        saveButton.setText("Save");
        rootPanel.add(saveButton, new GridConstraints(9, 7, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setBackground(new Color(-12828863));
        cancelButton.setFont(new Font("Courier New", cancelButton.getFont().getStyle(), 18));
        cancelButton.setForeground(new Color(-4486332));
        cancelButton.setHideActionText(false);
        cancelButton.setIcon(new ImageIcon(getClass().getResource("/icons/remove-symbol.png")));
        cancelButton.setText("Cancel");
        rootPanel.add(cancelButton, new GridConstraints(9, 8, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setFont(new Font("Courier New", label8.getFont().getStyle(), 18));
        label8.setForeground(new Color(-4486332));
        label8.setText("Hours");
        rootPanel.add(label8, new GridConstraints(3, 5, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        minutesLength = new JTextField();
        minutesLength.setColumns(2);
        minutesLength.setFont(new Font("Courier New", minutesLength.getFont().getStyle(), 16));
        rootPanel.add(minutesLength, new GridConstraints(3, 7, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setFont(new Font("Courier New", label9.getFont().getStyle(), 18));
        label9.setForeground(new Color(-4486332));
        label9.setText("Minutes");
        rootPanel.add(label9, new GridConstraints(3, 8, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setFont(new Font("Courier New", Font.BOLD, 20));
        label10.setForeground(new Color(-4486332));
        label10.setText("Register Movie");
        rootPanel.add(label10, new GridConstraints(0, 5, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        CoverFilm = new JLabel();
        CoverFilm.setText("");
        rootPanel.add(CoverFilm, new GridConstraints(6, 8, 1, 4, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setFont(new Font("Courier New", label11.getFont().getStyle(), 18));
        label11.setForeground(new Color(-4486332));
        label11.setText("Director");
        rootPanel.add(label11, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        directorComboBox = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        directorComboBox.setModel(defaultComboBoxModel1);
        rootPanel.add(directorComboBox, new GridConstraints(7, 1, 1, 6, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-12828863));
        rootPanel.add(panel1, new GridConstraints(8, 1, 1, 6, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        rating5 = new JRadioButton();
        rating5.setBackground(new Color(-12828863));
        rating5.setFont(new Font("Courier New", rating5.getFont().getStyle(), 18));
        rating5.setForeground(new Color(-4486332));
        rating5.setHideActionText(false);
        rating5.setSelected(true);
        rating5.setText("5");
        panel1.add(rating5, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rating4 = new JRadioButton();
        rating4.setBackground(new Color(-12828863));
        rating4.setFont(new Font("Courier New", rating4.getFont().getStyle(), 18));
        rating4.setForeground(new Color(-4486332));
        rating4.setHideActionText(false);
        rating4.setText("4");
        panel1.add(rating4, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rating3 = new JRadioButton();
        rating3.setBackground(new Color(-12828863));
        rating3.setFont(new Font("Courier New", rating3.getFont().getStyle(), 18));
        rating3.setForeground(new Color(-4486332));
        rating3.setHideActionText(false);
        rating3.setText("3");
        panel1.add(rating3, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rating2 = new JRadioButton();
        rating2.setBackground(new Color(-12828863));
        rating2.setFont(new Font("Courier New", rating2.getFont().getStyle(), 18));
        rating2.setForeground(new Color(-4486332));
        rating2.setHideActionText(false);
        rating2.setText("2");
        panel1.add(rating2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rating1 = new JRadioButton();
        rating1.setBackground(new Color(-12828863));
        rating1.setFont(new Font("Courier New", rating1.getFont().getStyle(), 18));
        rating1.setForeground(new Color(-4486332));
        rating1.setHideActionText(false);
        rating1.setText("1");
        panel1.add(rating1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        ButtonGroup buttonGroup;
        buttonGroup = new ButtonGroup();
        buttonGroup.add(rating5);
        buttonGroup.add(rating1);
        buttonGroup.add(rating2);
        buttonGroup.add(rating3);
        buttonGroup.add(rating4);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPanel;
    }
}
