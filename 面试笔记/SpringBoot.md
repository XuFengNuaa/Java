

# **SpringBoot-微服务阶段**

## 1. 基础原理：

### Start:

​	SSM配置过于繁杂，SpringBoot旨在简化配置

​	IDEA创建推荐SpringBoot版本

#### pom.xml: 

​		核心依赖在父类中！控制jar包的版本,

​		需要什么就加入**starter**

```xml
<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter</artifactId>
</dependency>
```



```java
@SpringBootConfiguration: sringboot的配置类
	
@EnableAutoConfiguration:自动配置  在autoconfig目录下的MATF-INF下的spring.factories(所有的配置																				类全在这下面)
    						自动导包
```



### yaml：

​		修改AutoConfig的默认值  k:(空格）value

​		给实体类赋值（也可以在实体类@value赋值，太麻烦），具体事例看project

​		@validation   JSR303校验 

​		**可是使用占位符提取数据   ${perosn.name}**



### 配置文件：

​		优先级别： file: ./config/application.yaml          project路径下

​							file: ./application.yaml

​							classpath: ./config/application.yaml    src类路径下

​							classpath: ./application.yaml

​	  多环境配置： spring.profiles.active:  dev,test



![](I:\KSDownload\autoConfig.png)



## 2. Spring-Web开发

​		 ![](I:\KSDownload\web.png)

### 静态资源导入

​		引入webjars:  搜索webjars，导入相应的依赖

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>jquery</artifactId>
    <version>3.5.1</version>
</dependency>
```

​	<img src="I:\KSDownload\webjars.png" style="zoom: 200%;" />

​		**资源放置目录： classpath： public,  static,   resources**

​		**优先级： resources> static(默认)>public**     

​		**<u>当在yaml配置文件中配置static-path-pattern时，上面三个目录全部失效，只访问指定的目录！！</u>**

​		首页：index.jsp   当目录下存在index.jsp时，lcoalhost:8080/,就自动访问index目录！

### thymeleaf

  1. tempalte下的jsp文件只能通过Controller来进行跳转

  2. 需要模板引擎的支持，thymeleaf

     ```xml
     <dependency>
     			<groupId>org.thymeleaf</groupId>
     			<artifactId>thymeleaf-spring5</artifactId>
     </dependency>
     <dependency>
     			<groupId>org.thymeleaf.extras</groupId>
     			<artifactId>thymeleaf-extras-java8time</artifactId>
     </dependency>
     ```

     ![](I:\KSDownload\thymeleaf.png)

     3.重点：导入约束

     ```javascript
     xmlns:th="http://www.thymeleaf.org"
     
     th:href  th:text  ....
     ```


### 扩展WebMVC

```
	标注这是一个配置类，WebMvcConfigurerAdapter扩展springmvc功能
    @EnableWebMvc(一般不使用）--不接管springMvc，只扩展功能
    推荐使用实现借口 webMvcConfiguer,而不是extends WebMvcConfiguerSupports
    Alt+insert  看Override methods
```

```java
@Configuration 
// 配置页面跳转
public class MyMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
    }
}
```

### 国际化

![](I:\KSDownload\2020-05-15_161253.png)

```java
spring.messages.basename=i18n.login
```

***<u>		Alt + enter   new一个新的对象后，自动补全前面的class</u>***

i18n配置文件

自定义MyLocaleResolver组件

@bean  加入到 spring组件中

thymeleaf中运用#{  }

**如果发现页面跳转失败（静态资源目录不对，可能少个“ / ”）**



### 配置页面拦截器

```java
public void addInterceptors(InterceptorRegistry registry) {
        //拦截静态资源，出去/asserts/**的静态资源即可
        registry.addInterceptor(new LoginHandlerInterceptor())
                .addPathPatterns("/**").excludePathPatterns("/","/index.html","/user/login","/asserts/**");
    }   

```

***	注意：将静态资源排除在外！！***

```java
public class LoginHandlerInterceptor  implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object loginUser = request.getSession().getAttribute("loginUser");
        if(loginUser==null){
            request.setAttribute("msg","请登录！");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            return true;
        } 
    }
}
```

### Restful风格的CRUD

![](I:\KSDownload\2020-05-16_141932.png)



![1111142228](I:\KSDownload\1111142228.png)

#### thymeleaf 提取公共页面

**文档注释：Alt+shift+/**

```html
1. 提取页面
<div th:frame = "topbar">
    *******
</div>
<div  id="sidebar">
</div>
2. 引入片段
<div th:insert="~{footer :: copy}"></div>
~{templatename :: selector}    模板名：：选择器    {dashboard:: #sidebar}
~{templatename :: fragmentname} 模板名：：片段名  {dashboard::topbar}
```



​	**th:insert:   将公共的片段整个插入到声明中，外面有层div**

​	**th:replace:  将整个div替换为公共元素**

​	**th:inclide:  将片段的内容放入这个标签里**

![](I:\KSDownload\公共页面.png)

## 3. Spring Data

### 	JDBC

​		自带JDBC template，里面包含了许多sql方法！

###    Druid

​		1. 导入依赖

```xml
<dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
            <version>1.1.22</version>
</dependency>
```

 2. 自带许多配置，若想生效，需要配置MyDruidConfig，并注入进Bean

    ```java
    /* 让自己的配置文件中的属性生效
        @ConfigurationProperties(prefix = "spring.datasource")
        @Bean
    */
    @Configuration
    public class MyDruidConfig {
        @ConfigurationProperties(prefix = "spring.datasource")
        @Bean
        public DataSource druidDataSource(){
             return new DruidDataSource();
        }
    }
    ```

3. 配置druid监控，***<u>必须注入进bean</u>***

   ```java
   //配置Druid的监控
       //1、配置一个管理后台的Servlet
       @Bean    
       public ServletRegistrationBean statViewServlet(){
           ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
   
           Map<String,String> initParams = new HashMap<>();
           initParams.put("loginUsername","admin");
           initParams.put("loginPassword","123456");
           initParams.put("allow","");//默认就是允许所有访问
      //     initParams.put("deny","192.168.15.21");
   
           bean.setInitParameters(initParams);
           return bean;
       }
   
       //2、配置一个web监控的filter
       @Bean
       public FilterRegistrationBean webStatFilter(){
           FilterRegistrationBean bean = new FilterRegistrationBean();
           bean.setFilter(new WebStatFilter());
   
           Map<String,String> initParams = new HashMap<>();
           initParams.put("exclusions","*.js,*.css,/druid/*"); //监控的地址和上面的name，pwd
   
           bean.setInitParameters(initParams);
   
           bean.setUrlPatterns(Arrays.asList("/*"));
   
           return  bean;
       }
   ```




### Mybatis

#### 1).  导入Springboot的整合包

```xml
<!--Mybatis依赖-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>2.1.1</version>
        </dependency>
```

#### 2).  操作--注解版

加上**@Mapper**，这是操作database的mapper

```java
@Mapper
public interface DepartMapper {
    @Select("select * from department where deid=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where deid=#{id}")
    public int deleteDeptById(Integer id);

    @Insert("insert from department(departName) values(#{departName})")
    public int insertDept(Department department);

    @Update("update from department set departName=#{departName} where deid=#{deid}")
    public int updateDept(Department department);
}
```

**当Mapper文件较多时，也可不用@mapper，运用MapperScan批量扫描**

```java
@MapperScan(value="com.wangsw.mapper")
在Boot的主程序class文件上加
```



#### 3).  操作--配置版

```xml
mybatis:
# 指定全局配置文件位置
  config-location: classpath:mybatis/mybatis-config.xml
# 指定sql映射文件位置
  mapper-locations: classpath:mybatis/mapping/*.xml
```



## 4. Spring Security

​	shiro , Spring security  (认证，授权)

