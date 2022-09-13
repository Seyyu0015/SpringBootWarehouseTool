## 合租食材计算器（后端部分）

##### 简述：

用于自动计算每人需要为一起采购的食材支付的金额的工具。

##### 技术：

Spring Boot、MyBatis

##### 直接使用后端：
1. 运行“cc.sql”创建数据库

2. 在“user”表录入租客姓名，最多5名

3. 在“food”表录入食材信息

4. 访问以下地址查看计算结果

   ```
   localhost:4006/index
   ```

   ![QQ截图20220910170956](https://user-images.githubusercontent.com/103107612/189477087-a81f65fa-7f5b-42eb-aeb8-6ee120ffb9fc.png)
##### 接口标准
1. #### 新增用户
    * 地址：/addUser
    * 请求类型：Get(http://localhost:4006/addUser)
    * 参数：String usrname
    * 成功返回结果：200,"插入成功：" + username
    * 失败返回结果：500,"目前最多支持五人！"
    * 示例：
   ```
   http://localhost:4006/addUser?username=王五
   ```

2. #### 新增食材
   * 地址：/addFood
   * 请求类型：Get(http://localhost:4006/addFood)
   * 参数：String food,double value
   * 成功返回结果：200,"插入成功：" + food
   * 失败返回结果：500, String.valueOf(e)
   * 示例：
   ```
   http://localhost:4006/addFood?food=黄瓜&value=5.0
   ```
##### 已知问题：
* 出现重名用户时hashMap get返回null；
* 删除用户后id断层；——已修复