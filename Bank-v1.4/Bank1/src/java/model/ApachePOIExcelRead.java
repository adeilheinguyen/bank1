//package model;
//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//
///**
// *
// * @author 
// */
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//import model_test.AccountIBTest;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//
//public class ApachePOIExcelRead {
//
//    private static final String FILE_NAME = "C:\\Users\\Admin\\Downloads\\Bank-v1.4\\Bank1\\login.xlsx";
//    public static final int COLUMN_INDEX_ID = 0;
//    public static final int COLUMN_INDEX_TITLE = 1;
//    public static final int COLUMN_INDEX_PRICE = 2;
//    public static final int COLUMN_INDEX_QUANTITY = 3;
//    public static final int COLUMN_INDEX_TOTAL = 4;
//
//    private static Workbook getWorkbook(InputStream inputStream, String excelFilePath) throws IOException {
//        Workbook workbook = null;
//        if (excelFilePath.endsWith("xlsx")) {
//            workbook = new XSSFWorkbook(inputStream);
//        } else if (excelFilePath.endsWith("xls")) {
//            workbook = new HSSFWorkbook(inputStream);
//        } else {
//            throw new IllegalArgumentException("The specified file is not Excel file");
//        }
//
//        return workbook;
//    }
//
//    public static List<AccountIBTest> readDataLoginFromExcel(String excelFilePath) throws IOException {
//        List<AccountIBTest> listBooks = new ArrayList<>();
//
//        // Get file
//        InputStream inputStream = new FileInputStream(new File(excelFilePath));
//
//        // Get workbook
//        Workbook workbook = getWorkbook(inputStream, excelFilePath);
//
//        // Get sheet
//        Sheet sheet = workbook.getSheetAt(0);
//
//        // Get all rows
//        Iterator<Row> iterator = sheet.iterator();
//        while (iterator.hasNext()) {
//            Row nextRow = iterator.next();
//            if (nextRow.getRowNum() == 0) {
//                // Ignore header
//                continue;
//            }
//
//            // Get all cells
//            Iterator<Cell> cellIterator = nextRow.cellIterator();
//
//            // Read cells and set value for book object
//            AccountIBTest accountIBTest = new AccountIBTest();
//            ArrayList<String> list = new ArrayList<String>();
//            while (cellIterator.hasNext()) {
//                //Read cell
//                Cell cell = cellIterator.next();
//                Object cellValue = getCellValue(cell);
//                if (cellValue == null || cellValue.toString().isEmpty()) {
//                    continue;
//                }
//                // Set value for book object
//                int columnIndex = cell.getColumnIndex();
//                switch (columnIndex) {
//                    case 1:
//                        accountIBTest.setUsername(getCellValue(cell).toString().substring(1,getCellValue(cell).toString().length()-1));
//                        break;
//                    case 2:
//                        accountIBTest.setPassword(getCellValue(cell).toString().substring(1,getCellValue(cell).toString().length()-1));
//                        break;
//                    case 3:
//                        accountIBTest.setExpectation(getCellValue(cell).toString().substring(1,getCellValue(cell).toString().length()-1));
//                        break;
//
//                    default:
//                        break;
//                }
//            }
//            listBooks.add(accountIBTest);
//        }
//        workbook.close();
//        inputStream.close();
//        return listBooks;
//    }
//
//    private static Object getCellValue(Cell cell) {
//        CellType cellType = cell.getCellTypeEnum();
//        Object cellValue = null;
//        switch (cellType) {
//            case BOOLEAN:
//                cellValue = cell.getBooleanCellValue();
//                break;
//            case FORMULA:
//                Workbook workbook = cell.getSheet().getWorkbook();
//                FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
//                cellValue = evaluator.evaluate(cell).getNumberValue();
//                break;
//            case NUMERIC:
//                cellValue = cell.getNumericCellValue();
//                break;
//            case STRING:
//                cellValue = cell.getStringCellValue();
//                break;
//            case _NONE:
//            case BLANK:
//            case ERROR:
//                break;
//            default:
//                break;
//        }
//
//        return cellValue;
//    }
//
//    public static void main(String[] args) throws IOException {
//        final List<AccountIBTest> accountIBs = readDataLoginFromExcel(FILE_NAME);
//        for (AccountIBTest list1 : accountIBs) {
//            System.out.println(list1.getUsername()+" "+list1.getPassword()+" "+list1.getExpectation());
//           
//        }
//
//    }
//
//}