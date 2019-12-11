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
        label="profile"
        width="150"
        align="center"
      >
        <template slot-scope="scope">
          {{ scope.row.profile.name }}
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
        label="操作"
        width="200"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="primary" 
            @click="handleModify(scope.row)">
            修改
          </el-button>
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

    <!-- 更新/新增dialog -->
    <el-dialog 
      :title="textMap[dialogStatus]" 
      :visible.sync="dialogFormVisible"
    >
      <el-form ref="dataForm" :rules="rules" :model="temp" label-position="left" label-width="70px" style="width: 400px; margin-left:50px;">
        <el-form-item
          label="Id"
          prop="id"
        >
          <el-input v-model="temp.id" :disabled="true" />
        </el-form-item>

        <el-form-item label="名称" prop="name">
          <el-input v-model="temp.name" />
        </el-form-item>
        
        <el-form-item label="描述" prop="description">
          <el-input v-model="temp.description" :autosize="{ minRows: 2, maxRows: 4}" type="textarea" />
        </el-form-item>

        <el-form-item label="操作状态" prop="operatingState">
          <el-select v-model="temp.operatingState" class="filter-item" placeholder="请选择">
            <el-option v-for="item in operatingStates" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>

        <el-form-item label="管理状态" prop="adminState">
          <el-select v-model="temp.adminState" class="filter-item" placeholder="请选择">
            <el-option v-for="item in adminStates" :key="item" :label="item" :value="item" />
          </el-select>
        </el-form-item>

        <el-form-item label="profileName" prop="profile.name">
          <el-input v-model="temp.profile.name" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">
          取消
        </el-button>
        <el-button type="primary" @click="dialogStatus==='create'?createData():updateData()">
          确认
        </el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { getDeviceDetailList } from '@/api/device'

export default {
  name: 'DeviceDetail',
  data() {
    return {
      list: null,
      listLoading: true,
      temp: {
        name: '',
        description: '',
        profile: {
          name: ''
        }
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '更新',
        create: '新增'
      },
      rules: {
        name: [{ required: true, message: '设备名不能为空', trigger: 'blur' }]
      },
      operatingStates: ['ENABLE', 'DISABLE'],
      adminStates: ['LOCKED', 'UNLOCKED']
    }
  },
  created() {
    console.log('生命周期函数：created')
    this.fetchData()
  },
  methods: {
    fetchData() {
      this.listLoading = true
      console.log('查询数据:' + this.$route.query.name)
      getDeviceDetailList(this.$route.query.name).then(response => {
        console.log('getDeviceServiceList:' + JSON.stringify(response))
        this.list = response
        this.listLoading = false
      })
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
    },
    handleModify(row) {
      console.log('handleModify:' + row)
      this.temp = Object.assign({}, row)//copy obj
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      // 异步移除表单的校验结果
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    // 保存修改
    updateData() {
      console.log('保存修改:')
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          for (const v of this.list) {
            if (v.id === this.temp.id) {
              const index = this.list.indexOf(v)
              // 把原来的删掉，把新的加进来
              this.list.splice(index, 1, this.temp)
              break
            }
          }
          this.dialogFormVisible = false
          this.$notify({
            title: 'Success',
            message: '更新设备成功',
            type: 'success',
            duration: 2000
          })
        }
      })
    },
    handleDelete(row) {
      console.log('handleDelete:' + row)
      this.$confirm('此操作将永久删除该设备, 是否继续?', '提示', {
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
