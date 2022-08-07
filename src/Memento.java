public class Memento {
    private String operator;
    private double left;
    private boolean operatorPressed;
    private boolean operatorActive;
    public boolean isOperatorPressed() {
        return operatorPressed;
    }
    public void setOperatorPressed(boolean operatorPressed) {
        this.operatorPressed = operatorPressed;
    }
    public boolean isOperatorActive() {
        return operatorActive;
    }
    public void setOperatorActive(boolean operatorActive) {
        this.operatorActive = operatorActive;
    }
    public String getOperator() {
        return operator;
    }
    public void setOperator(String operator) {
        this.operator = operator;
    }
    public double getLeft() {
        return left;
    }
    public void setLeft(double left) {
        this.left = left;
    }
}
