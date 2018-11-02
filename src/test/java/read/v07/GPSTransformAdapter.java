package read.v07;

import com.github.chensl5566.excel.metadata.DataTransformationAdapter;

public class GPSTransformAdapter implements DataTransformationAdapter {
    @Override
    public Object transformation(Object obj) {
        System.out.println("========数据处理" + obj);

        return obj;
    }
}
