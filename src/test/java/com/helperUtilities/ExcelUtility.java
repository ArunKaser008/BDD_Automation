package com.helperUtilities;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

/**
 * @author Arun Kaser
 */
public class ExcelUtility {

    /**
     * @method read_ExcelFile used to read all data from input sheet
     */
    public static void read_ExcelFile(String fileName,int index) {
        try {
            File file = new File(fileName);                    //creating a new file instance
            FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the fil
            XSSFWorkbook wb = new XSSFWorkbook(fis);            // creating Workbook instance that refers to .xlsx file
            XSSFSheet sheet = wb.getSheetAt(index);           //creating a Sheet object to retrieve object
            Iterator<Row> itr = sheet.iterator();               //iterating over excel file
            while (itr.hasNext()) {
                Row row = itr.next();
                Iterator<Cell> cellIterator = row.cellIterator(); //iterating over each column
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cell.getCellType()) {
                        case STRING:                             //field that represents string cell type
                            System.out.print(cell.getStringCellValue() + "\t\t\t");
                            break;
                        case NUMERIC:                           //field that represents number cell type
                            System.out.print(cell.getNumericCellValue() + "\t\t\t");
                            break;
                        default:
                    }
                }
                System.out.println("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @method readSpecificValueFromExcelFile used to read a specific data
     */

    public static String readSpecificValueFromExcelFile(String fileName, int sheetIndex, int vRow, int vColumn) {
        String value = null;          //variable for storing the cell value
        Workbook wb = null;           //initialize Workbook null
        try {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            wb = new XSSFWorkbook(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Sheet sheet = wb.getSheetAt(sheetIndex);   //getting the XSSFSheet object at given index
        Row row = sheet.getRow(vRow);          //returns the logical row
        Cell cell = row.getCell(vColumn);      //getting the cell representing the given column
        value = cell.getStringCellValue();     //getting cell value
        return value;                        //returns the cell value
    }
}
