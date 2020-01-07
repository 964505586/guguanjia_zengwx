let vm = new Vue({
    el:'#main-container',
    data:{
        qualification:'',
        oid:'',
    },
    methods:{
        doUpdate:function () {
        }
    },
    //在vue对象创建的时候获取父窗口layer对象绑定的数据，放入当前app对象中
    created:function () {
        this.qualification = parent.layer.obj;
        this.oid = parent.layer.oid;
    }
});