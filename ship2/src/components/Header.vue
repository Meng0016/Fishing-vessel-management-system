<template>
<div style="font-size: 12px;line-height: 60px; display: flex">
  <div style="flex: 1; ">
    <span :class="collapseBtnClass" style="cursor: pointer;font-size: 18px" @click="collapse"></span>

    <el-breadcrumb separator="/" style="display: inline-block;margin-left: 10px">
      <el-breadcrumb-item :to="'/'">首页</el-breadcrumb-item>
      <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>

    </el-breadcrumb>
  </div>
  <el-dropdown style="width: 120px; cursor: pointer">
    <div style="display: inline-block">
    <img :src="user.avatar" alt=""
          style="width: 30px;border-radius: 50%;position: relative;top: 10px;right: 7px">
    <span>{{ user.realname }}</span><i class="el-icon-caret-bottom" style="margin-left: 5px"></i>
    </div>
    <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center" >
      <el-dropdown-item style="font-size: 14px;padding: 5px 0">
<!--        <router-link to="/person">个人信息</router-link>-->
        <span style="font-size: 14px;" @click="individual">个人信息</span>
      </el-dropdown-item>
      <el-dropdown-item style="font-size: 14px;padding: 5px 0">
        <span  style="text-decoration: none" @click="logout">退出</span>
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</div>
</template>

<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass : String,
    collapse : Function,
    user : Object

  },
  data(){
    return{
      // user: localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  watch: {
    currentPathName (newVal, oldVal) {
      console.log(newVal)
    }
  },
  computed:{
    // 获取当前路由的所有父级路由
    currentPathName () {
      return this.$store.state.currentPathName;　　//需要监听的数据
    }
  },
  methods:{
    logout(){
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    },
    individual(){
      this.$router.push("/person")
    }
  }
}
</script>

<style scoped>

</style>