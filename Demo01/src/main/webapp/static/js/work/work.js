let a = new Vue({
    el:'#main-container',
    data:function() {
        return {
            setting: {
                data: {
                    simpleData: {
                        enable: true,   // 开启简单数据模式支持
                        pIdKey: "parentId"
                    }
                },
                callback: {
                    // 如果设置this.xxx  methods对象还没有，不能绑定上
                    onClick: this.clickNode
                },
                view: {
                    // 每次对元素节点进行创建或修改的时候都会自动调用该样式设置规则
                    fontCss:this.fontCss
                }
            },
            name:'',
            pageInfo:'',
            params:{
                status:'',
                type:'',
                officeId:''
            },
            officeName:'全部'
        }
    },
    methods: {
        // 分页查询
        selectAll:function (pageNum, pageSize) {
            this.params.pageNum = pageNum;
            this.params.pageSize = pageSize;
            // 发送ajax请求查询分页数据，并返回给userList接收，通过vue接管遍历显示数据
            axios({
                url:'manager/admin/work/toList',
                data:this.params,
                method:'post'
                // 箭头函数可以自动将上下文的this传递到当前函数中
            }).then(responce => {
                this.pageInfo = responce.data
            }).catch(error => {
                layer.msg(error);
            })
        },

        clear:function() {
            this.params = {status:""};
            this.officeName = '全部'
        },
        select:function() {
            // 清空vue的条件对象   调用selectAll()
            this.clear();
            this.selectAll(1, 5);
        },
        selectOne:function(id) {
            axios({
                url:'manager/admin/work/selectByOid',
                params:{oid:id}
            }).then(response => {   // 箭头函数可以自动将上下文的this传递到当前函数中
                layer.obj = response.data;
                console.log(layer.obj);
                layer.open({
                    type: 2,
                    content:'manager/admin/work/toDetail',
                    area:['80%', '80%']
                })
            }).catch(error => {
                layer.msg(error)
            })
        },
        // 初始化菜单树
        initTree:function () {
            // 菜单树支持两种结果的节点数组：
            // 简单数组格式 :[{"id":1,name:'个人中心',"pId":0},{"id":2,name:'单位管理',"pId":0},{"id":3,name:'业务管理',"pId":0},{"id":4,name:'我的资料',"pId":1},{"id":5,name:'单位信息',"pId":2},{"id":6,name:'单位账号',"pId":2},{"id":7,name:'电子台账',"pId":2}]
            // let  nodes= [{"id": 1, name: '个人中心', "pId": 0, open: true},
            //     {"id": 2, name: '单位管理', "pId": 0},
            //     {"id": 3, name: '业务管理', "pId": 0},
            //     {"id": 4, name: '我的资料', "pId": 1},
            //     {"id": 5, name: '单位信息', "pId": 2},
            //     {"id": 6, name: '单位账号', "pId": 2},
            //     {"id": 7, name: '电子台账', "pId": 2}]

            axios({
                url:'manager/sysOffice/list',
            }).then(responce => {
                let nodes = responce.data;
                nodes[nodes.length] = {id:0, name:'所有机构', open:true};
                // ztree对象的初始化函数 :  init(obj,zSetting,zNodes)
                // obj:用于挂载树元素的html的dom对象
                // zSetting:用于配置树的配置对象
                // zNodes：用于显示的节点集合
                $.fn.zTree.init($("#pullDownTreeone"), this.setting, nodes)

            }).catch(error => {
                layer.msg(error)
            })
        },
        clickNode:function (event, treeId, treeNode) {
            this.officeName = treeNode.name;
            this.params.officeId = treeNode.id;
        },
        search:function () {
            /**
             * 1.获取树对象
             * 2.进行模糊查询匹配到所有的匹配节点数组
             */
            let treeObj = $.fn.zTree.getZTreeObj("pullDownTreeone");
            // key:需要匹配属性名   value:需要匹配的值   parentNode 父节点
            let nodes = treeObj.getNodesByParamFuzzy("name", this.name, null);
            /**
             * 3.获取所有节点数据，转换成简单数组模式
             * 4.遍历所有节点，给所有找到的节点设置一个高亮标记属性  清除前需要修改旧查询到的节点标记为false
             * 5.更新树对象
             */
            let treeNodes = treeObj.transformToArray(treeObj.getNodes());   // 转换成简单数据格式
            for (let i = 0; i < treeNodes.length; i++) {
                treeNodes[i].highLight = false;
                treeNodes.updateNode(treeNodes[i])
            }

            for (let i = 0; i < treeNodes.length; i++) {
                for (let j = 0; j < nodes.length; j++) {
                    if (treeNodes[i].name = nodes[j].name) {       // 找到需要设置高亮的节点
                        treeNodes[i].highLight = true;
                        treeNodes.updateNode(treeNodes[i])   //在树对象上更新节点
                        break;
                    }
                }
            }
        },
        fontCss: function (treeId, treeNode) {
            // 如果treeNode是 单位管理，则高亮
            // return treeNode.name=="单位管理"?{color:"red"}:'';
            // 如果是highLight标记为true的高亮
            return treeNode.highLight ? {color:"red"} : {color:"black"};
        }
    },
    created:function () {
        this.selectAll(1, 5)
    },
    mounted:function () {
        this.initTree();
    }
});