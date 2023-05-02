import javax.swing.*;
public class checkInput extends JFrame{
    public boolean checkInput(String textData){
        boolean inputStatus = true;

        if(textData==null){
            JOptionPane.showMessageDialog(this,"Stockname can not be null!",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            inputStatus = false ;
        } else if(textData.isEmpty()){
            JOptionPane.showMessageDialog(this,"Please enter a name",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            inputStatus = false ;
        } else if (textData.isBlank()){
            JOptionPane.showMessageDialog(this,"Please enter a valid name",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            inputStatus = false ;
        }
        return inputStatus;
    }

    public boolean checkInput(int intData){
        boolean inputstatus = true;

        if(intData<0){
            JOptionPane.showMessageDialog(this,"Volume must be greater than 0",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            inputstatus = false;
        }
        return inputstatus;
    }

    public boolean checkInput(double intData){
        boolean inputstatus = true;

        if(intData<0){
            JOptionPane.showMessageDialog(this,"Price must be greater than 0",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
            inputstatus = false;
        }
        return inputstatus;
    }
}
