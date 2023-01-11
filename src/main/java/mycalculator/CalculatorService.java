package mycalculator;

/**
 * @author leonid.barsucovschi
 */

public interface CalculatorService {

    String rpn(String inputStr);

    boolean isOperation(char sign);

    byte operationPriority(char operation);

    double calculate(String inputStr);

    void startApp();


}
