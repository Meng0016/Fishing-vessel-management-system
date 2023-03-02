<template>
    <el-card style="width: 500px;padding: 20px;">
      <el-form label-width="70px">

        <el-upload
            class="avatar-uploader"
            action="http://localhost:9999/file/uploadavatar"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="form.avatar" :src="form.avatar" class="avatar">
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>

        <el-form-item label="用户名" >
          <el-input v-model="form.username" disabled autocomplete="off"  ></el-input>
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
    this.getUser().then(res =>{
      this.form = res
    });
  },
  methods:{
    async getUser(){
      return (await  this.request.get("/user/username/" + this.user.username)).data
    },
    save(){
      this.request.post("/user",this.form).then(res =>{
        if (res.code === '200'){
          this.$message.success("保存成功")
          //父级更新方法
          this.$emit("refreshUser")
          //更新浏览器用户信息
          this.getUser().then(res =>{
            res.token = JSON.parse(localStorage.getItem("user")).token
            localStorage.setItem("user" , JSON.stringify(res))
          })

        }else {
          this.$message.error("保存失败")
        }
      })
    },
    handleAvatarSuccess(res){
      this.form.avatar  =res;
    }
  }
}
</script>

<style>
.avatar-uploader{
  text-align: center;
  padding-bottom: 20px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;

}
.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>