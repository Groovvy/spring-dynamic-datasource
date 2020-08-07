# spring-dynamic-datasource

#### 项目介绍
spring boot +mybatis 动态切换、添加数据源demo

#### 软件架构
- AbstractRoutingDataSource 作为 DataSource 路由
- AOP 切面拦截 service 参数，动态切换、创建数据源
- 策略模式根据数据源动态选择service

