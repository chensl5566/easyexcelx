package com.github.chensl5566.excel.analysis;

import com.github.chensl5566.excel.event.AnalysisEventListener;
import com.github.chensl5566.excel.metadata.Sheet;
import com.github.chensl5566.excel.support.ExcelTypeEnum;

import java.io.InputStream;
import java.util.List;

/**
 *
 * @author jipengfei
 */
public interface ExcelAnalyser {

    void init(InputStream inputStream, ExcelTypeEnum excelTypeEnum, Object custom, AnalysisEventListener eventListener,
              boolean trim);

    void analysis(Sheet sheetParam);



    void analysis();


    List<Sheet> getSheets();



}
