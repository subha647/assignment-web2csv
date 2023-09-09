package assignment.web2csv;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class csvtopreetytable {
	public void show_table(String csv_path) {
		
		// Print the csv file as table in console
        try (CSVReader reader = new CSVReader(new FileReader(csv_path))) {
            List<String[]> lines = reader.readAll();

            // Determine column widths based on the maximum length of each column
            int[] columnWidths = new int[lines.get(0).length];
            for (String[] line : lines) {
                for (int i = 0; i < line.length; i++) {
                    if (line[i].length() > columnWidths[i]) {
                        columnWidths[i] = line[i].length();
                    }
                }
            }

            // Print the table header
            for (int i = 0; i < lines.get(0).length; i++) {
                System.out.printf("| %-" + (columnWidths[i] + 2) + "s", lines.get(0)[i]);
            }
            System.out.println();
            for (int i = 0; i < lines.get(0).length; i++) {
            	String repeatedString = new String(new char[columnWidths[i]+2]).replace("\0","=");
                System.out.printf("|=%-" + (columnWidths[i] + 2) + "s",repeatedString );
            }
            System.out.println();

            // Print the table data
            for (int i = 1; i < lines.size(); i++) {
                for (int j = 0; j < lines.get(i).length; j++) {
                    System.out.printf("| %-" + (columnWidths[j] + 2) + "s", lines.get(i)[j]);
                }
                System.out.println();
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
		
	}

}
