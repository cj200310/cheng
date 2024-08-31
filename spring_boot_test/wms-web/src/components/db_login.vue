<template>
  <div class="loginBody">
    <div class="loginDiv">
      <div class="login-content">
        <h1 class="login-title">用户登录</h1>
        <el-form :model="loginForm" label-width="100px"
                 :rules="rules" ref="loginForm">
          <el-form-item label="账号" prop="no">
            <el-input style="width: 200px" type="text" v-model="loginForm.no"
                      autocomplete="off" size="small"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-input style="width: 200px" type="password" v-model="loginForm.password"
                      show-password autocomplete="off" size="small" @keyup.enter.native="confirm"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="confirm" :disabled="confirm_disabled">确 定</el-button>
            <el-button type="primary" @click="add" :disabled="confirm_disabled">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
      <el-dialog
          title="提示"
          :visible.sync="centerDialogVisible"
          width="30%"
          center>
        <el-form ref="form" :rules="rules" :model="form" label-width="80px">
          <el-form-item label="账号" prop="no">
            <el-col :span="20">
              <el-input v-model="form.no"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="姓名" prop="name">
            <el-col :span="20">
              <el-input v-model="form.name"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="密码" prop="password">
            <el-col :span="20">
              <el-input v-model="form.password"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="年龄" prop="age">
            <el-col :span="20">
              <el-input v-model="form.age"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="form.sex">
              <el-radio label="1">男</el-radio>
              <el-radio label="0">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="角色">
            <el-radio-group v-model="form.roleId">
              <el-radio label="1">管理员</el-radio>
              <el-radio label="0">超级管理员</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="电话" prop="phone">
            <el-col :span="20">
              <el-input v-model="form.phone"></el-input>
            </el-col>
          </el-form-item>
          <el-form-item label="邮箱" prop="email">
            <el-col :span="20">
              <el-input v-model="form.email"></el-input>
            </el-col>
          </el-form-item>

        </el-form>
        <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="save">确 定</el-button>
  </span>
      </el-dialog>
    </div>
  </div>
</template>
<script>
export default {
  name: "db_login",
  data(){
    return{
      confirm_disabled:false,
      centerDialogVisible:false,
      loginForm:{
        no:'',
        password:''
      },
      form:{
        no:'',
        name:'',
        password:'',
        age:'',
        phone:'',
        sex:'0',
        roleId:'1',
        email:'',
      },
      rules:{
        no: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输密码', trigger: 'blur' }
        ],
      }
    }
  },
  methods:{

    add(){
      this.centerDialogVisible=true
      this.$nextTick(()=>{
        this.resetForm();
      })

    },
    save(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          if(this.form.id){
            this.doMod();
          }else{
            this.doSave();
          }

        } else {
          console.log('error submit!!');
          return false;
        }
      });

    },
    doSave(){
      this.$axios.post(this.$httpUrl+'/user/save',this.form
      ).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){

          this.$message({
            message: '操作成功',
            type: 'success'
          });
          this.centerDialogVisible=false

        }else{
          this.$message({
            message: '操作失败',
            type: 'error'
          });
        }

      })
    },
    doMod(){
      this.$axios.post(this.$httpUrl+'/user/update',this.form
      ).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){

          this.$message({
            message: '操作成功',
            type: 'success'
          });
          this.centerDialogVisible=false

        }else{
          this.$message({
            message: '操作失败',
            type: 'error'
          });
        }

      })
    },
    confirm(){
      this.confirm_disabled=true;
      this.$refs.loginForm.validate((valid) => {
        if (valid) { //valid成功为true，失败为false
          //去后台验证用户名密码
          this.$axios.post(this.$httpUrl+'/user/login',this.loginForm).then(res=>res.data).then(res=>{
            console.log(res)
            if(res.code==200){
              //存储
              sessionStorage.setItem("CurUser",JSON.stringify(res.data.user))
              console.log(res.data.menu)
              this.$store.commit("setMenu",res.data.menu)
              //跳转到主页
              this.$router.replace('/MM');
            }else{
              this.confirm_disabled=false;
              alert('校验失败，用户名或密码错误！');
              return false;
            }
          });
        } else {
          this.confirm_disabled=false;
          console.log('校验失败');
          return false;
        }
      });
    }
  }
}
</script>
<style scoped>
.loginBody {
  position: absolute;
  width: 100%;
  height: 100%;
  background-color: #B3C0D1;
}
.loginDiv {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -149px;
  margin-left: -250px;
  width: 450px;
  height: 330px;
  background: #fff;
  border-radius: 5%;

}
.login-title {
  margin: 20px 0;
  text-align: center;
}
.login-content {
  width: 400px;
  height: 250px;
  position: absolute;
  top: 25px;
  left: 25px;
}
</style>
