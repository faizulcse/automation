package testcases;

import helper.DriverConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.FBookPage;

public class FBookTest extends DriverConfig {
    @Test
    public void test1(){
        new FBookPage().setEmailAndPassword();
        new FBookPage().clickLogin();
        Assert.assertEquals(new FBookPage().getLoginPageTitle(), props.getProperty("expected_result_fb"));
    }
}
