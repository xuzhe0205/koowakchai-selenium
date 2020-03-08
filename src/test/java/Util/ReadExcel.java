package Util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReadExcel {
    public Map<String, List<String>> readExcelData(Map<String, List<String>> excelData, String sheetName) throws Exception{
        System.out.println("wocao??");
        HSSFWorkbook workbook = new HSSFWorkbook();
        String excelDirectory = "/Users/zhe/Desktop/NEU/INFO6250/data/signup_data.xls";
        FileInputStream excelFile = new FileInputStream(excelDirectory);
        workbook = (HSSFWorkbook) WorkbookFactory.create(excelFile);
        Sheet sheet = workbook.getSheet(sheetName);
        List<String> columnNames = new ArrayList<String>();
        for (int c = 0; c<sheet.getRow(0).getLastCellNum(); c++){
            columnNames.add(sheet.getRow(0).getCell(c).toString());
        }
        int numRows = sheet.getLastRowNum() + 1;
        for (int i = 1; i < numRows; i++ ){
            Row row = sheet.getRow(i);
            for (int j = 0; j < columnNames.size(); j++){
                if (i>1) {
                    if (row.getCell(j).toString().length() == 0) {
                        return excelData;
                    }
                    excelData.get(columnNames.get(j)).add(row.getCell(j).toString());
                }
                else {
                    List<String> colValues = new ArrayList<String>();
                    String cellValue = row.getCell(j).toString();
                    colValues.add(cellValue);
                    excelData.put(columnNames.get(j), colValues);
                }
            }
        }
        return excelData;
    }
}
