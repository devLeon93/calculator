import mycalculator.CalcServiceImpl;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author leonid.barsucovschi
 */

public class CalcServiceTest {

    @Test
    public void calcShouldCheckSignOperation(){
        CalcServiceImpl calcService = new CalcServiceImpl();
        boolean operation = calcService.isOperation('+');
        if (!operation){
            System.out.println("It's not valid operation");
            Assert.fail();
        } else System.out.println("Valid operation ");

    }
    @Test
    public void calcShouldTransformRPN(){
        CalcServiceImpl calcService = new CalcServiceImpl();
        String myInpExpr = calcService.rpn("34 - 12 * (90 - 23)");
        System.out.println("Your Expression in Reverse Polish Notation: \n  " + calcService.rpn(myInpExpr));
    }

    @Test
    public void calcShouldAdding(){
        CalcServiceImpl calcService = new CalcServiceImpl();
        double resSum = calcService.calculate("23 12 +");
        if(resSum != 35.0){
            Assert.fail();
        } else System.out.println("Result = " + resSum);
    }

    @Test
    public void calcShouldSubstr(){
        CalcServiceImpl calcService = new CalcServiceImpl();
        Assert.assertEquals(8.0,calcService.calculate("12 4 -"),0);
    }

    @Test
    public void calcShouldMult(){
        CalcServiceImpl calcService = new CalcServiceImpl();
        Assert.assertEquals(30.0,calcService.calculate("15 2 *"),0);
    }

    @Test
    public void calcShouldDiv(){
        CalcServiceImpl calcService = new CalcServiceImpl();
        Assert.assertEquals(4.0,calcService.calculate("8 2 /"),0);
    }
}