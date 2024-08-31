<script>
export default {
  name: "db_Header",
  data(){
    return{
      user: JSON.parse(sessionStorage.getItem('CurUser'))
    }

  },
  props:{
    icon:String
  },
  methods:{
    toUser(){
      console.log('to_user')

      this.$router.push("/db_Home")
    },
    logout(){
      console.log('logout')
      this.$confirm('您确定要退出登录吗', '提示', {
            confirmButtonText: '确定',
            type:'warning',
            center:true,//文字居中

        }).then(() => {
          this.$message({
            type:'success',
            message:'退出登录成功'
          })
          this.$router.push("/")
          sessionStorage.clear()
        }).catch(() => {
          // 如果取消跳转地址栏会变化，这时保持地址栏不变
          this.$message({
               type:'info',
               message:'已取消退出登录'
          })
        });


    },
    collapse(){
      this.$emit('doCollapse')
    }
  },
  created(){
    this.$router.push("/db_Home")
  }
}
</script>

<template>
  <div style="display: flex;line-height: 60px">
    <div style="margin-top: 8px;">
      <i :class="icon" style="font-size: 20px;cursor: pointer;" @click="collapse" ></i>
    </div>
    <div style="flex: 1;text-align: center;font-size: 34px;">
      <span>欢迎来到温湿度监测系统</span>
    </div>
    <span>{{user.name}}</span>
  <el-dropdown>
    <i class="el-icon-arrow-down" style="margin-left: 5px"></i>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
      <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>

  </div>
</template>

<style scoped>

</style>