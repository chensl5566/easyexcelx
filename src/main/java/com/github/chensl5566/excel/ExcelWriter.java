package com.github.chensl5566.excel;

import com.github.chensl5566.excel.metadata.BaseRowModel;
import com.github.chensl5566.excel.metadata.Sheet;
import com.github.chensl5566.excel.metadata.Table;
import com.github.chensl5566.excel.parameter.GenerateParam;
import com.github.chensl5566.excel.support.ExcelTypeEnum;
import com.github.chensl5566.excel.write.ExcelBuilder;
import com.github.chensl5566.excel.write.ExcelBuilderImpl;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 *
 * @author jipengfei
 */
public class ExcelWriter {

    private ExcelBuilder excelBuilder;

    /**
     *
     *
     */
    public ExcelWriter(OutputStream outputStream, ExcelTypeEnum typeEnum) {
        this(outputStream, typeEnum, true);
    }

    private Class<? extends BaseRowModel> objectClass;

    private String sheetName;

    public ExcelWriter(GenerateParam generateParam) {

        this(generateParam.getOutputStream(), generateParam.getType(), true);
        this.objectClass = generateParam.getClazz();
        this.sheetName = generateParam.getSheetName();
    }

    /**
     *
     *
     * @param outputStream
     * @param typeEnum
     */
    public ExcelWriter(OutputStream outputStream, ExcelTypeEnum typeEnum, boolean needHead) {
        excelBuilder = new ExcelBuilderImpl();
        excelBuilder.init(null, outputStream, typeEnum, needHead);
    }

    /**
     *
     * @param templateInputStream
     * @param outputStream
     * @param typeEnum
     */
    public ExcelWriter(InputStream templateInputStream, OutputStream outputStream, ExcelTypeEnum typeEnum, boolean needHead) {
        excelBuilder = new ExcelBuilderImpl();
        excelBuilder.init(templateInputStream,outputStream, typeEnum, needHead);
    }

    /**
     *
     * @param data
     * @param sheet
     * @return this
     */
    public ExcelWriter write(List<? extends BaseRowModel> data, Sheet sheet) {
        excelBuilder.addContent(data, sheet);
        return this;
    }

    //public Sheet(int sheetNo, int headLineMun, Class<? extends BaseRowModel> clazz, String sheetName,
    //             List<List<String>> head) {
    //    this.sheetNo = sheetNo;
    //    this.clazz = clazz;
    //    this.headLineMun = headLineMun;
    //    this.sheetName = sheetName;
    //    this.head = head;
    //}

    @Deprecated
    public ExcelWriter write(List data) {
        if (objectClass != null) {
            return this.write(data,new Sheet(1,0,objectClass));
        }else {
            return this.write0(data,new Sheet(1,0,objectClass));

        }
    }

    /**
     *
     *
     * @param data
     * @param sheet
     * @return this
     */
    public ExcelWriter write1(List<List<Object>> data, Sheet sheet) {
        excelBuilder.addContent(data, sheet);
        return this;
    }

    /**
     *
     * @param data
     * @param sheet
     * @return this
     */
    public ExcelWriter write0(List<List<String>> data, Sheet sheet) {
        excelBuilder.addContent(data, sheet);
        return this;
    }



    public ExcelWriter write(List<? extends BaseRowModel> data, Sheet sheet, Table table) {
        excelBuilder.addContent(data, sheet, table);
        return this;
    }


    public ExcelWriter write0(List<List<String>> data, Sheet sheet, Table table) {
        excelBuilder.addContent(data, sheet, table);
        return this;
    }


    public ExcelWriter write1(List<List<Object>> data, Sheet sheet, Table table) {
        excelBuilder.addContent(data, sheet, table);
        return this;
    }

    public void finish() {
        excelBuilder.finish();
    }
}
