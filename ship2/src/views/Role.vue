<template>
<div>

  <div style="padding: 10px 0 ">
    <el-input style="width: 200px" placeholder="请输入用户名" suffix-icon="el-icon-user" v-model="name"></el-input>
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
  </div>


  <el-table :data="tableData" border stripe header-cell-class-name="headerBg" @selection-change="handleSelectionChange">
    <el-table-column type="selection" width="55"></el-table-column>
    <el-table-column prop="id" label="ID" width="50" ></el-table-column>
    <el-table-column prop="name" label="名称" width="90" ></el-table-column>
    <el-table-column prop="flag" label="唯一标识" width="130" ></el-table-column>
    <el-table-column prop="description" label="描述" ></el-table-column>

    <el-table-column label="操作"  width="400" align="center" fixed="right">
      <template slot-scope="scope">
        <el-button type="info" @click="selectMenu(scope.row.id)" icon="el-icon-menu" >分配菜单</el-button>
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

  <el-dialog title="角色信息" :visible.sync="dialogFormVisible" width="35%">
    <el-form label-width="70px">
      <el-form-item label="名称" >
        <el-input v-model="form.name" autocomplete="off"  ></el-input>
      </el-form-item>
      <el-form-item label="唯一标识" >
        <el-input v-model="form.flag" autocomplete="off"  ></el-input>
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

  <el-dialog title="菜单分配" :visible.sync="menuVisible" width="35%">
    <el-tree
        :props = "props"
        :data = "menuData"
        show-checkbox
        node-key="id"
        ref="tree"
        :default-expanded-keys="expends"
        :default-checked-keys="checks"
    >
      <span class="custom-tree-node" slot-scope="{ node, data }">
        <span><i :class="data.icon"></i> {{ data.name }}</span>
      </span>
    </el-tree>
    <div slot="footer" class="dialog-footer">
      <el-button @click="load;menuVisible=false">取 消</el-button>
      <el-button type="primary" @click="saveRoleMenu">确 定</el-button>
    </div>
  </el-dialog>
</div>
</template>

<script>
export default {
  name: "Role",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      name : '',
      dialogFormVisible: false,
      menuVisible :false,
      multipleSelection:[],
      form: {},
      menuData:[],
      props:{
        label : "name"
      },
      expends:[],
      checks:[],
      roleId : 0,
    }
  },
  methods:{
    load(){
      this.request.get("/role/page",{
        params:{
          pageNum : this.pageNum,
          pageSize : this.pageSize,
          name : this.name,
        }
      }).then(res =>{
        console.log(res)
        this.tableData = res.data.records;
        this.total = res.data.total;
      })

      this.dialogFormVisible =false //BUG 用来改单击取消出现table数据改变的情况
    },
    save(){
      this.request.post("/role",this.form).then(res =>{
        if (res.code === '200'){
          this.$message.success("保存成功")
          this.dialogFormVisible=false
          this.load()
        }else {
          this.$message.error("保存失败")
        }
      })
    },
    saveRoleMenu(){
      console.log(this.$refs.tree.getCheckedKeys());
      this.request.post("/role/roleMenu/"+this.roleId , this.$refs.tree.getCheckedKeys()).then(res =>{
        console.log(res)
        if (res.code === '200'){
          this.$message.success("绑定成功")
          this.menuVisible = false
        }else {
          this.$message.error(res.msg)
        }
      })
    },
    del(id){
      this.request.delete("/role/delete/"+id).then(res=>{
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
      this.request.delete("/role/batchDelete",{data : ids}).then(res=>{
        if (res.code === '200'){
          this.$message.success("批量删除成功")
          this.load()
        }else {
          this.$message.error("批量删除失败")
        }
      })
    },
    handleAdd(){
      this.dialogFormVisible =true;
      this.form = {};
    },
    handleEdit(row){
      this.form = row;
      console.log(row)
      this.dialogFormVisible =true;

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
    selectMenu(roleId){
      this.menuVisible = true;
      this.roleId = roleId
      //请求菜单
      this.request.get("/menu/all").then(res =>{
        this.menuData = res.data;
        //后台菜单数据处理成id数组
        this.expends= this.menuData.map(value => value.id)
      });

      this.request.get("/role/roleMenu/" + roleId).then(res => {
        this.checks = res.data
      });
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