# gralde入门

### gradle的功能
gradle是一个项目构建工具，类似于maven，核心使用java开发，使用Groovy语言(一种基于jvm的脚本语言，开发简便)构建，
使用DSL(领域特定语言)简化配置

### gradle下载与配置
* 去官网下载gradle的bin文件
* 解压
* 配置环境变量GRADLE_HOME=gradle的解压文件位置/bin

### gradle的hello world
* 新建文件build.gradle，内容如下
```
task hello {
    print 'hello world'
}
```
* 在当前目录执行gradle hello，运行结果如下
```
Starting a Gradle Daemon (subsequent builds will be faster)

> Configure project :
hello world
BUILD SUCCESSFUL in 5s
``` 

### 关于Gradle Daemon
gradle deamon为了解决一次构建使用时间过长的问题，在maven中，构建一次需要开启一次虚拟机，当构建完成后，将虚拟机关闭。
这样每次构建都需要比较长的时间，而且，本次构建和上次构建可能有许多相同之处，需要缓存。所以gradle daemon使用了以下的架构
![gradle daemon](./gradle daemon.png)


### gradle wrapper
一般gradle不会直接使用，而是推荐使用gradle wrapper的方式，所谓gradle wrapper就是在一台没有安装gradle的机器
上，通过事先使用gradle wrapper的方式将项目与gradle绑定起来，通过gradle wrapper部署在这台没有安装gradle的服
务器上，gradle wrapper实际上是一个脚本，解决了环境和版本的不确定性带来的对构建结果的影响，不必要事先安装gradle
...

### groovy的闭包

### gradle的生命周期



### gradle的插件开发


