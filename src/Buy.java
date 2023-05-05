import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.Scanner;

public class Buy extends JFrame implements ActionListener{
        JTextField nameField, volumeField, priceField;  //skapar variablerna för alla fälten i JFrame
        JFrame buyFrame = new JFrame("Buy Stocks"); //Skapar ett nytt JFrame

    public void buyStocksOnMarket() {

        //Resten av variablerna
        //Hela kodblocket är bara menyn till köp
        JPanel fieldPanel, mainPanel;
        JLabel name, volume, price, l_register;
        JButton b_save, b_cancel;
        GridBagConstraints c;


        fieldPanel = new JPanel();
        fieldPanel.setLayout(new GridBagLayout());
        fieldPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 10, 5, 10);  // top, left, bottom, right padding
        name = new JLabel("Stock name: ");
        volume = new JLabel("Volume: ");
        price = new JLabel("Price: ");
        nameField = new JTextField(15);
        volumeField = new JTextField(15);
        priceField = new JTextField(15);
        c.gridx = 0;
        c.gridy = 0;
        fieldPanel.add(name, c);
        c.gridx = 1;
        c.gridy = 0;
        fieldPanel.add(nameField, c);
        c.gridx = 0;
        c.gridy = 1;
        fieldPanel.add(volume, c);
        c.gridx = 1;
        c.gridy = 1;
        fieldPanel.add(volumeField, c);
        c.gridx = 0;
        c.gridy = 2;
        fieldPanel.add(price, c);
        c.gridx = 1;
        c.gridy = 2;
        fieldPanel.add(priceField, c);
        c.gridx = 0;
        c.gridy = 3;

        mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        Font font = new Font("MS Sans Serif", Font.BOLD, 18);
        l_register = new JLabel("Enter the stock you bought");
        l_register.setFont(font);
        l_register.setAlignmentX(Component.CENTER_ALIGNMENT);
        b_save = new JButton("Save");
        b_save.addActionListener(this);
        b_save.setAlignmentX(Component.CENTER_ALIGNMENT);
        b_cancel = new JButton("Cancel");
        b_cancel.addActionListener(this);
        b_cancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(l_register);
        mainPanel.add(fieldPanel);
        mainPanel.add(b_save);
        mainPanel.add(b_cancel);

        buyFrame.setContentPane(mainPanel);
        buyFrame.pack();
        buyFrame.setSize(450, 300);
        buyFrame.setVisible(true);
        buyFrame.setResizable(false);
        buyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    //Detta kodblocket sköter alla inmatningar - Button clicks och inmatning i textfälten.
    public void actionPerformed(ActionEvent e){
        String buySelection = e.getActionCommand();
        System.out.println(buySelection);

        switch (buySelection) {
            case "Save":
                //Kontrollerar giltigt värde i Namnfältet
                checkInput instance = new checkInput();
                String inName = nameField.getText();
                if (instance.checkInput(inName) == false) {
                    System.out.println("Hoppar ur");
                    break;
                }

                //Kontrollerar giltigt värde på Volymen
                int inVolume;
                try {
                    Integer.parseInt(volumeField.getText());//Kontrollerar att det är en interger
                    inVolume = Integer.parseInt(volumeField.getText());
                    if (instance.checkInput(inVolume) == false) {
                        break;
                    }
                } catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid volume number",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                }

                //Kontrollerar giltigt värde på priset
                double inPrice = 0;
                try {
                    Double.parseDouble(priceField.getText()); //Kontrollerar att det är en double
                    inPrice = Double.parseDouble(priceField.getText());
                    if (instance.checkInput(inPrice) == false) {
                        break;
                    }
                } catch (NumberFormatException err) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid price number",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                //Allt är okej - skapar eller uppdaterar rad i filen
                buyStocks(inName, inVolume, inPrice);
                JOptionPane.showMessageDialog(this, "Your stocks have been saved",
                        "SAVED", JOptionPane.INFORMATION_MESSAGE);
                buyFrame.dispose();
                break;

            case "Cancel":
                System.out.println("Avslutat");
                buyFrame.dispose();
                break;
        }

        }

    public static void buyStocks(String inName, int inVolume, double inPrice) {
        //Läs stockfilen för att få de aktuella aktieinnehaven i en Array
        ArrayList<String> stocklist = FileManager.readFile();

        System.out.println("The stocklist before : " + stocklist);

        //Kontrollera om aktien redan ägs eller inte
        //loopa genom arrayen för att hitta positionen för det befintliga lagret
        int i = 0;
        Boolean exist = false;

        while (i < stocklist.size()) {
            String str = stocklist.get(i);
            String cmp = Unpack.getName(str);
            if (cmp.equals(inName)) {
                exist = true;
                break;
            }
            i++;
        }

        if (exist) {
            //Aktien ägs redan - adderar mer till holding
            System.out.println("The stock " + inName + " is already in the list");

            String stockrow = stocklist.get(i);
            System.out.println("The stock " + stockrow + " to be modified");

            //Plusar ihop den nya och den gamla volymen av aktier
            int exVolume = Integer.parseInt(Unpack.getVol(stockrow));
            int newVolume = exVolume + inVolume;

            //Räknar ut det nya köppriset
            Double exPrice = Double.parseDouble(Unpack.getPrice(stockrow));
            Double newPrice = ((exVolume * exPrice) + (inVolume * inPrice)) / newVolume;

            //Uppdaterar raden i Array med den nya datan av aktien
            String sVolume = String.valueOf(newVolume);
            String sPrice = String.valueOf(newPrice);
            stocklist.set(i, inName + ";" + sVolume + ";" + sPrice);


        } else {

            System.out.println("The stock " + inName + " is not in the list");
            //Aktien är ny - addera till listan
            String sVolume = String.valueOf(inVolume);
            String sPrice = String.valueOf(inPrice);
            stocklist.add(inName + ";" + sVolume + ";" + sPrice);

        }

        System.out.println("The stocklist after : " + stocklist);
        FileManager.writeFile(stocklist);
    }
}

