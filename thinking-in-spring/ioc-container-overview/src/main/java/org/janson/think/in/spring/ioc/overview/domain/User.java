package org.janson.think.in.spring.ioc.overview.domain;

import lombok.ToString;
import org.janson.think.in.spring.ioc.overview.enums.City;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Properties;

/**
 * @Description: 用户类
 * @Author: Janson
 * @Date: 2020/7/19 12:47
 **/
@ToString
public class User implements BeanNameAware {
    private Long id;
    private String name;
    private City city;
    private City[] workCities;
    private List<City> lifeCities;
    private Resource configFileLocation;
    private Company company;
    private Properties context;

    public Properties getContext() {
        return context;
    }

    public void setContext(Properties context) {
        this.context = context;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    /**
     * 当前bean的名称
     **/
    private transient String beanName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public static User createUser() {
        User user = new User();
        user.setId(2L);
        user.setName("janson");
        return user;
    }

    @PostConstruct
    public void init() {
        System.out.println("User Bean[" + beanName + "]对象初始化...");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("User Bean[" + beanName + "]对象销毁...");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
