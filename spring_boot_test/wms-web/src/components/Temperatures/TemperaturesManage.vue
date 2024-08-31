<script>
import mqtt from 'mqtt'
export default {
  name: "TemperaturesManage",
  data() {
    return {
      title1: "温度",
      title2: "湿度",
      title3: "土壤湿度",
      data1:'',
      data2:'',
      data3:'45',
      value: false,
      tableData: [],
      dummyData: [],
      pageSize:10,
      pageNum:1,
      total:0,
      centerDialogVisible:false,
      client: null, // 用于存储MQTT客户端实例
      form:{
        temperaturec:'',
        humiditypercent:'',
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
        this.publish('qianduan', JSON.stringify({data: newVal}))
        console.log('开启水泵');
      } else {
        // 执行关闭水泵的逻辑
        this.publish('qianduan', JSON.stringify({data: newVal}))
        console.log('关闭水泵');
      }
    },
  },
  methods:{
    initSetTimeout() {
      setInterval(() => {
        this.loadPost();
      }, 500);
    },
    loadPost(){
      this.$axios.post(this.$httpUrl+'/list',{
        pageSize:this.pageSize,
        pageNum:this.pageNum,
        param:{
        }
      }).then(res=>res.data).then(res=>{
        console.log(res)
        if (res.code==200){
          this.temperaturec=res.data.temperaturec
          this.tableData=res.data
          this.total=res.total
        }else{
          alert('获取数据失败')
        }
      })
    },
    initMqtt() {
      // 连接配置选项
      let options = {
        connectTimeout: 4000, // 超时时间
        // 认证信息
        clientId: 'qianduan', //不填默认随机生成一个ID
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
    //订阅多个信息
    subscribes() {
      const arr = ['Taichi-Maker-Pub-60:55:F9:7B:78:4C', 'Taichi-Maker-Pub1-60:55:F9:7B:78:4C']
      this.client.subscribe(arr, { qos: 1 }, (err) => {
        if (!err) {
          console.log(`主题为：“${arr}” 的消息订阅成功`)
        } else {
          console.log('消息订阅失败')
        }
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
    //接收消息
    receive() {
      this.client.on('message', (topic, message) => {
        if (topic === 'Taichi-Maker-Pub-60:55:F9:7B:78:4C') {
          // 直接赋值，不需要模板字符串
          //this.tableData = message.data;
          console.log(`收到来自：${topic} 的消息：${message}`)
          this.data1 = JSON.parse(message) //接受到控制信号的数据
          console.log(this.data1)
        }else{
          //this.dummyData = message.data;
          console.log(`收到来自：${topic} 的消息：${message}`)
          this.data2 = JSON.parse(message) //接受到控制信号的数据
          console.log(this.data2)
        }
      })
    },
    change(value){
      this.publish('qianduan', JSON.stringify({data: value}))
    }
  },

  mounted() {
    // 在组件挂载后连接MQTT
    this.initMqtt()
    this.subscribes()
    this.receive()
  },
  beforeMount() {
    this.loadPost()
    //this.initSetTimeout()
    //断开连接
    this.client.end()
    this.client = null
    console.log('已经与mqtt客户端断开连接')
  }
}
</script>
<template>
  <div>
  <div>
    <el-row :gutter="20">
      <el-col :span="6">
        <div>
          <el-statistic
              group-separator=","
              :precision="2"
              :value= "data1"
              :title="title1"
          ></el-statistic>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
          <el-statistic
              group-separator=","
              :precision="2"
              :value= "data2"
              :title="title2"
          ></el-statistic>
        </div>
      </el-col>
      <el-col :span="6">
        <div>
          <el-statistic
              group-separator=","
              :precision="2"
              :value= "45"
              :title="title3"
          ></el-statistic>
        </div>
      </el-col>
    </el-row>
    </div>
    <div>
      <el-switch
          v-model="value"
          active-color="#13ce66"
          inactive-color="#ff4949"
          active-text="开启水泵"
          inactive-text="关闭水泵">
      </el-switch>
    </div>
  </div>
</template>
<style scoped>

</style>