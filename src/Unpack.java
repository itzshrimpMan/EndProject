

public class Unpack {

    //Method that gets the name out of the ArrayList and returns
    public static String getName(String stocks){

        System.out.println("Into the getName method with the Array " + stocks);

        String[] stockarray = stocks.split(";");
        String name = stockarray[0];

        System.out.println("Into the getName method name " + name);
        return name;

    }

    //Method that gets the volume out of the ArrayList and returns
    public static String getVol(String stocks){

        System.out.println("Into the getVol method with the Array " + stocks);

        String[] stockarray = stocks.split(";");
        String vol = stockarray[1];

        System.out.println("Into the getName method volume " + vol);
        return vol;

    }

    //Method that gets the price out of the ArrayList and returns
    public static String getPrice(String stocks){

        System.out.println("Into the getPrice method with the Array " + stocks);

        String[] stockarray = stocks.split(";");
        String price = stockarray[2];

        System.out.println("Into the getPrice method price " + price);
        return price;

    }

    //Method that gets the current value out of the ArrayList and returns
    public static String getVal(String stocks){

        System.out.println("Into the getVal method with the Array " + stocks);

        String[] stockarray = stocks.split(";");
        String value = stockarray[3];

        System.out.println("Into the getPrice method price " + value);
        return value;

    }

}
