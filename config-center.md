

#### 功能比对

> Apollo使用1.2.0 release版本，Nacos使用0.5版本

<table>
  <thead>
    <tr>
      <th>功能点</th>
      <th>Apollo</th>
      <th>Nacos</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>配置实时推送</td>
      <td>支持(HTTP长轮询1s内)</td>
      <td>支持(HTTP长轮询1s内)</td>
    </tr>
    <tr>
      <td>版本管理</td>
      <td>支持</td>
      <td>支持</td>
    </tr>
    <tr>
      <td>配置回滚</td>
      <td>支持</td>
      <td>支持</td>
    </tr>
    <tr>
      <td>灰度发布</td>
      <td>支持</td>
      <td>支持&#xff08;不太好用&#xff09;</td>
    </tr>
    <tr>
      <td>权限管理</td>
      <td>支持</td>
      <td>支持</td>
    </tr>
    <tr>
      <td>审计</td>
      <td>支持</td>
      <td>不支持</td>
    </tr>
    <tr>
      <td>多集群</td>
      <td>支持</td>
      <td>支持</td>
    </tr>
    <tr>
      <td>多环境</td>
      <td>支持</td>
      <td>支持</td>
    </tr>
    <tr>
      <td>client本地缓存</td>
      <td>支持</td>
      <td>支持</td>
    </tr>
    <tr>
      <td>监听查询</td>
      <td>支持</td>
      <td>支持</td>
    </tr>
    <tr>
      <td>运维成本</td>
      <td>中等</td>
      <td>较低</td>
    </tr>
    <tr>
      <td>通信协议</td>
      <td>HTTP</td>
      <td>HTTP</td>
    </tr>
    <tr>
      <td>配置格式校验</td>
      <td>支持</td>
      <td>支持</td>
    </tr>
    <tr>
      <td>单机读(QPS)</td>
      <td>9000</td>
      <td>15000</td>
    </tr>
    <tr>
      <td>单击写(QPS)</td>
      <td>1100</td>
      <td>1800</td>
    </tr>
    <tr>
      <td>3节点读(QPS)</td>
      <td>27000</td>
      <td>45000</td>
    </tr>
    <tr>
      <td>3节点写(QPS)</td>
      <td>3300</td>
      <td>5600</td>
    </tr>
  </tbody>
</table>



#### 整体情况

- Apollo容器化较困难，Nacos有官网的镜像可以直接部署。
- Nacos的部署结构比较简单，运维成本较低。Apollo部署组件较多，运维成本比Nacos高。

- Apollo相对于Nacos在配置管理做的更加全面，不过配置也麻烦一些。
- Nacos使用起来相对比较简洁，在对性能要求比较高的大规模场景更适合。
- Nacos除了提供配置中心的功能，还提供了动态服务发现、服务共享与管理的功能，降低了服务化改造过程中的难度。



##### 参考文档

- https://cloud.tencent.com/developer/article/1427223