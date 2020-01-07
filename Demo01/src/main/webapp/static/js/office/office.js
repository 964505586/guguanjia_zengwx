let vm = new Vue({
    el:'#main-container',
    data: function{
        return {
            pageInfo: {
                pageNum:'1',
                pageSize:'4',
                list:'',
                type:'',
            },
            settings:{
                data:{
                    simpleData:{
                        enable:true,
                        pIdKey:'parentId',  // 根据node节点中的parentId属性来作为pId属性值
                    }
                },
                callback:{
                    onClick:this.onClick,
                    beforeEditName:this.beforeEditName,  // 在触发修改前调用  如果返回false则会阻止原ztree默认的编辑节点名的行为
                    beforeRemove:this.beforeRemove  // 触发删除前调用
                },
                edit:{
                    enable: true,   // 设置开启支持修改nodes
                    showRemoveBtn:true, // 开启删除按钮   默认true
                    removeTitle:'删除公司',
                    renameTitle:'修改公司',
                },
                view:{
                    addHoverDom:this.addHoverDom,
                    removeHoverDom:this.removeHoverDom,
                }

            },
            // params: {
            //     pageNum:'',
            //     pageSize:'',
            //     type:''
            // },



        }
    },
    methods:{
        selectAll:function (pageNum, pageSize) {
            this.pageInfo.pegeNum = pageNum;
            this.pageInfo.pageSize = pageSize;
            axios({
                url:'manager/sysOffice/selectPage',
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
                url:'manager/sysOffice/toUpdate',
                params:{id:id},
            }).then(response => {
                layer.obj = response.data;  // 返回数据，绑定到layer上，传递给子窗口
                let index = layer.open({
                    type:'2',
                    title:'区域修改',
                    content:'manager/sysOffice/toUpdatePage',
                    area:['80%', '80%'],
                    end:() => {     // 将then函数中的this传递到end的回调函数中
                        // 刷新页面数据    1.直接查询selectAll实现    2.获取layer.appVersion更新当前pageInfo的该数据
                        this.selectAll(this.pageInfo.pegeNum, this.pageInfo.pageSize);
                    }
                })
            }).catch(error => {
                layer.msg(error)
            })
        },
        initTree:function () {  // 初始化ztree
            // 获取nodes
            axios({
                url:'manager/sysOffice/list'
            }).then(response => {
                this.nodes = response.data;
                this.nodes[this.nodes.length] = {
                    "id": 0,
                    "name": "所有机构"
                };
                $.fn.zTree.init($("#treeMenu"), this.settings, this.nodes);
            }).catch(error => {
                layer.msg(error)
            })
        },
        onClick: function (event, treeId, treeNode) {
             this.pageInfo.aid = treeNode.id;
             this.selectAll(this.pageInfo.pegeNum, this.pageInfo.pageSize)
        },
        beforeEditName: function (treeId, treeNode) {
            this.toUpdate(treeNode.id);
            return false;
        },


    },
    created:function () {
        this.selectAll(this.pageInfo.pegeNum, this.pageInfo.pageSize);
    }
});