package gymnago;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.opencsv.CSVReader;

import jxl.read.biff.BiffException;

public class Login_Test {
	WebDriver driver;
	ExtentReports extentreport;
	ExtentHtmlReporter Html;
	ExtentTest testcase1;
	ExtentTest testcase2;

	@BeforeTest
	public void befourtest()
	{
		extentreport = new ExtentReports();
		Html =new ExtentHtmlReporter(System.getProperty("user.dir")+"/test-output/myReport.html");
		Html.config().setDocumentTitle("Automation Report");
		Html.config().setReportName("Functional Report");
		Html.config().setTheme(Theme.DARK);
		extentreport.attachReporter(Html);
		extentreport.setSystemInfo("Testername", "satheesh");
		extentreport.setSystemInfo("Browser", "GoogleChrome");
		extentreport.setSystemInfo("Os", "Windows 7");
		System.setProperty("webdriver.chrome.driver", "./chromedriver/chromedriver.exe");
		driver= new ChromeDriver();
		testcase1= extentreport.createTest(" Enter The Browser");
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String url ="https://skoolgo.pixelmindit.com:5000/#/";
		
		driver.get(url);
		testcase1.log(Status.INFO, " Entering The Skoolgo Url");
	}
	@AfterTest
	public void aftertest()
	{
		testcase2.log(Status.INFO,"Login Testing is completed");
		driver.quit();
		extentreport.flush();
	}
	
	@Test
	public void login() throws IOException, InterruptedException 
	{
		String csv_file="./Data/logindata.csv";
		CSVReader reader = new CSVReader(new FileReader(csv_file));
		String[] cell;
		while ((cell = reader.readNext()) != null) 
		{   
			String username = cell[0];
			String password = cell[1];

			testcase2= extentreport.createTest(" Entering The Username and password");
			driver.findElement(By.id("userName")).sendKeys(username);
			testcase2.log(Status.INFO, "Entering the Username = "+username);
			driver.findElement(By.id("password")).sendKeys(password);
			testcase2.log(Status.INFO, "Entering the password = "+password);
			driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
			testcase2.log(Status.INFO, " login Successfully");
			Thread.sleep(8000);
			driver.navigate().refresh(); 
		    Thread.sleep(8000);
		try
		{ 
			driver.findElement(By.xpath("//span[@class='userName']")).isDisplayed();
			{ 
				Actions action = new Actions(driver);
				System.out.println("Element is Visible"); 
				testcase2.log(Status.PASS,"ActualURL and ExpectedURL are Equal");
				testcase2.log(Status.PASS,"Homepage Displayed");
				testcase2.log(Status.PASS,"Login Testcase is Pass");
			WebElement username1 =	driver.findElement(By.xpath("//span[@class='userName']"));
			WebElement password1=	driver.findElement(By.xpath("//small[text()='Logout']"));
				action.moveToElement(username1).build().perform();
				action.doubleClick(password1).build().perform();
				driver.navigate().refresh(); 
			} 
		}
		catch(NoSuchElementException e)
		{ 
			{ 
				System.out.println("Element is InVisible"); 
				testcase2.log(Status.ERROR,"ActualURL and ExpectedURL are NOT Equal");
				testcase2.log(Status.ERROR,"Homepage is not Displayed");
				testcase2.log(Status.ERROR,"Login Testcase is failed");
				TakesScreenshot screenshot= (TakesScreenshot)  driver;
				File src = screenshot.getScreenshotAs(OutputType.FILE);
				String destination =System.getProperty("user.dir")+"/myReport.png";
				File des = new File(destination);
				org.openqa.selenium.io.FileHandler.copy(src, des);
				testcase2.addScreenCaptureFromPath(destination);
			}
		}
		}
	
	}
}
		

