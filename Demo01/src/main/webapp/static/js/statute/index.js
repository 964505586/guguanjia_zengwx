let vm = new Vue({
    el:'#main-container',
    data:{
        pageInfo: {
            pageNum:'1',
            pageSize:'4',
            list:'',
            type:'',
        },
        // params: {
        //     pageNum:'',
        //     pageSize:'',
        //     type:''
        // },
        statute:{
            description:''
        }
    },
    methods:{
        selectAll:function (pageNum, pageSize) {
             this.pageInfo.pegeNum = pageNum;
             this.pageInfo.pageSize = pageSize;
             axios({
                 url:'manager/statute/toList',
                 method:'post',
                 data:this.pageInfo
             }).then(response => {
                 this.pageInfo = response.date;
             }).catch(error => {
                 layer.msg(error);
             })
        },
        toUpdate:function (id) {
            axios({
                url:'manager/statute/toUpdate',
                params:{id:id},
            }).then(response => {
                layer.obj = response.data;  // 返回数据，绑定到layer上，传递给子窗口
                let index = layer.open({
                    type:'2',
                    title:'修改法规',
                    content:'manager/statute/toUpdatePage',
                    area:['80%', '80%'],
                    end:() => {     // 将then函数中的this传递到end的回调函数中
                        // 刷新页面数据    1.直接查询selectAll实现    2.获取layer.appVersion更新当前pageInfo的该数据
                        this.selectAll(this.pageInfo.pegeNum, this.pageInfo.pageSize);
                    }
                })
            }).catch(error => {
                layer.msg(error)
            })
        }
    },
    created:function () {
        this.selectAll(this.pageInfo.pegeNum, this.pageInfo.pageSize);
    }
});