<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/ship2.png" alt="" style="width: 20px; position: relative; top: 5px; margin-right: 5px">
      <b style="color: white" v-show="logoTextShow">渔船管理系统</b>
    </div>
<!--    <el-menu-item index="/"><i class="el-icon-s-home"></i>主页</el-menu-item>-->
    <el-menu-item index="/" style="height: 60px;text-align: left">
        <i class="el-icon-s-home"></i>
        <span>主页</span>
    </el-menu-item>
<!--    <el-submenu index="/">-->
<!--      <template slot="title">-->
<!--        <i class="el-icon-menu"></i>-->
<!--        <span slot="title">系统管理</span>-->
<!--      </template>-->
      <el-menu-item index="user" style="height: 60px"><i class="el-icon-user-solid"></i>用户管理</el-menu-item>
      <el-menu-item index="ship" style="height: 60px">&nbsp;<i class="iconfont icon-chuanzhi"></i>&nbsp;&nbsp;船只管理</el-menu-item>
      <el-menu-item index="role" style="height: 60px"><i class="el-icon-lock"></i>权限管理</el-menu-item>
      <el-menu-item index="certificate" style="height: 60px"><i class="el-icon-document-checked"></i>证书管理</el-menu-item>
<!--    </el-submenu>-->

  </el-menu>

</template>

<script>
import '@/assets/aliiconfont/iconfont.css';
export default {
  name: "Aside",
  props:{
    isCollapse : Boolean,
    logoTextShow : Boolean
  },
  methods:{

  }
}
</script>

<style scoped>

</style>