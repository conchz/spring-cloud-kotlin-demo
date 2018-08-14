FROM openjdk:8-jre-alpine
MAINTAINER Zongzhi Bai <dolphineor@gmail.com>

RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories
RUN apk update && apk --no-cache add bash ca-certificates tzdata vim && \
    ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && \
    echo "Asia/Shanghai" > /etc/timezone

COPY ./target/*.jar  /app/authority-service.jar
COPY startup /app

WORKDIR /app

RUN chmod +x startup

CMD ["sh", "startup"]
