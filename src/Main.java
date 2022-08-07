/*
    written by mehmet butgul
    date: 07/08/2022
    mail: mehmetbutgulmail@gmail.com
 */
import javax.swing.*;
public class Main {
    public static void main(String[] args) {
        try {
            MainPane pane = new MainPane();
            pane.setTitle("Calculator");
            pane.setSize(500, 600);
            pane.setVisible(true);
            pane.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        }catch(Exception e){
                JOptionPane.showMessageDialog(new JFrame(), "ERROR");
        }
    }
}