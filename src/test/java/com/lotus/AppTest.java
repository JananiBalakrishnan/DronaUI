
  package com.lotus;
  
  import java.io.File; import java.util.List;
  
  import org.openqa.selenium.By; import org.openqa.selenium.OutputType; import
  org.openqa.selenium.TakesScreenshot; import org.openqa.selenium.WebDriver;
  import org.openqa.selenium.WebElement; import
  org.openqa.selenium.chrome.ChromeDriver; import org.testng.annotations.Test;
  import org.apache.commons.io.FileUtils; import org.junit.Assert;
  
  public class AppTest{
	  /* 
	    * This test connects to google website
		* searches for the text - selenium
		* fetches the result statistics text displayed with time
		*/
		@Test
		public void fetchGoogleSearchStats() throws InterruptedException {
			
			WebDriver driver = null;
			try {
			driver = new ChromeDriver();
			System.setProperty("webdriver.chrome.driver", "chromedriver");
			driver.get("http://www.google.com/");
			
			WebElement element = driver.findElement(By.xpath("//input[@type='text']"));
			element.sendKeys("selenium");
			element.submit();		
			
			//String text = driver.findElement(By.xpath("//div[@id='resultStats']")).getText();
			String text = driver.findElement(By.id("resultStats")).getText();
			System.out.println(" results :"+text);
			
			File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File("result.png"));
			
				/*
				 * List<WebElement> links = driver.findElements(By.tagName("a")); int linkcount
				 * = links.size(); System.out.println("linkcount :" + linkcount);
				 * 
				 * for (WebElement myElement : links){ String link = myElement.getText();
				 * System.out.println("link : "+link);
				 * System.out.println("myElement : "+myElement); }
				 */
	         

				/*
				 * WebElement links2 = driver.findElement(By.xpath("//*[@id='hdtb-msb-vis']"));
				 * Assert.assertTrue(links2.isDisplayed());
				 */
			
			List<WebElement> links2 = driver.findElements(By.xpath("//*[@id='hdtb-msb-vis']"));
			System.out.println(" links2.size() :"+links2.size());
			for(int i=0;i<links2.size();i++) {
				System.out.println("******** "+links2.get(i).getText() +"\n");
				System.out.println("\n");
			}
			  
		}
			catch(Exception ex) {
				System.out.println(" printing exception "+ex.getMessage());
			}
			finally {
				driver.quit();
			}
		}
	}