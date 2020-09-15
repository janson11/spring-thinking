## Spring 面试题

### 1.什么是Spring Framework？
Spring makes it easy to create Java enterprise applications.It provides everything you need to embrace the Java language in
an enterprise environment,with support for Groovy and Kotlin as create many kinds od architectures depending on an application's needs.
(Spring使得创建Java企业应用程序变得很容易。它提供了包含Java语言所需的企业环境，同时支持Groovy和Kotlin的，可以根据应用程序的需要创建多种架构。)

### 2、Spring Framework有哪些核心模块？
spring-core:Spring的基础API模块，如资源管理、泛型处理。
spring-beans:Spring Bean相关，如依赖查找、依赖注入。
spring-aop:Spring AOP处理，如动态处理，AOP字节码提升。
spring-context:事件驱动、注解驱动、模块驱动。
spring-expression:Spring表达式语言模块。

### 3、Spring Framework的优势和不足是什么？
这个答案将贯穿整个Spring系列。

### 4、什么是IoC？
简单的说，IoC是反转控制，类似与好莱坞原则，主要有依赖查找和依赖注入实现。
JavaBeans、Servlet是IoC容器的实现。

### 5、依赖查找和依赖注入的区别？
依赖查找是主动或者手动的依赖查找方式，通常需要依赖容器或标准API实现。
而依赖注入是手动或者自动依赖绑定的方式。无需依赖特定的容器和API。

### 6、Spring作为IoC容器有什么优势？

- 典型的IoC管理，依赖查找和依赖注入。
- AOP抽象
- 事务抽象
- 事件机制
- SPI扩展
- 强大的第三方整合
- 易测试性
- 更好的面向对象

### 7、ObjectFactory与BeanFactory的区别

ObjectFactory与BeanFactory均提供依赖查找的能力。

不过ObjectFactory仅关注一个或一种类型的Bean依赖查找，并且自身不具备依赖查找的能力，能力则由BeanFactory输出。

BeanFactory则提供了单一类型、集合类型以及层次性等多种依赖查找方式。



### 8、BeanFactory.getBean操作是否线程安全？

BeanFactory.getBean方法的执行是线程安全的，操作过程中会增加互斥锁。

### 9、有多少种依赖注入的方式？

构造器注入：少依赖并且是强制依赖的情况

Setter注入：多依赖并且是非强制依赖的情况

字段注入：主要是开发比较便利

方法注入：通过@Bean，可以做一个相关的声明

接口回调注入：setApplication

### 10、你偏好构造器注入还是Setter注入？

两种依赖注入的方式均可使用，如果是必须依赖的话，那么推荐使用构造器注入，Setter注入用于可选依赖。

### 11、Spring依赖注入的来源有哪些？

Spring BeanDefinition

单例对象

Resolvable Dependency

@Value 外部化配置

### 12、注入和查找的依赖来源是否相同？

否。依赖查找的来源仅限于Spring BeanDefinition以及单例对象，而依赖注入的来源还包括Resolvable Dependency以及@Value所标注的外部化配置。

### 13、单例对象能在IoC容器启动后注册嘛？

可以的，单例对象的注册与BeanDefinition不同，BeanDefinition会被org.springframework.beans.factory.config.ConfigurableListableBeanFactory#freezeConfiguration()方法影响，从而冻结注册，单例对象则没有这个限制。

### 14、Spring内建的Bean作用域有几种?

从设计模式：分为单例（singleton）和原生或原型（prototype）这两种方式。

从Web角度：分为request（请求）、session（会话） .application（应用）和websocket这四种方式。

### 15、singleton Bean是否在一个应用是唯一的？

否。singleton bean 仅在当前Spring IOC容器(BeanFactory)中是单例对象。

### 16、“application” Bean是否被其他方案替代？

可以的，实际上，“application” Bean与“singleton” bean 没有本质区别。

### 17、BeanPostProcessor的使用场景有哪些？

BeanFactoryProcessor提供Spring Bean 初始化前和初始化后的生命周期回调，分别对应postProcessBeforeInitialization以及postProcessAfterInitialization方法，允许对关心的Bean进行扩展，甚至是替换。

加分项：其中，ApplicationContext相关的Aware回调也是基于BeanPostProcessor实现，即ApplicationContextAwareProcessor。   

### 18、BeanFactoryPostProcessor与BeanPostProcessor的区别？

BeanFactoryPostProcessor是Spring BeanFactory(实际为ConfigurableListableBeanFactory)的后置处理器，用于扩展BeanFactory，或通过BeanFactory进行依赖查找或依赖注入。

加分项：BeanFactoryPostProcessor必须有Spring ApplicationContext执行，BeanFactory无法与其直接交互。

而BeanPostProcessor则直接与BeanFactory关联，属于N对于1的关系。

### 19、BeanFactory是怎样处理Bean生命周期？

BeanFactory的默认实现DefaultListableBeanFactory，其中Bean生命周期与方法映射如下：

- BeanDefinition注册阶段 -registerBeanDefinition
- BeanDefinition合并阶段 -getMergedBeanDefinition
- Bean实例化前阶段-resovleBeforeInstantiation
- Bean实例化阶段-createBeanInstance
- Bean实例化后阶段-populateBean
- Bean属性赋值前阶段-populateBean
- Bean属性赋值阶段-populateBean
- Bean Aware接口回调阶段-initializeBean
- Bean初始化前阶段- initializeBean
- Bean初始化阶段- initializeBean
- Bean初始化后阶段- initializeBean
- Bean初始化完成阶段- initializeBean
- Bean初始化完成阶段-preInstantiateSingletons
- Bean销毁前阶段-destroyBean
- Bean销毁阶段-destroyBean

### 20、Spring内建XML Schema常见有哪些？

| 命名空间 | 所属模块       | Schema资源URL                                                |
| -------- | -------------- | ------------------------------------------------------------ |
| beans    | spring-beans   | https://www.springframework.org/schema/beans/spring-beans.xsd |
| context  | spring-context | https://www.springframework.org/schema/context/spring-context.xsd |
| aop      | spring-aop     | https://www.springframework.org/schema/aop/spring-aop.xsd    |
| tx       | spring-tx      | https://www.springframework.org/schema/tx/spring-tx.xsd      |
| util     | spring-beans   | https://www.springframework.org/schema/util/spring-util.xsd  |
| tool     | spring-beans   | https://www.springframework.org/schema/tool/spring-tool.xsd  |



### 21、Spring配置元信息具体有哪些？

- Bean配置元信息：通过媒介（如XML、Properties等），解析BeanDefinition
- IoC容器配置元信息：通过媒介（如XML、Properties等），控制IoC容器行为，比如注解驱动、AOP等。
- 外部化配置：通过资源抽象（如Properties、YAML等），控制PropertySource
- Spring Profile：通过外部化配置，提供条件分支流程。

### 22、Extensible XML authoring的缺点

- 高复杂度：开发人员需要熟悉XML Schema、spring.handlers、spring.schemas以及Spring API。
- 嵌套元素支持较弱：通常需要使用方法递归或者其嵌套解析的方式处理嵌套（子）元素。
- XML处理性能较差：Spring XML基于DOM Level 3 API实现，该API便于理解，然而新能较差。
- XML框架移植性差：很难适配高性能和便利性的XML框架，如JAXB。

### 23、Spring配置资源中有哪些常见类型？

- XML资源
- Properties资源、
- YAML资源:

### 24、请列举不同类型Spring配置资源？

XML资源：

- 普通Bean Definition XML配置资源 —*.xml
- Spring Schema资源 —*.xsd

Properties资源：
 -  普通Properties格式资源—*.properties
 -  Spring Handler实现类映射文件—META-INF/spring.handlers
 -  Spring Schema资源映射文件—META-INF/spring.schemas


YAML资源:
- 普通YAML配置资源—*.yaml或*.yml 

### 25、Java标准资源管理扩展的步骤？

- 简易实现：

  实现URLStreamHandler并放置到在sun.net.www.protocol.${protocol}.Handler包下

- 自定义实现

  实现URLStreamHandler

  添加-Djava.protocol.handler.pkgs启动参数，指向URLStreamHandler实现类的包下。

- 高级实现

  实现URLStreamHandlerFactory并传递到URL之中。

### 26、Spring国际化接口有哪些？

核心接口：MessageSource

层次性接口：org.springframework.context.HierarchicalMessageSource



### 27、Spring有哪些MessageSource内建实现？

org.springframework.context.support.ResourceBundleMessageSource

org.springframework.context.support.ReloadableResourceBundleMessageSource

org.springframework.context.support.StaticMessageSource

org.springframework.context.support.DelegatingMessageSource



### 28、如何实现配置自动更新MessageSource？

主要技术：

Java NIO 2：java.nio.file.WatchService

Java Concurrency：java.util.concurrent.ExecutorService

Spring：org.springframework.context.support.AbstractMessageSource



### 29、Spring校验接口是哪个？

org.springframework.validation.Validator



### 30、Spring有哪些校验核心组件？

- 校验器：org.springframework.validation.Validator
- 错误收集器：org.springframework.validation.Errors
- Java Bean错误描述：org.springframework.validation.ObjectError
- Java Bean属性错误描述：org.springframework.validation.FieldError
- Bean Validation适配：
- org.springframework.validation.beanvalidation.LocalValidatorFactoryBean



### 31、请通过示例演示Spring Bean的校验？



### 32、Spring数据绑定API是什么？

org.springframework.validation.DataBinder

### 33、BeanWrapper与JavaBeans之间关系是？

Spring 底层JavaBeans基础设施的中心化接口。

BeanWrapper是基于JavaBeans的二次封装，有且仅有一个实现类BeanWrapperImpl

### 34、DataBinder是怎么完成属性类型转换的？



### 35、Spring类型转换实现有哪些？

1. 基于JavaBeans PropertyEditor接口实现
2. Spring 3.0+ 通用类型转换实现

### 36、Spring类型转换器接口有哪些？

- 类型转换接口：org.springframework.core.convert.converter.Converter
- 通用类型转换接口：org.springframework.core.convert.converter.GenericConverter
- 类型条件接口：org.springframework.core.convert.converter.ConditionalConverter
- 综合类型转换接口：org.springframework.core.convert.converter.ConditionalGenericConverter

### 37、TypeDescriptor是如何处理泛型？

下章揭晓

### 38、Java泛型擦写发生在编译时还是运行时？

运行时

### 39、请介绍Java 5 Type类型的派生类或接口？

- java.lang.Class
- java.lang.reflect.GenericArrayType
-  java.lang.reflect.TypeVariable
-  java.lang.reflect.ParameterizedType
-  java.lang.reflect.WildcardType



### 40、请说明ResolvableType的设计优势

- 简化Java 5 Type API开发，屏蔽复杂API的运用，如ParameterizedType
- 不变性设计(Immutability)
- Fluent API 设计(Builder模式)、链式（流式）编程



### 41、Spring事件核心接口/组件？

Spring事件：org.springframework.context.ApplicationEvent

Spring事件监听器：org.springframework.context.ApplicationListener

Spring事件发布器：org.springframework.context.ApplicationEventPublisher

Spring事件广播器：org.springframework.context.event.ApplicationEventMulticaster



### 42、Spring同步和异步事件处理的使用场景？

Spring同步事件：绝大多数Spring使用场景，如ContextRefreshedEvent

Spring异步事件：主要@EventListener与@Asyc配合，实现异步处理，不阻塞主线程，比如长时间的数据计算任务等。不要轻易调整SimpleApplicationEventMulticaster中关联的taskExecutor对象，除非使用者非常了解Spring事件机制，否则容易出现异常行为。



### 43、@EventListener的工作原理

源码导读：org.springframework.context.event.EventListenerMethodProcessor



### 44、Spring模式注解有哪些？

- @org.springframework.stereotype.Component
- org.springframework.stereotype.Repository
- org.springframework.stereotype.Service
- org.springframework.stereotype.Controller
- org.springframework.context.annotation.Configuration

### 45、@PropertySource的工作原理？







