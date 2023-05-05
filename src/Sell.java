import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Sell extends JFrame implements ActionListener {
    JTextField nameField, volumeField, priceField;
    JFrame sellFrame = new JFrame("Sell Stocks");

    public void sellStocksOnMarket() {
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
        l_register = new JLabel("Enter the stock you want to sell");
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

        sellFrame.setContentPane(mainPanel);
        sellFrame.pack();
        sellFrame.setSize(450, 300);
        sellFrame.setVisible(true);
        sellFrame.setResizable(false);
        sellFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        String sellSelection = e.getActionCommand();
        System.out.println(sellSelection);

        switch (sellSelection) {
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
                    Double.parseDouble(priceField.getText());//Kontrollerar att det är en double
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
                sellStocks(inName, inVolume, inPrice);
                JOptionPane.showMessageDialog(this, "Your stocks have been saved",
                        "SAVED", JOptionPane.INFORMATION_MESSAGE);
                sellFrame.dispose();
                break;

            case "Cancel":
                System.out.println("Avslutat");
                sellFrame.dispose();
                break;
        }

    }
    public static void sellStocks(String inName, int inVolume, double inPrice)
    {
        //Läs stockfilen för att få det aktuella aktieinnehavet i en Array
        ArrayList<String> stocklist = FileManager.readFile();

        System.out.println("The stocklist before : "+stocklist);

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


        //Kontrollera om aktien redan ägs eller inte
        //loopa genom arrayen för att hitta positionen för det befintliga lagret

        if (exist) {
            //Beståndet ägs - då kan det säljas
            System.out.println("The stock " + inName + " is in the list");

            //Gör en sträng av den faktiska raden i Arrayen för att extrahera och ändra värdena
            String stockrow = stocklist.get(i);
            System.out.println("The stock " + stockrow + " to be modified");

            int exVolume = Integer.parseInt(Unpack.getVol(stockrow)); //Existing volume

            //Kontrollera om försäljningsvolymen är större än ägd - sedan ändra till ägd
            if (inVolume > exVolume ) {
                inVolume = exVolume;
            }

            //Beräkna den återstående volymen efter försäljning (0 om alla aktier är sålda)
            int newVolume = exVolume - inVolume;

            //Hämta köppriset för aktien från arrayen
            Double exPrice = Double.parseDouble(Unpack.getPrice(stockrow));
            Double profit = (inPrice - exPrice)*inVolume;
            System.out.println("The stock " + inName + " generated " + String.valueOf(profit) + " before tax");

            //Kontrollera om aktieinnehavet i filen ska uppdateras eller raderas (alla sålda)
            if (newVolume>0){
                //Update holding in Array
                String sVolume = String.valueOf(newVolume);
                String sPrice = String.valueOf(exPrice); //Ingen uppdatering av köpkurs vid försäljning av aktier
                stocklist.set(i, inName + ";" + sVolume + ";" + sPrice);

            }else {
                //Allt är sålt och posten kan tas bort
                stocklist.remove(i);
            }

            //Uppdatering av filen med Arrayen för de aktuella stock holdigs
            System.out.println("The stocklist after selling : " + stocklist);
            FileManager.writeFile(stocklist);

        } else {

            System.out.println("The stock " + inName + " is not in the list and cannot be sold");

        }


    }
}

