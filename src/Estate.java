import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Estate {
    public static void main(String[] args) throws InterruptedException {

        //for opening the main webpage
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://maharera.mahaonline.gov.in");
        WebElement changeLanguage = driver.findElement((By.xpath(".//ul[@class=\"list-inline\"]/li/a[@href=\"/HeaderMain/ChangeCurrentCulture/1\"]")));  //change language to english
        changeLanguage.click();

        //traversing through it (main page)
        List<WebElement> opt = driver.findElements(By.xpath(".//ul[@class=\"nav navbar-nav\"]/li[@class=\"dropdown\"]")); //find the navbar menu
        opt.get(3).click(); //get the 4th option of navbar clicked i.e. Registration
        List<WebElement> registrationOptions = driver.findElements(By.xpath(".//*[@aria-labelledby=\"Registration\"]/li")); //store all the options of Registration menu in a List
        registrationOptions.get(0).click(); //click the first option of the Registration (Stored at 0th position of the List)

        driver.switchTo().alert().accept(); //switch to the alert box and accept the prompt to open the Search Page in another Tab

        //give control to the child page i.e Search page in new tab
        String handlewindow = (String) driver.getWindowHandles().toArray()[1];
        driver.switchTo().window(handlewindow); //control transferred


        //Traverse through the Search Page
        WebElement registeredProjects = driver.findElement(By.xpath("//input[@id=\"Promoter\"]")); //find the HTML element input
        registeredProjects.click(); //to click on the radio button *Registered Projects

        driver.findElement(By.id("CertiNo")).sendKeys("P51800004446"); //find the element search and fill it with the specified RERA ID of the Property
        driver.findElement(By.id("btnSearch")).click(); //click on the search button

        //search result : find and click on the view details button
        List<WebElement> searchResult = driver.findElements(By.xpath("//td[@class=\"grid-cell\"]/b"));
        searchResult.get(0).click();


        //get the link of the print page
        List<WebElement> allLinks = driver.findElements(By.xpath("//td[@class=\"grid-cell\"]/b/a"));
        String link1 = allLinks.get(0).getAttribute("href");
        System.out.println(link1);



        // to handle the print page
        String handleWindow = (String) driver.getWindowHandles().toArray()[2];
        driver.switchTo().window(handleWindow); //control transferred

        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.chord(Keys.LEFT_CONTROL, "p")).build().perform();
        //the operating system isn't supporting this pressing keys left ctrl and such - found in stackoverflow




//        System.out.println(driver.findElement(By.xpath("//a[@href=\"https://maharerait.mahaonline.gov.in/searchlist/search?MenuID=1069\"]")));

    }
}


//        WebElement xyz = driver.findElement(By.id("Registration"));
//        WebElement xyz = driver.findElement(By.id("Registration"));
//        driver.findElement(By.xpath("//a[@href=\"/PrintPreview/PrintPreview?q=yNBu%2fj%2fAt3pgKh2M0OvOxjkPrNawGaDwuhTBUedx0wMAxf%2bQAD%2bcBkIFbL43KAqjIPduFzK2Ioc9%2fBK0nKkoQloCyNw%2fsURD8Nq4jV6K7QFCpfQP%2f2fJhHNHWxCu6qMLefQPwrOF4jGWJXbcOn3h5aSVB1RMP6xf21IMVBdnJqdQYMhyxnqbVQ%3d%3d\"]"));


//        String str = "नोंदणीकृत प्रकल्प";
//        for(WebElement webElement : opt) {
//            if(webElement.getText().trim().equals(str)){
//                System.out.println(webElement);
//                break;
//            }
//        }