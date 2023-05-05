

public class Unpack {

    //Metod som hämtar namnet från ArrayList och returnerar
    public static String getName(String stocks){

        System.out.println("Into the getName method with the Array " + stocks);

        String[] stockarray = stocks.split(";");
        String name = stockarray[0];

        System.out.println("Into the getName method name " + name);
        return name;

    }

    //Metod som får ut volymen från ArrayList och returnerar
    public static String getVol(String stocks){

        System.out.println("Into the getVol method with the Array " + stocks);

        String[] stockarray = stocks.split(";");
        String vol = stockarray[1];

        System.out.println("Into the getName method volume " + vol);
        return vol;

    }

    //Metod som får ut priset från ArrayList och returnerar
    public static String getPrice(String stocks){

        System.out.println("Into the getPrice method with the Array " + stocks);

        String[] stockarray = stocks.split(";");
        String price = stockarray[2];

        System.out.println("Into the getPrice method price " + price);
        return price;

    }

    //Metod som hämtar det aktuella värdet från ArrayList och returnerar
    public static String getVal(String stocks){

        System.out.println("Into the getVal method with the Array " + stocks);

        String[] stockarray = stocks.split(";");
        String value = stockarray[3];

        System.out.println("Into the getPrice method price " + value);
        return value;

    }

}
