package gymnago;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.opencsv.CSVReader;

public class Signup
{
	WebDriver driver;
	ExtentReports extentreport;
	ExtentHtmlReporter Html;
	ExtentTest testcase1;
	ExtentTest testcase2;
	ExtentTest testcase3;
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
	driver= new ChromeDriver();
	testcase1= extentreport.createTest(" Enter The Browser");
	driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	driver.get("https://skoolgo.pixelmindit.com:5000/#/");
	testcase1.log(Status.INFO, " Entering The Skoolgo Url");
	testcase1.log(Status.INFO, " Successfully Clicked on Signup");
	testcase1.log(Status.INFO, " Create Account Page Displayed");
	WebElement signup =driver.findElement(By.xpath("//a[text()='Sign Up']"));
		signup.click();
		
	}
	@AfterTest
	public void aftertest()
	{
		
		testcase3.log(Status.INFO, "Signup testing Successfully Complited ");
		driver.quit();
		extentreport.flush();
	
	}

	@Test
	public void login() throws IOException, InterruptedException, AWTException 
	{
		String csv_file="./Data/VerifycationData.csv";
		CSVReader reader = new CSVReader(new FileReader(csv_file));
		String[] cell;
		while ((cell = reader.readNext()) != null) 
		{   
			String Name= cell[0];
			String Emailid = cell[1];
			String phonenumbers =cell[2];
			String personalid = cell[3];
			String Nationality = cell[4];
			String Gender = cell[5];
			String Branch = cell[6];
			String Height = cell[7];
			String Weights = cell[8];
			String EmergencyNo = cell[9];
			String relationship= cell[10];
			String imagefiles = cell[11];
			String createpassword = cell[12];
			String conformpass = cell[13];

		testcase2= extentreport.createTest(" Create Account Testcase  ");
		
			driver.findElement(By.id("fullName")).sendKeys(Name);
			testcase2.log(Status.INFO, "Entering the Fullname = "+Name);
			driver.findElement(By.id("email")).sendKeys(Emailid);
			testcase2.log(Status.INFO, "Entering the EmailId = "+Emailid);
			driver.findElement(By.id("mobile")).sendKeys(phonenumbers);
			testcase2.log(Status.INFO, "Entering the Mobile number= "+phonenumbers);
			driver.findElement(By.id("personalId")).sendKeys(personalid);
			testcase2.log(Status.INFO, "Entering the personalId = "+personalid);
			Thread.sleep(5000);

			
			// ==== Calender Handling	====

			// Click on DOB
			WebElement DOB= driver.findElement(By.xpath("//input[@class='MuiInputBase-input MuiInput-input']"));
			DOB.click();
			Thread.sleep(5000);

			// Click On Year
			WebElement year= driver.findElement(By.xpath("//h6[text()='2020']"));
			year.click();

			// Select the Date
			Thread.sleep(5000);
			Actions action = new Actions(driver);
			WebElement myyear= driver.findElement(By.xpath("//div[text()='1996']"));
			action.moveToElement(myyear).build().perform();
			action.doubleClick().build().perform();

			// Select the Month
			Thread.sleep(5000);
			Actions action1 = new Actions(driver);
			WebElement date= driver.findElement(By.xpath("//button[@class='MuiButtonBase-root MuiIconButton-root MuiPickersCalendarHeader-iconButton']"));

			action1.moveToElement(date).click().build().perform();

			// Select the Date
			WebElement day= driver.findElement(By.xpath("//p[text()='12']"));
			action.doubleClick(day).build().perform();
			Thread.sleep(5000);
			
			testcase2.log(Status.INFO, " DOB successfully Entered");
			
			//Select the Nationality
			Thread.sleep(5000);
			WebElement nationality= driver.findElement(By.id("nationality"));
			Select select = new Select(nationality);
			List<WebElement> options= select.getOptions();
			int fullList=options.size();

			for (WebElement webElement : options)
			{
				String text = webElement.getText();
				testcase2.log(Status.INFO, "Enter the Nationality = "+ Nationality);
				select.selectByVisibleText(Nationality);
				testcase2.log(Status.INFO, "Nationality successfully Entered");
				
			}

			//	Gender selecting
			Thread.sleep(5000);

			WebElement gender= driver.findElement(By.id("gender"));
			Select selectsgender= new Select(gender);
			List<WebElement> options1= selectsgender.getOptions();

			for (WebElement webElement : options1)
			{
				String gentertext = webElement.getText();
				testcase2.log(Status.INFO, "Enter the Gender = "+ Gender);
				selectsgender.selectByVisibleText(Gender);
				testcase2.log(Status.INFO, "Gender successfully Entered");

			}
			// Branch selecting
			Thread.sleep(8000);
			WebElement branch= driver.findElement(By.id("branch"));

			Select selectbranch1= new Select (branch);

			List<WebElement> options2= selectbranch1.getOptions();
			int fullList2=options2.size();
			for (WebElement webElement : options2)
			{
				String text2 = webElement.getText();
				testcase2.log(Status.INFO, "Enter the Branch = "+ Branch);
				selectbranch1.selectByVisibleText(Branch);
				testcase2.log(Status.INFO, "Branch successfully Entered");
			}
			//Height
			Thread.sleep(5000);
			WebElement height= driver.findElement(By.id("height"));
			Thread.sleep(3000);
			height.clear();
			height.sendKeys(Height);
			testcase2.log(Status.INFO, "Enter the Height = "+ Height);

			//weight
			Thread.sleep(5000);
			WebElement weight= driver.findElement(By.id("weight"));
			Thread.sleep(3000);
			weight.clear();
			weight.sendKeys(Weights);	
			testcase2.log(Status.INFO, "Enter the Weight = "+ Weights);

			// Emergency No (optional)
			Thread.sleep(3000);
			driver.findElement(By.id("emergencyNumber")).sendKeys(EmergencyNo);
			testcase2.log(Status.INFO, "Enter the EmergencyNo = "+ EmergencyNo);

			// Relationship
			Thread.sleep(3000);
			driver.findElement(By.id("relationship")).sendKeys(relationship);
			testcase2.log(Status.INFO, "Enter the relationship = "+ relationship);

			// User Photo upload
			Thread.sleep(10000);
			testcase2.log(Status.INFO, "Photo uploading Started");
			WebElement	uploading=	driver.findElement(By.xpath("//label[text()='Upload Photo']"));
			Thread.sleep(3000);
			action.moveToElement(uploading).click().build().perform();
              
			//	String imagefiles= "C:\\Users\\~ SATHEZ ~\\satheesh.jpg";
			StringSelection imageselection = new StringSelection(imagefiles);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(imageselection, null);
			Thread.sleep(3000);
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);

			Thread.sleep(8000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);

			Thread.sleep(15000);
			testcase2.log(Status.INFO, "Photo Successfully Uploaded");
			
		     testcase3= extentreport.createTest(" Password Creating testcase  ");
			driver.findElement(By.id("password")).sendKeys(createpassword);
			testcase3.log(Status.INFO, "New Password Entered" + createpassword);
			driver.findElement(By.id("confirmPassword")).sendKeys(conformpass);
			testcase3.log(Status.INFO, "Conform Password Entered" + conformpass);
			Thread.sleep(11000);
			
			testcase3.log(Status.INFO, "Clicked on create account button");
			WebElement createAccount = driver.findElement(By.id("create-account"));
		   action.doubleClick(createAccount).build().perform();
			Thread.sleep(30000);
			
			
			
			
			driver.findElement(By.xpath("//button[text()='VERIFY']")).click();
			
			Thread.sleep(20000);
		try
		{ 
			Thread.sleep(5000);
			driver.findElement(By.xpath("//h2[text()='Authentication']")).isDisplayed();
			{ 
				testcase3.log(Status.PASS, "Authentication Page displayed");
				testcase3.log(Status.PASS, "Authentication code Came successfully");
				testcase3.log(Status.INFO, "===Testcase pass===");
				System.out.println("Element is Visible"); 
			} 
		}
		catch(Exception e)
		{ 
			{ 
				testcase3.log(Status.ERROR, "Authentication Page is not displayed");
				testcase3.log(Status.INFO, "=== Its a Nagative Testcase ===");
				TakesScreenshot screenshot= (TakesScreenshot)  driver;
				File src = screenshot.getScreenshotAs(OutputType.FILE);
				String destination =System.getProperty("user.dir")+"/myReport.png";
				File des = new File(destination);
				org.openqa.selenium.io.FileHandler.copy(src, des);
				testcase2.addScreenCaptureFromPath(destination);
			}
		}
		driver.navigate().refresh();
	}
	}
	
}