<script>
export default {
  name: "dapengManage",
  data() {

    return {

      tableData: [],
      pageSize:10,
      pageNum:1,
      total:0,
      dapengname:'',
      centerDialogVisible:false,
      form:{
        dapengid:'',
        dapengname:'',
        seat:'',
        area:'',
        status:'',
      },
      rules: {
        dapengname: [
          {required: true, message: '请输入大棚名称', trigger: 'blur'},
        ],
      },
    }
  },
  methods:{
    resetForm() {
      this.$refs.form.resetFields();
    },
    del(dapengid){
      console.log(dapengid)

      this.$axios.get(this.$httpUrl+'/dapengname/del?id='+dapengid).then(res=>res.data).then(res=>{
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
      this.centerDialogVisible=true

      this.$nextTick(()=>{
        this.form.dapengid=row.dapengid
        this.form.dapengname=row.dapengname
        this.form.seat=row.seat
        this.form.area=row.area
        this.form.status=row.status

      })
    },
    add(){
      this.centerDialogVisible=true
      this.$nextTick(()=>{
        this.resetForm();
      })

    },
    doSave(){
      this.$axios.post(this.$httpUrl+'/dapengname/save',this.form
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
    doMod(){
      this.$axios.post(this.$httpUrl+'/dapengname/update',this.form
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
    save(){
      this.$refs.form.validate((valid) => {
        if (valid) {
          if(this.form.dapengid){
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
      this.dapengname='0'
    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/dapengname/dapeng',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
          dapengname:this.dapengname,
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
    }
  },
  beforeMount() {
    this.loadPost()
  }
}
</script>

<template>
  <div>
    <div style="margin-bottom: 5px">
      <el-input v-model="dapengname" placeholder="请输入大棚名" suffix-icon ="el-icon-search" style="width:200px"
                @keyup.enter.native="loadPost"></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="loadPost">查询</el-button>
      <el-button type="success" style="margin-left: 5px" @click="resetparam">重置</el-button>
      <el-button type="danger" style="margin-left: 5px" @click="add">新增</el-button>

    </div>
    <el-table :data="tableData"
              :header-cell-style="{background:'#cedbe8',color:'#555'}"
              border>
      <el-table-column prop="dapengid" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="dapengname" label="大棚名称" width="180">
      </el-table-column>
      <el-table-column prop="seat" label="大棚位置" width="180">
      </el-table-column>
      <el-table-column prop="area" label="大棚面积" width="80">
      </el-table-column>
      <el-table-column prop="status" label="状态" width="80">
        <template slot-scope="scope">
          <el-tag
              :type="scope.row.status === '1' ? 'primary' : 'success'"
              disable-transitions>{{scope.row.status === '1' ? '启用' : '禁用'}}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="operate" label="操作">
        <template slot-scope="scope">
          <el-button size="small" type="success" @click="mod(scope.row)">编辑</el-button>
          <el-popconfirm
              title="确定要删除吗？"
              @confirm="del(scope.row.dapengid)"
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
        <el-form-item label="大棚名称" prop="dapengname">
          <el-col :span="20">
            <el-input v-model="form.dapengname"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="位置" prop="seat">
          <el-col :span="20">
            <el-input v-model="form.seat"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="面积" prop="area">
          <el-col :span="20">
            <el-input v-model="form.area"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio-group v-model="form.status">
            <el-radio label="1">启用</el-radio>
            <el-radio label="0">禁用</el-radio>
          </el-radio-group>
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