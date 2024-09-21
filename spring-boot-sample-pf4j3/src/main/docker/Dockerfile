FROM 10.71.33.155/zfsoft/openjdk:1.8
ENV LC_ALL en_US.UTF-8
ENV TZ 'Asia/Shanghai'

#创建日志目录和临时目录
RUN mkdir -p /u01/boot/{logs,tmp}

#拷贝本地文件到指定目录
ADD /lib $BOOT/lib
ADD /conf $BOOT/conf

#ENV JAVA_HOME /opt/jdk
#ENV PATH $PATH:$JAVA_HOME/bin

EXPOSE 8081
CMD ["java", "-classpath", "/u01/boot/conf:/u01/boot/lib/zftal-boot-datay.jar", "org.springframework.boot.loader.JarLauncher"]
