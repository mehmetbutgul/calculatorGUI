import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MainPane extends JFrame {
    private final ArrayList<JButton> buttons=new ArrayList<>();
   private JButton[] buttons0_10;
   private final JButton plus=new JButton("+");
   private final JButton minus=new JButton("-");
   private final JButton multiplier=new JButton("*");
   private final JButton divider=new JButton("/");
   private final JButton allClear=new JButton("AC");
   private final JButton equal=new JButton("=");
   private final JButton comma=new JButton(".");
    private final JButton clear=new JButton("C");
    private final JButton positivNegativ =new JButton("+/-");
    private final JButton threeZero =new JButton("000");
   private final GridLayout layout=new GridLayout(5,4);
   private final JTextField textField=new JTextField("0");
   private final Memento memento=new Memento();
   private final JPanel panel=new JPanel();

   public MainPane(){
       initForInitialization();
       initForReferance();
        initForLayout();
       initForListeners();
       initForColors();
    }
    private void initForInitialization(){
        buttons0_10=new JButton[10];
        for(int i=1; i<=10; i++){
            buttons0_10[i%10]=new JButton(String.valueOf(i%10));
        }
    }
    private void initForReferance(){
       for (int i=1;i<=3;i++){
           buttons.add(buttons0_10[i]);
       }
       buttons.add(plus);
        for (int i=4;i<=6;i++){
            buttons.add(buttons0_10[i]);
        }
        buttons.add(minus);
        for (int i=7;i<=9;i++){
            buttons.add(buttons0_10[i]);
        }
        buttons.add(multiplier);
        buttons.add(allClear);
        buttons.add(buttons0_10[0]);
        buttons.add(clear);
        buttons.add(divider);
        buttons.add(threeZero);
        buttons.add(positivNegativ);
        buttons.add(comma);
        buttons.add(equal);
    }
    private void initForColors(){
       textField.setBackground(Color.black);
       textField.setForeground(Color.yellow);
       textField.setFont(new Font(Arrays.stream(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()).findAny().get(),5,70));
       for (JButton button:buttons){
           button.setBackground(new Color(0,0,0));
           button.setFont(new Font(Arrays.stream(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()).findAny().get(),5,50));
            button.setForeground(Color.ORANGE);
       }
    }
    private void initForLayout(){
       textField.setHorizontalAlignment(JTextField.RIGHT);
       add(textField, BorderLayout.PAGE_START);
       panel.setBounds(0,0,400,400);
       panel.setLayout(layout);
        for (JButton button:buttons)
            panel.add(button);
        add(panel, BorderLayout.CENTER);
    }
    private void initForListeners(){
        for(int i=1; i<=10; i++){
            buttons0_10[i%10].addActionListener(new Buttons0_10ActionListener());
        }
        plus.addActionListener(new OperatorsActionListener());
        minus.addActionListener(new OperatorsActionListener());
        divider.addActionListener(new OperatorsActionListener());
        multiplier.addActionListener(new OperatorsActionListener());
        equal.addActionListener(new EqualActionListener());
        allClear.addActionListener(new AllClearActionListener());
        threeZero.addActionListener(new ThreeZeroActionListener());
        clear.addActionListener(new ClearActionListener());
        positivNegativ.addActionListener(new PositivNegativActionListener());
        comma.addActionListener(new CommaActionListener());
    }
    private void write(double x){
        write(String.valueOf(x));
    }
    private void write(String str){
        String text=str;
        if(text.contains(".")) {
            StringTokenizer tokenizer = new StringTokenizer(text, ".");
            if (tokenizer.countTokens() > 1) {
                tokenizer.nextToken();
                if (tokenizer.nextToken().equals("0"))
                    text = new StringTokenizer(text, ".").nextToken();
            }
        }
        textField.setText(text);
    }
    private String getText(){
        String text=textField.getText();
        if(text.contains(".")){
            String[] strs=text.split(".");
            if(strs[1].equals("0"))
                text=strs[0];
        }
        return text;
    }
    private double getTextAsDouble(){
       return Double.parseDouble(textField.getText());
    }
    private class Buttons0_10ActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if (memento.isOperatorActive()) {
                if (memento.isOperatorPressed()) {
                    write(e.getActionCommand());
                    memento.setOperatorPressed(false);
                }
                else write(textField.getText() + e.getActionCommand());
            } else {
                if (getTextAsDouble() != 0)
                    write(textField.getText() + e.getActionCommand());
                else write(e.getActionCommand());
            }

        }
   }

    private class OperatorsActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            memento.setLeft(getTextAsDouble());
            memento.setOperator(e.getActionCommand());
            memento.setOperatorActive(true);
            memento.setOperatorPressed(true);
        }
    }
    private class EqualActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(memento.isOperatorActive()) {
                double result = OperationCenter.getInstance().calculate(memento.getOperator(), memento.getLeft(), getTextAsDouble());
                memento.setLeft(result);
                write(result);
                memento.setOperatorActive(false);
            }
        }
    }
    private class AllClearActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            memento.setLeft(0.0);
            write("0");
            memento.setOperatorActive(false);
        }
    }
    private class ThreeZeroActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            write(getTextAsDouble()*1000);

        }
    }
    private class ClearActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            write(textField.getText().length()>1 ? textField.getText().substring(0,textField.getText().length()-1):"0");
        }
    }
    private class PositivNegativActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            write(getTextAsDouble()*(-1));
        }
    }
    private class CommaActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(!textField.getText().contains("."))
                write(getText()+".");
        }
    }


}
