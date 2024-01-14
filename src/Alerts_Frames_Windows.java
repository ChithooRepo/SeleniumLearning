import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v114.page.Page;
import org.openqa.selenium.devtools.v114.page.model.DialogType;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Alerts_Frames_Windows {


    public static EdgeDriver driver;

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Automate the following element methods");
        launchBrowser("https://demoqa.com/selectable");
        selectable();
    }
    public static void launchBrowser(String url){

        driver=new EdgeDriver();
        driver.navigate().to(url);
        driver.manage().window().maximize();
    }


    public static void Alerts() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Alerts, Frame & Windows']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@class='btn btn-light ']//child::span[text()='Alerts']")).click();

        WebElement alert1=driver.findElement(By.xpath("//span[text()='Click Button to see alert ']//following::button[@id='alertButton']"));
        alert1.click();
        Alert a1=driver.switchTo().alert();
        a1.accept();
        //a1.sendKeys("123");
        //a1.dismiss();

        WebElement alert2=driver.findElement(By.xpath("//span[text()='On button click, alert will appear after 5 seconds ']//following::button[@id='timerAlertButton']"));
        alert2.click();
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(6000));
        wait.until(ExpectedConditions.alertIsPresent());

        Alert a2=driver.switchTo().alert();
        a2.accept();
    }

    public static void windows() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Alerts, Frame & Windows']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@class='btn btn-light ']//child::span[text()='Browser Windows']")).click();

        String main=driver.getWindowHandle();
        driver.findElement(By.xpath("//button[@id='tabButton']")).click();

        Set<String> win=driver.getWindowHandles();
        for(String w:win){
            driver.switchTo().window(w);
        }
        driver.close();
        driver.switchTo().window(main);

        Thread.sleep(4000);

        driver.findElement(By.xpath("//button[@id='messageWindowButton']")).click();

        Set<String> win1=driver.getWindowHandles();
        for(String w1:win1){
           //if(!w1.equals(main)){
                driver.switchTo().window(w1);
                //break;
            //}
        }
        driver.manage().window().maximize();
        Thread.sleep(2000);
        String y=driver.findElement(By.xpath("/html/body/text()")).getText();
        System.out.println(y);

    }

    public static void frames() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Alerts, Frame & Windows']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@class='btn btn-light ']//child::span[text()='Frames']")).click();
        WebElement frame1=driver.findElement(By.xpath("//iframe[@id='frame1']"));
        driver.switchTo().frame(frame1);
        String frm1=driver.findElement(By.xpath("//*[@id=\"sampleHeading\"]")).getText();
        System.out.println("This is frame 1 text "+frm1);

        driver.switchTo().defaultContent();

        WebElement frame2=driver.findElement(By.xpath("//iframe[@id='frame2']"));
        driver.switchTo().frame(frame2);
        String frm2=driver.findElement(By.xpath("//*[@id=\"sampleHeading\"]")).getText();
        System.out.println("This is frame 2 text "+frm2);

    }

    public static void nestedFrame() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Alerts, Frame & Windows']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@class='btn btn-light ']//child::span[text()='Nested Frames']")).click();

        WebElement parent=driver.findElement(By.xpath("//iframe[@id='frame1']"));
        driver.switchTo().frame(parent);
        Thread.sleep(2000);
        WebElement child=driver.findElement(By.xpath("//p[text()='Child Iframe']"));
        driver.switchTo().frame(child);
        System.out.println("This is inside child frame "+child.getText());

    }

    public static void dialog() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='card mt-4 top-card']//h5[text()='Alerts, Frame & Windows']")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//li[@class='btn btn-light ']//child::span[text()='Modal Dialogs']")).click();

        //DevTools devtool=driver.getDevTools();
        //devtool.createSession();

        WebElement small=driver.findElement(By.xpath("//button[@id='showSmallModal']"));
        small.click();
        System.out.println(small.getText());
        WebElement msg=driver.findElement(By.xpath("//div[@class='modal-body']"));
        System.out.println(msg.getText());
        driver.findElement(By.xpath("//button[@id='closeSmallModal']")).click();

    }

    public static void sortable() throws InterruptedException {
        WebElement source=driver.findElement(By.xpath("//div[text()='Six']"));
        WebElement Target=driver.findElement(By.xpath("//div[text()='One']"));

        Actions act=new Actions(driver);
        act.clickAndHold(source).moveToElement(Target).release().perform();
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[@id='demo-tab-grid']")).click();
        act.clickAndHold(source).moveToElement(Target).release().perform();
    }

    public static void selectable() throws InterruptedException {
        List<WebElement> li=driver.findElements(By.xpath("//ul[@id='verticalListContainer']//li"));


        Actions act=new Actions(driver);
        act.click(li.get(0)).click(li.get(1)).build().perform();


    }
}
