import com.atm.command.BalanceCommand;
import com.atm.command.InputCommand;
import com.atm.command.OutputCommand;
import com.atm.currencies.CurrencyHolder;
import com.atm.util.CheckService;
import com.atm.util.UserScanner;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class AtmTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    @Autowired
    private CheckService checkService;
    @Autowired
    private CurrencyHolder currencyHolder;

    @Test
    public void CheckServiceTest() {
        Assert.assertFalse(checkService.banknotesCheck(300));

        Assert.assertTrue(checkService.banknotesCheck(100));
        Assert.assertTrue(checkService.banknotesCheck(1000));
        Assert.assertTrue(checkService.banknotesCheck(10));
        Assert.assertTrue(checkService.banknotesCheck(500));
        Assert.assertTrue(checkService.banknotesCheck(50));
        Assert.assertTrue(checkService.banknotesCheck(5000));

        Assert.assertTrue(checkService.currencyCheck("ABC"));
        Assert.assertFalse(checkService.currencyCheck("ABCD"));

        Assert.assertTrue(checkService.numberCheck("1000"));
        Assert.assertFalse(checkService.numberCheck("-1000"));
        Assert.assertFalse(checkService.numberCheck("0"));

        Assert.assertTrue(checkService.valueCheck("1000"));
        Assert.assertFalse(checkService.valueCheck("-1000"));
        Assert.assertFalse(checkService.valueCheck("0"));


        Assert.assertTrue(checkService.directivesCheck("?"));
        Assert.assertTrue(checkService.directivesCheck("+"));
        Assert.assertTrue(checkService.directivesCheck("-"));

        Assert.assertFalse(checkService.directivesCheck("#"));
    }

    @Test
    public void CurrencyHolderCheck() throws FileNotFoundException {
        System.setOut(new PrintStream(outContent));

        currencyHolder.putCashIntoHolder("RUB", "100", 100);
        Assert.assertEquals("OK\n", outContent.toString());

        System.setOut(new PrintStream(outContent));
        currencyHolder.getBalance();
        Assert.assertEquals("OK\nRUB 100 100\n", outContent.toString());

        Assert.assertEquals("100 10", currencyHolder.getCashFromHolder("RUB", "1000"));
    }

    @TestConfiguration
    static class ServiceImplTestContextConfiguration {
        @Bean
        public CheckService checkService() {
            return new CheckService() {
            };
        }

        @Bean
        public CurrencyHolder currencyHolder() {
            return new CurrencyHolder() {
            };
        }

        @Bean
        public UserScanner userScanner() {
            return new UserScanner() {
            };
        }
    }
}
