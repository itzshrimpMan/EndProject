import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.awt.event.*;
import java.util.EventObject;

public class graphics extends JFrame implements ActionListener {
            JFrame frame = new JFrame("Menu");
    public graphics(){
            JButton buttonBuy = new JButton("Buy stocks");
            JButton buttonSell = new JButton("Sell stocks");
            JButton buttonList = new JButton("List holdings");
            JButton buttonExit = new JButton("Exit");

            buttonBuy.addActionListener(this);
            buttonSell.addActionListener(this);
            buttonList.addActionListener(this);
            buttonExit.addActionListener((event) -> System.exit(0));

            frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

            frame.add(buttonBuy);
            frame.add(buttonSell);
            frame.add(buttonList);
            frame.add(buttonExit);

            frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
            frame.setSize(450,450);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        public void actionPerformed(ActionEvent e){
            System.out.println("Action performed " + e.getActionCommand());

            String mSelection = e.getActionCommand();
            System.out.println(mSelection);
            switch (mSelection){
                case "Buy stocks":
                    System.out.println("Köp");
                    Buy bInstance = new Buy();
                    bInstance.buyStocksOnMarket();
                    break;
                case "Sell stocks":
                    System.out.println("Sälj");
                    Sell sInstance = new Sell();
                    sInstance.sellStocksOnMarket();
                    break;
                case "List holdings":
                    System.out.println("Lista");
                    break;
                default:
                    System.out.println("Not a valid option.");
            }
        }

    }
