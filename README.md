# MG_notice

### 可创建一般通知与进度条通知，并实现基于bundle的参数传递
### Create general notification and progress bar notifications and implement bundle-based parameter passing


##### 调用示例如下
 示例：<br>
   MG_Notification mg_notification = new MG_Notification.Builder(this, MG_NotificationType.NORMAL_NOTIFICATION)<br>
                .title("标题 title")<br>
                .message("内容  content")<br>
                .intentAction("com.example.otherPage")  //隐式跳转action<br>
                .intentBundle(bundle) //跳转传值<br>
                .create();<br>
     mg_notification.show();
 

##### 若需要对通知样式修改，可以配置notification_view_custom.xml文件
##### If you need to modify the notification style, you can configure the notification_view_custom.xml file.




