let a = new Vue({
    el:'#main-container',
    data:{
        pageInfo:'',
        params: {
            type:'',
            check:'',
            start:'',
            end:'',
        }
    },
    methods:{
        // 分页查询
        selectAll:function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            // 发送ajax请求查询分页数据，并返回给userList接收，通过vue接管遍历显示数据
            axios({
                url:'manager/qualification/toList',
                data: this.params,
                method:'post'
                // params:{pageNum:pageNum, pageSize:pageSize} // get方法
                // 箭头函数可以自动将上下文的this传递到当前函数中
            }).then(responce => {
                this.pageInfo = responce.data;
            }).catch(function (error) {
                layer.msg(error)
            })
        },
        toUpdate:function (id) {
            axios({
                url:'manager/qualification/toUpdate',
                params:{id:id}
                // 箭头函数可以自动将上下文的this传递到当前函数中
            }).then(responce =>{
                layer.obj = responce.data.qualification;
                layer.oid = responce.data.oid;
                layer.open({
                    type:2,
                    content:'manager/qualification/toUpdatePage',
                    area:['80%', '80%'],
                    end:() => {
                        this.selectAll(1, 3)
                    }
                })
            }).catch(function (error) {
                layer.msg(error);
            })
        },
        insert:function () {
        },
        clear:function () {
        },
    },
    created:function () {
        this.selectAll(1, 3);
    }
});