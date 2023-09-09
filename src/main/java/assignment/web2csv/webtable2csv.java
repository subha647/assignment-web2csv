package assignment.web2csv;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class webtable2csv {

	public static void main(String[] args) {
        // Set the path to the ChromeDriver executable (You should download it from the Selenium website)
        // Initialize ChromeDriver
        WebDriver driver = new ChromeDriver();
        String output_csv = "table_data.csv";
        
        try {
        // Navigate to the web page
        driver.get("http://the-internet.herokuapp.com/challenging_dom");
        // Create a CSVWriter
        FileWriter writer = new FileWriter(output_csv);
		CSVWriter csvWriter = new CSVWriter(writer); 

        // Locate the HTML table element (modify this based on your web page's structure)
        WebElement thead = driver.findElement(By.xpath("//div[@class='large-10 columns']/table/thead"));
        // System.out.println(thead.getText().replace(" ",",").replace("Action",""));
        String[] headArray = thead.getText().replace("Action","").split(" ");
        csvWriter.writeNext(headArray);
        	
        List <WebElement> tbody_rows = driver.findElements(By.xpath("//div[@class='large-10 columns']/table/tbody/tr"));
        
        
        for (WebElement row : tbody_rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            String[] rowData = new String[columns.size()-1];
            int i = 0;
            for (WebElement column : columns) {
            	
            	// Eliminating the last column
                if (i == columns.size()-1) {
                	break;
                }
                rowData[i++] = column.getText();
            
            }
            // Write the data to the CSV file
            csvWriter.writeNext(rowData);
            
        }
        // Close the CSVWriter and FileWriter
        	csvWriter.close();
        	writer.close();
       }
        catch (IOException e) {
        	e.printStackTrace();
        }
        csvtopreetytable printtable = new csvtopreetytable();
        printtable.show_table(output_csv);
        driver.quit();
        
       
    }
}
