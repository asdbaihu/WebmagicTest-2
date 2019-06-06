import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.webmagic.model.DealExcelProperty;
import org.apache.commons.compress.utils.Lists;
import org.jsoup.helper.DescendableLinkedList;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class exceltext {

    @Test
    public void test1() throws IOException {

        OutputStream out = new FileOutputStream("F:\\下载\\卢冕\\77.xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);
        Sheet sheet = new Sheet(1, 0,DealExcelProperty.class);
        sheet.setSheetName("1");
        writer.write1(createTestListObject(),sheet);
        writer.finish();


    }

    private static List<List<Object>> createTestListObject() {
        List<List<Object>> object = new ArrayList<List<Object>>();
        for (int i = 0; i < 1000; i++) {
            List<Object> da = new ArrayList<Object>();
            da.add("字符串"+i);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            da.add(1);
            object.add(da);
        }
        return object;
    }
}
