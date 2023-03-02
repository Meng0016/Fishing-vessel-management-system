<template>
  <div>

    <div style="padding: 10px 0 ">
      <el-upload
          action="http://localhost:9999/file/upload"
          style="display: inline-block"
          class="mr-5"
          :show-file-list="false"
          accept="xlsx"
          :on-success="handleFileUploadSuccess">
        <el-button type="primary" class="ml-5">上传 <i class="el-icon-top"></i></el-button>
      </el-upload>
      <el-button type="warning" @click="reset" >重置&nbsp;&nbsp;<i class="el-icon-refresh-right" /></el-button>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

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
      <el-table-column prop="id" label="ID"  width="60"></el-table-column>
      <el-table-column prop="name" label="文件名称" width="100" ></el-table-column>
      <el-table-column prop="type" label="文件类型"  width="80"></el-table-column>
      <el-table-column prop="size" label="文件大小（kb）" width="130"></el-table-column>
      <el-table-column prop="url" label="文件地址" ></el-table-column>
      <el-table-column label="下载" width="110">
        <template slot-scope="scope">
          <el-button type="primary" @click="downloadFile(scope.row.url)">下载</el-button>
        </template>
      </el-table-column>
      <el-table-column label="启用" width="100">
        <template slot-scope="scope">
            <el-switch v-model="scope.row.enable" active-color="#13ce66" inactive-color="#ccc" @change="changeEnable(scope.row)"></el-switch>
        </template>
      </el-table-column>

      <el-table-column label="操作"  width="250" align="center" fixed="right">
        <template slot-scope="scope">
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

  </div>
</template>

<script>
export default {
  name: "FileUP",
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
      this.request.get("/file/page",{
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
    downloadFile(url){
      // url.slice(27)
      window.open(url)
    },
    del(id){
      this.request.delete("/file/delete/"+id).then(res=>{
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
      this.request.delete("/file/batchDelete",{data : ids}).then(res=>{
        if (res.data){
          this.$message.success("批量删除成功")
          this.load()
        }else {
          this.$message.error("批量删除失败")
        }
      })
    },
    handleEdit(row){
      this.form = row;
      this.dialogFormVisible =true;
    },
    reset(){
      this.name = "";
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
    handleFileUploadSuccess(res){
      console.log(res);
      this.$message.success("文件上传成功");
      this.load();
    },
    changeEnable(row){
      this.request.post("/file/update",row).then(res=>{
        if (res.code === '200'){
          this.$message.success("更新成功")
        }
      })
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