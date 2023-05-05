import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;


public class ListStocks {

    static void ListHolding()
    {
        JFrame frame = new JFrame("List of current stock holdings");

        String col[] = {"Stock name","Number of stocks","Buy price (SEK)", "Total holding value (SEK)"};

        DefaultTableModel tableModel = new DefaultTableModel(col, 0);
        JTable table = new JTable(tableModel);

        ArrayList<String> stocklist = FileManager.readFile();   //Skriver över filen till en Array

        for (int i = 0; i < stocklist.size(); i++){
            String name = Unpack.getName(stocklist.get(i));
            String volume = Unpack.getVol(stocklist.get(i));
            String price = Unpack.getPrice(stocklist.get(i));
            Double value = Integer.parseInt(volume) * Double.parseDouble(price);

            System.out.println(name);
            System.out.println(volume);
            System.out.println(price);

            Object[] data = {name, volume, price, value};

            tableModel.insertRow(i, data);

        }

        JScrollPane sp=new JScrollPane(table);
        frame.add(sp);
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }


}