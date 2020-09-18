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



### Spring IOC依赖注入

1. 依赖注入的模式和类型

   - 手动模式：配置或者编程的方式,提前安排注入规则。XML资源配置元信息、Java注解配置元信息和API配置元信息

   - 自动模式：实现方提供依赖自动关联的方式，按照内建的注入原则。Autowiring（自动绑定）

     | 依赖注入类型 | 配置元数据举例                                 |
     | ------------ | ---------------------------------------------- |
     | Setter方法   | <property name="user" ref="userBean" />        |
     | 构造器       | <constructor-arg name="user" ref="userBean" /> |
     | 字段         | @Autowired User user;                          |
     | 方法         | @Autowired public void user(User user) {...}   |
     | 接口回调     | class myBean implements BeanFactoryAware{...}  |

2. 自动绑定(Autowiring)

3. 自动绑定(Autowiring)模式

   | 模式        | 说明                                                         |
   | ----------- | ------------------------------------------------------------ |
   | no          | 默认值，未激活Autowiring,需要手动指定依赖注入对象            |
   | byName      | 根据被注入属性的名称作为Bean名称进行依赖查找，并将对象设置到该属性。 |
   | byType      | 根据被注入属性的类型作为依赖类型进行查找，并将对象设置到该属性 |
   | constructor | 特性byType类型，用于构造参数                                 |

   备注：参考枚举类：org.springframework.beans.factory.annotation.Autowire

4. 自动绑定(Autowiring)限制和不足

5. Setter方法依赖注入

   实现方法：

   - 手动模式:XML资源配置元信息、Java注解配置元信息和API配置元信息。
   - 自动模式：byName和byType

6. 构造器依赖注入

   实现方法：

   - 手动模式:XML资源配置元信息、Java注解配置元信息和API配置元信息。
   - 自动模式：constructor

7. 字段注入

   手动模式：java注解配置元信息

   - @Autowired
   - @Resource
   - @Inject(可选)

8. 方法注入

   手动模式：java注解配置元信息

   - @Autowired
   - @Resource
   - @Inject(可选)
   - @Bean

9. 回调注入

   Aware系列接口回调

   - 自动模式

     | 内建接口                       | 说明                                              |
     | ------------------------------ | ------------------------------------------------- |
     | BeanFactoryAware               | 获取IoC容器 -BeanFactory                          |
     | ApplicationContextAware        | 获取Spring应用上下文-ApplicationContext对象       |
     | EnvironmentAware               | 获取Environment对象                               |
     | ResourceLoaderAware            | 获取资源加载器对象-ResourceLoader                 |
     | BeanClassLoaderAware           | 获取加载当前Bean Class的ClassLoader               |
     | BeanNameAware                  | 获取当前Bean的名称                                |
     | MessageSourceAware             | 获取MessageSource对象，用于Spring国际化           |
     | ApplicationEventPublisherAware | 获取ApplicationEventPublisher对象，用于Spring对象 |
     | EmbeddedValueResolverAware     | 获取StringValueResolver对象，用于占位符处理       |

     

10. 依赖注入类型选择

    注入选型

    - 低依赖性：构造器注入
    - 多依赖：Setter方法注入
    - 便利性：字段注入
    - 声明类：方法注入

11. 基础类型注入

    基础类型:

    原生类型(Primitive)：boolean、byte、char、short、int、float、long、double

    标量类型(Scalar):Number、Character、Boolean、Enum、Locale、Charset、Currentcy、Properties、UUID

    常规类型(General):Object、String、TimeZone、Calendar、Optional等

    Spring类型：Resource、InputSource、Formatter等。

12. 集合类型注入

    数组类型（Array）：原生类型、标量类型、常规类型、Spring类型。

    集合类型（Collection）：List、Set（SortedSet、NavigableSet、EnumSet） Map：Properties

13. 限定注入

    使用注解@Qualifier限定：通过Bean名称限定，通过分组限定

    基于注解@Qualifier扩展限定，自定义注解：如Spring Cloud @LoadBalanced

14. 延迟依赖注入

    使用API ObjectFactory延迟注入：单一类型、集合类型

    使用API ObjectProvider延迟注入（推荐）：单一类型、集合类型

15. 依赖处理过程

    基础知识：

    入口：DefaultListableBeanFactory#resolveDependency

    依赖描述：DependencyDescriptor

    自动绑定候选对象处理器：AutowireCandidateResolver

16. @Autowired注入原理

    @Autowired注入过程：元信息解析、依赖查找、依赖注入（字段、方法）

17. JSR-330 @Inject注入原理

    如果JSR-330存在于ClassPath中，复用AutowiredAnnotationBeanPostProcessor实现。

18. Java通用注解注入原理
     CommonAnnotationBeanPostProcessor
    
    注入注解：
    
    - javax.xml.ws.WebServiceRef
    
    - javax.ejb.EJB
    
    - javax.annotation.Resource
    
    生命周期注解
    - javax.annotation.PostConstruct
    - javax.annotation.PreDestroy
    


19. 自定义依赖注入注解

    基于AutowiredAnnotationBeanPostProcessor实现

    自定义实现：

    生命周期处理：

      - InstantiationAwareBeanPostProcessorAdapter

      - MergedBeanDefinitionPostProcessor

    元数据
      - InjectionMetadata
      - InjectedElement

      


### Spring IOC依赖来源

1. 依赖查找的来源

  - 查找来源

| 来源                  | 配置元数据                                   |
| --------------------- | -------------------------------------------- |
| Spring BeanDefinition | <bean id="user" class="org.janson...User" /> |
|                       | @Bean public User user(){...}                |
|                       | BeanDefinitionBuilder                        |
| 单例对象              | API实现                                      |

  - Spring内建BeanDefinition

| Bean名称                                                     | Bean实例                                   | 使用场景                                            |
| ------------------------------------------------------------ | ------------------------------------------ | --------------------------------------------------- |
| org.springframework.context.annotation.<br />ConfigurationClassPostProcessor | ConfigurationClassPostProcessor对象        | 处理Spring配置类                                    |
| org.springframework.beans.factory.annotation.<br />AutowiredAnnotationBeanPostProcessor | AutowiredAnnotationBeanPostProcessor对象   | 处理@Autowired以及@Value注解                        |
| org.springframework.context.annotation.<br />CommonAnnotationBeanPostProcessor | CommonAnnotationBeanPostProcessor对象      | （条件激活）处理JSR-250注解，如@PostConstruct等     |
| org.springframework.context.event.<br />EventListenerMethodProcessor | EventListenerMethodProcessor对象           | 处理标注@EventListener注解的Spring事件监听方法      |
| org.springframework.context.event.<br />DefaultEventListenerFactory | DefaultEventListenerFactory对象            | @EventListener事件监听方法适配为ApplicationListener |
| org.springframework.orm.jpa.support.<br />PersistenceAnnotationBeanPostProcessor | PersistenceAnnotationBeanPostProcessor对象 | (条件激活)处理JPA注解场景                           |

  - Spring内建单例对象

| Bean名称                    | Bean实例                        | 使用场景               |
| --------------------------- | ------------------------------- | ---------------------- |
| environment                 | Environment对象                 | 外部化配置以及Profiles |
| systemProperties            | java.util.Properties对象        | Java系统属性           |
| systemEnvironment           | java.util.Map对象               | 操作系统环境变量       |
| messageSource               | MessageSource对象               | 国际化方案·            |
| lifecycleProcessor          | LifecycleProcessor对象          | Lifecycle Bean处理器   |
| applicationEventMulticaster | ApplicationEventMulticaster对象 | Spring事件广播器       |

2. 依赖注入的来源

   | 来源                  | 配置元数据                                   |
   | --------------------- | -------------------------------------------- |
   | Spring BeanDefinition | <bean id="user" class="org.janson...User" /> |
   |                       | @Bean public User user(){...}                |
   |                       | BeanDefinitionBuilder                        |
   | 单例对象              | API实现                                      |
   | 非Spring容器管理对象  |                                              |

   

3. Spring容器管理和游离对象

   依赖对象

   | 来源                  | Spring Bean对象 | 生命周期管理 | 配置元信息 | 使用场景           |
   | --------------------- | --------------- | ------------ | ---------- | ------------------ |
   | Spring BeanDefinition | 是              | 是           | 有         | 依赖查找、依赖注入 |
   | 单体对象              | 是              | 否           | 无         | 依赖查找、依赖注入 |
   | ResolvableDependency  | 否              | 否           | 无         | 依赖注入           |

   

4. Spring BeanDefinition作为依赖来源

   要素：

   - 元数据：BeanDefinition
   - 注册：org.springframework.beans.factory.support.BeanDefinitionRegistry#registerBeanDefinition
   - 类型：延迟和非延迟
   - 顺序：Bean生命周期顺序按照注册顺序

5. 单例对象作为依赖来源

   要素：

   - 来源：外部普通Java对象（不一定是POJO）
   - 注册：org.springframework.beans.factory.config.SingletonBeanRegistry#registerSingleton

   限制：

   - 无生命周期管理
   - 无法实现延迟初始化Bean

6. 非Spring容器管理对象作为依赖来源

   要素：

   - 注册：org.springframework.beans.factory.config.ConfigurableListableBeanFactory#registerResolvableDependency

   限制：

   - 无生命周期管理
   - 无法实现延迟初始化Bean
   - 无法通依赖查找

7. 外部化配置作为依赖来源

   要素：

   - 类型：非常规Spring对象依赖来源

   限制：

   - 无生命周期管理
   - 无法实现延迟初始化Bean
   - 无法通依赖查找

### Spring Bean作用域

1. Spring Bean作用域

   | 来源        | 说明                                                   |
   | ----------- | ------------------------------------------------------ |
   | singleton   | 默认Spring Bean作用域，一个BeanFactory有且仅有一个实例 |
   | prototype   | 原型作用域，每次依赖查找和依赖注入生成新的Bean对象。   |
   | request     | 将Spring Bean存储在ServletRequest上下文中              |
   | session     | 将Spring Bean存储在HTTPSession                         |
   | application | 将Spring Bean存储在ServletContext中                    |

   

2. "singleton" Bean作用域

   Only one instance is ever created

3. "prototype" Bean作用域

   A brand new bean instance is created

   Spring 容器没有办法管理prototype Bean的完整生命周期，也没有办法记录实例的存在，销毁回调方法将不会执行，可以利用BeanPostProcessor进行清扫工作。

4. "request" Bean作用域

   配置：

   XML -<bean class="..." scope="request" />

   Java注解 - @RequestScope或@Scope(WebApplicationContext.SCOPE_REQUEST)

   实现：

   API -RequestScope

5. "session" Bean作用域 

   配置：

   XML -<bean class="..." scope="session" />

   Java注解 - @SessionScope或@Scope(WebApplicationContext.SCOPE_SESSION)

   实现：

   API -SessionScope

6. "application" Bean作用域

   配置：

   XML -<bean class="..." scope="application" />

   Java注解 - @ApplicationScope或@Scope(WebApplicationContext.SCOPE_APPLICATION)

   实现：

   API -ApplicationScope

   ![image-20200808214704446](C:\Users\janso\AppData\Roaming\Typora\typora-user-images\image-20200808214704446.png)

7. 自定义Bean作用域

   实现Scope：org.springframework.beans.factory.config.Scope

   注册Scope：

   - API：org.springframework.beans.factory.config.ConfigurableBeanFactory#registerScope

   - 配置：

     <bean class="org.springframework.beans.factory.config.CustomScopeConfigurer">

     ​    <property names = "scopes">

     			<map>
     			    <entry keys="..."></entry>
     			</map>
     ​    </property>

     </bean>

8. 课外资料

   Spring Cloud RefreshScope是如何控制Bean的动态刷新？

   自定义Scope：RefreshScope



## Spring Bean生命周期

1. Spring Bean元信息配置阶段

   BeanDefinition配置：

   面向资源：

   - XML配置
   - Properties资源配置

   面向注解：

   面向API：

2. Spring Bean元信息解析阶段

   面向资源BeanDefinition解析

   - BeanDefinitionReader
   - XML解析器-BeanDefinitionParser

   面向注解BeanDefinition解析

    - AnnotatedBeanDefinitionReader

3.  BeanDefinition注册接口：BeanDefinitionRegistry

4. Spring BeanDefinition合并阶段

   BeanDefinition合并：

   父子BeanDefinition合并：

   当前BeanFactory查找

   层次性BeanFactory查找

5. Spring Bean Class加载阶段
   - ClassLoader类加载
   
   - Java Security安全控制
   
   - ConfigurableBeanFactory临时类加载ClassLoader

6. Spring Bean实例化前阶段

   非主流生命周期—Bean实例化前阶段

   org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation

7. Spring Bean 实例化阶段

   实例化方式

   - 传统实例化方式

     实例化策略-InstantiationStrategy

   - 构造器依赖注入

8. Spring Bean 实例化后阶段

   Bean属性赋值(Populate)判断

   org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation

9. Spring Bean属性赋值前阶段

   Bean属性值元信息

   - PropertyValues
     

   Bean属性赋值前回调
       Spring1.2-5.0：org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessPropertyValues

   ​    Spring5.1：org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessProperties 

10. Spring Bean Aware接口回调阶段

    Spring Aware接口

    - BeanNameAware
    - BeanClassLoaderAware
    - BeanFactoryAware
    - EnvirinmentAware
    - EmbeddedValueResolverAware
    - ResourceLoaderAware
    - ApplicationEventPublisherAware
    - MessageSourceAware
    - ApplicationContextAware

11. Spring Bean初始化前阶段

    - Bean实例化
    - Bean属性赋值
    - Bean Aware接口回调
    - org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization

12. Spring Bean初始化阶段

    Bean初始化(Initialization)

    - @PostConstruct标注方法
    - 实现InitializingBean接口的afterPropertiesSet()方法
    - 自定义初始化方法

13. Spring Bean初始化后阶段

    方法回调

    org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization

14. Spring Bean初始化完成阶段

    方法回调

    Spring 4.1+：org.springframework.beans.factory.SmartInitializingSingleton#afterSingletonsInstantiated

15. Spring Bean销毁前阶段

    方法回调

    org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor#postProcessBeforeDestruction

16. Spring Bean销毁阶段

    Bean销毁(Destroy)

    -    @PreDestory标注方法

    - 实现DisposableBean接口的destroy()方法

    - 自定义销毁方法

17. Spring Bean垃圾收集

    Bean垃圾回收(GC)

    - 关闭Spring容器(应用上下文)
    - 执行GC
    - Spring Bean 覆盖的finalize()方法被回调



## Spring配置元信息

1. Spring配置元信息

   - Spring Bean配置元信息—BeanDefinition
   - Spring Bean属性元信息—PropertyValues
   - Spring 容器配置元信息
   - Spring 外部化配置元信息—PropertySource
   - Spring Profile 元信息—@Profile

2. Spring Bean配置元信息

   - GenericBeanDefinition：通用型BeanDefinition
   - RootBeanDefinition：无Parent的BeanDefinition或者合并后BeanDefinition
   - AnnotatedBeanDefinition：注解标注的BeanDefinition

3. Spring Bean属性元信息

   Bean属性元信息：

   - PropertyValues

   - 可修改实现-MutablePropertyValues
   - 元素成员-PropertyValue

   Bean属性上下文存储：AttributeAccessor

   Bean元信息元素：BeanMetadataElement

4. Spring容器配置元信息

   | bean元素属性                | 默认值     | 使用场景                                                     |
   | --------------------------- | ---------- | ------------------------------------------------------------ |
   | profile                     | null(留空) | Spring Profiles配置值                                        |
   | default-lazy-init           | default    | 当outter beans "default-lazy-init"属性存在时，继承该值，否则为："false" |
   | default-merge               | defalut    | 当outter beans "default-merge"属性存在时，继承该值，否则为："false" |
   | default-autowire            | defalut    | 当outter beans "default-autowire"属性存在时，继承该值，否则为："no" |
   | default-autowire-candidates | null(留空) | 默认Spring Beans 名称pattern                                 |
   | default-init-method         | null(留空) | 默认Spring Beans 自定义初始化方法                            |
   | default-destroy-method      | null(留空) | 默认Spring Beans 自定义销毁方法                              |

5. 基于XML文件装载Spring Bean配置元信息

   | XML元素          | 使用场景                                  |
   | ---------------- | ----------------------------------------- |
   | <beans:beans />  | 单XML资源下的多个Spring Beans配置         |
   | <beans:bean />   | 单个Spring Bean定义(BeanDefintion)配置    |
   | <beans:alias />  | 为Spring Bean定义(BeanDefinition)映射别名 |
   | <beans:import /> | 加载外部Spring XML配置资源                |

   

6. 基于Properties文件装载Spring Bean配置元信息

   | Properties属性名 | 使用场景                      |
   | ---------------- | ----------------------------- |
   | (class)          | Bean类全称限定名              |
   | (abstract)       | 是否为抽象的BeanDefinition    |
   | (parent)         | 指定parent BeanDefinition名称 |
   | (lazy-init)      | 是否为延迟初始化              |

   底层实现-PropertiesBeanDefinitionReader

7. 基于Java注解装载Spring Bean配置元信息

   Spring模式注解

   | Spring注解     | 场景说明          | 起始版本 |
   | -------------- | ----------------- | -------- |
   | @Repository    | 数据仓储模式注解  | 2.0      |
   | @Component     | 通用组件模式注解  | 2.5      |
   | @Service       | 服务模式注解      | 2.5      |
   | @Controller    | Web控制器模式注解 | 2.5      |
   | @Configuration | 配置类模式注解    | 3.0      |

   Spring Bean依赖注入注解

   | Spring注解 | 场景说明                           | 起始版本 |
   | ---------- | ---------------------------------- | -------- |
   | @Autowired | Bean依赖注入，支持多种依赖查找方式 | 2.5      |
   | @Qualifer  | 细粒度的@Autowired依赖查找         | 2.5      |
   | Java注解   | 场景说明                           | 起始版本 |
   | @Resource  | 类似于@Autowired                   | 2.5      |
   | @Inject    | 类似于@Autowired                   | 2.5      |

   Spring Bean条件装配注解

   | Spring注解   | 场景说明       | 起始版本 |
   | ------------ | -------------- | -------- |
   | @Profile     | 配置化条件装配 | 3.1      |
   | @Conditional | 编程条件装配   | 4.0      |

   Spring Bean生命周期回调注解

   | Spring注解     | 场景说明                                                | 起始版本 |
   | -------------- | ------------------------------------------------------- | -------- |
   | @PostConstruct | 替换XML元素<bean init-method="...">或InitializingBean   | 2.5      |
   | @PreDestroy    | 替换XML元素<bean destory-method="..."或者DisposableBean | 2.5      |

   

8. Spring Bean配置元信息底层实现

   Spring BeanDefinition解析与注册

   | 实现场景       | 实现类                         | 起始版本 |
   | -------------- | ------------------------------ | -------- |
   | XML资源        | XmlBeanDefinitionReader        | 1.0      |
   | Properties资源 | PropertiesBeanDefinitionReader | 1.0      |
   | Java注解       | AnnotatedBeanDefinitionReader  | 3.0      |

>   Spring XML资源BeanDefinition解析与注册
>

   核心API —XmlBeanDefinitionReader

   资源-Resource

   底层-BeanDefinitionDocumentReader

   - XML解析 -Java DOM Level 3 API 
   - BeanDefinition解析-BeanDefinitionParserDelegate
   - BeanDefinition注册-BeanDefinitionRegistry

>   Spring Properties资源BeanDefinition解析与注册

  核心API：PropertiesBeanDefinitionReader

资源

- 字节流：Resource
- 字符流：EncodedResource

底层

- 存储-javas.util.Properties
- BeanDefinition解析-API内部实现
- BeanDefinition注册-BeanDefinition

> Spring Java 注册BeanDefinition解析与注册

核心API-AnnotatedBeanDefinitionReader
 资源

  类对象：java.lang.Class

底层

条件评估-ConditionEvaluator

Bean范围解析-ScopeMetadataResolver

BeanDefinition解析-内部API实现

BeanDefinition处理-AnnotationConfigUtils.processCommonDefinitionAnnotations

BeanDefinition注册-BeanDefinitionRegistry

9. 基于XML文件装载Spring IoC容器配置元信息

   | XML元素          | 使用场景                                  |
   | ---------------- | ----------------------------------------- |
   | <beans:beans />  | 单XML资源下的多个Spring Beans配置         |
   | <beans:bean/ >   | 单个Spring Bean定义(BeanDefinition)配置   |
   | <beans:alias />  | 为Spring Bean定义(BeanDefinition)映射别名 |
   | <beans:import /> | 加载外部Spring XML配置资源                |

   底层实现—XMLBeanDefinitionReader

10. 基于Java注解装载Sprig IoC容器配置元信息

    Spring IoC容器装配注解

    | Spring注解      | 场景说明                                | 起始版本 |
    | --------------- | --------------------------------------- | -------- |
    | @ImportResource | 替换XML元素<import>                     | 3.0      |
    | @Import         | 导入Configuration Class                 | 3.0      |
    | @ComponentScan  | 扫描指定package下标注Spring模式注解的类 | 3.1      |

    Spring IoC配置属性注解

    | Spring注解       | 场景说明                       | 起始版本 |
    | ---------------- | ------------------------------ | -------- |
    | @PropertySource  | 配置属性抽象PropertySource注解 | 3.1      |
    | @PropertySources | @PropertySource集合注解        | 4.0      |

    

11. 基于Extensible XML authoring扩展Spring XML元素

    Spring XML扩展

    - 编写XML Schema文件：定义XML结构
    - 自定义NamespaceHandler实现：命名空间绑定
    - 自定义BeanDefinitionParser实现：XML元素与BeanDefinition解析
    - 注册XML扩展：命名空间与XML Schema映射

12. Extensible XML authoring扩展原理

    触发时机：

    org.springframework.context.support.AbstractApplicationContext#obtainFreshBeanFactory

    ​	org.springframework.context.support.AbstractRefreshableApplicationContext#refreshBeanFactory

    org.springframework.context.support.AbstractXmlApplicationContext#loadBeanDefinitions(org.springframework.beans.factory.support.DefaultListableBeanFactory)

    org.springframework.beans.factory.xml.XmlBeanDefinitionReader#doLoadBeanDefinitions

    org.springframework.beans.factory.xml.BeanDefinitionParserDelegate#parseCustomElement(org.w3c.dom.Element)

    核心流程：

    - 获取namespace
- 通过namespace解析NamespaceHandler
    - 构造ParserContext
- 解析元素，获取BeanDefinition。
  
13. 基于Properties文件装载外部化配置

    1. 注解驱动：

       @org.springframework.context.annotation.PropertySource

       @org.springframework.context.annotation.PropertySources

    2. API编程：

       org.springframework.core.env.PropertySource
       org.springframework.core.env.PropertySources

    

14. 基于YAML文件装载外部化配置

    org.springframework.beans.factory.config.YamlProcessor

      	org.springframework.beans.factory.config.YamlMapFactoryBean

      		org.springframework.beans.factory.config.YamlPropertiesFactoryBean

### Spring资源管理

1. 引入动机

   Java标准资源管理强大，然而扩展复杂，资源存储方式不统一

   Spring要自立门户

   Spring "抄"、"超"和"潮"

2. Java标注资源管理

   - Java标准资源定位

     | 职责         | 说明                                                         |
     | ------------ | ------------------------------------------------------------ |
     | 面向资源     | 文件系统、artifact(jar、war、ear文件)以及远程资源（HTTP、FTP等） |
     | API整合      | java.lang.ClassLoader#getResource、java.io.File或java.net.URL |
     | 资源定位     | java.net.URL或java.net.URI                                   |
     | 面向流式存储 | java.net.URLConnection                                       |
     | 协议扩展     | java.net.URLStreamHandler或java.net.URLStreamHandlerFactory  |
 - 基于java.net.URLStreamHandler扩展协议 

   JDK1.8内建协议实现

   | 协议   | 实现类                              |
   | ------ | ----------------------------------- |
   | file   | sun.net.www.protocol.file.Handler   |
   | ftp    | sun.net.www.protocol.ftp.Handler    |
   | http   | sun.net.www.protocol.http.Handler   |
   | http   | sun.net.www.protocol.https.Handler  |
   | jar    | sun.net.www.protocol.jar.Handler    |
   | mailto | sun.net.www.protocol.mailto.Handler |
   | netdoc | sun.net.www.protocol.netdoc.Handler |

   基于java.net.URLStreamHandler扩展协议

   实现类名必须为"Handler"

   | 实现类命名规则 | 说明                                                         |
   | -------------- | ------------------------------------------------------------ |
   | 默认           | sun.net.www.protocol.${protocol}.Handler                     |
   | 自定义         | 通过Java Properties java.proptocol.handler.pkgs指定实现类包名，实现类名必须为"Handler"。如果存在多包名指定，通过分隔符"\|" |

   


3. Spring资源接口

   资源接口

   | 类型       | 接口                                                |
   | ---------- | --------------------------------------------------- |
   | 输入流     | org.springframework.core.io.InputStreamSource       |
   | 只读资源   | org.springframework.core.io.Resource                |
   | 可写资源   | org.springframework.core.io.WritableResource        |
   | 编码资源   | org.springframework.core.io.support.EncodedResource |
   | 上下文资源 | org.springframework.core.io.ContextResource         |

   

4. Spring内建Resource实现

   内建实现

   | 资源来源       | 资源协议      | 实现类                                                       |
   | -------------- | ------------- | ------------------------------------------------------------ |
   | Bean定义       | 无            | org.springframework.beans.factory.support.BeanDefinitionResource |
   | 数组           | 无            | org.springframework.core.io.ByteArrayResource                |
   | 类路径         | classpath:/   | org.springframework.core.io.ClassPathResource                |
   | 文件系统       | file:/        | org.springframework.core.io.FileSystemResource               |
   | URL            | URL支持的协议 | org.springframework.core.io.UrlResource                      |
   | ServletContext | 无            | org.springframework.web.context.support.ServletContextResource |

   

5. Spring Resource接口扩展

   可写资源接口：

   org.springframework.core.io.WritableResource

   ​	org.springframework.core.io.FileSystemResource

   ​		org.springframework.core.io.FileUrlResource

   ​			org.springframework.core.io.PathResource（@Deprecated）

   编码资源接口：

   org.springframework.core.io.support.EncodedResource

6. Spring资源加载器

   Resource加载器

   org.springframework.core.io.ResourceLoader

   ​	org.springframework.core.io.DefaultResourceLoader

   ​		org.springframework.core.io.FileSystemResourceLoader

   ​		org.springframework.core.io.ClassRelativeResourceLoader

   org.springframework.context.support.AbstractApplicationContext

7. Spring通配路径资源加载器

   通配路径 ResourceLoader

   org.springframework.core.io.support.ResourcePatternResolver

   ​	org.springframework.core.io.support.PathMatchingResourcePatternResolver

   路径匹配器

   org.springframework.util.PathMatcher

    Ant模式匹配实现：org.springframework.util.AntPathMatcher

8. Spring通配路径资源扩展

   实现org.springframework.util.PathMatcher

   重置PathMatcher

   org.springframework.core.io.support.PathMatchingResourcePatternResolver#setPathMatcher

9. 依赖注入Spring Resource

10. 依赖注入ResourceLoader

    方法一：实现org.springframework.context.ResourceLoaderAware回调

    方法二：实现@Autowired注入ResourceLoader

    方法三：注入ApplicationContext作为ResourceLoader



### Spring国际化

1. Spring国际化使用场景

   - 普通国际化方案
   - Bean Validation校验国际化文案
   - Web站点页面渲染
   - Web MVC错误消息提示

2. Spring国际化接口
   核心接口：org.springframework.context.MessageSource
   主要概念：

   - 文案模板编码（code）

   - 文案模板参数（args）

   - 区域（Locale）

3. 层次性MessageSource

   - Spring层次性接口回顾

     org.springframework.beans.factory.HierarchicalBeanFactory

     org.springframework.context.ApplicationContext

     org.springframework.beans.factory.config.BeanDefinition

   - Spring层次性国际化接口

     org.springframework.context.HierarchicalMessageSource

4. Java国际化标准实现

   核心接口

   抽象实现：java.util.ResourceBundle

   核心特性

   - Key-Value设计
   - 层次性设计
   - 缓存设计
   - 字符编码控制-java.util.ResourceBundle.Control
   - Control SPI扩展-java.util.spi.ResourceBundleControlProvider

   Properties资源实现：java.util.PropertyResourceBundle

   例举实现：java.util.ListResourceBundle

5. Java文本格式化

   核心接口：java.text.MessageFormat

   基本用法：

   - 设置消息格式模式：new MessageFormat(...)
   - 格式化：format(new Object[]{...})

   消息格式模式：

   - 格式元素：{ArgumentIndex,(FormatType,(FormateStyle))}
   - FormatType:消息格式类型，可选项，每种类型在number、date、time和choice类型选其一。
   - formateStyle：消息格式风格，可选项，包括：short、medium、long、full、integer、currency。

   高级特性：

   - 重置消息格式模式
   - 重置java.text.Locale
   - 重置java.text.Format

6. MessageSource开箱即用实现

   - 基于ResourceBundle+MessageFormat组合MessageSource实现

     org.springframework.context.support.ResourceBundleMessageSource

   - 可重载Properties+MessageFormat组合MessageSource实现

     org.springframework.context.support.ReloadableResourceBundleMessageSource

7. MessageSource内建实现

   MessageSource内建Bean可能来源

   预注册Bean名称为："messageSource",类型为MessageSource Bean

   默认内建实现—DelegatingMessageSource

   层次性查找MessageSource对象

8. 课外资料

   Spring Boot为什么要新建MessageSource Bean？

   - AbstractApplicationContext的实现决定了MessageSource内建实现

   - Spring Boot通过外部化配置简化MessageSource Bean构建

   - Spring Boot基于Bean Validation校验非常普通

     

   ### Spring校验（Validation）

   1. Spring校验使用场景

      - Spring常规校验(Validator)
      - Spring数据绑定(DataBinder)
      - Spring Web参数绑定(WebDataBinder)
      - Spring WebMVC/WebFlux处理方法参数校验

   2. Validator接口设计

      接口职责：Spring内部校验器接口，通过编程的方式校验目标对象。

      核心方法：supports(Class):校验目标类能否校验

      ​					validate(Object,Errors)校验目标对象，并将校验失败的内容输出至Errors对象

      配置组件

      错误收集器：org.springframework.validation.Errors

      Validator工具类：org.springframework.validation.ValidationUtils

   3. Errors接口设计

      接口职责：数据绑定和校验错误收集接口，与Java Bean和其属性有强关联性。
      核心方法：

      reject方法（重载）：收集错误方案

      rejectValue方法(重载)：收集对象字段中的错误文案

      配套组件：

      Java Bean错误描述：org.springframework.validation.ObjectError

      Java Bean属性错误描述：org.springframework.validation.FieldError

   4. Errors文案生成步骤

      - 选择Errors实现(如：org.springframework.validation.BeanPropertyBindingResult)
      - 调用reject或rejectValue方法
      - 获取Errors 对象中的ObjectError或FieldError
      - 将ObjectError或FieldError中的code和args,关联MessageSource实现（如：org.springframework.context.support.ResourceBundleMessageSource）

   5. 自定义Validator

      实现org.springframework.validation.Validator接口

      - 实现supports方法

      - 实现validate方法

         通过Errors对象收集错误

        ​    ObjectError:对象（Bean）错误

        ​    FieldError:对象（Bean）属性（Property）错误

        通过ObjectError和FieldError关联MessageSource实现获取最终文案。

   6. Validator的救赎

      Bean Validation与Validator适配

      核心组件：org.springframework.validation.beanvalidation.LocalValidatorFactoryBean

      依赖Bean Validation—JSR-303or JSR-349 provider

      Bean方法参数校验-org.springframework.validation.beanvalidation.MethodValidationPostProcessor

   7. 面试题精选




   ### Spring数据绑定

   1. Spring数据绑定使用场景

      - Spring BeanDefinition到Bean实例创建
      - Spring数据绑定（DataBinder）
      - Spring Web 参数绑定（WebDataBinder）

   2. Spring数据绑定组件

      标准组件

      - org.springframework.validation.DataBinder

      Web组件：

      - org.springframework.web.bind.WebDataBinder
      - org.springframework.web.bind.ServletRequestDataBinder
      - org.springframework.web.bind.support.WebRequestDataBinder
      - org.springframework.web.bind.support.WebRequestDataBinder（since 5.0）

      DataBinder核心属性

      | 属性                 | 说明                         |
      | -------------------- | ---------------------------- |
      | target               | 关联目标Bean                 |
      | objectName           | 目标Bean名称                 |
      | bindingResult        | 属性绑定结果                 |
      | typeConverter        | 类型转换器                   |
      | conversionService    | 类型转换服务                 |
      | messageCodesResolver | 校验错误文案Code处理器       |
      | validators           | 关联的Bean Validator实例集合 |

      DataBinder绑定方法

      bind(PropertyValues):将PropertyValues key-value内容映射到关联Bean（target）中的属性上。假设PropertyValues中包含"name=小单"的键值对，同时Bean对象User中存在name属性，当bind方法执行时，User对象的中的name属性值将被绑定为"小单"。

   3. Spring数据绑定元数据

      DataBinder元数据—PropertyValues

      | 特征         | 说明                                                         |
      | ------------ | ------------------------------------------------------------ |
      | 数据来源     | BeanDefinition，主要来源XML资源配置BeanDefinition            |
      | 数据结构     | 由一个或多个PropertyValue组成                                |
      | 成员结构     | PropertyValue包含属性名称，以及属性值（包括原始值、类型转换后的值） |
      | 常见实现     | MutablePropertySources                                       |
      | Web扩展实现  | ServletConfigPropertyValues、ServletRequestParameterPropertyValues |
      | 相关生命周期 | org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessPropertyValues |

      

   4. Spring数据绑定控制参数

      DataBinder绑定特殊场景分析

      - 当PropertyValues中包含名称x的PropertyValue，目标对象B不存在x属性，当bind方法执行时，会发生什么？无异常
      - 当PropertyValues中包含名称x的PropertyValue，目标对象B存在x属性，当bind方法执行时，如何避免B属性x不被绑定？设置
      - 当PropertyValues中包含名称x、y的PropertyValue，目标对象B存在x属性（嵌套y属性），当bind方法执行时，会发生什么？正常

   5. Spring 底层Java Beans替换实现

      JavaBeans核心实现—java.beans.BeanInfo

      - 属性（Property）java.beans.PropertyEditor
      - 方法（Method）
      - 事件（Event）
      - 表达式（Expression）

      Spring替代实现-org.springframework.beans.BeanWrapper

      - 属性（Property）：java.beans.PropertyEditor
      - 嵌套属性路径：nested path

   6. BeanWrapper的使用场景

      - Spring 底层JavaBeans基础设施的中心化接口
      - 通常不会直接使用，间接用于BeanFactory和DataBinder
      - 提供标准JavaBeans分析和操作，能够单独或批量存储Java Bean的属性（properties）
      - 支持嵌套属性路径（nested path）
      - 实现类org.springframework.beans.BeanWrapperImpl

   7. 课外资料

      标准JavaBeans是如何操作属性的？

       

      | API                           | 说明                    |
      | ----------------------------- | ----------------------- |
      | java.beans.Introspector       | Java Beans内建API       |
      | java.beans.BeanInfo           | Java Bean元信息API      |
      | java.beans.BeanDescriptor     | Java Bean信息描述符     |
      | java.beans.PropertyDescriptor | Java Bean属性描述符     |
      | java.beans.MethodDescriptor   | Java Bean方法描述符     |
      | java.beans.EventSetDescriptor | Java Bean事件集合描述符 |

      

   8. DataBinder数据校验

      DataBinder与BeanWrapper

      - bind方法生成BeanPropertyBindingResult
      - BeanPropertyBindingResult关联BeanWrapper

### Spring类型转换

1. Spring 类型转换的实现

   基于JavaBeans接口类型转换实现：基于java.beans.PropertyEditor接口扩展

   Spring 3.0+通用类型转换实现：

2. 使用场景

   | 场景               | 基于JavaBeans接口的类型转换实现 | Spring 3.0+通用类型转换实现 |
   | ------------------ | ------------------------------- | --------------------------- |
   | 数据绑定           | YES                             | YES                         |
   | BeanWrapper        | YES                             | YES                         |
   | Bean属性类型转换   | YES                             | YES                         |
   | 外部化属性类型转换 | NO                              | YES                         |

   

3. 基于JavaBeans接口的类型转换

   核心职责：将Spring类型的内容转换为目标类型的对象

   扩展原理：

   - Spring框架将文本内容传递到PropertyEditor实现的setAsText(String)方法
   - PropertyEditor#setAsText(String)方法实现将String类型转换为目标类型的对象。
   - 将目标类型的对象传入PropertyEditor#setValue(Object)方法
   - PropertyEditor#setValue(Object)方法实现需要临时存储传入对象
   - Spring框架将通过PropertyEditor#getValue()获取类型转换后的对象

4. Spring内建PropertyEditor扩展

   内建扩展(org.springframework.beans.propertyeditors包下)

   | 转换场景         | 实现类                                                       |
   | ---------------- | ------------------------------------------------------------ |
   | String—>Byte数组 | org.springframework.beans.propertyeditors.ByteArrayPropertyEditor |
   | String—>Char     | org.springframework.beans.propertyeditors.CharacterEditor    |
   | String—>Char数组 | org.springframework.beans.propertyeditors.CharArrayPropertyEditor |
   | String—>Charset  | org.springframework.beans.propertyeditors.CharsetEditor      |
   | String—>Class    | org.springframework.beans.propertyeditors.ClassEditor        |
   | String—>Currency | ororg.springframework.beans.propertyeditors.CurrencyEditor   |

5. 自定义PropertyEditor扩展

   扩展模式：扩展java.beans.PropertyEditorSupport类

   实现org.springframework.beans.PropertyEditorRegistrar#registerCustomEditors(PropertyEditorRegistry registry)

   向org.springframework.beans.PropertyEditorRegistrar注册自定义PropertyEditor实现

   org.springframework.beans.PropertyEditorRegistry#registerCustomEditor(java.lang.Class<?>, java.lang.String, java.beans.PropertyEditor)

6. Spring PropertyEditor的设计缺陷

   违反职责单一原则：

   java.beans.PropertyEditor接口职责太多，除了类型转换，还包括Java Beans事件和Java GUI交互

   java.beans.PropertyEditor实现类型局限：来源类型只能为java.lang.String类型

   java.beans.PropertyEditor实现缺少类型安全：除了实现类名可以表达语义，实现类无法感知目标转换类型。

7. Spring 3 通用类型转换接口

   - 类型转换接口：org.springframework.core.convert.converter.Converter<S,T>

     泛型参数S：来源参数，参数T：目标类型

     核心方法：T convert(S） 

   - 通用类型转换接口：org.springframework.core.convert.converter.GenericConverter

     核心方法：org.springframework.core.convert.converter.GenericConverter#convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType)

     配对类型：org.springframework.core.convert.converter.GenericConverter.ConvertiblePair

     类型描述：org.springframework.core.convert.TypeDescriptor

8. Spring 内建类型转换器

   内建扩展

   | 转换场景             | 实现类所在包名(package)                      |
   | -------------------- | -------------------------------------------- |
   | 日期/时间相关        | org.springframework.format.datetime          |
   | Java 8 日期/时间相关 | org.springframework.format.datetime.standard |
   | 通用实现             | org.springframework.core.io.support          |

9. Converter接口的局限性

   局限一：缺少Source Type和Target  Type前置判断

   应对：增加org.springframework.core.convert.converter.ConditionalConverter实现 
   局限二：仅能转换单一的Source Type和Target Type

   应对：使用org.springframework.core.convert.converter.GenericConverter代替

10. GenericConverter接口

    org.springframework.core.convert.converter.GenericConverter

    | 核心要素 | 说明                                                         |
    | -------- | ------------------------------------------------------------ |
    | 使用场景 | 用于"复合"类型转换场景，比如Collection、Map和数组等          |
    | 转换范围 | Set<ConvertiblePair> getConvertibleTypes()                   |
    | 配对类型 | org.springframework.core.convert.converter.GenericConverter.ConvertiblePair |
    | 转换方法 | convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) |
    | 类型描述 | org.springframework.core.convert.TypeDescriptor              |

11. 优化GenericConverter接口

    - GenericConverter局限性：

      缺少Source Type和Target Type前置判断

      单一类型转换复杂

    - GenericConverter优化接口：ConditionalGenericConverter

      复合类型转换：org.springframework.core.convert.converter.GenericConverter

      类型条件判断：org.springframework.core.convert.converter.ConditionalConverter

12. 扩展Spring类型转换器

    实现转换器接口

    org.springframework.core.convert.converter.Converter

    org.springframework.core.convert.converter.ConverterFactory
    org.springframework.core.convert.converter.GenericConverter
    注册转换器实现：

    通过org.springframework.context.support.ConversionServiceFactoryBean Spring Bean 

    通过org.springframework.core.convert.ConversionService API

13. 统一类型转换服务

    org.springframework.core.convert.ConversionService

    | 实现类型                           | 说明                                                         |
    | ---------------------------------- | ------------------------------------------------------------ |
    | GenericConversionService           | 通用ConversionService模板实现，不内置转化器实现              |
    | DefaultConversionService           | 基础ConversionService实现，内置常用转化器实现                |
    | FormattingConversionService        | 通用Formatter+GenericConversionService实现，不内置转化器和Formatter实现 |
    | DefaultFormattingConversionService | DefaultConversionService+格式化实现，比如：SR-354 Money & Currency, JSR-310 Date-Time |

14. ConversionService作为依赖

    类型转换器底层接口org.springframework.beans.TypeConverter

    - 起始版本：Spring 2.0
    - 核心方法：convertIfNecessary重载方法
    - 抽象实现：org.springframework.beans.TypeConverterSupport
    - 简单实现：org.springframework.beans.SimpleTypeConverter

    类型转化器底层委派实现org.springframework.beans.TypeConverterDelegate

    - 构造来源org.springframework.beans.AbstractNestablePropertyAccessor实现org.springframework.beans.BeanWrapperImpl

    - 依赖 -java.beans.PropertyEditor

      默认内建实现—org.springframework.beans.PropertyEditorRegistrySupport#registerDefaultEditors

    - 可选依赖：org.springframework.core.convert.ConversionService

15. 面试题精选



### Spring泛型处理

1. Java泛型基础

   泛型类型：泛型类型是在类型上参数化的泛型类或接口

   泛型使用场景：

   - 编译时强类型检查。
   - 避免类型强转。
   - 实现通用算法。

   泛型类型擦写：

   泛型被引入到Java语言中，以便在编译时提供更严格的类型检查并支持泛型编程。类型擦除确保不会为参数化类型创建新类；因此，泛型不会产生运行时开销。为了实现泛型。编译器将类型擦除应用于：

   - 将泛型类型中的所有类型参数替换为其边界，如果类型参数是无边界的，则将其替换为“Object”。因此，生成的字节码只包含普通类、接口和方法。
   - 必要时插入类型转换以保持类型安全。
   - 生成桥方法以保留扩展泛型类型中的多态性。

2. Java 5 类型接口

   java.lang.reflect.Type

   | 派生类或接口                        | 说明                                  |
   | ----------------------------------- | ------------------------------------- |
   | java.lang.Class                     | Javal类API，如java.lang.String        |
   | java.lang.reflect.GenericArrayType  | 泛型数组类型                          |
   | java.lang.reflect.ParameterizedType | 泛型参数类型                          |
   | java.lang.reflect.TypeVariable      | 泛型类型变量 ，如果Collection<E>中的E |
   | java.lang.reflect.WildcardType      | 泛型通配类型                          |

   泛型反射API

   | 类型                             | API                                  |
   | -------------------------------- | ------------------------------------ |
   | 泛型信息（Generics info）        | java.lang.Class#getGenericInfo       |
   | 泛型参数（Parameters）           | java.lang.reflect.ParameterizedType  |
   | 泛型父类（Super Classes）        | java.lang.Class#getGenericSuperclass |
   | 泛型接口（Interfaces）           | java.lang.Class#getGenericInterfaces |
   | 泛型声明（Generics Declaration） | java.lang.reflect.GenericDeclaration |

3. Spring泛型类型辅助类

   核心API：org.springframework.core.GenericTypeResolver

   - 版本支持：[2.5.2,)

   - 处理类型相关（Type）相关方法：resolveReturnType和resolveType

   - 处理泛型参数类型(ParameterizedType)相关方法：resolveReturnTypeArgument、resolveTypeArgument和resolveTypeArguments
   - 处理泛型类型变量（TypeVariable）相关方法：getTypeVariableMap

4. Spring泛型集合类型辅助类

   核心API：org.springframework.core.GenericCollectionTypeResolver

   - 版本支持：[2.0,4.3]
   - 替换实现：org.springframework.core.ResolvableType
   - 处理Collection相关：getCollection*Type
   - 处理Map相关：getMapKey*Type、getMapValue*Type

5. Spring方法参数封装—MethodParameter

   核心API：org.springframework.core.MethodParameter

   - 版本支持：[2.0,)

   - 元信息：

     关联的方法：Method

     关联的构造器：Constructor

     构造器或方法参数索引：parameterIndex

     构造器或方法参数类型：parameterType

     构造器或方法参数泛型类型：genericParameterType

     构造器或方法参数参数名称：parameterName

     所在的类：containingClass

6. Spring 4.2 泛型优化实现—ResolvableType

   核心API：org.springframework.core.ResolvableType

   - 起始版本：[4.0,)
   - 扮演角色：GenericTypeResolver和GenericCollectionTypeResolver替代者
   - 工厂方法：for* 方法
   - 转换方法：as*方法
   - 处理方法：resolve*方法

7. ResolvableType的局限性

   局限一：ResolvableType无法处理泛型擦写

   局限二：ResolvableType无法处理非具体化的ParameterizedType

8. 面试题精选



### Spring事件

1. Java 事件/监听器编程模型

   设计模式—观察者模式扩展

   - 可观察者对象（消息发送者）-java.util.Observable

   - 观察者：-java.util.Observer

  标准化接口 

-   事件对象:-java.util.EventObject
-  事件监听器：-java.util.EventListener

2. 面向接口的事件/监听器设计模式

   事件/监听器场景举例

   | Java技术规范    | 事件接口                              | 监听器接口                               |
   | --------------- | ------------------------------------- | ---------------------------------------- |
   | JavaBeans       | java.beans.PropertyChangeEvent        | java.beans.PropertyChangeListener        |
   | Java AWT        | java.awt.event.MouseEvent             | java.awt.event.MouseListener             |
   | Java Swing      | javax.swing.event.MenuEvent           | javax.swing.event.MenuListener           |
   | Java Preference | java.util.prefs.PreferenceChangeEvent | java.util.prefs.PreferenceChangeListener |

3. 面向注解的事件/监听器设计模式

   事件/监听器注解场景举例

   | Java技术规范 | 事件注解                       | 监听器注解                            |
   | ------------ | ------------------------------ | ------------------------------------- |
   | Servlet 3.0+ |                                | @javax.servlet.annotation.WebListener |
   | JPA 1.0+     | @javax.persistence.PostPersis  |                                       |
   | Java Common  | @PostConstruct                 |                                       |
   | EJB 3.0+     | @javax.ejb.PrePassivate        |                                       |
   | JSF 2.0+     | @javax.faces.event.ListenerFor |                                       |

4. Spring标准事件—ApplicationEvent

   Java标准事件：java.util.EventObject扩展

   ​	扩展特性：事件发生事件戳

   Spring应用上下文ApplicationEvent扩展—ApplicationContextEvent

   ​	Spring应用上下文(ApplicatonEvent)作为事件源

   ​    具体实现：org.springframework.context.event.ContextClosedEvent

   ​			org.springframework.context.event.ContextRefreshedEvent

   ​			org.springframework.context.event.ContextStartedEvent

   ​			org.springframework.context.event.ContextStoppedEvent

5. 基于接口的Spring事件监听器

   Java标准事件监听器java.util.EventListener扩展

   - 扩展接口：org.springframework.context.ApplicationListener
   - 设计特点：单一类型事件处理
   - 处理方法:onApplicationEvent(E event)
   - 事件类型：org.springframework.context.ApplicationEvent

6. 基于注解的Spring事件监听器

   Spring注解：org.springframework.context.event.EventListener

   | 特性                 | 说明                                     |
   | -------------------- | ---------------------------------------- |
   | 设计特点             | 支持多ApplicationEvent类型，无须接口约束 |
   | 注解目标             | 方法                                     |
   | 是否支持异步执行     | 支持                                     |
   | 是否支持泛型类型事件 | 支持                                     |
   | 是否支持顺序控制     | 支持，配合@Order注解控制                 |

7. 注册Spring ApplicationListener

   方法一：ApplicationListener作为Spring Bean注册

   方法二：通过ConfigurableApplicationContext API注册

8. Spring事件发布器

   方法一：通过ApplicationEventPublisher发布Spring事件

   ​			获取ApplicationEventPublisher：依赖注入

   方法二：通过ApplicationEventMulticaster发布Spring事件

   ​			获取ApplicationEventMulticaster：依赖注入、依赖查找。

9. Spring层次性上下文事件传播

   发生说明：当Spring应用出现多层次Spring应用上下文(ApplicationContext)时，如Spring WebMVC、Spring Boot或Spring Cloud场景下，由子ApplicationContext发起Spring事件可能会传递到其Parent ApplicationContext(直到Root)的过程。

   如何避免：定位Spring事件源(ApplicationContext)进行过滤处理。

10. Spring内建事件

    ApplicationContextEvent派生事件

    - ContextRefreshedEvent：Spring应用上下文就绪事件
    - ContextStartedEvent：Spring应用上下文启动事件
    - ContextStoppedEvent：Spring应用上下文停止事件
    - ContextClosedEvent：Spring应用上下文关闭事件

11. Spring 4.2 Payload事件

    Spring Payload事件：org.springframework.context.PayloadApplicationEvent

    使用场景：简化Spring事件发送，关注事件源主体

    发送方法：ApplicationEventPublisher#publishEvent(java.lang.Object)

12. 自定义Spring事件

    - 扩展org.springframework.context.ApplicationEvent
    - 实现org.springframework.context.ApplicationListener
    - 注册org.springframework.context.ApplicationListener

13. 依赖注入ApplicationEventPublisher

    通过ApplicationEventPublisherAware回调接口

    通过@Autowired ApplicationEventPublisher

14. 依赖查找ApplicationEventMulticaster

    查找条件：

    - Bean名称 "applicationEventMulticaster"
    - Bean类型：org.springframework.context.event.ApplicationEventMulticaster

15. ApplicationEventPublisher底层实现

    - 接口：org.springframework.context.event.ApplicationEventMulticaster

    - 抽象类：org.springframework.context.event.AbstractApplicationEventMulticaster

    - 实现类：org.springframework.context.event.SimpleApplicationEventMulticaster

16. 同步和异步Spring事件广播

    基于实现类：org.springframework.context.event.SimpleApplicationEventMulticaster

    模式切换：org.springframework.context.event.SimpleApplicationEventMulticaster#setTaskExecutor(java.util.concurrent.Executor)方法

    ​		默认模式：同步

    ​		异步模式：如：java.util.concurrent.ThreadPoolExecutor

    设计缺陷：非基于接口契约编程

    基于注解：org.springframework.context.event.EventListener

    模式切换：

      模式模式：同步

      异步模式：标注@org.springframework.scheduling.annotation.Async

    实现限制：无法直接实现同步/异步动态切换

17. Spring 4.1事件异常处理

    Spring 3.0 错误处理接口：org.springframework.util.ErrorHandler

    使用场景：	

    ​	Spring 事件（Events）

    ​    org.springframework.context.event.SimpleApplicationEventMulticaster Spring4.1开始支持

    ​    Spring 本地调到（Scheduling）

    ​    org.springframework.scheduling.concurrent.ConcurrentTaskScheduler
    ​    org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler

18. Spring事件/监听器实现原理

    核心类：org.springframework.context.event.SimpleApplicationEventMulticaster

    设计模式：观察者模式扩展

    被观察者：org.springframework.context.ApplicationListener：API添加和依赖查找

    通知对象：org.springframework.context.ApplicationEvent

    执行模式：同步/异步

    异常处理：org.springframework.util.ErrorHandler

    泛型处理：org.springframework.core.ResolvableType

19. 课外资料

    Spring Boot事件

    | 事件类型                            | 发生时机                              |
    | ----------------------------------- | ------------------------------------- |
    | ApplicationStartingEvent            | 当Spring Boot应用开始启动时           |
    | ApplicationStartedEvent             | 当Spring Boot应用已启动时             |
    | ApplicationEnvironmentPreparedEvent | 当Spring Boot Environment实例已准备时 |
    | ApplicationPreparedEvent            | 当Spring Boot应用预备时               |
    | ApplicationReadyEvent               | 当Spring Boot应用完全可用时           |
    | ApplicationFailedEvent              | 当Spring Boot应用启动失败时           |

    Spring Cloud事件

    | 事件类型                   | 发生时机                            |
    | -------------------------- | ----------------------------------- |
    | EnvironmentChangeEvent     | 当Environment示例配置属性发生变化时 |
    | HeartbeatEvent             | 当DiscoveryClient客户端发送心跳时   |
    | InstancePreRegisteredEvent | 当服务实例注册前                    |
    | InstanceRegisteredEvent    | 当服务实例注册后                    |
    | RefreshEvent               | 当RefreshEndpoint被调用时           |
    | RefreshScopeRefreshedEvent | 当Refresh Scope Bean刷新后          |


### Spring注解

1. Spring 注解驱动编程发展历程

   - 注解驱动启蒙时代：Spring Framework 1.x
   - 注解驱动过渡时代：Spring Framework 2.x
   - 注解驱动黄金时代：Spring Framework 3.x
   - 注解驱动完善时代：Spring Framework 4.x
   - 注解驱动当下时代：Spring Framework 5.x

2. Spring 核心注解场景分类

   Spring模式注解

   | Spring注解     | 场景说明          | 起始版本 |
   | -------------- | ----------------- | -------- |
   | @Repository    | 数据仓储模式注解  | 2.0      |
   | @Component     | 通用组件模式注解  | 2.5      |
   | @Service       | 服务模式注解      | 2.5      |
   | @Controller    | Web控制器模式注解 | 2.5      |
   | @Configuration | 配置类模式注解    | 3.0      |
   
    装配注解

   | Spring注解      | 场景说明                                | 起始版本 |
   | --------------- | --------------------------------------- | -------- |
   | @ImportResource | 替换XML元素<import>                     | 2.5      |
   | @Import         | 导入Configuration类                     | 2.5      |
   | @ComponentScan  | 扫描指定package下标注Spring模式注解的类 | 3.1      |
   
      依赖注入注解
   
   | Spring注解 | 场景说明                           | 起始版本 |
| ---------- | ---------------------------------- | -------- |
   | @Autowired | Bean依赖注入，支持多种依赖查找方式 | 2.5      |
   | @Qualifier | 细粒度的@Autowired依赖查找         | 2.5      |
   
3. Spring注解编程模型

   编程模型

   - 元注解（Meta—Annotations）
   - Spring模式注解（Stereotype Annotations）
   - Spring组合注解（Composed Annotations）
   - Spring注解属性别名和覆盖(Attribute Overrides andAliases)

4. Spring元注解（Meta—Annotations）

   一个注解标注在另外一个注解上，属性相互描述。

   举例说明：java.lang.annotation.Documented

   java.lang.annotation.Inherited

   java.lang.annotation.Repeatable

5. Spring模式注解（Stereotype Annotations）

   理解@Componet "派生性"

   元标注@Component的注解在XML元素<context:component-scan>或注解@ComponentScan扫描中"派生"了@Component的特性，并且从Spring Framework 4.0开始之初多层次"派生性"

   举例说明：

   - @Respository

   - @Service

   - @Controller

   - @Configuration

   - @SpringBootConfiguration(Spring Boot)

   @Component "派生性"原理

   - 核心组件：org.springframework.context.annotation.ClassPathBeanDefinitionScanner

     org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider

   - 资源处理：org.springframework.core.io.support.ResourcePatternResolver

   - 资源—类元信息：org.springframework.core.type.classreading.MetadataReaderFactory

   - 类元信息：org.springframework.core.type.ClassMetadata

     ASM实现：org.springframework.core.type.classreading.ClassMetadataReadingVisitor

     反射实现：org.springframework.core.type.StandardAnnotationMetadata

   - 注解元信息：org.springframework.core.type.AnnotationMetadata

     ASM实现：org.springframework.core.type.classreading.AnnotationMetadataReadingVisitor

     反射实现：org.springframework.core.type.StandardAnnotationMetadata

6. Spring组合注解（Composed Annotations）

   Spring组合注解的元注解允许是Spring模式注解与其他Spring功能性注解的任意组合。

7. Spring注解属性别名(Attribute Aliases)

   显示别名和隐性别名

8. Spring 注解属性覆盖（Attribute Overrides）

   显示覆盖和隐性覆盖

9. Spring @Enable模块驱动

   @Enable模块驱动：是以@Enable为前缀的注解驱动编程模型。所谓"模块"是指具备相同领域的功能组件集合，组合所形成的一个独立的单元。比如Web MVC模块、AspectJ代理模块、Caching模块（缓存）、JMX（java管理扩展）模块、Async（异步处理）模块等。

   举例说明：

   - @EnableWebMvc
   - @EnableTransactionManagement
   - @EnableCaching
   - @EnableMBeanExport
   - @EnableAsync

   @Enable模块驱动编程模式

   - 驱动注解：@EnableXXX

   - 导入注解：@Import具体实现

   - 具体实现：

     基于Configuration Class 

     基于ImportSelector接口实现

     基于ImportBeanDefinitionRegister接口实现

10. Spring条件注解

    基于配置条件注解：org.springframework.context.annotation.Profile

    - 关联对象：org.springframework.core.env.Environment中的Profiles

    - 实现变化：从Spring 4.0开始 @Profile基于@Conditional实现

    基于编程条件注解：org.springframework.context.annotation.Conditional

    - 关联对象：org.springframework.context.annotation.Condition具体实现

    @Conditional实现原理

    - 上下文对象：org.springframework.context.annotation.ConditionContext

    - 条件判断：org.springframework.context.annotation.ConditionEvaluator

    - 配置阶段：org.springframework.context.annotation.ConfigurationCondition.ConfigurationPhase

    - 判断入口：org.springframework.context.annotation.ConfigurationClassPostProcessor

         org.springframework.context.annotation.ConfigurationClassParser

11. 课外资料

    Spring Boot注解

    | 注解                     | 场景说明                | 起始版本 |
    | ------------------------ | ----------------------- | -------- |
    | @SpringBootConfiguration | Spring Boot配置类       | 1.4.0    |
    | @SpringBootApplication   | Spring Boot应用引导注解 | 1.2.0    |
    | @EnableAutoConfiguartion | Spring Boot激活自动转配 | 1.0.0    |

    Spring Cloud注解

    | 注解                    | 场景说明                           | 起始版本 |
    | ----------------------- | ---------------------------------- | -------- |
    | @SpringCloudApplication | Spring Cloud应用引导注解           | 1.0.0    |
    | @EnableDiscoveryClient  | Spring Cloud激活服务发现客户端注解 | 1.0.0    |
    | @EnableCircuitBreaker   | Spring Cloud激活熔断注解           | 1.0.0    |




### Spring Environment抽象

1. 理解Spring Environment 抽象

   - 统一的Spring配置属性管理：Spring Framework 3.1 开始引入Environment抽象，它统一Spring配置属性的存储，包括占位符处理和类型转换，不仅完整地替换PropertyPlaceholderConfigurer，而且还支持更丰富的配置属性源（PropertySource）

   - 条件化Spring Bean 装配管理

     通过Environment Profiles信息，帮助Spring容器提供条件化装配Bean。

2. Spring Environment接口使用场景

   - 用于属性占位符处理
   - 用于转换Spring配置属性类型
   - 用于存储Spring配置属性源（PropertySource）
   - 用于Profiles状态的维护

3. Environment 占位符处理

   Spring 3.1前占位符处理

   组件：org.springframework.beans.factory.config.PropertyPlaceholderConfigurer

   接口：org.springframework.util.StringValueResolver

   Spring 3.1+占位符处理

   组件：org.springframework.context.support.PropertySourcesPlaceholderConfigurer

   接口：org.springframework.beans.factory.config.EmbeddedValueResolver

4. 理解条件配置 Spring Profiles

   Spring 3.1 条件配置

    API：org.springframework.core.env.ConfigurableEnvironment

   - 修改：addActiveProfile(String profile)、setActiveProfiles(String... profiles)和setDefaultProfiles(String... profiles)

   - 获取：getActiveProfiles()和getDefaultProfiles()

   - 匹配：acceptsProfiles(String... profiles)和acceptsProfiles(Profiles profiles)

​      注解：org.springframework.context.annotation.Profile

5. Spring 4 重构 @Profile

   基于Spring 4 org.springframework.context.annotation.Condition接口实现

   ​    org.springframework.context.annotation.ProfileCondition

6. 依赖注入 Environment 

   - 直接依赖注入：

     通过EnvironmentAware接口回调

     通过@Autowired注入Environment

   - 间接依赖注入

     通过ApplicationContextAware接口回调

     通过@Autowired注入ApplicationContext

7. 依赖查找 Environment 

   - 直接依赖查找：

     org.springframework.context.ConfigurableApplicationContext#ENVIRONMENT_BEAN_NAME

   - 间接依赖查找

     org.springframework.context.ConfigurableApplicationContext#getEnvironment

8. 依赖注入 @Value

   通过注入@Value

   实现：org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor

9. Spring 类型转换在 Environment中的运用

   Environment底层实现

   - 底层实现：org.springframework.core.env.PropertySourcesPropertyResolver

     核心方法：<T> T convertValueIfNecessary(Object value, @Nullable Class<T> targetType)

   - 底层服务：org.springframework.core.convert.ConversionService

     默认实现：org.springframework.core.convert.support.DefaultConversionService

10. Spring 类型转换在@Value中的运用

    @Value底层实现

    底层实现：org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor

    ​     org.springframework.beans.factory.support.DefaultListableBeanFactory#doResolveDependency

    底层服务：org.springframework.beans.TypeConverter

    默认实现：org.springframework.beans.TypeConverterDelegate

    - java.beans.PropertyEditor

    - org.springframework.core.convert.ConversionService

11. Spring 配置属性源 PropertySource

    API：

    单配置属性源：org.springframework.core.env.PropertySource

    多配置属性源：org.springframework.core.env.PropertySources

    注解：

    单配置属性源：org.springframework.context.annotation.PropertySource

    多配置属性源：org.springframework.context.annotation.PropertySources

    关联：

    存储对象：org.springframework.core.env.MutablePropertySources

    关联方法：org.springframework.core.env.ConfigurableEnvironment#getPropertySources

12. Spring内建的配置属性源

    内建PropertySource

    | PropertySource类型                                           | 说明                 |
    | ------------------------------------------------------------ | -------------------- |
    | org.springframework.core.env.CommandLinePropertySource       | 命令行配置属性源     |
    | org.springframework.jndi.JndiPropertySource                  | JNDI配置属性源       |
    | org.springframework.core.env.PropertiesPropertySource        | Properties配置属性源 |
    | org.springframework.web.context.support.ServletConfigPropertySource | Servlet配置属性源    |
    | org.springframework.core.env.SystemEnvironmentPropertySource | 环境变量配置属性源   |
    | 。。。                                                       | 。。。               |

13. 基于注解扩展Spring 配置属性源

    org.springframework.context.annotation.PropertySource实现原理：

    入口：org.springframework.context.annotation.ConfigurationClassParser#doProcessConfigurationClass

    org.springframework.context.annotation.ConfigurationClassParser#processPropertySource

    4.3 新增语义

    - 配置属性字符编码—encoding

    - org.springframework.core.io.support.PropertySourceFactory

   适配对象：org.springframework.core.env.CompositePropertySource

14. 基于API扩展Spring 配置属性源
    - Spring应用上下文启动前装配PropertySource
    - Spring应用上下文启动后装配PropertySource
15. 课外资料
16. 面试题精选

