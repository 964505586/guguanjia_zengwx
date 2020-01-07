let vm = new Vue({
    el:'#main-container',
    data:{
        area:{},
    },


  methods:{
      selectIcon:function () {
          layer.open({
              type: 2,
              title: '区域修改 ',
              content: 'manager/area/awesome',
              area: ['80%', '80%'],
              end:() => {
                  this.area.icon = layer.icon;
              }
          })
      },
      update:function () {
          this.area.parentId = 6;   // 模拟修改了父id
          // 根据parentId从后台获取id为6的sysArea的parentIds
          this.area.parentIds = "0,1,6,";
          axios({
              url:'manager/area/doUpdate',
              method:'post',
              data:this.area
          }).then(response => {
              layer.msg(response.data.msg)
              if (response.data.success) {
                  // 更新成功，关闭当前窗口
                  let index = parent.layer.getFrameIndex(window.name);  // 获取子窗口索引值
                  parent.layer.close(index);
                  parent.layer.msg(response.data.msg);
                  return;
              }
          }).catch(error => {
              layer.msg(error)
          })
      },
  },
  created:function () {
        this.area = parent.layer.obj;

  },

});