import java.util.ArrayList;
import java.util.Scanner;

public class StockTransactions {
    public static void sellStocks()
    {
        //Read the stockfile to get the current Stock holdings into an Array
        ArrayList<String> stocklist = FileManager.readFile();

        System.out.println("The stocklist before : "+stocklist);

        //User input on what stock to sell
        Scanner inObj = new Scanner(System.in);
        System.out.print("Enter the name of stock to sell: ");
        String inName = inObj.nextLine();

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


        //Check if the stock is already owned or not
        //Loop through the array to find the position of the existing stock

        if (exist) {
            //The stock is owned - then it can be sold
            System.out.println("The stock " + inName + " is in the list");

            //Make a string of the actual row in the Array to extract and modify the values
            String stockrow = stocklist.get(i);
            System.out.println("The stock " + stockrow + " to be modified");

            System.out.print("Enter the quantity to sell     : ");
            int inVol = inObj.nextInt();

            int exVolume = Integer.parseInt(Unpack.getVol(stockrow)); //Existing volume

            //Check if the sales volume is larger than owned - then set to owned
            if (inVol > exVolume ) {
                inVol = exVolume;
            }

            //Calculate the remaining volume after selling (0 if all stocks are sold)
            int newVolume = exVolume - inVol;

            //Enter price to which the stock was sold
            System.out.print("Enter the stock price         : ");
            Double inPrice = inObj.nextDouble();

            //Retrieve the buy price of the stock from the array
            Double exPrice = Double.parseDouble(Unpack.getPrice(stockrow));
            Double profit = (inPrice - exPrice)*inVol;
            System.out.println("The stock " + inName + " generated " + String.valueOf(profit) + " before tax");

            //Check if the stock holding in the file should be updated or deleted (all sold)
            if (newVolume>0){
                //Update holding in Array
                String sVolume = String.valueOf(newVolume);
                String sPrice = String.valueOf(exPrice); //No update on buy price when selling teh stocks
                stocklist.set(i, inName + ";" + sVolume + ";" + sPrice);

            }else {
                //All is sold and the entry can be removed
                stocklist.remove(i);
            }

            //Updating the file with the Array of the current stock holdigs
            System.out.println("The stocklist after selling : " + stocklist);
            FileManager.writeFile(stocklist);

        } else {

            System.out.println("The stock " + inName + " is not in the list and cannot be sold");

        }


    }

}