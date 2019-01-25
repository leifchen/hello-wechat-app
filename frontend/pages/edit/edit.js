// pages/edit.js
const apiUrl = 'http://127.0.0.1:8081/wechat/api/depts/'

Page({
  data: {
    deptId: undefined,
    deptName: '',
    seq: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    if (options.deptId === undefined) {
      return
    }

    var that = this
    this.setData({
      deptId: options.deptId
    })
    wx.request({
      url: apiUrl + options.deptId,
      method: 'GET',
      data: {},
      success: res => {
        var result = res.data
        if (result.code !== 200) {
          var toastText = '获取数据失败!' + result.msg
          wx.showToast({
            title: toastText,
            icon: 'none',
            duration: 2000
          })
        } else {
          that.setData({
            deptName: result.data.name,
            seq: result.data.seq
          })
        }
      }
    })
  },

  /**
   * 部门信息表单提交
   */
  formSubmit: function(e) {
    var that = this
    var formData = e.detail.value
    var url = apiUrl
    var method = 'POST'
    if (that.data.deptId !== undefined) {
      formData.id = that.data.deptId
      url += that.data.deptId
      method = 'PUT'
    }
    wx.request({
      url: url,
      method: method,
      header: {
        'Content-Type': 'application/json'
      },
      data: JSON.stringify(formData),
      success: res => {
        var result = res.data
        var flag = true
        var toastText = '操作成功'
        if (result.code !== 200) {
          flag = false
          toastText = '操作失败\n' + result.message
        }
        wx.showToast({
          title: toastText,
          icon: 'none',
          duration: 2000,
          success: () => {
            if (flag) {
              setTimeout(() => {
                wx.navigateTo({
                  url: '../dept/dept',
                })
              }, 1000);
            }
          }
        })
      }
    })
  }
})