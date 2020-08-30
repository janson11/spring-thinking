package sun.net.www.protocol.x;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * @Description: x 协议 {@link URLStreamHandler}
 * @Author: Janson
 * @Date: 2020/8/30 17:59
 **/
public class Handler extends URLStreamHandler {
    @Override
    protected URLConnection openConnection(URL u) throws IOException {
        return new XURLConnection(u);
    }
}
