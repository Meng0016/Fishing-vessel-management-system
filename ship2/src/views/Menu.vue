<template>
<div>

  <div style="padding: 10px 0 ">
<!--    <el-input style="width: 200px" placeholder="请输入名称" suffix-icon="el-icon-user" v-model="name"></el-input>-->
<!--    <el-button type="primary" style="margin-left: 10px" @click="load">搜索&nbsp;&nbsp;<i class="el-icon-search" /></el-button>-->
<!--    <el-button type="warning" @click="reset" >重置&nbsp;&nbsp;<i class="el-icon-refresh-right" /></el-button>-->
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    <el-button type="primary" @click="handleAdd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
    <el-popconfirm
        class="ml-5"
        confirm-button-text='确定'
        cancel-button-text='取消'
        icon="el-icon-info"
        icon-color="red"
        title="您确定删除吗？"
        @confirm="batchDelete"
    >
      <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
    </el-popconfirm>
  </div>


  <el-table :data="tableData" border stripe header-cell-class-name="headerBg"
            row-key="id"
            default-expand-all
            @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column prop="id" label="ID" width="100" ></el-table-column>
    <el-table-column prop="name" label="名称" width="90" ></el-table-column>
    <el-table-column prop="path" label="路径" width="110" ></el-table-column>
    <el-table-column label="图标" width="130" class-name="fontSize18" align="center" label-class-name="fontSize12" >
        <template slot-scope="scope">
          <span :class="scope.row.icon"/>
        </template>
    </el-table-column>
    <el-table-column prop="description" label="描述" ></el-table-column>

    <el-table-column label="操作"  width="350" align="center" fixed="right">
      <template slot-scope="scope">
        <el-button type="primary" @click="handleAdd(scope.row.id)" v-if="!scope.row.pid && !scope.row.path">新增子菜单 <i class="el-icon-plus"></i></el-button>
        <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除吗？"
            @confirm="del(scope.row.id)"
        >
          <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog title="菜单信息" :visible.sync="dialogFormVisible" width="35%">
    <el-form label-width="70px">
      <el-form-item label="名称" >
        <el-input v-model="form.name" autocomplete="off"  ></el-input>
      </el-form-item>
      <el-form-item label="路径" >
        <el-input v-model="form.path" autocomplete="off"  ></el-input>
      </el-form-item>
      <el-form-item label="图标" >
          <el-select clearable v-model="form.icon" placeholder="请选择" style="width: 80%">
            <el-option v-for="item in options" :key="item.name" :label="item.name" :value="item.value">
              <i :class="item.value" />&nbsp;{{ item.name }}
            </el-option>
          </el-select>
      </el-form-item>
      <el-form-item label="描述" >
        <el-input v-model="form.description" autocomplete="off"></el-input>
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="load">取 消</el-button>
      <el-button type="primary" @click="save">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "Menu",
  data(){
    return{
      tableData: [],
      name : '',
      dialogFormVisible: false,
      multipleSelection:[],
      form: {},
      options:[],
    }
  },
  methods:{
    load(){
      this.request.get("/menu/all",{
        params:{
          name : this.name,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data;
      })
      this.dialogFormVisible =false //BUG 用来改单击取消出现table数据改变的情况
    },
    save(){
      this.request.post("/menu",this.form).then(res =>{
        if (res.code === '200'){
          this.$message.success("保存成功")
          this.dialogFormVisible=false
          this.load()
        }else {
          this.$message.error("保存失败")
        }
      })
    },
    del(id){
      this.request.delete("/menu/delete/"+id).then(res=>{
        if (res.code === '200'){
          this.$message.success("删除成功")
          this.dialogFormVisible=false
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val){
      this.multipleSelection = val //将选中数组赋给multipleSelection
      console.log(val)
    },
    batchDelete(){
      let ids = this.multipleSelection.map(v => v.id) //[{},{},{}]  =>[1,2,3]
      this.request.delete("/menu/batchDelete",{data : ids}).then(res=>{
        if (res.code === '200'){
          this.$message.success("批量删除成功")
          this.load()
        }else {
          this.$message.error("批量删除失败")
        }
      })
    },
    handleAdd(pid){
      this.dialogFormVisible =true;
      this.form = {};
      if (pid){
        this.form.pid= pid;
      }

    },
    handleEdit(row){
      this.form = JSON.parse(JSON.stringify(row));
      // this.form = row;
      console.log(row)
      this.dialogFormVisible =true;
      //查看图标数据
      this.request.get("/menu/icons",).then(res =>{
        console.log(res)
        this.options = res.data;
      })

    },
    reset(){
      this.name = '';
      this.load()
    },
    handleSizeChange(pageSize){
      console.log(pageSize);
      this.pageSize= pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){
      console.log(pageNum);
      this.pageNum=pageNum;
      this.load();
    },
  },
  created() {
    this.load()
  }
}
</script>

<style>
.fontSize18{
  font-size: 18px;
}
.fontSize12{
  font-size: 12px;
}
.headerBg{
  background-color: #eee !important;
}
.el-button--primary {
  background: rgba(51, 183, 250, 0.83) !important;
  border-color: #33b7fa !important;
}
.el-button--primary:hover {
  background: #387dff !important;
  border-color: #387dff !important;
  color: #FFF !important;
}
.el-button--danger {
  background: #f56c6c !important;
  border-color: #f56c6c !important;
}
.el-button--danger:hover {
  background: #ec0202 !important;
  border-color: #ec0202 !important;
  color: #FFF !important;
}
.el-button--warning {
  background: #e6a23c !important;
  border-color: #e6a23c !important;
}
.el-button--warning:hover {
  background: #da8200 !important;
  border-color: #da8200 !important;
  color: #FFF !important;
}
.el-button--success {
  background: #67c23a !important;
  border-color: #67c23a !important;
}
.el-button--success:hover {
  background: #0bff3d !important;
  border-color: #0bff3d !important;
  color: #FFF !important;
}
</style>