<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="视频类型" prop="videoType">
        <el-select v-model="queryParams.videoType" placeholder="请选择视频类型" clearable>
          <el-option
            v-for="dict in dict.type.video_type"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="视频简介" prop="videoBrief">
        <el-input
          v-model="queryParams.videoBrief"
          placeholder="请输入视频简介"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="视频状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择视频状态" clearable>
          <el-option
            v-for="dict in dict.type.video_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['video:video:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['video:video:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['video:video:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['video:video:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="videoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column type="expand">
        <template slot-scope="props">
        <el-form label-position="left" inline >
          <el-form-item label="详情：">
            <p v-html="props.row.videoContent"></p>
          </el-form-item>
        </el-form>
      </template>
      </el-table-column>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="视频id" align="center" prop="videoId" />
      <el-table-column label="视频类型" align="center" prop="videoType">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.video_type" :value="scope.row.videoType"/>
        </template>
      </el-table-column>
      <el-table-column label="视频简介" align="center" prop="videoBrief" />
      <el-table-column label="视频路径" align="center" prop="videoPath" />
      <el-table-column label="视频封面" align="center" prop="imgPath" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.imgPath" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="视频状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.video_status" :value="scope.row.status"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['video:video:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['video:video:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改视频信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="900px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="视频类型" prop="videoType">
          <el-select v-model="form.videoType" placeholder="请选择视频类型">
            <el-option
              v-for="dict in dict.type.video_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="视频简介" prop="videoBrief">
          <el-input v-model="form.videoBrief" placeholder="请输入视频简介" />
        </el-form-item>
        <el-form-item label="视频详情">
          <editor v-model="form.videoContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="视频路径" prop="videoPath">
          <file-upload v-model="form.videoPath" :limit="1" :fileSize="200" :fileType="['mp4','avi']"/>
        </el-form-item>
        <el-form-item label="视频封面" prop="imgPath">
          <image-upload v-model="form.imgPath"/>
        </el-form-item>
        <el-form-item label="视频状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio
              v-for="dict in dict.type.video_status"
              :key="dict.value"
              :label="dict.value"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listVideo, getVideo, delVideo, addVideo, updateVideo } from "@/api/video/video";

export default {
  name: "Video",
  dicts: ['video_type', 'video_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 视频信息表格数据
      videoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        videoType: null,
        videoBrief: null,
        videoContent: null,
        videoPath: null,
        imgPath: null,
        status: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        videoType: [
          { required: true, message: "视频类型不能为空", trigger: "change" }
        ],
        videoBrief: [
          { required: true, message: "视频简介不能为空", trigger: "blur" }
        ],
        videoPath: [
          { required: true, message: "视频路径不能为空", trigger: "blur" }
        ],
        imgPath: [
          { required: true, message: "视频封面不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "视频状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询视频信息列表 */
    getList() {
      this.loading = true;
      listVideo(this.queryParams).then(response => {
        this.videoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        videoId: null,
        videoType: null,
        videoBrief: null,
        videoContent: null,
        videoPath: null,
        imgPath: null,
        status: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.videoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加视频信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const videoId = row.videoId || this.ids
      getVideo(videoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改视频信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.videoId != null) {
            updateVideo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addVideo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const videoIds = row.videoId || this.ids;
      this.$modal.confirm('是否确认删除视频信息编号为"' + videoIds + '"的数据项？').then(function() {
        return delVideo(videoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('video/video/export', {
        ...this.queryParams
      }, `video_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
