import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;

public class OperationCenter {
    private final Map<String, DoubleBinaryOperator> map= new HashMap<>();
    private static OperationCenter instance;
    private OperationCenter(){
        init();
    }
    public static OperationCenter getInstance(){
            if(instance==null){
                synchronized (OperationCenter.class){
                    if(instance==null){
                        instance=new OperationCenter();
                    }
                }
            }
        return instance;
    }
    public OperationCenter add(String operator, DoubleBinaryOperator function){
        map.put(operator, function);
        return this;
    }
    public OperationCenter remove(String operator){
        map.remove(operator);
        return this;
    }
    private void init(){
        map.put("+", (x,y)->x+y);
        map.put("-",(x,y)->x-y);
        map.put("*",(x,y)->x*y);
        map.put("/",(x,y)->{if (y==0)return 0;return x/y;});
    }
    public double calculate(String operator, double num1, double num2){
        return map.get(operator).applyAsDouble(num1, num2);
    }
}


