package haodong.com.netprac;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * describe :
 * date on 2019/3/19
 * author linghailong
 * email 105354999@qq.com
 */
public class UrlTest {
    public static void main(String[] args) throws IOException {
        try {
            URL url=new URL("http://www.lalcats.com");
            try(InputStream in=url.openStream()){
                int c;
                while ((c=in.read())!=-1){
                    System.out.println();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
