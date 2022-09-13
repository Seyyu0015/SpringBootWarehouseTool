## 合租食材计算器（后端部分）

##### 简述：

用于自动计算每人需要为一起采购的食材支付的金额的工具。

##### 技术：

Spring Boot、MyBatis

##### 直接使用后端：
* #### 查询结果
1. 运行“cc.sql”创建数据库

2. 在“user”表录入租客姓名，最多5名

3. 在“food”表录入食材信息

4. 访问以下地址查看计算结果

   ```
   localhost:4006/index
   ```

   ![QQ截图20220910170956](https://user-images.githubusercontent.com/103107612/189477087-a81f65fa-7f5b-42eb-aeb8-6ee120ffb9fc.png)
* #### 新增用户
   ```
   http://localhost:4006/addUser?username=王五
   ```
##### 已知问题：
* 出现重名用户时hashMap get返回null；
* 删除用户后id断层；——已修复