import javax.swing.*;

public class graphics extends JFrame {
    public graphics(){

        this.setTitle("Menu");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JFrame frame = new JFrame("Menu");
            JPanel panel = new JPanel();

            JLabel label = new JLabel("Choose an option:");
            panel.add(label);

            JButton button1 = new JButton("Option 1");
            button1.addActionListener(e -> JOptionPane.showMessageDialog(null, "You chose option 1."));
            panel.add(button1);

            JButton button2 = new JButton("Option 2");
            button2.addActionListener(e -> JOptionPane.showMessageDialog(null, "You chose option 2."));
            panel.add(button2);

            JButton button3 = new JButton("Option 3");
            button3.addActionListener(e -> JOptionPane.showMessageDialog(null, "You chose option 3."));
            panel.add(button3);

            JButton button4 = new JButton("Exit");
            button4.addActionListener(e -> System.exit(0));
            panel.add(button4);

            frame.add(panel);
            frame.setSize(300, 150);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        }

    }
