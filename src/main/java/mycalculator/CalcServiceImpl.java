package mycalculator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author leonid.barsucovschi
 */

public class CalcServiceImpl implements CalculatorService {
    @Override
    public String rpn(String inputStr) {
        StringBuilder sbStack = new StringBuilder(""),
                sbOut = new StringBuilder("");
        char cInp, cTmp;

        for (int i = 0; i < inputStr.length(); i++) {
            cInp = inputStr.charAt(i);
            if (isOperation(cInp)) {
                while (sbStack.length() > 0) {
                    cTmp = sbStack.substring(sbStack.length() - 1).charAt(0);
                    if (isOperation(cTmp) && (operationPriority(cInp) <= operationPriority(cTmp))) {
                        sbOut.append(" ").append(cTmp).append(" ");
                        sbStack.setLength(sbStack.length() - 1);
                    } else {
                        sbOut.append(" ");
                        break;
                    }
                }
                sbOut.append(" ");
                sbStack.append(cInp);
            } else if ('(' == cInp) {
                sbStack.append(cInp);
            } else if (')' == cInp) {
                cTmp = sbStack.substring(sbStack.length() - 1).charAt(0);
                while ('(' != cTmp) {
                    if (sbStack.length() < 1) {
                        throw new ExceptionMessage("Bracket parsing error. Check if the expression is correct.");
                    }
                    sbOut.append(" ").append(cTmp);
                    sbStack.setLength(sbStack.length() - 1);
                    cTmp = sbStack.substring(sbStack.length() - 1).charAt(0);
                }
                sbStack.setLength(sbStack.length() - 1);
            } else {
                sbOut.append(cInp);
            }
        }

        while (sbStack.length() > 0) {
            sbOut.append(" ").append(sbStack.substring(sbStack.length() - 1));
            sbStack.setLength(sbStack.length() - 1);
        }

        return sbOut.toString();
    }

    @Override
    public boolean isOperation(char sign) {
        switch (sign) {
            case '-':
            case '+':
            case '*':
            case '/':
                return true;
        }
        return false;
    }

    @Override
    public byte operationPriority(char operation) {
        switch (operation) {
            case '*':
            case '/':
                return 2;
        }
        return 1;
    }

    @Override
    public double calculate(String inputStr) {
        double num1, num2;
        String strTmp;
        Deque<Double> stack = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(inputStr);
        while (st.hasMoreTokens()) {
            try {
                strTmp = st.nextToken().trim();
                if (1 == strTmp.length() && isOperation(strTmp.charAt(0))) {
                    if (stack.size() < 2) {
                        throw new Exception("Invalid amount of data on the stack for operation " + strTmp);
                    }
                    num2 = stack.pop();
                    num1 = stack.pop();
                    switch (strTmp.charAt(0)) {
                        case '+':
                            num1 += num2;
                            break;
                        case '-':
                            num1 -= num2;
                            break;
                        case '/':
                            if (num2 == 0) {
                                num2 = 1;
                            }
                            num1 /= num2;
                            break;
                        case '*':
                            num1 *= num2;
                            break;
                        default:
                            throw new Exception("Invalid operation " + strTmp);
                    }
                    stack.push(num1);
                } else {
                    num1 = Double.parseDouble(strTmp);
                    stack.push(num1);
                }
            } catch (Exception e) {
                throw new ExceptionMessage("Invalid character in expression");
            }
        }

        if (stack.size() > 1) {
            throw new ExceptionMessage("The number of operators does not match the number of operands");
        }

        return stack.pop();
    }

    @Override
    public void startApp() {
        Scanner sc = new Scanner(System.in);
        String inpStr;
        boolean repeatApp = true;
        String repStr;
        System.out.println("");
        System.out.println("                         <<<<<<<<  Calculator >>>>>>>>                           ");
        System.out.println("");
        System.out.println(" Operations: + , - , * , / and parentheses precedence ( ) are supported: ");
        while (repeatApp) {
            System.out.println("");
            System.out.println(" Enter an expression to calculate please ");
            System.out.println("");
            System.out.print(" >>> ");
            inpStr = sc.nextLine();
            inpStr = rpn(inpStr);
            System.out.println("");
            System.out.println(" Result = " + Math.ceil(calculate(inpStr)));
            System.out.println("");
            System.out.println(" Do you want to start again ? ( If YES press + , IF NOT press - ) ");
            System.out.println("");
            System.out.print(" >>> ");
            repStr = sc.nextLine();

            if (repStr.equals("+")) {
                repeatApp = true;
            } else if (repStr.equals("-")) {
                repeatApp = false;
                System.out.println("");
                System.out.println("              Goodbye!              ");
            }
        }
    }
}

