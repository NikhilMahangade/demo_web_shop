package utills;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

	public static List<String[]> getLoginData(String filePath) {
		List<String[]> data = new ArrayList<>();
		try (FileInputStream fis = new FileInputStream(filePath); Workbook workbook = new XSSFWorkbook(fis)) {

			Sheet sheet = workbook.getSheetAt(0);
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);
				String email = row.getCell(0).getStringCellValue();
				String password = row.getCell(1).getStringCellValue();
				data.add(new String[] { email, password });

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

//	public static void main(String[] args) {
//		String filePath = "C:\\Users\\AM116405\\Downloads\\LoginTest.xlsx"; // Update path if needed
//
//		List<String[]> loginData = ExcelReader.getLoginData(filePath);
//		for (String[] row : loginData) {
//			System.out.println("email: " + row[0] + ", Password: " + row[1]);
//		}
//	}

}
