<template>
  <div>
    <div>
      <el-input style="width: 200px" placeholder="Search Operation" v-model="operation"></el-input>
      <el-select style="margin: 0 5px" v-model="type">
        <el-option v-for="item in ['Add', 'Update', 'Delete']" :key="item" :value="item" :label="item"></el-option>
      </el-select>
      <el-input style="width: 200px" placeholder="Search operator" v-model="optUser"></el-input>
      <el-button type="primary" style="margin-left: 10px" @click="load(1)">Search</el-button>
      <el-button type="info" @click="reset">Reset</el-button>
    </div>
    <div style="margin: 10px 0">
      <el-button type="danger" plain @click="delBatch">batch delete</el-button>
    </div>
    <el-table :data="tableData" stripe :header-cell-style="{ backgroundColor: 'aliceblue', color: '#666' }"
              @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"></el-table-column>
      <el-table-column prop="id" label="id" width="70" align="center"></el-table-column>
      <el-table-column prop="operation" label="operation"></el-table-column>
      <el-table-column prop="type" label="type">
        <template v-slot="scope">
          <el-tag type="primary" v-if="scope.row.type === '新增'">{{ scope.row.type }}</el-tag>
          <el-tag type="info" v-if="scope.row.type === '修改'">{{ scope.row.type }}</el-tag>
          <el-tag type="danger" v-if="scope.row.type === '删除'">{{ scope.row.type }}</el-tag>
          <el-tag type="danger" v-if="scope.row.type === '批量删除'">{{ scope.row.type }}</el-tag>
          <el-tag type="success" v-if="scope.row.type === '登录'">{{ scope.row.type }}</el-tag>
          <el-tag type="success" v-if="scope.row.type === '注册'">{{ scope.row.type }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="ip" label="ip"></el-table-column>
      <el-table-column prop="user" label="Operator"></el-table-column>
      <el-table-column prop="time" label="time"></el-table-column>
      <el-table-column label="Operation" align="center" width="180">
        <template v-slot="scope">
          <el-button size="mini" type="danger" plain @click="del(scope.row.id)">delete</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-size="pageSize"
          layout="total, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>

export default {
  name: "Logs",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 5,  // 每页显示的个数
      operation: '',
      total: 0,
      form: {},
      user: JSON.parse(localStorage.getItem('honey-user') || '{}'),
      ids: [],
      type: '',
      optUser: ''
    }
  },
  created() {
    this.load()
  },
  methods: {
    delBatch() {
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确认批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/logs/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)
    },
    del(id) {
      this.$confirm('Confirm to delete？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/logs/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('Operation success')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    reset() {
      this.operation = ''
      this.type = ''
      this.optUser = ''
      this.load()
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/logs/selectByPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          operation: this.operation,
          type: this.type,
          user: this.optUser,
        }
      }).then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style>
.el-tooltip__popper {
  max-width: 300px !important;
}
</style>