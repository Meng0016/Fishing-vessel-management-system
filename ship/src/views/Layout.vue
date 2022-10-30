<template>
  <div>
    <!--    头部-->
    <Header :user="user"/>

    <!--    主体-->
    <div style="display: flex">
      <!--      侧边栏-->
      <Aside />
      <!--      内容区域-->
      <router-view style="flex: 1" @userInfo="refreshUser"/>
    </div>
  </div>
</template>

<script>
export default {
  name: "Layout"
}
</script>

<style scoped>

</style>