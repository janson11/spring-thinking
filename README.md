# spring-thinking
spring核心思想

## spring编程模型

### 面向对象编程
契约接口：Aware、BeanPostProcessor...
设计模式：观察者模式、组合模式、模板模式
对象继承：Abstract* 类

### 面向切面编程
动态代理：JdkDynamicAopProxy
字节码提升：ASM、CGLib、AspectJ

### 面向元编程
注解：模式注解(@Component、@Service、@Repository...)
配置：Environment抽象、PropertySources、BeanDefinition...
泛型：GenericTypeResolver、ResolvableType...

### 函数驱动
函数接口：ApplicationEventPublisher
Reactive：Spring WebFlux

### 模块驱动
Maven Artifacts
OSGI Bundles
Java 9 Automatic Modules
Spring @Enable*

### 面试题：
1.什么是Spring Framework？
Spring makes it easy to create Java enterprise applications.It provides everything you need to embrace the Java language in
an enterprise environment,with support for Groovy and Kotlin as create many kinds od architectures depending on an application's needs.
(Spring使得创建Java企业应用程序变得很容易。它提供了包含Java语言所需的企业环境，同时支持Groovy和Kotlin的，可以根据应用程序的需要创建多种架构。)

2、Spring Framework有哪些核心模块？
spring-core:Spring的基础API模块，如资源管理、泛型处理。
spring-beans:Spring Bean相关，如依赖查找、依赖注入。
spring-aop:Spring AOP处理，如动态处理，AOP字节码提升。
spring-context:事件驱动、注解驱动、模块驱动。
spring-expression:Spring表达式语言模块。

3、Spring Framework的优势和不足是什么？
这个答案将贯穿整个Spring系列。

4、什么是IoC？
简单的说，IoC是反转控制，类似与好莱坞原则，主要有依赖查找和依赖注入实现。
JavaBeans、Servlet是IoC容器的实现。

5、依赖查找和依赖注入的区别？
依赖查找是主动或者手动的依赖查找方式，通常需要依赖容器或标准API实现。
而依赖注入是手动或者自动依赖绑定的方式。无需依赖特定的容器和API。

6、Spring作为IoC容器有什么优势？

- 典型的IoC管理，依赖查找和依赖注入。
- AOP抽象
- 事务抽象
- 事件机制
- SPI扩展
- 强大的第三方整合
- 易测试性
- 更好的面向对象

### 传统Ioc容器的实现

- Java Beans作为IoC容器
特性：
-  依赖查找
-  生命周期管理
-  配置元信息
-  事件
-  自定义
-  资源管理
-  持久化

### Spring IoC依赖查找

- 根据Bean的名称查找
    实时查找
    延迟查找
- 根据Bean类型查找
 单个Bean对象
 集合Bean对象
 - 根据Bean名称+类型查找
 - 根据Java注解查找
 单个Bean对象
 集合Bean对象


 ### Spring IoC依赖注入
- 根据Bean的名称注入

-  根据Bean类型注入
  单个Bean对象
  集合Bean对象
  
- 注入容器内建Bean对象

- 注入非Bean对象

-  注入类型
  实时注入
  延迟注入

### Spring IoC依赖来源

 - 自定义Bean
 - 容器内建的Bean对象
 - 容器内建依赖



### Spring IoC配置元信息

- Bean定义配置
  - 基于XML文件
  - 基于Properties文件
  - 基于Java注解
  - 基于Java API
- IoC容器配置
  -  基于XML文件
  - 基于Java注解
  - 基于Java API
- 外部化属性配置
  -  基于Java注解