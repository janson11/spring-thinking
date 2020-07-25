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

### Spring IoC容器
BeanFactory和ApplicationContext谁才是Spring IoC容器?
BeanFactory是一个底层的IoC容器，提供一些配置框架并且提供一些基本特性。
ApplicationContext是在BeanFactory基础上增加了一些它的特性，提供更多企业级特性的功能。ApplicationContext是对BeanFactory的一个超集。
BeanFactory含有的所有能力，ApplicationContext全都有并且提供更多的特性（aop的支持，国际化，事件发布等）。

### Spring应用上下文
ApplicationContext除了IoC容器角色，还有提供:

- 面向切面（AOP）
- 配置元信息（Configuration Metadata）
- 资源管理（Resources）
- 事件（Events）
- 国际化（i18n）
- 注解（Annotations）
- Environment抽奖(Environment Abstraction)






### Spring IoC容器生命周期

- 启动
- 运行
- 停止



### Spring Bean基础

- 定义Spring Bean：

  什么是BeanDefinition？

  ```
  BeanDefinition是Spring Framework中定义Bean的配置元信息接口，包含：
  Bean的类名
  Bean的行为配置元素，如作用域、自动绑定的模式、生命周期的回调等
  其他Bean的引用，又可称作合作者(Collaborators)或者依赖（Dependencies）
  配置设置，比如Bean属性（Properties）
  ```

- BeanDefinition元信息

  BeanDefinition构建

  ​	通过BeanDefinitionBuilder

  ​    通过AbstractBeanDefinition以及派生类

- 命名Spring Bean

   Bean的名称：

   每个Bean拥有一个或多个标识符，这些标识符在Bean所在的容器必须是唯一的。通常，一个Bean仅有一个标识符，如果需要额外的，可考虑使用别名来扩充。（通过半角逗号，或者分号；）

  Bean名称生成器BeanNameGenerator

  默认通用的DefaultBeanNameGenerator实现

  基于注解扫描的AnnotationBeanNameGenerator实现。 

- Spring Bean的别名

  Bean别名（Alias）的价值

  复用现有的BeanDefinition

  更具有场景化 的命名方法

- 实例化Spring Bean

- 初始化Spring Bean

- 延迟初始化Spring Bean

- 销毁Spring Bean

- 垃圾回收 Spring Bean

- 面试题精选



### 注册Spring Bean

- BeanDefinition注册

  -  XML配置元信息：<bean name="" />

  - Java注解配置元信息：@Bean @Component @import

  - Java API配置元信息：

    命名方式：org.springframework.beans.factory.support.BeanDefinitionRegistry#registerBeanDefinition(String beanName, BeanDefinition beanDefinition)

    非命名方式：	org.springframework.beans.factory.support.BeanDefinitionReaderUtils#registerWithGeneratedName(AbstractBeanDefinition definition, BeanDefinitionRegistry registry)
    
    配置类方式：
    
    org.springframework.context.annotation.AnnotatedBeanDefinitionReader#register(Class<?>... componentClasses)



### 单一类型依赖查找

- 根据Bean名称查找

  getBean(String)

  Spring 2.5覆盖默认参数：getBean(String,Object...)
  
- 根据Bean类型查找
  Bean实时查找
  Spring 3.0 getBean(Class)
  Spring 4.1 覆盖默认参数：getBean(Class,Object...)
  Spring 5.1 Bean延迟查找
  getBeanProvider(Class)
  getBeanProvider(ResolvableType)
  
- 根据Bean名称+类型查找
   getBean(String,Class)







### 集合类型依赖查找接口-ListableBeanFactory

- 根据Bean名称查找

  获取同类型Bean名称列表

  getBeanNamesForType(Class<?> type);
  Spring 4.2 覆盖默认参数：getBeanNamesForType(ResolvableType type)
获取同类型Bean实例列表
getBeansOfType(Class<T> type)以及重载方法

- 通过注解类型查找
  Spring 3.0获取标注类型Bean名称列表
  getBeanNamesForAnnotation(Class<? extends Annotation> annotationType)
  
  Spring 3.0获取标注类型Bean实例列表
  getBeansWithAnnotation(Class<? extends Annotation> annotationType) 

  Spring 3.0获取指定名称+标注类型实例
  findAnnotationOnBean(String beanName, Class<A> annotationType)

### 层次性依赖查找接口-HierarchicalBeanFactory

- 双亲BeanFactory：getParentBeanFactory()

- 层次性查找

  - 根据Bean名称查找

    基于containsLocalBean方法实现

  - 根据Bean类型查找实例列表

    - 单一类型：BeanFactoryUtils#beanOfType
    - 集合类型：BeanFactoryUtils#beansOfTypeIncludingAncestors

  - 根据Java注解查找名称列表
    BeanFactoryUtils#beanNamesForAnnotationIncludingAncestors

    


### 延迟依赖查找

- Bean延迟依赖查找接口

  org.springframework.beans.factory.ObjectFactory

  org.springframework.beans.factory.ObjectProvider

  Spring 5 对Java8特性扩展

  函数式接口

  ```
  getIfAvailable(Supplier<T> defaultSupplier) 
  ifAvailable(Consumer<T> dependencyConsumer)
  ```
  Stream扩展-stream()

### 安全依赖查找

| 依赖查找类型 | 代表实现                           | 是否安全 |
| ------------ | ---------------------------------- | -------- |
| 单一类型查找 | BeanFactory#getBean                | 否       |
|              | ObjectFactory#getObject            | 否       |
|              | ObjectProvider#getIfAvailable()    | 是       |
| 集合类型查找 | ListableBeanFactory#getBeansOfType | 是       |
|              | ObjectProvider#stream              | 是       |

注意:层次性依赖查找的安全性取决于其扩展的单一或集合类型的BeanFactory接口。



### 内建可查找的依赖

- AbstractApplicationContext内建可查找的依赖

| Bean名称                    | Bean实例                        | 使用场景               |
| --------------------------- | ------------------------------- | ---------------------- |
| environment                 | Environment对象                 | 外部化配置以及Profiles |
| systemProperties            | java.util.Properties对象        | Java系统属性           |
| systemEnvironment           | java.util.Map对象               | 操作系统环境变量       |
| messageSource               | MessageSource对象               | 国际化方案·            |
| lifecycleProcessor          | LifecycleProcessor对象          | Lifecycle Bean处理器   |
| applicationEventMulticaster | ApplicationEventMulticaster对象 | Spring事件广播器       |

- 注解驱动Spring应用上下文内建可查找的依赖（部分）

| Bean名称                                                     | Bean实例                                   | 使用场景                                            |
| ------------------------------------------------------------ | ------------------------------------------ | --------------------------------------------------- |
| org.springframework.context.annotation.<br />ConfigurationClassPostProcessor | ConfigurationClassPostProcessor对象        | 处理Spring配置类                                    |
| org.springframework.beans.factory.annotation.<br />AutowiredAnnotationBeanPostProcessor | AutowiredAnnotationBeanPostProcessor对象   | 处理@Autowired以及@Value注解                        |
| org.springframework.context.annotation.<br />CommonAnnotationBeanPostProcessor | CommonAnnotationBeanPostProcessor对象      | （条件激活）处理JSR-250注解，如@PostConstruct等     |
| org.springframework.context.event.<br />EventListenerMethodProcessor | EventListenerMethodProcessor对象           | 处理标注@EventListener注解的Spring事件监听方法      |
| org.springframework.context.event.<br />DefaultEventListenerFactory | DefaultEventListenerFactory对象            | @EventListener事件监听方法适配为ApplicationListener |
| org.springframework.orm.jpa.support.<br />PersistenceAnnotationBeanPostProcessor | PersistenceAnnotationBeanPostProcessor对象 | (条件激活)处理JPA注解场景                           |

  ### 依赖查找中的经典异常

- BeansException子类型

| 异常类型                        | 触发条件（举例）                        | 场景举例                                       |
| ------------------------------- | --------------------------------------- | ---------------------------------------------- |
| NoSuchBeanDefinitionException   | 当查找Bean不存在于IOC容器时             | BeanFactory#getBean<br />ObjectFactory#getBean |
| NoUniqueBeanDefinitionException | 类型依赖查找时，IOC容器存在多个Bean实例 | BeanFactory#getBean(Class)                     |
| BeanInstantiationException      | 当Bean所对应的类型非具体类时            | BeanFactory#getBean                            |
| BeanCreationException           | 当Bean初始化过程中                      | Bean初始化方法执行异常时                       |
| BeanDefinitionStoreException    | 当BeanDefinition配置元信息非法时        | XML配置资源无法打开时                          |

