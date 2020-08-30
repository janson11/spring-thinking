package org.janson.think.in.spring.resource.springx;

import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @Description: 简单地继承 {@Handler} 类
 * @Author: Janson
 * @Date: 2020/8/30 18:16
 **/
public class Handler extends sun.net.www.protocol.x.Handler {
    // -Djava.protocol.handler.pkgs=org.janson.think.in.spring.resource

    public static void main(String[] args) throws IOException {
        // springx 协议
        URL url = new URL(" springx:///META-INF/default.properties");
        InputStream inputStream = url.openStream();
        System.out.println(StreamUtils.copyToString(inputStream, Charset.forName("UTF-8")));
    }

}
