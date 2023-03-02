<template>
<div class="wrapper">
  <div style="margin: 220px auto; background-color: #fff;width: 350px;height: 320px;padding: 20px;border-radius: 10px">
  <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
    <el-form :model="user" :rules="rules" ref="userForm">
      <el-form-item prop="username">
        <el-input placeholder="请输入账号"  size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username" ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input placeholder="请输入密码" size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password" ></el-input>
      </el-form-item>
      <el-form-item style="margin: 10px 0; text-align: right">
        <el-button type="primary" size="small"  autocomplete="off" @click="login">登录</el-button>
        <el-button type="warning" size="small"  autocomplete="off" @click="$router.push('/register')">注册</el-button>
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
          {pattern: /^(?!\s+).*(?<!\s)$/,  message: '不能输入空格', trigger: 'blur'},  //同上 :onkeyup="input= input.replace(/[ ]/g,'')"  不可输入空格
          { min: 4, max: 10, message: '长度在 4 到 10 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          {pattern: /^(?!\s+).*(?<!\s)$/,  message: '不能输入空格', trigger: 'blur'},
          { min: 3, max: 12, message: '长度在 3 到 12 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    login(){
      this.$refs.userForm.validate((valid) => { // 验证表单 回调函数 valid 为true时验证通过
        if (valid) {
          this.request.post("/user/login",this.user).then(res =>{
            if(res.code === '200'){
              localStorage.setItem("user",JSON.stringify(res.data))  //存储用户信息到浏览器
              this.$message.success("登录成功");
              this.$router.push("/");
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