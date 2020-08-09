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