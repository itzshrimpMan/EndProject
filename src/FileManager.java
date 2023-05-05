import java.util.ArrayList;
import java.io.File; // Managing files
import java.io.IOException;
import java.io.FileWriter; //Write to files
import java.util.Scanner; // To read from text files

public class FileManager {

    //Metod för att skapa portföljfilen (såvida den inte redan finns)
    static void createFile() {
        try {
            File portfolio = new File("StockPortfolio.txt");
            if (portfolio.createNewFile()) {
                System.out.println("File created: " + portfolio.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException err) {
            System.out.println("An error occurred while creating Portfolio file.");
            err.printStackTrace();
        }
    }

    //Metod för att läsa innehåll från aktieportföljfilen och lägga i en ArrayList
    public static ArrayList<String> readFile()
    {
        ArrayList<String> stocks = new ArrayList<String>();
        try {
            File portfolio = new File("StockPortfolio.txt");
            Scanner readfile = new Scanner(portfolio);

            System.out.println("In the method of FileManager");
            while (readfile.hasNextLine()) {
                String data = readfile.nextLine();
                // Lägg filens innehåll rad för rad i en ArrayList att arbeta med
                System.out.println(data);
                stocks.add(data);

            }
            readfile.close();

        } catch (IOException err) {
            System.out.println("An error occurred when trying to read from the Portfolio file");
            err.printStackTrace();
        }

        return stocks;
    }

    //Metod för att skriva innehållet i aktiernas ArrayList till en fil
    public static void writeFile(ArrayList<String> stocks){

        try {
            FileWriter wFile = new FileWriter("StockPortfolio.txt");
            System.out.print("ArrayList to be written to file: ");

            int len = stocks.size();
            int i = 0;

            while (i < len){
                wFile.write(stocks.get(i) + System.lineSeparator());
                i++;
            }

            wFile.close();

            System.out.println("Successfully wrote to the file.");

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}