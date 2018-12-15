# MG_notice

###可创建一般通知与进度条通知，


####MG_Notification mg_notice_progress = new MG_Notification.Builder(this, MG_NotificationType.PROGRESS_NOTIFICATION)
####                .title("标题_进度条")
####                .message("内容_进度条")
####                .progress_max(100) //最大值
####                .progress_rate(1)  //当前值,初始为默认为0
####                .intentAction("com.example.otherPage") //点击跳转的隐式action
####                .create();
####        mg_notice_progress.show();
