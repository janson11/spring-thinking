package org.janson.think.in.spring.resource;

import org.apache.commons.io.IOUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.FileSystemResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

/**
 * @Description: 带有字符编码的 {@link FileSystemResource}示例
 * @see FileSystemResourceLoader
 * @Author: Janson
 * @Date: 2020/8/30 11:48
 **/
public class EncodedFileSystemResourceLoaderDemo {

    public static void main(String[] args){
        String currentJavaFilePath = System.getProperty("user.dir") + "/thinking-in-spring/resource/src/main/java/org/janson/think/in/spring/resource/EncodedFileSystemResourceDemo.java";
        File currentJavaFile = new File(currentJavaFilePath);
        FileSystemResourceLoader resourceLoader = new FileSystemResourceLoader();
        Resource resource = resourceLoader.getResource(currentJavaFilePath);
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        Reader reader = null;
        try {
            reader = encodedResource.getReader();
            System.out.println(IOUtils.toString(reader));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
