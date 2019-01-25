// pages/dept.js
const apiUrl = 'http://127.0.0.1:8081/wechat/api/depts'

Page({
  /**
   * 页面初始数据
   */
  data: {
    deptList: []
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    var that = this
    wx.request({
      url: apiUrl,
      method: 'GET',
      data: {
        'pageSize': 10
      },
      success: res => {
        var result = res.data;
        if (result.code !== 200) {
          var toastText = '获取数据失败!' + result.msg
          wx.showToast({
            title: toastText,
            icon: 'none',
            duration: 2000
          })
        } else {
          that.setData({
            deptList: result.data
          })
        }
      }
    })
  },

  /**
   * 添加部门
   */
  addDept: function() {
    wx.navigateTo({
      url: '../edit/edit',
    })
  },

  /**
   * 删除部门
   */
  deleteDept: function(e) {
    var that = this
    wx.showModal({
      title: '提示',
      content: '确定要删除[' + e.target.dataset.name + ']吗?',
      success: sm => {
        if (sm.confirm) {
          wx.request({
            url: apiUrl + '/' + e.target.dataset.id,
            method: 'DELETE',
            data: {},
            success: res => {
              var result = res.data;
              var toastText = '删除成功'
              if (result.code !== 200) {
                toastText = '删除失败'
              } else {
                that.data.deptList.splice(e.target.dataset.index, 1)
                that.setData({
                  deptList: that.data.deptList
                })
              }
              wx.showToast({
                title: toastText,
                icon: 'none',
                duration: 2000,
                success: () => {
                  that.onShow
                }
              })
            }
          })
        }
      }
    })
  }
})