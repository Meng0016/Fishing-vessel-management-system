<template>
<div class="wrapper">
  <div style="margin: 220px auto; background-color: #fff;width: 350px;height: 390px;padding: 20px;border-radius: 10px">
  <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>注册</b></div>
    <el-form :model="user" :rules="rules" ref="userForm">
      <el-form-item prop="username">
        <el-input placeholder="请输入账号" size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input placeholder="请输入密码" size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
      </el-form-item>
      <el-form-item prop="confirm">
        <el-input placeholder="请确认密码" size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.confirm"></el-input>
      </el-form-item>
      <el-form-item style="margin: 10px 0; text-align: right">
        <el-button type="primary" size="small"  autocomplete="off" @click="register">注册</el-button>
        <el-button type="warning" size="small"  autocomplete="off" @click="$router.push('/login')">取消</el-button>
      </el-form-item>
    </el-form>
  </div>
</div>
</template>

<script>
export default {
  name: "Login",
  data(){
    return{
      user:{},
      rules:{
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 4, max: 10, message: '长度在 4 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' }
        ],
        confirm: [
          { required: true, message: '请确认密码', trigger: 'blur' },
          { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    register(){
      this.$refs.userForm.validate((valid) => { // 验证表单 回调函数 valid 为true时验证通过
        if (valid) {
          if (this.user.password !== this.user.confirm){
            this.$message.warning("两次输入密码不一致")
            return false
          }
          this.request.post("/user/register",this.user).then(res =>{
            if(res.code === '200'){
              this.$message.success("注册成功");
            }else {
              this.$message.error(res.msg);
            }
          })
        }
      });
    },
  }
}
</script>

<style scoped>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #FC466B , #3F5EFB);
  overflow: hidden;
}
</style>