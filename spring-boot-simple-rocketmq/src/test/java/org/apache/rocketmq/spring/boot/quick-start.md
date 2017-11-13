#http://rocketmq.apache.org/docs/quick-start/

一、系统要求

	The following softwares are assumed installed:

    64bit OS, Linux/Unix/Mac is recommended;
    64bit JDK 1.8+;
    Maven 3.2.x
    Git

二、服务安装：

1、安装包模式

https://www.apache.org/dyn/closer.cgi?path=incubator/rocketmq/
下载最新版本
参照 http://blog.csdn.net/loongshawn/article/details/51086876 安装


2、源码没模式

Clone & Build

  > git clone -b release-4.1.0-incubating https://github.com/apache/incubator-rocketmq.git
  > cd incubator-rocketmq
  > mvn -Prelease-all -DskipTests clean install -U
  > cd distribution/target/apache-rocketmq

三、服务启动、关闭

Start Name Server

  > nohup sh mqnamesrv 1>$ROCKETMQ_HOME/logs/ng.log 2>$ROCKETMQ_HOME/logs/ng-err.log &

  > nohup sh bin/mqnamesrv &
  > tail -f ~/logs/rocketmqlogs/namesrv.log
  The Name Server boot success...

Start Broker

  > nohup sh bin/mqbroker -n localhost:9876 &
  > tail -f ~/logs/rocketmqlogs/broker.log 
  The broker[%s, 172.30.30.233:10911] boot success...


Send & Receive Messages

Before sending/receiving messages, we need to tell clients the location of name servers. RocketMQ provides multiple ways to achieve this. For simplicity, we use environment variable NAMESRV_ADDR

 > export NAMESRV_ADDR=localhost:9876
 > sh bin/tools.sh org.apache.rocketmq.example.quickstart.Producer
 SendResult [sendStatus=SEND_OK, msgId= ...

 > sh bin/tools.sh org.apache.rocketmq.example.quickstart.Consumer
 ConsumeMessageThread_%d Receive New Messages: [MessageExt...

Shutdown Servers

> sh bin/mqshutdown broker
The mqbroker(36695) is running...
Send shutdown request to mqbroker(36695) OK

> sh bin/mqshutdown namesrv
The mqnamesrv(36664) is running...
Send shutdown request to mqnamesrv(36664) OK

