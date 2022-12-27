<template>
<div>

  <div style="padding: 10px 0 ">
    <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-user" v-model="username"></el-input>
    <el-input class="ml-5" style="width: 200px" placeholder="请输入真实姓名" suffix-icon="el-icon-s-check" v-model="realname"> </el-input>
    <el-button type="primary" style="margin-left: 10px" @click="load">搜索&nbsp;&nbsp;<i class="el-icon-search" /></el-button>
    <el-button type="warning" @click="reset" >重置&nbsp;&nbsp;<i class="el-icon-refresh-right" /></el-button>
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
    <el-upload
        action="http://localhost:9999/user/import"
        style="display: inline-block"
        class="mr-5"
        :show-file-list="false"
        accept="xlsx"
        :on-success="importExcel">
      <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
    </el-upload>
    <el-button type="primary" @click="exportExcel">导出 <i class="el-icon-top"></i></el-button>
  </div>


  <el-table :data="tableData" border stripe header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column prop="id" label="ID" width="50" ></el-table-column>
    <el-table-column prop="username" label="用户" width="90" ></el-table-column>
    <el-table-column prop="realname" label="真实姓名" width="90"></el-table-column>
    <el-table-column prop="phone" label="电话" width="115" ></el-table-column>
    <el-table-column prop="email" label="邮箱" width="170"></el-table-column>
    <el-table-column  prop="status" label="使用状态" width="80">
<!--      <el-switch-->
<!--          disabled-->
<!--          v-model="tableData.status"-->
<!--          active-color="#13ce66"-->
<!--          active-value= 1-->
<!--          inactive-value= 0 >-->
<!--      </el-switch>-->
    </el-table-column>
    <el-table-column prop="access" label="通过状态" width="80"></el-table-column>
    <el-table-column prop="loginCount" label="登录次数" width="80"></el-table-column>
    <el-table-column prop="dateCreated" label="注册日期" width="170" ></el-table-column>
    <el-table-column prop="smtp" label="Smtp" ></el-table-column>
    <el-table-column prop="port" label="港口"></el-table-column>


    <el-table-column label="操作"  width="250" align="center" fixed="right">
      <template slot-scope="scope">
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
  <div style="padding: 10px 0 ">
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
  </div>

  <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="35%">
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
  name: "User",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      username: '',
      realname: '',
      dialogFormVisible: false,
      multipleSelection:[],
      form: {},
    }
  },
  methods:{
    load(){
      this.request.get("/user/page",{
        params:{
          pageNum : this.pageNum,
          pageSize : this.pageSize,
          username : this.username,
          realname : this.realname,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records;
        this.total = res.data.total;
      })
      this.dialogFormVisible =false //BUG 用来改单击取消出现table数据改变的情况
    },
    save(){
      this.request.post("/user",this.form).then(res =>{
        if (res.data){
          this.$message.success("保存成功")
          this.dialogFormVisible=false
          this.load()
        }else {
          this.$message.error("保存失败")
        }
      })
    },
    del(id){
      this.request.delete("/user/delete/"+id).then(res=>{
        if (res.data){
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
      this.request.delete("/user/batchDelete",{data : ids}).then(res=>{
        if (res.data){
          this.$message.success("批量删除成功")
          this.load()
        }else {
          this.$message.error("批量删除失败")
        }
      })
    },
    importExcel(){
      this.$message.success("文件导入成功")
      this.load()
    },
    exportExcel(){
      window.open("http://localhost:9999/user/export")
    },
    handleAdd(){
      this.dialogFormVisible =true;
      this.form = {};
    },
    handleEdit(row){
      this.form = row;
      this.dialogFormVisible =true;
    },
    reset(){
      this.username = "";
      this.realname = "";
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
.headerBg{
  background-color: #eee !important;
}
.el-button--primary {
  background: #33b7fa !important;
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