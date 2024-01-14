import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.interactions.Actions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

class Elements{

    public static WebDriver driver;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Automate the following element methods");
        launchBrowser();
        //webTable();
    }

    public static void launchBrowser(){

        driver=new EdgeDriver();
        driver.navigate().to("https://demoqa.com/profile");
        driver.manage().window().maximize();
    }

    public static void TextBox() throws InterruptedException {
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        EdgeOptions eop=new EdgeOptions();
        eop.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        Thread.sleep(6000);
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Elements']")).click();
        driver.findElement(By.xpath("//div[@class='element-group']//child::span[text()='Text Box']")).click();
        driver.findElement(By.xpath("//form[@id='userForm']//child::input[@placeholder='Full Name']")).sendKeys("Test");
        driver.findElement(By.xpath("//form[@id='userForm']//child::input[contains(@placeholder,'@example.com')]")).sendKeys("User@example.com");
        driver.findElement(By.xpath("//form[@id='userForm']//textarea[@placeholder='Current Address']")).sendKeys("No 1,win street,chennai,Thailand.");
        driver.findElement(By.xpath("//form[@id='userForm']//textarea[@id='permanentAddress']")).sendKeys("No 1,win street,chennai,Thailand." + Keys.TAB);
        Thread.sleep(2000);
        driver.findElement(By.id("submit")).click();
    }

    public static void Links() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Elements']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='element-group']//child::span[text()='Links']")).click();
        //driver.findElement(By.partialLinkText("Home")).click();
        List<WebElement> linklist=driver.findElements(By.xpath("//a[@id='dynamicLink']//following::a"));
        for(WebElement E: linklist){
            if(E.getText().equals("Moved")){
                System.out.println(E.getText());
                E.click();
            }
        }

    }

    public static void BrokenLinks() throws InterruptedException{
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Elements']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='element-group']//child::span[text()='Broken Links - Images']")).click();

        List<WebElement> brokenLink=driver.findElements(By.partialLinkText("Link"));
        for(WebElement E:brokenLink){
            System.out.println(E.getText());
            String href=E.getAttribute("href");
            if(href!=null && !href.isEmpty()){
                try{
                    URL url=new URL(href);
                    HttpURLConnection api=(HttpURLConnection) url.openConnection();
                    api.setRequestMethod("HEAD");
                    api.connect();
                    int code=api.getResponseCode();
                    if(code!=200){
                        System.out.println("Broken Link");
                    }else{
                        System.out.println("Good link");
                    }
                    api.disconnect();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void buttons() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Elements']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='element-group']//child::span[text()='Buttons']")).click();
        WebElement doubleclick=driver.findElement(By.xpath("//button[@id='doubleClickBtn']"));
        Actions act=new Actions(driver);
        act.doubleClick(doubleclick).perform();
        Thread.sleep(2000);
        WebElement rclick=driver.findElement(By.xpath("//button[@id='rightClickBtn']"));
        act.contextClick(rclick).perform();

        //Get X Y co-ordinate
        Point xy=doubleclick.getLocation();
        System.out.println((xy.getX() + xy.getY()));

        //Get color of the button
        String color=rclick.getCssValue("background-color");
        System.out.println(color);

        //Get size of the button (height and width)
        System.out.println(rclick.getSize().getHeight() + rclick.getSize().getWidth());

    }

    public static void checkBox() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Elements']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='element-group']//child::span[text()='Check Box']")).click();
        Thread.sleep(8000);
        driver.findElement(By.xpath("//span[text()='Public']//preceding-sibling::span[@class='rct-checkbox']")).click();

    }

    public static void radiobutton() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Elements']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='element-group']//child::span[text()='Radio Button']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//label[@for='yesRadio']")).click();
        /*WebElement yesradio=driver.findElement(By.xpath("//input[@id='yesRadio']"));
        if(yesradio.isEnabled()){
            yesradio.click();
            System.out.println("is enabled");
        }*/
        Thread.sleep(2000);

        WebElement noradio=driver.findElement(By.xpath("//label[@for='noRadio']//preceding-sibling::input"));
        if(noradio.isEnabled()){
            noradio.click();
            System.out.println("is enabled");
        }else{
            System.out.println("not enabled");
        }

    }

    public static void webTable() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Elements']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='element-group']//child::span[text()='Web Tables']")).click();
//Table
        WebElement table=driver.findElement(By.xpath("//div[@class='rt-table']"));
        List<WebElement> headList=table.findElements(By.xpath("//div[@role='columnheader']"));
        for(WebElement e:headList){
            System.out.print(e.getText()+"\t");
        }
//Row
        List<WebElement> rowList=driver.findElements(By.xpath("//div[@class='rt-tr']"));
        for(WebElement row: rowList){
//column
            List<WebElement> colList=row.findElements(By.xpath("//div[@class='rt-td']"));
            for(WebElement col: colList){
                System.out.print(col.getText()+"\t");
            }
            System.out.println();
        }



    }


}
