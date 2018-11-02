package com.github.chensl5566.excel.context;

import com.github.chensl5566.excel.metadata.ExcelHeadProperty;
import com.github.chensl5566.excel.metadata.Table;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.OutputStream;

/**
 * @author jipengfei
 */
public interface GenerateContext {


    /**
     * @return current analysis sheet
     */
    Sheet getCurrentSheet();

    /**
     *
     * @return
     */
    CellStyle getCurrentHeadCellStyle();

    /**
     * @return
     */
    CellStyle getCurrentContentStyle();


    /**
     * @return
     */
    Workbook getWorkbook();

    /**
     * @return
     */
    OutputStream getOutputStream();

    /**
     * @param sheet
     */
    void buildCurrentSheet(com.github.chensl5566.excel.metadata.Sheet sheet);

    /**
     * @param table
     */
    void buildTable(Table table);

    /**
     * @return
     */
    ExcelHeadProperty getExcelHeadProperty();

    /**
     *
     * @return
     */
    boolean needHead();
}


