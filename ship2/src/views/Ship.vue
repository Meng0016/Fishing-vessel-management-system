<template>
<div>
  <div style="padding: 10px 0 ">
    <el-input style="width: 200px" placeholder="请输入船只名称" suffix-icon="el-icon-ship" v-model="shipName"></el-input>
    <el-input class="ml-5" style="width: 200px" placeholder="请输入船只编号" suffix-icon="el-icon-s-order" v-model="shipCode"> </el-input>
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
        action="http://localhost:9999/ship/import"
        style="display: inline-block"
        class="mr-5"
        :show-file-list="false"
        accept="xlsx"
        :on-success="importExcel">
      <el-button type="primary" class="ml-5">导入 <i class="el-icon-bottom"></i></el-button>
    </el-upload>
    <el-button type="primary" @click="exportExcel">导出 <i class="el-icon-top"></i></el-button>
  </div>

  <el-table :data="tableData" border stripe header-cell-class-name="headerBg" @selection-change="handleSelectionChange" style="text-align: center">
    <el-table-column type="selection" width="55" fixed="left"></el-table-column>
    <el-table-column prop="shipId" label="ID" fixed="left"></el-table-column>
    <el-table-column prop="shipName" label="船只名称" fixed="left" ></el-table-column>
    <el-table-column prop="shipCode" label="船只编号" fixed="left"></el-table-column>
    <el-table-column prop="shipPort" label="船籍港"  ></el-table-column>
    <el-table-column prop="shipType" label="船只类型"></el-table-column>
    <el-table-column  prop="shipWidth" label="船只宽度" ></el-table-column>
    <el-table-column prop="shipLength" label="船只长度" ></el-table-column>
    <el-table-column prop="typeDeep" label="型深" ></el-table-column>
    <el-table-column prop="totalTons" label="总吨数"  ></el-table-column>
    <el-table-column prop="hostMode" label="主机型号" ></el-table-column>
    <el-table-column prop="hostNum" label="主机数量"></el-table-column>
    <el-table-column prop="hostId" label="主机编号"></el-table-column>
    <el-table-column prop="hostPower" label="主机功率"></el-table-column>
    <el-table-column prop="shipFactory" label="建造厂商"></el-table-column>
    <el-table-column prop="shipRegNum" label="检验登记号" width="100"></el-table-column>
    <el-table-column prop="shipPhysical" label="船只材质"></el-table-column>
    <el-table-column prop="shipArea" label="核定航区"></el-table-column>
    <el-table-column prop="endDate" label="完工日期"></el-table-column>
    <el-table-column prop="surEquip" label="救生设备"></el-table-column>
    <el-table-column prop="firEquip" label="消防设备"></el-table-column>
    <el-table-column prop="othEquip" label="其他设备"></el-table-column>
    <el-table-column prop="insId" label="渔船检验证书编号" width="135"></el-table-column>
    <el-table-column prop="natId" label="国籍证书编号" width="110"></el-table-column>
    <el-table-column prop="ownRgsid" label="所有权登记证书编号" width="150"></el-table-column>
    <el-table-column prop="ownDate" label="所有权取得日期" width="130"></el-table-column>
    <el-table-column prop="licenseId" label="渔业捕捞证许可号" width="140"></el-table-column>
    <el-table-column prop="fishGg" label="渔具规格"></el-table-column>
    <el-table-column prop="fishNum" label="渔具数量"></el-table-column>
    <el-table-column prop="workType" label="作业类型"></el-table-column>
    <el-table-column prop="workSpace" label="作业场所"></el-table-column>
    <el-table-column prop="ownName" label="所有人姓名" width="100"></el-table-column>
    <el-table-column prop="ownShares" label="所有人股份" width="100"></el-table-column>
    <el-table-column prop="ownAddress" label="所有人地址" width="100"></el-table-column>
    <el-table-column prop="ownId" label="所有人ID"></el-table-column>
    <el-table-column prop="ownPhone" label="所有人联系电话" width="120"></el-table-column>
    <el-table-column prop="mutalInfor" label="身份证"></el-table-column>
    <el-table-column prop="shipState" label="船只状态"></el-table-column>
    <el-table-column prop="files" label="文件"></el-table-column>
    <el-table-column prop="reUserId" label="注册人ID"></el-table-column>
    <el-table-column prop="regDate" label="注册时间"></el-table-column>
    <el-table-column prop="aprovDate" label="申请时间"></el-table-column>
    <el-table-column prop="fileFlag" label="文件状态"></el-table-column>
    <el-table-column prop="certDate" label="证书颁发日期" width="120"></el-table-column>
    <el-table-column prop="valid" label="有效期"></el-table-column>


    <el-table-column label="操作"  align="center" width="170px" fixed="right">
      <template slot-scope="scope">
        <el-button type="success" size="small" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
        <el-popconfirm
            class="ml-5"
            confirm-button-text='确定'
            cancel-button-text='取消'
            icon="el-icon-info"
            icon-color="red"
            title="您确定删除吗？"
            @confirm="del(scope.row.shipId)"
        >
          <el-button type="danger" size="small" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
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


  <el-dialog title="船只信息" :visible.sync="dialogFormVisible" width="40%">
    <el-form label-width="140px" >
      <el-row>
        <el-col :span="12">
          <el-form-item label="船只名称" >
            <el-input v-model="form.shipName" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="船只类型">
            <el-input v-model="form.shipCode" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="船籍港">
            <el-input v-model="form.shipPort" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="船只类型">
            <el-input v-model="form.shipType" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="船只长度">
            <el-input v-model="form.shipLength" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="船只宽度">
            <el-input v-model="form.shipWidth" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="型深">
            <el-input v-model="form.shipDeep" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="总吨数">
            <el-input v-model="form.totalTons" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="主机型号">
            <el-input v-model="form.hostModel" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主机数量">
            <el-input v-model="form.hostNum" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="主机编号">
            <el-input v-model="form.hostId" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="主机功率">
            <el-input v-model="form.hostPower" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="建造厂商">
            <el-input v-model="form.shipFactory" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="检验登记号">
            <el-input v-model="form.shipRegNum" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="船只材质">
            <el-input v-model="form.shipPhysical" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="核定航区">
            <el-input v-model="form.shipArea" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="完工日期">
            <el-input v-model="form.endDate" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="救生设备">
            <el-input v-model="form.surEquip" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="消防设备">
            <el-input v-model="form.firEquip" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="其他设备">
            <el-input v-model="form.othEquip" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="渔船检验证书编号">
            <el-input v-model="form.insId" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="国籍证书编号">
            <el-input v-model="form.natId" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="12">
          <el-form-item label="所有权登记证书编号">
            <el-input v-model="form.ownRgsid" style="width: 200px"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
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
  name: "Ship",
  data(){
    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      multipleSelection:[],
      dialogFormVisible: false,
      shipName: '',
      shipCode: '',
      form: {},
    }
  },
  methods:{
    load() {
      this.request.get("/ship/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          shipName: this.shipName,
          shipCode: this.shipCode
        }
      }).then(res => {
        console.log(res)
          this.tableData = res.data.records;
          this.total = res.data.total;
        })
      this.dialogFormVisible =false //BUG 用来改单击取消出现table数据改变的情况
    },
    handleSelectionChange(val){
      this.multipleSelection = val //将选中数组赋给multipleSelection
      console.log(val)
    },
    reset(){
      this.shipName = '';
      this.shipCode = '';
      this.load();
    },
    save(){
      this.request.post("/ship",this.form).then(res =>{
        if (res.data){
          this.$message.success("保存成功")
          this.dialogFormVisible=false
          this.load()
        }else {
          this.$message.error("保存失败")
        }
      })
    },
    del(shipId){
      this.request.delete("/ship/delete/"+shipId).then(res=>{
        if (res.data){
          this.$message.success("删除成功")
          this.dialogFormVisible=false
          this.load()
        }else {
          this.$message.error("删除失败")
        }
      })
    },
    batchDelete(){
      let ids = this.multipleSelection.map(v => v.shipId) //[{},{},{}]  =>[1,2,3]
      this.request.delete("/ship/batchDelete",{data : ids}).then(res=>{
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
      window.open("http://localhost:9999/ship/export")
    },
    handleAdd(){
      this.dialogFormVisible =true;
      this.form = {};
    },
    handleEdit(row){
      this.form = row;
      this.dialogFormVisible =true;
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
  created(){
    this.load()
  },
}
</script>

<style>

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