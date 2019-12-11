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
        width="300"
      >
        <template slot-scope="scope">
          {{ scope.row.id }}
        </template>
      </el-table-column>
      <el-table-column 
        abel="Name"
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
        width="300"
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
        align="center"
        label="地址信息"
        width="200"
      >
        <template slot-scope="scope">
          <el-popover
            trigger="click"
            placement="top"
          >
            <p>ID: {{ scope.row.addressable.id }}</p>
            <p>Name: {{ scope.row.addressable.name }}</p>
            <p>协议: {{ scope.row.addressable.protocol }}</p>
            <p>地址: {{ scope.row.addressable.address }}</p>
            <p>端口: {{ scope.row.addressable.port }}</p>
            <p>路径: {{ scope.row.addressable.path }}</p>
            <div
              slot="reference"
              class="name-wrapper"
            >
              <el-button type="info">
                详细数据
              </el-button>
            </div>
          </el-popover>
        </template>
      </el-table-column>

      <el-table-column
        label="操作状态"
        width="150"
        align="center"
      >
        <template slot-scope="scope">
          {{ scope.row.operatingState }}
        </template>
      </el-table-column>

      <el-table-column
        label="管理状态"
        width="150"
        align="center"
      >
        <template slot-scope="scope">
          {{ scope.row.adminState }}
        </template>
      </el-table-column>

      <el-table-column
        align="center"
        label="设备列表"
        width="200"
      >
        <template slot-scope="scope">
          <el-button 
            type="primary" 
            @click="handleClick(scope.row)"
          >
            查看
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      layout="prev, pager, next"
      :total="9"
    />

    <!-- 新增deviceService的dialog -->
    <el-dialog :title="textMap['create']" :visible.sync="dialogFormVisible">

      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item label="名称" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>

        <el-form-item label="协议" prop="protocol">
          <el-input v-model="temp.protocol" />
        </el-form-item>

        <el-form-item label="地址" prop="address">
          <el-input v-model="temp.address" />
        </el-form-item>

        <el-form-item label="端口" prop="port">
          <el-input v-model="temp.port" />
        </el-form-item>

        <el-form-item label="路径" prop="path">
          <el-input v-model="temp.path" />
        </el-form-item>

        <el-form-item label="publisher" prop="publisher">
          <el-input v-model="temp.publisher" />
        </el-form-item>

        <el-form-item label="user" prop="user">
          <el-input v-model="temp.user" />
        </el-form-item>

        <el-form-item label="password" prop="password">
          <el-input v-model="temp.password" />
        </el-form-item>

        <el-form-item label="topic" prop="topic">
          <el-input v-model="temp.topic" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="createData()">
          确认
        </el-button>
      </div>
    </el-dialog>
    
  </div>
</template>

<script>
import { getDeviceServiceList } from '@/api/device'

export default {
  name: 'DeviceService',
  data() {
    return {
      list: null,
      listLoading: true,
      temp: {
        name: '',
        protocol: '',
        address: '',
        port: '',
        path: '',
        publisher: '',
        user: '',
        password: '',
        topic: ''
      },
      rules: {
        name: [{ required: true, message: '设备名不能为空', trigger: 'blur' }]
      },
      textMap: {
        create: '新增'
      },
      dialogFormVisible: false
    }
  },
  created() {
    console.log('生命周期函数：created')
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      console.log('查询数据')
      getDeviceServiceList().then(response => {
        console.log('getDeviceServiceList:' + JSON.stringify(response))
        this.list = response
        this.listLoading = false
      })
    },
    handleClick(row) {
      console.log(row)
      const location = '/deviceDetail/list'
      this.$router.push({
        path: location,
        query: {
          name: row.name
        }}
      )
    },
    // 新增设备
    handleCreate() {
      console.log('handleCreate:')
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
    },
    // 保存新增设备
    createData() {
      console.log('保存新增设备:')
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          this.temp.id = 'ba549f63-f700-41e5-b760-8cb096158abe' //先造个假id
          this.list.unshift(this.temp)
          this.dialogFormVisible = false
          this.$notify({
            title: 'Success',
            message: '新增设备成功',
            type: 'success',
            duration: 2000
          })
        }
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
