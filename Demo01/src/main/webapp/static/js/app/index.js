let vm = new Vue({
    el:'#main-container',
    data:{
        pageInfo:'',
        app:{   // 新增页面使用的对象
            forceUpdate:1   // 模拟默认值
        }
    },
    methods:{
        // 分页查询
        selectAll:function (pageNum, pageSize) {
            // 发送ajax请求查询分页数据，并返回给userList接收，通过vue接管遍历显示数据
            axios({
                url:'manager/app/toList',
                params:{pageNum:pageNum, pageSize:pageSize}
            // 箭头函数可以自动将上下文的this传递到当前函数中
            }).then(responce => {
                console.log(responce.data);
                this.pageInfo = responce.data;
            }).catch(function (error) {
                layer.msg(error)
            })
        },

        toUpdate:function (id) {
            axios({
                url:'manager/app/toUpdate',
                params:{id:id}
            }).then(responce =>{
                layer.obj = responce.data;
                console.log(layer.obj);
                layer.open({
                    type:2,
                    content:'manager/app/toUpdatePage',
                    area:['80%', '80%'],
                    end:() => {
                        this.selectAll(1, 5)
                    }
                })
            }).catch(error =>{

            })
        },

        insert:function () {
            // 提交信息到后端
            axios({
                url:'manager/app/insert',
                method:'post',
                data:this.app
            }).then(response => {
                // 返回结果如果是成功则显示提示、切换到列表页、清空新增表单信息
                if (response.data.success) {
                    this.selectAll(1, 3);
                    this.clear();
                    $("#home").addClass("active");
                    $("#profile").removeClass("active");
                    // 切换选项卡的激活状态
                    $("#myTab").find("li[class='active']").attr("class", '').siblings().attr("class", 'active');
                }
                layer.msg(response.data.msg);
            }).catch(error => {

            })
        },

        clear:function () {
            this.app = ''
        }
    },
    created:function () {
        this.selectAll(1, 3);
    }
});