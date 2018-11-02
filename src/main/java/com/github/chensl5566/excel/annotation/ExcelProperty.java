package com.github.chensl5566.excel.annotation;

import com.github.chensl5566.excel.metadata.DataTransformationAdapter;

import java.lang.annotation.*;

/**
 * @author jipengfei
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ExcelProperty {

     /**
      * @return
      */
     String[] value() default {""};


     /**
      * @return
      */
     int index() default 99999;

     /**
      *
      * default @see TypeUtil
      * if default is not  meet you can set format
      *
      * @return
      */
     String format() default "";

     /**
      * 数据转换接口
      * @return
      */
     Class<? extends DataTransformationAdapter> transformationAdapter() default DataTransformationAdapter.class;
}
