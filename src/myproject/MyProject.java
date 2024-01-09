package myproject;

import com.apex.ishwor.gui.Login;
import javax.swing.UIManager;



public class MyProject {

    public static void main(String[] args) {
        try{
        UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }

        Login l1 = new Login();
        l1.setLocationRelativeTo(null);
        l1.setVisible(true);
    }
    
}
