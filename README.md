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

