Usage:

克隆到本地后修改以下文件：

1. src/main/resources/application.yml中的数据库url、用户名、密码等。因为还没有使用云数据库，等有了云数据库就不用改了。
2. 每次build前端页面后，把build出来的文件名改为page后放入并覆盖src/resources下的page文件夹。

访问路径是：http://localhost:8080/page/index.html