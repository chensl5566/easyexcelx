package com.github.chensl5566.excel.write;

import com.github.chensl5566.excel.metadata.Sheet;
import com.github.chensl5566.excel.metadata.Table;
import com.github.chensl5566.excel.support.ExcelTypeEnum;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author jipengfei
 */
public interface ExcelBuilder {


    void init(InputStream templateInputStream, OutputStream out, ExcelTypeEnum excelType, boolean needHead);


    void addContent(List data, int startRow);


    void addContent(List data, Sheet sheetParam);


    void addContent(List data, Sheet sheetParam, Table table);


    void finish();
}
