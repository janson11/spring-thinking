package sun.net.www.protocol.x;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @Description: X Handler测试示例
 * @Author: Janson
 * @Date: 2020/8/30 18:09
 **/
public class HandlerTest {

    public static void main(String[] args) throws IOException {
        // 类似于classpath：
        URL url = new URL("x:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }
}
