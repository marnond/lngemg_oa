<template>
  <j-modal
    :title="title"
    :width="width"
    :visible="visible"
    :confirmLoading="confirmLoading"
    switchFullscreen
    @ok="handleOk"
    @cancel="handleCancel"
    cancelText="关闭">
    <a-spin :spinning="confirmLoading">
      <a-form-model ref="form" :model="model" :rules="validatorRules">
        <a-form-model-item label="父级节点" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="pid">
          <j-tree-select
            ref="treeSelect"
            placeholder="请选择父级节点"
            v-model="model.pid"
            dict="fos_sys_area,area_name,id"
            pidField="pid"
            pidValue="0"
            hasChildField="has_child"
            >
          </j-tree-select>
        </a-form-model-item>
        <a-form-model-item label="所有父级节点" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="pids">
          <a-input v-model="model.pids" placeholder="请输入所有父级节点" ></a-input>
        </a-form-model-item>
        <a-form-model-item label="是否最末级" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="treeLeaf">
          <a-input v-model="model.treeLeaf" placeholder="请输入是否最末级" ></a-input>
        </a-form-model-item>
        <a-form-model-item label="层次级别" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="treeLevel">
          <a-input v-model="model.treeLevel" placeholder="请输入层次级别" ></a-input>
        </a-form-model-item>
        <a-form-model-item label="全节点名" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="treeNames">
          <a-input v-model="model.treeNames" placeholder="请输入全节点名" ></a-input>
        </a-form-model-item>
        <a-form-model-item label="区域名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="areaName">
          <a-input v-model="model.areaName" placeholder="请输入区域名称" ></a-input>
        </a-form-model-item>
        <a-form-model-item label="区域类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="areaType">
          <a-input v-model="model.areaType" placeholder="请输入区域类型" ></a-input>
        </a-form-model-item>
        <a-form-model-item label="状态（0正常 1删除 2停用）" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="status">
          <a-input v-model="model.status" placeholder="请输入状态（0正常 1删除 2停用）" ></a-input>
        </a-form-model-item>
        
      </a-form-model>
    </a-spin>
  </j-modal>
</template>

<script>

  import { httpAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'
  export default {
    name: "FosSysAreaModal",
    components: { 
    },
    data () {
      return {
        title:"操作",
        width:800,
        visible: false,
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },

        confirmLoading: false,
        validatorRules: {
           pid: [
              { required: true, message: '请输入父级节点!'},
           ],
           pids: [
              { required: true, message: '请输入所有父级节点!'},
           ],
           treeLeaf: [
              { required: true, message: '请输入是否最末级!'},
           ],
           treeLevel: [
              { required: true, message: '请输入层次级别!'},
           ],
           treeNames: [
              { required: true, message: '请输入全节点名!'},
           ],
           areaName: [
              { required: true, message: '请输入区域名称!'},
           ],
           areaType: [
              { required: true, message: '请输入区域类型!'},
           ],
           status: [
              { required: true, message: '请输入状态（0正常 1删除 2停用）!'},
           ],
        },
        url: {
          add: "/tree/fosSysArea/add",
          edit: "/tree/fosSysArea/edit",
        },
        expandedRowKeys:[],
        pidField:"pid"
     
      }
    },
    created () {
       //备份model原始值
       this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add (obj) {
        this.modelDefault.pid=''
        this.edit(Object.assign(this.modelDefault , obj));
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      close () {
        this.$emit('close');
        this.visible = false;
        this.$refs.form.clearValidate()
      },
      handleOk () {
        const that = this;
        // 触发表单验证
       this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
             if(this.model.id && this.model.id === this.model[this.pidField]){
              that.$message.warning("父级节点不能选择自己");
              that.confirmLoading = false;
              return;
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                this.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
              that.close();
            })
          }else{
             return false
          }
        })
      },
      handleCancel () {
        this.close()
      },
      submitSuccess(formData,flag){
        if(!formData.id){
          let treeData = this.$refs.treeSelect.getCurrTreeData()
          this.expandedRowKeys=[]
          this.getExpandKeysByPid(formData[this.pidField],treeData,treeData)
          this.$emit('ok',formData,this.expandedRowKeys.reverse());
        }else{
          this.$emit('ok',formData,flag);
        }
      },
      getExpandKeysByPid(pid,arr,all){
        if(pid && arr && arr.length>0){
          for(let i=0;i<arr.length;i++){
            if(arr[i].key==pid){
              this.expandedRowKeys.push(arr[i].key)
              this.getExpandKeysByPid(arr[i]['parentId'],all,all)
            }else{
              this.getExpandKeysByPid(pid,arr[i].children,all)
            }
          }
        }
      }
      
      
    }
  }
</script>