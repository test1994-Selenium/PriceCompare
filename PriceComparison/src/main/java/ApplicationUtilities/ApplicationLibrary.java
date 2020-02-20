package ApplicationUtilities;

import PublicUtilities.Constant;
import PublicUtilities.PublicLibrary;
import org.openqa.selenium.WebElement;
import java.util.NoSuchElementException;

public class ApplicationLibrary {
    public PublicLibrary publicLibrary;

    public ApplicationLibrary()
    {
        publicLibrary=new PublicLibrary();
    }
    public String getObjectPriceFromFlipkart()
    {
        WebElement element=null;
        publicLibrary.navigateToUrl(Constant.flipkartUrl);
        publicLibrary.implicitWait(Constant.shortWait);
        PublicLibrary.driver.findElement(ApplicationObjects.loginCloseButton).click();
        PublicLibrary.driver.findElement(ApplicationObjects.flipkartSearchBox).sendKeys(Constant.searchItemName);
        PublicLibrary.driver.findElement(ApplicationObjects.flipkartSearchButton).click();
        try {
             element = PublicLibrary.driver.findElement(ApplicationObjects.flipkartItemPriceSection);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Item not present in the application");
        }
        return element.getText().substring(1).replaceAll(",","");
    }


    public void priceComparator()
    {
        double flipkartItemPrice=Double.parseDouble(getObjectPriceFromFlipkart());
        double amazonItemPrice=Double.parseDouble(getObjectPriceFromAmazon());
        if(amazonItemPrice>flipkartItemPrice)
        {
            System.out.println("Item price is lesser in Flipkart("+flipkartItemPrice+") than Amazon("+amazonItemPrice+")");
        }
        else if(amazonItemPrice==flipkartItemPrice)
        {
            System.out.println("Both Application have same price for searched Item ="+amazonItemPrice);
        }
        else
        {
            System.out.println("Item price is lesser in Amazon("+amazonItemPrice+") than Flipkart("+flipkartItemPrice+")");
        }
    }
    public String getObjectPriceFromAmazon()
    {
        WebElement element=null;
        publicLibrary.navigateToUrl(Constant.amazonUrl);
        publicLibrary.implicitWait(Constant.shortWait);
        publicLibrary.clickElement(ApplicationObjects.signinSection);
        publicLibrary.setValue(ApplicationObjects.amazonSearchBox, Constant.searchItemName);
        publicLibrary.clickElement(ApplicationObjects.amazonSearchButton);
        try {
            element = PublicLibrary.driver.findElement(ApplicationObjects.amazonItemPriceSection);
        }
        catch (NoSuchElementException e)
        {
            System.out.println("Item not present in the application");
        }
        return element.getText().replaceAll(",","");
    }
}
