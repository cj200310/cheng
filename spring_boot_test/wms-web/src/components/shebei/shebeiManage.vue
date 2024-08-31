<script>
import mqtt from "mqtt";

export default {
  name: "shebeiManage",
  data() {

    return {
      zhong:'',
      options1: [],
      dapengName:'',
      dapengId:'',
      tableData: [],
      pageSize:10,
      pageNum:1,
      total:0,
      shebeiname:'',
      dpname:'',
      centerDialogVisible:false,
      value:false,
      form:{
        shebeiid:'',
        shebeiname:'',
        type:'',
        state:'',
        dpid:'',
      },
      control:{
        deviceid:'',
        commandtype:'',
        commandvalue:'',
        executiontime:'',
        executionstatus:'',
      },
      rules: {
        shebeiname: [
          {required: true, message: '请输入设备名称', trigger: 'blur'},
        ],
      },
    }
  },
  watch: {
    // 监听 value 的变化
    value(newVal, oldVal) {
      // 这里是回调函数，当 value 变化时执行
      console.log('Switch value changed from', oldVal, 'to', newVal);
      // 你可以根据 newVal 的值（true 或 false）来执行不同的操作
      if (newVal) {
        // 执行开启水泵的逻辑
        this.form.state=newVal
        console.log('开启水泵');
      } else {
        // 执行关闭水泵的逻辑
        this.form.state=newVal
        console.log('关闭水泵');
      }
    },
  },
  methods:{
    resetForm() {
      this.$refs.form.resetFields();
    },
    initMqtt() {
      // 连接配置选项
      let options = {
        connectTimeout: 4000, // 超时时间
        // 认证信息
        clientId: 'qianduan2', //不填默认随机生成一个ID
        username: 'admin', //用户名
        password: '20031019qwe' //密码
      }
      this.client = mqtt.connect('ws://192.168.107.76:8083/mqtt', options) //调用连接的api
      //连接成功
      this.client.on('connect', (e) => {
        console.log('连接成功', e)
      })
      //重连提醒
      this.client.on('reconnect', (error) => {
        console.log('正在重连', error)
      })
      //连接失败提醒
      this.client.on('error', (error) => {
        console.log('连接失败', error)
      })
    },
    //发布信息
    publish(topic,message) {

      this.client.publish(topic, message, { qos: 0 }, (err) => {
        if (!err) {
          console.log(`主题为：${topic},内容为：${message} 发布成功`)
        }
      })
    },
    del(shebeiid){
      console.log(shebeiid)

      this.$axios.get(this.$httpUrl+'/shebei/del?id='+shebeiid).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){

          this.$message({
            message: '操作成功',
            type: 'success'
          });
          this.loadPost()

        }else{
          this.$message({
            message: '操作失败',
            type: 'error'
          });
        }

      })

    },
    mod(row){
      this.zhong='1'
      this.centerDialogVisible=true

      this.$nextTick(()=>{
        this.form.shebeiid=row.shebeiid
        this.form.shebeiname=row.shebeiname
        this.form.type=row.type
        this.form.state=row.state
        this.form.dpid=row.dpid
        this.control.deviceid=row.shebeiid
        this.control.commandtype=row.commandtype
        this.control.commandvalue=row.commandvalue
        this.control.executionstatus=row.executionstatus
        this.control.executiontime=row.executiontime
      })
    },
    add(){
      this.zhong='2'
      this.centerDialogVisible=true
      this.$nextTick(()=>{
        this.resetForm();
      })

    },
    doSave1(){
      this.$axios.post(this.$httpUrl+'/shebei/save',this.form
      ).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){

          this.$message({
            message: '操作成功',
            type: 'success'
          });
          this.centerDialogVisible=false
          this.loadPost()

        }else{
          this.$message({
            message: '操作失败',
            type: 'error'
          });
        }

      })
    },
    doSave2(){
      this.$axios.post(this.$httpUrl+'/control/save',this.control
      ).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){

          this.$message({
            message: '操作成功',
            type: 'success'
          });
          this.centerDialogVisible=false
          this.loadPost()

        }else{
          this.$message({
            message: '操作失败',
            type: 'error'
          });
        }

      })
    },
    doMod1(){
      this.$axios.post(this.$httpUrl+'/shebei/update',this.form
      ).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){

          this.$message({
            message: '操作成功1',
            type: 'success'
          });
          this.centerDialogVisible=false
          this.loadPost()

        }else{
          this.$message({
            message: '操作失败1',
            type: 'error'
          });
        }

      })
    },
    doMod2(){
      this.$axios.post(this.$httpUrl+'/control/update',this.control
      ).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){

          this.$message({
            message: '操作成功2',
            type: 'success'
          });
          this.centerDialogVisible=false
          this.loadPost()

        }else{
          this.$message({
            message: '操作失败2',
            type: 'error'
          });
        }

      })
    },
    save(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          if(this.zhong=='1'){
            // 执行开启水泵的逻辑
            this.doMod1();
            this.doMod2();
            this.publish('qianduan', JSON.stringify({data: this.value,one: this.form.shebeiid}))
          }else{
            this.doSave1();
            this.doSave2();
          }

        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageNum=1
      this.pageSize=val
      this.loadPost()
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.pageNum=val
      this.loadPost()
    },
    resetparam(){
      this.shebeiname='0'
    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/shebei/shebei',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          shebeiname:this.shebeiname,
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){
          this.tableData=res.data
          this.total=res.total

        }else{
          alert('获取数据失败')
        }

      })
    },
    loadPost1(){
      this.$axios.post(this.$httpUrl+'/list3',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){
          this.options1=res.data
          this.total=res.total
        }else{
          alert('获取数据失败')
        }

      })
    },
    loadPost2(){
      this.$axios.post(this.$httpUrl+'/shebei/dp',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          dpname:this.dpname,
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){
          this.tableData=res.data
          this.total=res.total

        }else{
          alert('获取数据失败')
        }

      })
    },
  },
  mounted() {
    // 在组件挂载后连接MQTT
    this.initMqtt()
  },
  beforeMount() {
    this.loadPost()
    this.loadPost1()
  }
}
</script>

<template>
  <div>
    <div style="margin-bottom: 5px">
      <el-input v-model="dpname" placeholder="请输入大棚名称" suffix-icon ="el-icon-search" style="width:200px"
                @keyup.enter.native="loadPost"></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost2">查询</el-button>
      <el-button type="success" style="margin-left: 5px" @click="resetparam">重置</el-button>
      <el-button type="danger" style="margin-left: 5px" @click="add">新增</el-button>
    </div>
    <el-table :data="tableData"
              :header-cell-style="{background:'#cedbe8',color:'#555'}"
              border>
      <el-table-column prop="shebeiid" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="dapengname" label="所属大棚" width="60">
      </el-table-column>
      <el-table-column prop="shebeiname" label="设备名称" width="180">
      </el-table-column>
      <el-table-column prop="type" label="设备类型" width="180">
      </el-table-column>
      <el-table-column prop="state" label="设备状态" width="80">
      </el-table-column>
      <el-table-column prop="commandtype" label="控制类型" width="180">
      </el-table-column>
      <el-table-column prop="commandvalue" label="控制值" width="180">
      </el-table-column>
      <el-table-column prop="executiontime" label="执行时间" width="180">
      </el-table-column>
      <el-table-column prop="executionstatus" label="执行状态" width="80">
      </el-table-column>
      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定要删除吗？"
              @confirm="del(scope.row.shebeiid)"
              style="margin-left: 5px;"
          >
            <el-button slot="reference" size="small" type="danger" >删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5 , 10 , 20, 30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>

    <el-dialog
        title="提示"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>

      <el-form ref="form" :rules="rules" :model="form" label-width="80px">
        <el-select v-model="form.dpid" placeholder="请选择大棚">
          <el-option
              v-for="item in options1"
              :key="item.dapengname"
              :label="item.dapengname"
              :value="item.dapengid">
          </el-option>
        </el-select>
        <el-form-item label="设备名称" prop="shebeiname">
          <el-col :span="20">
            <el-input v-model="form.shebeiname"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="设备类型" prop="type">
          <el-col :span="20">
            <el-input v-model="form.type"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="控制类型" prop="commandtype">
          <el-col :span="20">
            <el-input v-model="control.commandtype"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="控制值" prop="commandvalue">
          <el-col :span="20">
            <el-input v-model="control.commandvalue"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="执行时间" prop="executiontime">
          <el-col :span="20">
            <el-input v-model="control.executiontime"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="执行状态" prop="executionstatus">
          <el-col :span="20">
            <el-input v-model="control.executionstatus"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="设备状态" prop="state">
          <el-switch
              v-model="value"
              active-color="#13ce66"
              inactive-color="#ff4949"
              active-text="开启"
              inactive-text="关闭">
          </el-switch>
        </el-form-item>
      </el-form>

      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
    </el-dialog>
  </div>
</template>

<style scoped>

</style>