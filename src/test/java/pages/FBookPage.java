package pages;

import helper.DriverConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FBookPage extends DriverConfig {
    public FBookPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(name = "email") WebElement email;
    @FindBy(name = "pass") WebElement pass;
    @FindBy(id = "loginbutton") WebElement login;

    public void setEmailAndPassword(){
        email.sendKeys(props.getProperty("email"));
        pass.sendKeys(props.getProperty("password"));
    }
    public void clickLogin(){
        login.click();
    }

    public String getLoginPageTitle(){
        return driver.getTitle();
    }
}
