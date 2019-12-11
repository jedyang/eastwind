<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button 
        class="filter-item"
        style="float:right;"
        type="primary"
        icon="el-icon-edit"
        @click="handleCreate()">
        新增
      </el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :data="list"
      element-loading-text="Loading"
      stripe
      border
      fit
      highlight-current-row
      :header-cell-style="{background:'#eef1f6',color:'#606266'}"
    >
      <el-table-column
        align="center"
        label="序号"
        width="95"
      >
        <template slot-scope="scope">
          {{ scope.$index }}
        </template>
      </el-table-column>

      <el-table-column
        label="ID"
        align="center"
        width="250"
      >
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>

      <el-table-column
        label="Name"
        width="150"
        align="center"
      >
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>

      <el-table-column
        label="描述"
        align="center"
        width="150"
      >
        <template slot-scope="scope">
          {{ scope.row.description }}
        </template>
      </el-table-column>

      <el-table-column
        label="Labels"
        width="150"
        align="center"
      >
        <template slot-scope="scope">
          {{ scope.row.labels }}
        </template>
      </el-table-column>

      <el-table-column
        label="创建时间"
        width="150"
        align="center"
      >
        <template slot-scope="scope">
          {{ scope.row.created }}
        </template>
      </el-table-column>

      <el-table-column
        label="更新时间"
        width="150"
        align="center"
      >
        <template slot-scope="scope">
          {{ scope.row.modified }}
        </template>
      </el-table-column>
      
      <el-table-column
        align="center"
        label="操作"
        width="200"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.row)">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      layout="prev, pager, next"
      :total="9"
    />
  </div>
</template>

<script>
import { getDeviceProfileList } from '@/api/device'

export default {
  name: 'DeviceProfile',
  data() {
    return {
      list: null,
      listLoading: true
    }
  },
  created() {
    console.log('生命周期函数：created')
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      console.log('查询profile数据:')
      getDeviceProfileList().then(response => {
        console.log('getDeviceProfileList:' + JSON.stringify(response))
        this.list = response
        this.listLoading = false
      })
    },
    // 新增
    handleCreate() {
      console.log('handleCreate:')
    },
    // 保存新增设备
    createData() {
      console.log('保存新增设备:')
    },
    handleDelete(row) {
      console.log('handleDelete:' + row)
      this.$confirm('此操作将永久删除该定义, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$message({
          type: 'success',
          message: '删除成功!'
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
