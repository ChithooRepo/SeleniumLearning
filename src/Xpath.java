import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

class Xpath {
    public static void main(String[] args) {
        System.out.println("Welcome again !!");
        launchBrowser();
    }

    public static void launchBrowser(){
        WebDriver driver=new EdgeDriver();
        driver.navigate().to("https://demoqa.com/");
        driver.manage().window().maximize();
    }


}
