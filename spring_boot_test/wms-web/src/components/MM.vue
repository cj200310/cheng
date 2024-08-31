<template>
  <el-container style="height: 100%; border: 1px solid #eee">
    <el-aside :width="aside_witdh" style="background-color: rgb(238, 241, 246);height: 100%;margin-left: -1px">
      <db_aside :isCollapse="isCollapse"></db_aside>
    </el-aside>

    <el-container style="height: 100%;">
      <el-header style="text-align: right; font-size: 12px;height: 100%;border-bottom: rgba(169,169,169,0.3) 1px solid;">
        <db_Header @doCollapse="doCollapse" :icon="icon"></db_Header>
      </el-header>

      <el-main style="height: 100%">
<!--        <db_Main></db_Main>-->
        <router-view/>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import db_aside from "@/components/db_aside.vue";
import db_Header from "@/components/db_Header.vue";

export default {
  name:"MM",
  components: { db_Header, db_aside},
  data(){
    return{
      isCollapse:false,
      aside_witdh:'200px',
      icon:'el-icon-s-unfold'
    }
  },
  methods:{
    doCollapse(){
      console.log(1111)
      this.isCollapse= !this.isCollapse
      if(!this.isCollapse){//展开
        this.aside_witdh='200px'
        this.icon='el-icon-s-fold'
      }else{//关闭
        this.aside_witdh='64px'
        this.icon='el-icon-s-unfold'
      }
    }
  }

}
</script>



<style scoped>
.el-header {
  #background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}
.el-main{
  padding: 5px;
}
.el-aside {
  color: #333;
}
</style>