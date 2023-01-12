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
        boolean operation = calcService.isOperation('-');
        if (!operation){
            System.out.println("It's not valid operation");
            Assert.fail();
        } else System.out.println("Valid operation");

    }
}
