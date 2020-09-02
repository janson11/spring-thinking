package org.janson.think.in.spring.i18n;

import lombok.SneakyThrows;
import org.springframework.context.MessageSource;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.Watchable;
import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * @Description: 动态更新 资源 {@link MessageSource}实现
 * 实现步骤：
 * 1、定位资源位置(文件)
 * 2、初始化Properties对象
 * 3、实现AbstractMessageSource#resolveCode方法
 * 4、监听资源文件(Java NIO 2 WatchService)
 * 5、线程池处理文件变化
 * 6、重新装载文件
 * @Author: Janson
 * @Date: 2020/9/2 19:21
 **/
public class DynamicResourceMessageSource extends AbstractMessageSource implements ResourceLoaderAware {
    private static final String resourceFileName = "msg.properties";

    private static final String resourcePath = "/META-INF/" + resourceFileName;

    private static final String ENCODING = "UTF-8";

    private Properties messageProperties;

    private final Resource messagePropertiesResource;

    private ResourceLoader resourceLoader;

    private final ExecutorService executorService;


    public ResourceLoader getResourceLoader() {
        return this.resourceLoader != null ? this.resourceLoader : new DefaultResourceLoader();
    }

    @SneakyThrows
    public DynamicResourceMessageSource() {
        this.messagePropertiesResource = getMessagePropertiesResource();
        this.messageProperties = loadMessageProperties();
        this.executorService = Executors.newSingleThreadExecutor();
        // 监听资源文件
        onMessagePropertiesChanged();
    }

    private void onMessagePropertiesChanged() {
        if (this.messagePropertiesResource.isFile()) {
            // 获取对应文件系统中的文件
            try {
                File messagePropertiesFile = this.messagePropertiesResource.getFile();
                Path messagePropertiesPath = messagePropertiesFile.toPath();
                // 获取当前OS文件系统
                FileSystem fileSystem = FileSystems.getDefault();
                // 新建WatchService
                WatchService watchService = fileSystem.newWatchService();
                // 获取资源的文件所在的目录
                Path dirPath = messagePropertiesPath.getParent();
                // 注册WatchService dirPath并且关心修改事件
                dirPath.register(watchService, ENTRY_MODIFY);
                // 处理资源文件变化(异步)
                processMessagePropertiesChanged(watchService);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 处理资源文件变化
     *
     * @param watchService
     */
    private void processMessagePropertiesChanged(WatchService watchService) {
        executorService.submit(() -> {
            while (true) {
                // take发生阻塞
                WatchKey watchKey = watchService.poll();
                try {
                    if (watchKey.isValid()) {
                        for (WatchEvent event : watchKey.pollEvents()) {
                            Watchable watchable = watchKey.watchable();
                            // 目录路径(监听的注册目录)
                            Path dirPath = (Path) watchable;
                            // 事件所关联的对象即注册目录的字文件(或字目录)
                            // 事件发生源是相对路径
                            Path fileRelativePath = (Path) event.context();
                            if (resourceFileName.equals(fileRelativePath.getFileName().toString())) {
                                Path filePath = dirPath.resolve(fileRelativePath);
                                File file = filePath.toFile();
                                Properties properties = loadMessageProperties(new FileReader(file));
                                synchronized (messageProperties) {
                                    messageProperties.clear();
                                    messageProperties.putAll(properties);
                                }
                                System.out.println("修改的文件: " + filePath);
                            }
                        }
                    }
                } finally {
                    if (watchKey != null) {
                        // 重置watchKey
                        watchKey.reset();
                    }
                }
            }
        });
    }

    private Properties loadMessageProperties() throws IOException {
        EncodedResource encodedResource = new EncodedResource(this.messagePropertiesResource, ENCODING);
        return loadMessageProperties(encodedResource.getReader());
    }

    private Properties loadMessageProperties(Reader reader) {
        Properties properties = new Properties();
        try {
            properties.load(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return properties;
    }

    private Resource getMessagePropertiesResource() {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(resourcePath);
        return resource;
    }

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String messageFormatPattern = messageProperties.getProperty(code);
        if (StringUtils.hasText(messageFormatPattern)) {
            return new MessageFormat(messageFormatPattern, locale);
        }
        return null;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public static void main(String[] args) throws InterruptedException {
        DynamicResourceMessageSource messageSource = new DynamicResourceMessageSource();
        for (int i = 0; i < 10000; i++) {
            String message = messageSource.getMessage("name", new Object[]{}, Locale.getDefault());
            System.out.println(message);
            Thread.sleep(1000);
        }
    }
}
