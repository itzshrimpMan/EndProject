import javax.swing.*;
public class checkInput extends JFrame{

    //Kodblocket kollar igen inputs och ser till att allt stämmer
    public boolean checkInput(String textData){
        boolean inputStatus = true;

        if(textData==null){                                         //Kontrollerar att namnet inte är null
            JOptionPane.showMessageDialog(this,"Stockname can not be null!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            inputStatus = false ;
        } else if(textData.isEmpty()){                              //Kontrollerar att det finns något inskrivet
            JOptionPane.showMessageDialog(this,"Please enter a name",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            inputStatus = false ;
        } else if (textData.isBlank()){                             //Kontrollerar att namne är valid - inte finns några specialtecken
            JOptionPane.showMessageDialog(this,"Please enter a valid name",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            inputStatus = false ;
        }
        return inputStatus;
    }

    public boolean checkInput(int intData){
        boolean inputstatus = true;

        if(intData<0){                                          //Kontrollerar att den inmatade volymen inte är längre än 0
            JOptionPane.showMessageDialog(this,"Volume must be greater than 0",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            inputstatus = false;
        }
        return inputstatus;
    }

    public boolean checkInput(double intData){
        boolean inputstatus = true;

        if(intData<0){                                          //Kontrollerar att det inmatade priser inte är under 0
            JOptionPane.showMessageDialog(this,"Price must be greater than 0",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            inputstatus = false;
        }
        return inputstatus;
    }
}
