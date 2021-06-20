<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="12">
            <a-form-model-item label="行政区" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="district">
  	          <j-tree-select
                ref="treeSelect"
                placeholder="请选择行政区"
                v-model="model.district"
                dict="fos_sys_area,area_name,id"
                pidValue="0"
                >
              </j-tree-select>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="收藏人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="collector">
              <a-input v-model="model.collector" placeholder="请输入收藏人"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="收藏人性质" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="collectorNature">
              <j-dict-select-tag type="list" v-model="model.collectorNature" dictCode="fos_collector_nature" placeholder="请选择收藏人性质" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="联系人及联系电话" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="contactAndPhone">
              <a-input v-model="model.contactAndPhone" placeholder="请输入联系人及联系电话"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="标本名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specimenName">
              <a-input v-model="model.specimenName" placeholder="请输入标本名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="拉丁文名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="latinName">
              <a-input v-model="model.latinName" placeholder="请输入拉丁文名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :lg="24">
            <a-form-model-item label="图片" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specimenPictures">
              <j-image-upload isMultiple  v-model="fileList" ></j-image-upload>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specimenId">
              <a-input v-model="model.specimenId" placeholder="请输入编号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="收藏日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="collectDate">
              <j-date placeholder="请选择收藏日期" v-model="model.collectDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="标本来源" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specimenOrigin">
              <j-dict-select-tag type="list" v-model="model.specimenOrigin" dictCode="fos_specimen_origin" placeholder="请选择标本来源" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="标本位置" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specimenPosition">
              <a-input v-model="model.specimenPosition" placeholder="请输入标本位置"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="模式标本" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="model">
              <j-dict-select-tag type="radio" v-model="model.model" dictCode="is_open" placeholder="请选择模式标本" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="模式类型" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="modelType">
              <j-dict-select-tag type="list" v-model="model.modelType" dictCode="fos_model_type" placeholder="请选择模式类型" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="标本类别" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specimenFamily">
              <j-dict-select-tag type="list" v-model="model.specimenFamily" dictCode="fos_specimen_family" placeholder="请选择标本类别" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="标本去向" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specimenWhereabouts">
              <j-dict-select-tag type="list" v-model="model.specimenWhereabouts" dictCode="fos_specimen_whereabouts" placeholder="请选择标本去向" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="保护级别" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="protectLevel">
              <j-dict-select-tag type="list" v-model="model.protectLevel" dictCode="fos_protect_level" placeholder="请选择保护级别" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="标本状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="protectState">
              <j-dict-select-tag type="list" v-model="model.protectState" dictCode="fos_protect_state" placeholder="请选择标本状态" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="产出地点" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specimenSite">
              <a-input v-model="model.specimenSite" placeholder="请输入产出地点"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="时代" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="age">
              <a-input v-model="model.age" placeholder="请输入时代"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="标本描述" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="discription">
              <a-input v-model="model.discription" placeholder="请输入标本描述"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="鉴定人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="appraiser">
              <a-input v-model="model.appraiser" placeholder="请输入鉴定人"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="鉴定日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="appraisalDate">
              <j-date placeholder="请选择鉴定日期" v-model="model.appraisalDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="产地经." :labelCol="labelCol" :wrapperCol="wrapperCol" prop="siteLongitude">
              <a-input v-model="model.siteLongitude" placeholder="请输入产地经."  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="产地纬." :labelCol="labelCol" :wrapperCol="wrapperCol" prop="siteLatitude">
              <a-input v-model="model.siteLatitude" placeholder="请输入产地纬."  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="数量" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="quantity">
              <a-input-number v-model="model.quantity" placeholder="请输入数量" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="自采号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="serialNumber">
              <a-input v-model="model.serialNumber" placeholder="请输入自采号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="尺寸" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="specimenSize">
              <a-input v-model="model.specimenSize" placeholder="请输入尺寸"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="岩性" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rockCharacter">
              <a-input v-model="model.rockCharacter" placeholder="请输入岩性"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="地质层位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="rockUnit">
              <a-input v-model="model.rockUnit" placeholder="请输入地质层位"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="门" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="phylum">
              <a-input v-model="model.phylum" placeholder="请输入门"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="纲" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="cclass">
              <a-input v-model="model.cclass" placeholder="请输入纲"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="目" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="oorder">
              <a-input v-model="model.oorder" placeholder="请输入目"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="科" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="family">
              <a-input v-model="model.family" placeholder="请输入科"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="属" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="genus">
              <a-input v-model="model.genus" placeholder="请输入属"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="种" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="species">
              <a-input v-model="model.species" placeholder="请输入种"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="发掘单位" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="excavationUnit">
              <a-input v-model="model.excavationUnit" placeholder="请输入发掘单位"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="发掘日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="excavationDate">
              <j-date placeholder="请选择发掘日期" v-model="model.excavationDate"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="发掘原因" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="excavationReason">
              <a-input v-model="model.excavationReason" placeholder="请输入发掘原因"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="发掘批复函" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="excavationReply">
              <a-input v-model="model.excavationReply" placeholder="请输入发掘批复函"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="发掘评审" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="excavationReview">
              <a-input v-model="model.excavationReview" placeholder="请输入发掘评审"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="备注" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="remarks">
              <a-input v-model="model.remarks" placeholder="请输入备注"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="自设编号" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="serialId">
              <a-input v-model="model.serialId" placeholder="请输入自设编号"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="文献记录" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="documentation">
              <a-input v-model="model.documentation" placeholder="请输入文献记录"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="修复记录" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="repairRecord">
              <a-input v-model="model.repairRecord" placeholder="请输入修复记录"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createBy">
              <a-input v-model="model.createBy" placeholder="请输入创建人"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="创建日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="createTime">
              <j-date placeholder="请选择创建日期" v-model="model.createTime"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="更新人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="updateBy">
              <a-input v-model="model.updateBy" placeholder="请输入更新人"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="更新日期" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="updateTime">
              <j-date placeholder="请选择更新日期" v-model="model.updateTime"  style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <a-col :span="12">
            <a-form-model-item label="所属部门" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="sysOrgCode">
              <a-input v-model="model.sysOrgCode" placeholder="请输入所属部门"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'
  import JImageUpload from '@/components/jeecg/JImageUpload'
  export default {
    name: 'FosSpecimenRegisterInfoForm',
    components: {
      JImageUpload
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
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
        fileList: [],
        confirmLoading: false,
        validatorRules: {
           specimenName: [
              { required: true, message: '请输入标本名称!'},
           ],
           specimenId: [
              { required: true, message: '请输入编号!'},
           ],
           collectDate: [
              { required: true, message: '请输入收藏日期!'},
           ],
           specimenOrigin: [
              { required: true, message: '请输入标本来源!'},
           ],
           specimenPosition: [
              { required: true, message: '请输入标本位置!'},
           ],
           model: [
              { required: true, message: '请输入模式标本!'},
           ],
           specimenFamily: [
              { required: true, message: '请输入标本类别!'},
           ],
           specimenWhereabouts: [
              { required: true, message: '请输入标本去向!'},
           ],
           protectLevel: [
              { required: true, message: '请输入保护级别!'},
           ],
           protectState: [
              { required: true, message: '请输入标本状态!'},
           ],
           serialId: [
              { required: true, message: '请输入自设编号!'},
           ],
        },
        url: {
          add: "/fossil/fosSpecimenRegisterInfo/add",
          edit: "/fossil/fosSpecimenRegisterInfo/edit",
          queryById: "/fossil/fosSpecimenRegisterInfo/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
        if (record.id) {
          setTimeout(() => {
            this.fileList = record.specimenPictures
          }, 5)
        }
      },
      submitForm () {
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
            if(this.fileList != '') {
              this.model.specimenPictures = this.fileList;
            }else{
              this.model.specimenPictures = '';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>