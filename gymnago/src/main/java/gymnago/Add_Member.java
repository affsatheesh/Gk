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

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.opencsv.CSVReader;

public class Add_Member 
{
	WebDriver driver;
	@BeforeTest
	public void befourtest() throws InterruptedException, AWTException
	{
		driver= new ChromeDriver();	
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://skoolgo.pixelmindit.com:5000/#/");
		driver.findElement(By.id("userName")).sendKeys("admin@pixel.com");
		driver.findElement(By.id("password")).sendKeys("sk12345");
		driver.findElement(By.xpath("//button[text()='LOGIN']")).click();
		Thread.sleep(8000);
		 driver.manage().window().maximize();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,1000)");
		
		Thread.sleep(8000);
		WebElement humanResource= driver.findElement(By.xpath("//p[text()='Human Resources']"));
		WebElement  Addemployee = driver.findElement(By.xpath("//a[text()='Add Employee']"));
		Actions action = new Actions(driver);
		Thread.sleep(8000);
		action.moveToElement(humanResource).click().build().perform();
		action.doubleClick(Addemployee).build().perform();
	}
		@AfterTest
		public void aftertest()
		{
			driver.quit();
		}
		
		@Test
		public void login() throws IOException, InterruptedException 
		{
			String csv_file="./Data/Addmember.csv";
			CSVReader reader = new CSVReader(new FileReader(csv_file));
			String[] cell;
			while ((cell = reader.readNext()) != null) 
			{   
				
				//String designation = cell[0];
				String fullname = cell[1];
				String phonenumber = cell[2];
				String emailid = cell[3];
				String employeeType = cell[4];
				String	personalId = cell[5];
				String	branch = cell[6];
				String	Gender = cell[7];
				
/*				String	Address = cell[8];
				String	Userphoto = cell[9];
				String	nationality = cell[10];
				String	visa = cell[11];*/
				
				//Selecting Designation
				Thread.sleep(8000);
				
			WebElement Designation =driver.findElement(By.xpath("//select[@id='designation']"));
				Select select = new Select(Designation);
				
				List<WebElement> designationoptions= select.getOptions();	
				int fullList1=designationoptions.size();
				
				for (WebElement des : designationoptions)
				{
					String destext = des.getText();
					select.selectByVisibleText("Employee");
				}
				
				// Selecting Name
				driver.findElement(By.id("fullName")).sendKeys(fullname);
				// Selecting Phonenumber
				driver.findElement(By.xpath("//input[@class='PhoneInputInput']")).sendKeys(phonenumber);
				// Selecting EmailId
				driver.findElement(By.id("email")).sendKeys(emailid);
				
				// Selecting Employee
				WebElement Employe=	driver.findElement(By.id("employeeType"));				
				Select selectEmployee = new Select(Employe);			
				List<WebElement> Employeenoptions= select.getOptions();	
				int fullList=Employeenoptions.size();
				
				for (WebElement emp : Employeenoptions)
				{
					String emptext = emp.getText();
					selectEmployee.selectByVisibleText(employeeType);
				}
				
				// Selecting PersonalId
				driver.findElement(By.id("personalId")).sendKeys(personalId);
				
				// Selecting Branch
		     	WebElement Branch	=driver.findElement(By.xpath("//div[@class=' css-1hwfws3']"));	
				Select selectBranch = new Select(Branch);			
				List<WebElement> Branchoptions= select.getOptions();			
				for (WebElement Bran : Branchoptions)
				{
					String brantext = Bran.getText();
					selectBranch.selectByVisibleText(branch);
				}
				
				// Selecting gender
			WebElement gender=	driver.findElement(By.id("gender"));
				Select selectsgender= new Select(gender);
				List<WebElement> genderoptions= selectsgender.getOptions();

				for (WebElement gend : genderoptions)
				{
					String gentertext = gend.getText();
					selectsgender.selectByVisibleText(Gender);
				}
				
				Thread.sleep(5000);
				// Click on DOB
				WebElement DOB= driver.findElement(By.xpath("//input[@value='15/08/1996']"));
				DOB.click();
				Thread.sleep(5000);

				/*// Click On Year
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
				// Selecting Address
				driver.findElement(By.id("address")).sendKeys("Bangalore");
				
				WebElement joindate= driver.findElement(By.xpath("//input[@value='15/08/1996']"));
				joindate.click();
				Thread.sleep(5000);

				// Click On Year
				WebElement year1= driver.findElement(By.xpath("//h6[text()='2020']"));
				year1.click();

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
				// Selecting Address
				driver.findElement(By.id("address")).sendKeys("Bangalore");
				
				
	/*			// Uploading Photo
				WebElement uploading = driver.findElement(By.id("customFile"));
				Actions action1 = new Actions(driver);
				action1.moveToElement(uploading).click().build().perform();
	              
		    	//	String imagefiles= "C:\\Users\\~ SATHEZ ~\\satheesh.jpg";
				
				StringSelection imageselection = new StringSelection(Userphoto);
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
				
				Thread.sleep(5000);
				
				// Selecting nationality
				WebElement nationalitys=	driver.findElement(By.id("nationality"));
				
				Select selectnationality = new Select(nationalitys);
				List<WebElement> nationalityoptions= select.getOptions();
				
				for (WebElement national : nationalityoptions)
				{
					String gentertext = national.getText();
					selectsgender.selectByVisibleText(nationality);
				}
				
				Thread.sleep(5000);
				
				
				
				driver.findElement(By.xpath("//select[@class='bg-warning rounded w-100px px-3 py-1 border border-warning text-white']")).sendKeys(visa);	
				driver.findElement(By.xpath("//button[text()='Submit']")).click();
				//driver.findElement(By.xpath("//button[text()='Cancel']")).click();
*/				
			}
			
			}
		
		
	}
			