package read.v07.listener;

import com.github.chensl5566.excel.ExcelWriter;
import com.github.chensl5566.excel.context.AnalysisContext;
import com.github.chensl5566.excel.event.AnalysisEventListener;
import com.github.chensl5566.excel.metadata.BaseRowModel;
import com.github.chensl5566.excel.metadata.Sheet;
import javamodel.ExcelRowJavaModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jipengfei
 */
public class Excel2007WithJavaModelAnalysisListener extends AnalysisEventListener {

    private ExcelWriter excelWriter;

    public ExcelWriter getExcelWriter() {
        return excelWriter;
    }

    public void setExcelWriter(ExcelWriter excelWriter) {
        this.excelWriter = excelWriter;
    }

    public void invoke(Object object, AnalysisContext context) {
        List< BaseRowModel> ll = new ArrayList();
        ll.add((BaseRowModel)object);
        Sheet sheet = context.getCurrentSheet();
        sheet.setClazz(ExcelRowJavaModel.class);
        excelWriter.write(ll,sheet);
    }

    public void doAfterAllAnalysed(AnalysisContext context) {

    }

}
