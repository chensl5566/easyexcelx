package com.github.chensl5566.excel.modelbuild;

import com.github.chensl5566.excel.context.AnalysisContext;
import com.github.chensl5566.excel.event.AnalysisEventListener;
import com.github.chensl5566.excel.exception.ExcelGenerateException;
import com.github.chensl5566.excel.metadata.DataTransformationAdapter;
import com.github.chensl5566.excel.metadata.ExcelColumnProperty;
import com.github.chensl5566.excel.metadata.ExcelHeadProperty;
import com.github.chensl5566.excel.util.TypeUtil;
import org.apache.commons.beanutils.BeanUtils;

import java.util.List;

/**
 *
 * @author jipengfei
 */
public class ModelBuildEventListener extends AnalysisEventListener {


    @Override
    public void invoke(Object object, AnalysisContext context) {


        if(context.getExcelHeadProperty() != null && context.getExcelHeadProperty().getHeadClazz()!=null ){
            Object resultModel = buildUserModel(context, (List<String>)object);
            context.setCurrentRowAnalysisResult(resultModel);
        }

    }



    private Object buildUserModel(AnalysisContext context, List<String> stringList) {
        ExcelHeadProperty excelHeadProperty = context.getExcelHeadProperty();

        Object resultModel;
        try {
            resultModel = excelHeadProperty.getHeadClazz().newInstance();
        } catch (Exception e) {
            throw new ExcelGenerateException(e);
        }
        if (excelHeadProperty != null) {
            for (int i = 0; i < stringList.size(); i++) {
                ExcelColumnProperty columnProperty = excelHeadProperty.getExcelColumnProperty(i);
                if (columnProperty != null) {
                    //todo 做适配
                    Class<? extends DataTransformationAdapter> adapter = columnProperty.getDataTransformationAdapter();
                    boolean isint = adapter.isInterface();

                    //如果是默认值，则为接口对象，否则为实现类
                    if (!adapter.isInterface()) {
                        try {
                            DataTransformationAdapter adapter1 = adapter.newInstance();
                            stringList.set(i, adapter1.transformation(stringList.get(i)).toString());
                        } catch (InstantiationException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }


                    Object value = TypeUtil.convert(stringList.get(i), columnProperty.getField(),
                        columnProperty.getFormat(),context.use1904WindowDate());
                    if (value != null) {
                        try {
                            BeanUtils.setProperty(resultModel, columnProperty.getField().getName(), value);
                        } catch (Exception e) {
                            throw new ExcelGenerateException(
                                columnProperty.getField().getName() + " can not set value " + value, e);
                        }
                    }
                }
            }
        }
        return resultModel;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }
}
