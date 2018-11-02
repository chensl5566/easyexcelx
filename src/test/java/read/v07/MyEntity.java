package read.v07;

import com.github.chensl5566.excel.annotation.ExcelProperty;
import com.github.chensl5566.excel.metadata.BaseRowModel;

public class MyEntity extends BaseRowModel {

    @ExcelProperty(value = "名字", index = 0)
    private String name;

    @ExcelProperty(value = "纬度", index = 1, transformationAdapter = GPSTransformAdapter.class)
    private String lnt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLnt() {
        return lnt;
    }

    public void setLnt(String lnt) {
        this.lnt = lnt;
    }
}
