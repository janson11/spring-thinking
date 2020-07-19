# spring-thinking
spring核心思想

## spring编程模型

面向对象编程
契约接口：Aware、BeanPostProcessor...
设计模式：观察者模式、组合模式、模板模式
对象继承：Abstract* 类

面向切面编程
动态代理：JdkDynamicAopProxy
字节码提升：ASM、CGLib、AspectJ

面向元编程
注解：模式注解(@Component、@Service、@Repository...)
配置：Environment抽象、PropertySources、BeanDefinition...
泛型：GenericTypeResolver、ResolvableType...

函数驱动
函数接口：ApplicationEventPublisher
Reactive：Spring WebFlux

模块驱动
Maven Artifacts
OSGI Bundles
Java 9 Automatic Modules
Spring @Enable*