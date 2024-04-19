package doan.quanlykho.be.helper;

import doan.quanlykho.be.entity.Supplier;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.text.SimpleDateFormat;

public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERS = { "Mã nhà cung cấp", "Tên ", "Email","Số điện thoại","Địa chỉ","Ngày tạo","Ngày sửa","Trạng thái giao dịch", "Trạng thái liên kết"};
    static String SHEET = "Suppliers";
    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
    public static List<Supplier> excelToSuppliers(InputStream is) {
        try {
            Workbook workbook = new XSSFWorkbook(is);
            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();
            List<Supplier> suppliers = new ArrayList<Supplier>();
            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cellsInRow = currentRow.iterator();
                Supplier supplier = new Supplier();
                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();
                    switch (cellIdx) {
                        case 0:
                            supplier.setCode(currentCell.getStringCellValue());
                            break;
                        case 1:
                            supplier.setName(currentCell.getStringCellValue());
                            break;
                        case 2:
                            supplier.setEmail(currentCell.getStringCellValue());
                            break;
                        case 3:
                            supplier.setPhone(currentCell.getStringCellValue());
                            break;
                        case 4:
                            supplier.setAddress(currentCell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cellIdx++;
                }
                suppliers.add(supplier);
            }
            workbook.close();
            return suppliers;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream supplierToExcel(List<Supplier> suppliers) {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Sheet sheet = workbook.createSheet(SHEET);
            for (int i = 0; i < 9; i++) {
                if (i == 2 ||i ==4) {
                    sheet.setColumnWidth(i, 10000);
                    continue;
                } else if (i == 7 || i == 8) {
                    sheet.setColumnWidth(i, 7000);
                    continue;
                }
                sheet.setColumnWidth(i, 6000);
            }
            // Header
            CellStyle style = workbook.createCellStyle();
            // Tạo một font mới
            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 13);
            // Đặt font cho CellStyle
            style.setFont(font);
            Row headerRow = sheet.createRow(0);
            for (int col = 0; col < HEADERS.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(HEADERS[col]);
                cell.setCellStyle(style);
            }
            int rowIdx = 1;
            for (Supplier supplier : suppliers) {
                String dateCreate = sdf.format(new Date(supplier.getCreatedAt().getTime()));
                String modifyDate = sdf.format(new Date(supplier.getUpdateAt().getTime()));
                Row row = sheet.createRow(rowIdx++);
                row.createCell(0).setCellValue(supplier.getCode());
                row.createCell(1).setCellValue(supplier.getName());
                row.createCell(2).setCellValue(supplier.getEmail());
                row.createCell(3).setCellValue(String.valueOf(supplier.getPhone()));
                row.createCell(4).setCellValue(supplier.getAddress());
                row.createCell(5).setCellValue(dateCreate);
                row.createCell(6).setCellValue(modifyDate);
                row.createCell(7).setCellValue(supplier.getStatusTransaction() ? "Đang giao dịch" : "Ngừng giao dịch");
                row.createCell(8).setCellValue(supplier.getIsDelete() ? "Ngừng liên kiết" : "Đang liên kết");
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }
}
