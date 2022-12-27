<template>
    <el-card style="width: 500px;padding: 20px;">
      <el-form label-width="70px">
        <el-form-item label="用户名" >
          <el-input v-model="form.username" autocomplete="off"  ></el-input>
        </el-form-item>
        <el-form-item label="真实姓名" >
          <el-input v-model="form.realname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" >
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" >
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="使用状态" >
          <el-input v-model="form.status" autocomplete="off"/>
          <!--        <el-switch-->
          <!--            v-model="form.status"-->
          <!--            active-color="#13ce66"-->
          <!--            active-value="1"-->
          <!--            inactive-value="0"-->
          <!--        />-->
        </el-form-item>
        <!--            <el-form-item label="通过状态" >-->
        <!--              <el-input v-model="form.access" autocomplete="off"></el-input>-->
        <!--            </el-form-item>-->
        <!--            <el-form-item label="登录次数" >-->
        <!--              <el-input v-model="form.loginCount" autocomplete="off"></el-input>-->
        <!--            </el-form-item>-->
        <el-form-item label="Smtp" >
          <el-input v-model="form.smtp" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="港口" >
          <el-input v-model="form.port" autocomplete="off"></el-input>
        </el-form-item>
      <el-form-item>
          <el-button type="primary" @click="save">确 定</el-button>
          <el-button>取 消</el-button>
      </el-form-item>
      </el-form>
    </el-card>
</template>

<script>
export default {
  name: "Person",
  data(){
    return {
      form:{},
      user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {},
    }
  },
  created() {
    this.request.get("/user/username/"+ this.user.username).then(res=>{
      if (res.code === '200'){
        this.form =res.data
      }
    })
  },
  methods:{
    save(){
      this.request.post("/user",this.form).then(res =>{
        if (res.data){
          this.$message.success("保存成功")
        }else {
          this.$message.error("保存失败")
        }
      })
    },
  }
}
</script>

<style scoped>

</style>