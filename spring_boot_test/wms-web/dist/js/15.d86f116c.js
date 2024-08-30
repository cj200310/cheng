"use strict";(self["webpackChunkwms_web"]=self["webpackChunkwms_web"]||[]).push([[15],{6015:function(e,t,a){a.r(t),a.d(t,{default:function(){return m}});var s=function(){var e=this,t=e._self._c;return t("div",[t("div",{staticStyle:{"margin-bottom":"5px"}},[t("el-input",{staticStyle:{width:"200px"},attrs:{placeholder:"请输入菜名","suffix-icon":"el-icon-search"},nativeOn:{keyup:function(t){return!t.type.indexOf("key")&&e._k(t.keyCode,"enter",13,t.key,"Enter")?null:e.loadPost.apply(null,arguments)}},model:{value:e.menuName,callback:function(t){e.menuName=t},expression:"menuName"}}),t("el-button",{staticStyle:{"margin-left":"5px"},attrs:{type:"primary"},on:{click:e.loadPost}},[e._v("查询")]),t("el-button",{staticStyle:{"margin-left":"5px"},attrs:{type:"success"},on:{click:e.resetparam}},[e._v("重置")]),t("el-button",{staticStyle:{"margin-left":"5px"},attrs:{type:"danger"},on:{click:e.add}},[e._v("新增")])],1),t("el-table",{attrs:{data:e.tableData,"header-cell-style":{background:"#cedbe8",color:"#555"},border:""}},[t("el-table-column",{attrs:{prop:"menuId",label:"ID",width:"60"}}),t("el-table-column",{attrs:{prop:"menuName",label:"菜名",width:"180"}}),t("el-table-column",{attrs:{prop:"price",label:"价格",width:"180"}}),t("el-table-column",{attrs:{prop:"profit",label:"利润",width:"80"}}),t("el-table-column",{attrs:{prop:"operate",label:"操作"},scopedSlots:e._u([{key:"default",fn:function(a){return[t("el-button",{attrs:{size:"small",type:"success"},on:{click:function(t){return e.mod(a.row)}}},[e._v("编辑")]),t("el-popconfirm",{staticStyle:{"margin-left":"5px"},attrs:{title:"确定要删除吗？"},on:{confirm:function(t){return e.del(a.row.menuId)}}},[t("el-button",{attrs:{slot:"reference",size:"small",type:"danger"},slot:"reference"},[e._v("删除")])],1)]}}])})],1),t("el-pagination",{attrs:{"current-page":e.pageNum,"page-sizes":[5,10,20,30],"page-size":e.pageSize,layout:"total, sizes, prev, pager, next, jumper",total:e.total},on:{"size-change":e.handleSizeChange,"current-change":e.handleCurrentChange}}),t("el-dialog",{attrs:{title:"提示",visible:e.centerDialogVisible,width:"30%",center:""},on:{"update:visible":function(t){e.centerDialogVisible=t}}},[t("el-form",{ref:"form",attrs:{rules:e.rules,model:e.form,"label-width":"80px"}},[t("el-form-item",{attrs:{label:"菜名",prop:"menuName"}},[t("el-col",{attrs:{span:20}},[t("el-input",{model:{value:e.form.menuName,callback:function(t){e.$set(e.form,"menuName",t)},expression:"form.menuName"}})],1)],1),t("el-form-item",{attrs:{label:"价格",prop:"price"}},[t("el-col",{attrs:{span:20}},[t("el-input",{model:{value:e.form.price,callback:function(t){e.$set(e.form,"price",t)},expression:"form.price"}})],1)],1),t("el-form-item",{attrs:{label:"利润",prop:"profit"}},[t("el-col",{attrs:{span:20}},[t("el-input",{model:{value:e.form.profit,callback:function(t){e.$set(e.form,"profit",t)},expression:"form.profit"}})],1)],1)],1),t("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[t("el-button",{on:{click:function(t){e.centerDialogVisible=!1}}},[e._v("取 消")]),t("el-button",{attrs:{type:"primary"},on:{click:e.save}},[e._v("确 定")])],1)],1)],1)},o=[],l={name:"MenuManage",data(){return{tableData:[],pageSize:10,pageNum:1,total:0,menuName:"",centerDialogVisible:!1,form:{menuId:"",menuName:"",price:"",profit:""},rules:{menuName:[{required:!0,message:"请输入菜品名字",trigger:"blur"}]}}},methods:{resetForm(){this.$refs.form.resetFields()},del(e){console.log(e),this.$axios.get(this.$httpUrl+"/cook/del?id="+e).then((e=>e.data)).then((e=>{console.log(e),200==e.code?(this.$message({message:"操作成功",type:"success"}),this.loadPost()):this.$message({message:"操作失败",type:"error"})}))},mod(e){this.centerDialogVisible=!0,this.$nextTick((()=>{this.form.menuId=e.menuId,this.form.menuName=e.menuName,this.form.price=e.price,this.form.profit=e.profit}))},add(){this.centerDialogVisible=!0,this.$nextTick((()=>{this.resetForm()}))},doSave(){this.$axios.post(this.$httpUrl+"/cook/save",this.form).then((e=>e.data)).then((e=>{console.log(e),200==e.code?(this.$message({message:"操作成功",type:"success"}),this.centerDialogVisible=!1,this.loadPost()):this.$message({message:"操作失败",type:"error"})}))},doMod(){this.$axios.post(this.$httpUrl+"/cook/update",this.form).then((e=>e.data)).then((e=>{console.log(e),200==e.code?(this.$message({message:"操作成功",type:"success"}),this.centerDialogVisible=!1,this.loadPost()):this.$message({message:"操作失败",type:"error"})}))},save(){this.$refs.form.validate((e=>{if(!e)return console.log("error submit!!"),!1;this.form.menuId?this.doMod():this.doSave()}))},handleSizeChange(e){console.log(`每页 ${e} 条`),this.pageNum=1,this.pageSize=e,this.loadPost()},handleCurrentChange(e){console.log(`当前页: ${e}`),this.pageNum=e,this.loadPost()},resetparam(){this.menuName="0"},loadPost(){this.$axios.post(this.$httpUrl+"/cook/listPage",{pageSize:this.pageSize,pageNum:this.pageNum,param:{menuName:this.menuName}}).then((e=>e.data)).then((e=>{console.log(e),200==e.code?(this.tableData=e.data,this.total=e.total):alert("获取数据失败")}))}},beforeMount(){this.loadPost()}},r=l,i=a(1656),n=(0,i.A)(r,s,o,!1,null,"5796a56c",null),m=n.exports}}]);
//# sourceMappingURL=15.d86f116c.js.map