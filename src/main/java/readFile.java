import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class readFile {
    private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static byte[] readFile(String path){
        File file = new File(path);
        FileInputStream input = null;
        try{
            input = new FileInputStream(file);
            byte[] buf =new byte[input.available()];
            input.read(buf);
        }catch (Exception e){
            e.printStackTrace();
        }finally{
            if(input != null){
                try {
                    input.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public static String bytesToHexFun1(byte[] bytes) {
        // 一个byte为8位，可用两个十六进制位标识
        char[] buf = new char[bytes.length * 2];
        int a = 0;
        int index = 0;
        for(byte b : bytes) { // 使用除与取余进行转换
            if(b < 0) {
                a = 256 + b;
            } else {
                a = b;
            }

            buf[index++] = HEX_CHAR[a / 16];
            buf[index++] = HEX_CHAR[a % 16];
        }

        return new String(buf);
    }
    private static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (byte aSrc : src) {
            int v = aSrc & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    private static byte[] FileInputStreamTest(String Path) throws IOException {
        FileInputStream fis = new FileInputStream(Path);
        byte[] buf = new byte[1024];
        int hasRead = 0;

        //read()返回的是单个字节数据（字节数据可以直接专程int类型)，但是read(buf)返回的是读取到的字节数，真正的数据保存在buf中
        while ((hasRead = fis.read(buf)) > 0) {
            //每次最多将1024个字节转换成字符串，这里tmp2.txt中的字符小于1024，所以一次就读完了
            //循环次数 = 文件字符数 除以 buf长度
            System.out.println(new String(buf, 0 ,hasRead));
            /*
             * 将字节强制转换成字符后逐个输出，能实现和上面一样的效果。但是如果源文件是中文的话可能会乱码

            for (byte b : buf)    {
                char ch = (char)b;
                if (ch != '\r')
                System.out.print(ch);
            }
            */
        }
        //在finally块里close更安全

        fis.close();
        return buf;
    }

    public static void main(String[] args) throws IOException {
        String a = "F:\\下载\\20190401_星辰之兴证资管-浦发银行1号集合资产管理计划2018年年度报告.PDF";
        String text = bytesToHexString(FileInputStreamTest(a));
        System.out.println(text);
    }
}
