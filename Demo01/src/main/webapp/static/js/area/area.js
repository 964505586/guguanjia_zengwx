let vm = new Vue({
    el: '#main-container',
    data: function () {
        return {
            pageInfo: {
                pageNum: 1,
                pageSize: 4,
                areaName: '',
            },
            settings: {
                data: {
                    simpleData: {
                        enable: true,
                        pIdKey: 'parentId'
                    }
                },
                callback: {
                    onClick: this.onClick,
                    beforeEditName: this.beforeEditName
                },
                edit: {
                    enable: true
                },
                view: {  // 自定义节点上的元素
                    addHoverDom: this.addHoverDom,
                    removeHoverDom: this.removeHoverDom
                }
            },
            nodes: [],
            treeObj: {},
            params:{
                pageNum: 1,
                pageSize: 4,
                aid:0
            },
        }
    },
    methods: {
        selectAll: function () {

        },
        selectPage: function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            axios({
                url: 'manager/area/selectPage',
                method: 'post',
                data: this.params,
            }).then(response => {
                this.pageInfo = response.data;
            }).catch(error => {
                layer.msg(error)
            })
        },
        download: function () {
            // 下载区域信息数据
            location.href = 'manager/area/download';
        },
        upload: function (event) {
            // 获取事件源   上传input
            let formDate = new FormData();  // 构建表单对象
            // 绑定文件到upFile  upFile需要与后台接收方法参数MultipartFile的名字对应
            formDate.append("upFile", event.target.files[0]);
            // 提交信息
            axios({
                url: 'manager/area/upload',
                method: 'post',
                data: formDate,
                headers: {   // 设置请求头
                    'Content-Type': 'Multipart/form-data'  // 设置请求题格式
                }
            }).then(response => {
                layer.msg(response.data.msg)
            }).catch(error => {
                layer.msg(error)
            })
        },
        toUpdate: function (id) {
            axios({
                url: 'manager/area/toUpdate',
                params: {id: id}
            }).then(response => {
                layer.obj = response.data;
                let op = layer.open({
                    type: 2,
                    title: '区域修改 ',
                    content: 'manager/area/toUpdatePage',
                    area: ['80%', '80%'],
                    end: () => {     // 将then函数中的this传递到end的回调函数中
                        this.pageInfo.aid = 0;
                        this.selectPage(this.pageInfo.pageNum, this.pageInfo.pageSize);
                        this.initTree();
                    }
                })
            }).catch(error => {
                layer.msg(error)
            })
        },
        initTree: function () {  // 初始化ztree
            axios({
                url: 'manager/area/selectAll',
            }).then(response => {
                this.nodes = response.data;
                $.fn.zTree.init($("#treeMenu"), this.settings, this.nodes);
            }).catch(error => {
                layer.msg(error)
            })
        },
        onClick: function (event, treeId, treeNode) {
            this.params.aid = treeNode.id;
            this.selectPage(this.params.pageNum, this.params.pageSize)
        },
        // 结合开启修改节点按钮、点击修改按钮事件回调处理更新节点弹框
        beforeEditName: function (treeId, treeNode) {
            this.toUpdate(treeNode.id);
            return false;   // 阻止进入修改节点状态
        },
        addHoverDom: function (treeId, treeNode) {
            let aObj = $("#" + treeNode.tId + "_a");
            if ($("#treeMenu_" + treeNode.id + "_add").length > 0) return;
            // <span class="button edit" id="treeMenu_3_edit" title="rename" treenode_edit="" style=""></span>
            let editStr = `<span class="button add" id="treeMenu_${treeNode.id}_add" title="add" style=""></span>`;
            aObj.append(editStr);
            let span = $("#treeMenu_" + treeNode.id + "_add");
            if (span) span.bind("click", function () {
                alert("diy Button for " + treeNode.name);
            })
        },
        removeHoverDom: function (treeId, treeNode) {
            $("#treeMenu_" + treeNode.id + "_add").unbind().remove();
        },
    },
    created: function () {
        this.selectPage(this.params.pageNum, this.params.pageSize);
    },
    mounted: function () {
        this.initTree();
    }
});