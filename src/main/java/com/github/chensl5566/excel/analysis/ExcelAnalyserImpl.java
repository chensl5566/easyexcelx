package com.github.chensl5566.excel.analysis;

import com.github.chensl5566.excel.analysis.v03.XlsSaxAnalyser;
import com.github.chensl5566.excel.analysis.v07.XlsxSaxAnalyser;
import com.github.chensl5566.excel.context.AnalysisContext;
import com.github.chensl5566.excel.context.AnalysisContextImpl;
import com.github.chensl5566.excel.event.AnalysisEventListener;
import com.github.chensl5566.excel.exception.ExcelAnalysisException;
import com.github.chensl5566.excel.metadata.Sheet;
import com.github.chensl5566.excel.modelbuild.ModelBuildEventListener;
import com.github.chensl5566.excel.support.ExcelTypeEnum;

import java.io.InputStream;
import java.util.List;

/**
 * @author jipengfei
 */
public class ExcelAnalyserImpl implements ExcelAnalyser {

    private AnalysisContext analysisContext;

    private BaseSaxAnalyser saxAnalyser;

    private BaseSaxAnalyser getSaxAnalyser() {
        if (saxAnalyser == null) {
            try {
                if (ExcelTypeEnum.XLS.equals(analysisContext.getExcelType())) {
                    this.saxAnalyser = new XlsSaxAnalyser(analysisContext);
                } else {
                    this.saxAnalyser = new XlsxSaxAnalyser(analysisContext);
                }
            } catch (Exception e) {
                throw new ExcelAnalysisException("Analyse excel occur file error fileType " + analysisContext.getExcelType(),
                    e);
            }
        }
        return this.saxAnalyser;
    }

    public void init(InputStream inputStream, ExcelTypeEnum excelTypeEnum, Object custom,
                     AnalysisEventListener eventListener, boolean trim) {
        analysisContext = new AnalysisContextImpl(inputStream, excelTypeEnum, custom,
            eventListener, trim);
    }

    public void analysis(Sheet sheetParam) {
        analysisContext.setCurrentSheet(sheetParam);
        analysis();
    }

    public void analysis() {
        BaseSaxAnalyser saxAnalyser = getSaxAnalyser();
        appendListeners(saxAnalyser);
        saxAnalyser.execute();

        analysisContext.getEventListener().doAfterAllAnalysed(analysisContext);
    }

    public List<Sheet> getSheets() {
        BaseSaxAnalyser saxAnalyser = getSaxAnalyser();
        saxAnalyser.cleanAllListeners();
        return saxAnalyser.getSheets();
    }

    private void appendListeners(BaseSaxAnalyser saxAnalyser) {
        if (analysisContext.getCurrentSheet() != null && analysisContext.getCurrentSheet().getClazz() != null) {
            saxAnalyser.appendLister("model_build_listener", new ModelBuildEventListener());
        }
        if (analysisContext.getEventListener() != null) {
            saxAnalyser.appendLister("user_define_listener", analysisContext.getEventListener());
        }
    }

}
