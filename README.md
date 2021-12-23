# Spring Security OIDC 后端服务

- [集成介绍](#集成介绍)
- [项目搭建](#项目搭建)
- [配置文件](#配置文件)
- [运行测试](#运行测试)


### 集成介绍

> Authing OIDC 允许客户端根据授权服务器执行的身份验证来验证最终用户的身份，并以可互操作和类似 REST 的方式获取有关最终用户的基本配置文件信息。
> 允许所有类型的客户端（包括基于 Web 的客户端、移动客户端和 JavaScript 客户端）请求和接收有关经过身份验证的会话和最终用户的信息。规范套件是可扩展的，允许参与者在对他们有意义的时候使用可选功能，例如身份数据加密、OpenID 提供者的发现和会话管理。
> 允许应用程序和站点开发人员对用户进行身份验证，而无需承担存储和管理密码的责任，因为互联网上充斥着大量试图为了自己的利益而破坏用户账户的人。
>它简单、可靠、安全，并且可以让他们摆脱存储和管理他人密码的困难和危险工作。还有一个额外的好处是，它还使用户的注册过程更轻松，从而减少了用户跳出率。
>利用 Atuthing OIDC 服务作为用户认证中心的统一入口，使所有需要登录的地方都交给 OIDC 服务来做。简单来说就是把需要进行用户认证的部分都剥离出来交给 OIDC 认证中心来完成。


### 项目搭建

方式一：使用 Spring Initializr 快速构建项目

打开 IDEA，点击 New Project 创建一个新项目，选择 Spring Initializr 创建一个 Spring Boot 项目，输入项目的 Group 以及 Artifact 信息

添加 Spring Web, Spring Security 依赖

另外，集成过程中需要在 pom.xml 中需要添加一些其他的依赖包，如下：

```yaml
    <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    </dependency>
    <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-client</artifactId>
    </dependency>
    <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-jose</artifactId>
    </dependency>
```

方式二：maven 工具构建项目

打开 IDEA，点击 New Project 创建一个新项目，选择 maven 创建一个 maven 项目，然后点击 Next，填写项目名称，最后 Finish 即可

接下来在 pom.xml 中添加父工程依赖和集成过程中需要的其它依赖包

```yaml
    <parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.5</version>
    <relativePath/> <!-- lookup parent from repository -->
    </parent>

    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
    </dependency>
    <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-test</artifactId>
    <scope>test</scope>
    </dependency>
    <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    </dependency>
    <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-client</artifactId>
    </dependency>
    <dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-oauth2-jose</artifactId>
    </dependency>
```

### 配置文件

找到 src/main/resources/application.properties，将其重命名为 application.yml，并添加如下内容：

```yaml
  spring:
  security:
    oauth2:
      client:
        registration:
          authing:
            client-id: {替换为你的App ID如：App Secret5e72d72e3798fb03e1d57b13}
            client-secret: {替换为你的App Secret如：931f19ce2161e5560c072f586c706ee6}
            redirect-uri: '{替换为登录的回调地址}'
            client-authentication-method: post
            authorization-grant-type: authorization_code
            scope:
              - openid
              - profile
        provider:
          authing:
            issuer-uri: https://{替换为你的Issuer，如：authing-net-sdk-demo}.authing.cn/oidc
            user-name-attribute: preferred_username
```

### 运行测试

一切准备就绪了，现在启动项目并访问 http://localhost:8080，即可看到 Authing 登录窗口

Spring Security 默认会保护首页，在访问首页时会进行认证，未认证的访问请求会跳转到 /login。 注册并登录后，会跳转回首页，此时可以看到页面上的欢迎语显示了当前登录用户的用户名

