# basic_template_not_login_back
开发基本模版——后端

# basic_template_not_login_back

## 项目介绍
`basic_template_not_login_back` 是一个包含后端的基础开发模板，旨在为快速搭建Web应用提供完整的技术栈支持。项目后端基于Spring Boot 3 + MyBatis + PostgreSQL构建，集成了国密算法（SM2/SM3/SM4）加解密功能、动态数据源、Redis缓存等核心功能，可直接作为中小型Web项目的开发起点。


## 目录结构
```
basic_template_not_login_back                                                   
├── bin                                                                 # 批处理脚本
│   └── package.bat                                                     # 打包脚本
├── src                                                                 
│   ├── main                                                            
│   │   ├── java/com/gis/basic_template_not_login_back                 
│   │   │   ├── config                                                  # 配置类
│   │   │   │   ├── CryptoProperties.java                               # 加解密配置属性
│   │   │   │   ├── DataSource.java                                     # 数据源注解
│   │   │   │   ├── DataSourceAspect.java                               # 数据源切面
│   │   │   │   ├── DataSourceConfig.java                               # 数据源配置
│   │   │   │   ├── DataSourceContextHolder.java                        # 数据源上下文
│   │   │   │   ├── DynamicDataSource.java                              # 动态数据源
│   │   │   │   └── RedisConfig.java                                    # Redis配置
│   │   │   ├── controller                                              # 控制器层
│   │   │   │   ├── BaseController.java                                 # 基础控制器
│   │   │   │   └── CryptoController.java                               # 加解密控制器
│   │   │   ├── domain                                                  # 领域对象
│   │   │   │   └── ApiResponse.java                                    # 统一响应类
│   │   │   ├── filter                                                  # 过滤器
│   │   │   │   └── DecryptFilter.java                                  # 解密过滤器
│   │   │   ├── mapper                                                  # MyBatis Mapper接口
│   │   │   ├── service/ex                                              # 服务层异常
│   │   │   │   └── ServiceException.java                               # 业务异常类
│   │   │   ├── utils/safety                                            # 安全工具类
│   │   │   │   ├── SM2Utils.java                                       # SM2非对称加密工具
│   │   │   │   ├── SM3Utils.java                                       # SM3摘要算法工具
│   │   │   │   └── SM4Utils.java                                       # SM4对称加密工具
│   │   │   ├── vo                                                      # 视图对象
│   │   │   ├── wrapper                                                 # 请求响应包装
│   │   │   │   ├── DecryptRequestWrapper.java                          # 解密请求包装器
│   │   │   │   ├── EncryptResponseAdvice.java                          # 加密响应通知
│   │   │   │   └── Sm4KeyHolder.java                                   # SM4密钥持有者
│   │   │   └── BasicTemplateNotLoginBackApplication.java               # 启动类
│   │   └── resources                                                   # 资源文件
│   │       ├── application-database-dev.yml                            # 开发环境数据库配置
│   │       ├── application-database-prod.yml                           # 生产环境数据库配置
│   │       ├── application-dev.yml                                     # 开发环境配置
│   │       ├── application-prod.yml                                    # 生产环境配置
│   │       └── application.yml                                         # 主配置文件
├── LICENSE                                                             # 许可证
└── README.md                                                           # 介绍文件
```

### 环境要求
- JDK 17+
- Maven 3.6+
- PostgreSQL 数据库
- Redis 服务器

### 项目克隆
```bash
git clone https://github.com/wzy-warehouse/basic_template_not_login_back.git
cd basic_template_not_login_back
```

### 配置说明
1. **数据库配置**: 修改 `src/main/resources/application-database-dev.yml` 中的数据库连接信息
2. **Redis配置**: 修改 `src/main/resources/application.yml` 中的Redis连接信息
3. **端口配置**: 在 `application.yml` 中修改 `server.port`（默认8080）

### 启动方式

#### 方式一：Maven命令启动
```bash
# 开发环境（默认）
mvn spring-boot:run

# 生产环境
mvn spring-boot:run -Pprod
```

#### 方式二：打包后运行
```bash
# 开发环境打包
mvn clean package

# 生产环境打包
mvn clean package -Pprod

# 运行jar包
java -jar target/basic_template_not_login_back.jar
```

#### 方式三：使用批处理脚本
```bash
# Windows系统
bin\package.bat
```

## 配置文件说明
- `application.yml`: 主配置文件，公共配置
- `application-dev.yml`: 开发环境特定配置
- `application-prod.yml`: 生产环境特定配置
- `application-database-dev.yml`: 开发环境数据库配置
- `application-database-prod.yml`: 生产环境数据库配置

## 许可证
本项目基于 [MIT License](LICENSE) 开源，详情请查看LICENSE文件。
