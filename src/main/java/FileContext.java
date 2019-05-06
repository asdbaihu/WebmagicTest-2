import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class FileContext {
    public static List<String> getFileContext(String path) throws Exception {
        FileReader fileReader =new FileReader(path);
        BufferedReader bufferedReader =new BufferedReader(fileReader);
        List<String> list =new ArrayList<String>();
        String str=null;
        while((str=bufferedReader.readLine())!=null) {
            if(str.trim().length()>2) {
                list.add(str);
            }
        }
        return list;
    }


    public static void main(String[] args) throws Exception {
        String path = "F:\\url.txt";
        int count = 0;
        List<String> urls = getFileContext(path);
        for (String url : urls) {
            System.out.println(url);
            count++;

        }
        System.out.println(count);



    }

}