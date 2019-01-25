//index.js
//获取应用实例
const app = getApp()

Page({
  //事件处理函数
  bindViewTap: function() {
    wx.navigateTo({
      url: '../dept/dept'
    })
  }
})
