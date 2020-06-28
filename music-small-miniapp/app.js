//app.js
App({
  globalData: {
    customBar: null,
    custom: null,
    statusBar: null,
    userInfo: null,
    openid: null,
    baseUrl: 'http://localhost:8888/silin' //本地调试
  },
  onLaunch: function () {
    //云开发初始化
    wx.cloud.init({
      env: 'taxp1',
      traceUser: true,
    })
    this.db = wx.cloud.database(); //云数据库初始化
    this.getOpenid();
    wx.setNavigationBarTitle({
      title: 'mySkey音乐'
    })
    this.initAudio()
  },
  onShow(){
    if(this.audioDom.paused){
      this.player.status = 2;
    }
  },
  onHide: function () {
    
  },
  player: {
    list: [],
    current_music: 0,
    mode: 0,            // 0 单曲    1 顺序   2 随机
    status: 0,          // 0 未播放  1 播放   2 暂停中  3 已结束
    playbackRate: 1,    // 倍速
    a_resource: '',
    i_resource: '',
    global_show: 0,     // 全局显示  0 不显示 1 显示
    playing: {
      id: '',
      name: '',
      cover: '',
      url: '',
      singer: {
        avatar: '',
        name: ''
      },
      currentTime: 0,
      duration: 0,
      timeArr: [],
      lrcArr: []
    }
  },
  audioDom: wx.getBackgroundAudioManager(),
  initAudio(){
    this.audioDom.title = '起风了';
    this.audioDom.epname = '555';
    this.audioDom.singer = 'mySkey';
    this.audioDom.coverImgUrl = 'http://img.22family.com/mySKey/favicon.ico';
    this.audioDom.paused = true;
    this.audioDom.stop = true;
  },

  // 获取用户openid
  getOpenid: function () {
    var app = this;
    var openidStor = wx.getStorageSync('openid');
    if (openidStor) {
      console.log('本地获取openid:' + openidStor);
      app.globalData.openid = openidStor;
      app._getUserInfo();
    } else {
      wx.cloud.callFunction({
        name: 'getOpenid',
        success(res) {
          console.log('云函数获取成功', res)
          var openid = res.result.openid;
          wx.setStorageSync('openid', openid)
          app.globalData.openid = openid;
          app._getUserInfo();
        },
        fail(res) {
          console.log('云函数获取失败', res)
        }
      })
    }
  },
  // 获取用户信息，如果用户没有授权，就获取不到
  _getUserInfo: function () {
    var app = this;
    wx.getUserInfo({ //从网络获取最新用户信息
      success: function (res) {
        var user = res.userInfo;
        user.openid = app.globalData.openid;
        app.globalData.userInfo = user;
        console.log('请求获取user成功')
        console.log(user)
        app._saveUserInfo(user);
        // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
        // 所以此处加入 callback 以防止这种情况
        if (app.userInfoReadyCallback) {
          app.userInfoReadyCallback(res)
        }
      },
      fail: function (res) { //请求网络失败时，再读本地数据
        console.log('请求获取user失败')
        var userStor = wx.getStorageSync('user');
        if (userStor) {
          console.log('本地获取user')
          app.globalData.userInfo = userStor;
        }
      }
    })
  },

  _checkOpenid() {
    let openid = this.globalData.openid;
    console.log('_checkOpenid：' + openid);
    if (!openid) {
      app.getOpenid();
      wx.showLoading({
        title: 'openid不能为空，请重新登录',
      })
      return null;
    } else {
      return openid;
    }
  },
  // 保存userinfo
  _saveUserInfo: function (user) {
    this.globalData.userInfo = user;
    this._getMyUserInfo();
  },
  //获取自己后台的user信息
  _getMyUserInfo(user) {
    let app = this
    let userInfo = app.globalData.userInfo;
    wx.request({
      url: app.globalData.baseUrl + '/user/getUserInfo',
      data: {
        openid: app.globalData.openid
      },
      success: function (res) {
        if (res && res.data && res.data.data) {
          userInfo.realname = res.data.data.username;
          userInfo.realphone = res.data.data.phone;
          // userInfo.realzhuohao = res.data.data.zhuohao;
          // userInfo.realrenshu = res.data.data.renshu;
          app.globalData.userInfo = userInfo;
        }else{
          wx.request({
            url: app.globalData.baseUrl + '/user/getUserInfo',
            data: {
              openid: app.globalData.openid
            },
            success: function (res) {
              console.log("保存成功", res);
            }
          })
        }
      }
    })
    console.log("我的后台", userInfo)
    //缓存到sd卡里
    wx.setStorageSync('user', userInfo);
  }
})