<template>
  <a-card :bordered="false">
    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline" @keyup.enter.native="searchQuery">
        <a-row :gutter="24">
        </a-row>
      </a-form>
    </div>
    <!-- 查询区域-END -->

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('古生物化石标本登记表')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <!-- 高级查询区域 -->
      <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        :scroll="{x:true}"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        class="j-table-force-nowrap"
        @change="handleTableChange">

        <template slot="htmlSlot" slot-scope="text">
          <div v-html="text"></div>
        </template>
        <template slot="imgSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
          <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
        </template>
        <template slot="fileSlot" slot-scope="text">
          <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
          <a-button
            v-else
            :ghost="true"
            type="primary"
            icon="download"
            size="small"
            @click="downloadFile(text)">
            下载
          </a-button>
        </template>

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>

    <fos-specimen-register-info-modal ref="modalForm" @ok="modalFormOk"></fos-specimen-register-info-modal>
  </a-card>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import FosSpecimenRegisterInfoModal from './modules/FosSpecimenRegisterInfoModal'

  export default {
    name: 'FosSpecimenRegisterInfoList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      FosSpecimenRegisterInfoModal
    },
    data () {
      return {
        description: '古生物化石标本登记表管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'行政区',
            align:"center",
            dataIndex: 'district'
          },
          {
            title:'收藏人',
            align:"center",
            dataIndex: 'collector'
          },
          {
            title:'收藏人性质',
            align:"center",
            dataIndex: 'collectorNature'
          },
          {
            title:'联系人及联系电话',
            align:"center",
            dataIndex: 'contactAndPhone'
          },
          {
            title:'标本名称',
            align:"center",
            dataIndex: 'specimenName'
          },
          {
            title:'拉丁文名称',
            align:"center",
            dataIndex: 'latinName'
          },
          {
            title:'编号',
            align:"center",
            dataIndex: 'specimenId'
          },
          {
            title:'收藏日期',
            align:"center",
            dataIndex: 'collectDate'
          },
          {
            title:'标本来源:自采/购置/交换/捐赠/罚没/其它',
            align:"center",
            dataIndex: 'specimenOrigin'
          },
          {
            title:'标本位置',
            align:"center",
            dataIndex: 'specimenPosition'
          },
          {
            title:'模式标本:是/否',
            align:"center",
            dataIndex: 'model'
          },
          {
            title:'模式类型:正模/副模/其它',
            align:"center",
            dataIndex: 'modelType'
          },
          {
            title:'标本类别',
            align:"center",
            dataIndex: 'specimenFamily'
          },
          {
            title:'标本去向:馆藏/借出研究/出境/国内展出/遗失/注销',
            align:"center",
            dataIndex: 'specimenWhereabouts'
          },
          {
            title:'保护级别:一级/二级/三级/待定',
            align:"center",
            dataIndex: 'protectLevel'
          },
          {
            title:'标本状态:完好/破损',
            align:"center",
            dataIndex: 'protectState'
          },
          {
            title:'产出地点',
            align:"center",
            dataIndex: 'specimenSite'
          },
          {
            title:'时代',
            align:"center",
            dataIndex: 'age'
          },
          {
            title:'标本描述',
            align:"center",
            dataIndex: 'discription'
          },
          {
            title:'鉴定人',
            align:"center",
            dataIndex: 'appraiser'
          },
          {
            title:'鉴定日期',
            align:"center",
            dataIndex: 'appraisalDate'
          },
          {
            title:'产地经.',
            align:"center",
            dataIndex: 'siteLongitude'
          },
          {
            title:'产地纬.',
            align:"center",
            dataIndex: 'siteLatitude'
          },
          {
            title:'数量',
            align:"center",
            dataIndex: 'quantity'
          },
          {
            title:'自采号',
            align:"center",
            dataIndex: 'serialNumber'
          },
          {
            title:'尺寸',
            align:"center",
            dataIndex: 'specimenSize'
          },
          {
            title:'岩性',
            align:"center",
            dataIndex: 'rockCharacter'
          },
          {
            title:'地质层位',
            align:"center",
            dataIndex: 'rockUnit'
          },
          {
            title:'门',
            align:"center",
            dataIndex: 'phylum'
          },
          {
            title:'纲',
            align:"center",
            dataIndex: 'cclass'
          },
          {
            title:'目',
            align:"center",
            dataIndex: 'oorder'
          },
          {
            title:'科',
            align:"center",
            dataIndex: 'family'
          },
          {
            title:'属',
            align:"center",
            dataIndex: 'genus'
          },
          {
            title:'种',
            align:"center",
            dataIndex: 'species'
          },
          {
            title:'发掘单位',
            align:"center",
            dataIndex: 'excavationUnit'
          },
          {
            title:'发掘日期',
            align:"center",
            dataIndex: 'excavationDate'
          },
          {
            title:'发掘原因',
            align:"center",
            dataIndex: 'excavationReason'
          },
          {
            title:'发掘批复函',
            align:"center",
            dataIndex: 'excavationReply'
          },
          {
            title:'发掘评审',
            align:"center",
            dataIndex: 'excavationReview'
          },
          {
            title:'备注',
            align:"center",
            dataIndex: 'remarks'
          },
          {
            title:'自设编号',
            align:"center",
            dataIndex: 'serialId'
          },
          {
            title:'文献记录',
            align:"center",
            dataIndex: 'documentation'
          },
          {
            title:'修复记录',
            align:"center",
            dataIndex: 'repairRecord'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        url: {
          list: "/fossil/fosSpecimenRegisterInfo/list",
          delete: "/fossil/fosSpecimenRegisterInfo/delete",
          deleteBatch: "/fossil/fosSpecimenRegisterInfo/deleteBatch",
          exportXlsUrl: "/fossil/fosSpecimenRegisterInfo/exportXls",
          importExcelUrl: "fossil/fosSpecimenRegisterInfo/importExcel",
          
        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
    this.getSuperFieldList();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'district',text:'行政区'})
        fieldList.push({type:'string',value:'collector',text:'收藏人'})
        fieldList.push({type:'string',value:'collectorNature',text:'收藏人性质'})
        fieldList.push({type:'string',value:'contactAndPhone',text:'联系人及联系电话'})
        fieldList.push({type:'string',value:'specimenName',text:'标本名称'})
        fieldList.push({type:'string',value:'latinName',text:'拉丁文名称'})
        fieldList.push({type:'string',value:'specimenId',text:'编号'})
        fieldList.push({type:'string',value:'collectDate',text:'收藏日期'})
        fieldList.push({type:'string',value:'specimenOrigin',text:'标本来源:自采/购置/交换/捐赠/罚没/其它'})
        fieldList.push({type:'string',value:'specimenPosition',text:'标本位置'})
        fieldList.push({type:'string',value:'model',text:'模式标本:是/否'})
        fieldList.push({type:'string',value:'modelType',text:'模式类型:正模/副模/其它'})
        fieldList.push({type:'string',value:'specimenFamily',text:'标本类别'})
        fieldList.push({type:'string',value:'specimenWhereabouts',text:'标本去向:馆藏/借出研究/出境/国内展出/遗失/注销'})
        fieldList.push({type:'string',value:'protectLevel',text:'保护级别:一级/二级/三级/待定'})
        fieldList.push({type:'string',value:'protectState',text:'标本状态:完好/破损'})
        fieldList.push({type:'string',value:'specimenSite',text:'产出地点'})
        fieldList.push({type:'string',value:'age',text:'时代'})
        fieldList.push({type:'string',value:'discription',text:'标本描述'})
        fieldList.push({type:'string',value:'appraiser',text:'鉴定人'})
        fieldList.push({type:'string',value:'appraisalDate',text:'鉴定日期'})
        fieldList.push({type:'string',value:'siteLongitude',text:'产地经.'})
        fieldList.push({type:'string',value:'siteLatitude',text:'产地纬.'})
        fieldList.push({type:'int',value:'quantity',text:'数量'})
        fieldList.push({type:'string',value:'serialNumber',text:'自采号'})
        fieldList.push({type:'string',value:'specimenSize',text:'尺寸'})
        fieldList.push({type:'string',value:'rockCharacter',text:'岩性'})
        fieldList.push({type:'string',value:'rockUnit',text:'地质层位'})
        fieldList.push({type:'string',value:'phylum',text:'门'})
        fieldList.push({type:'string',value:'cclass',text:'纲'})
        fieldList.push({type:'string',value:'oorder',text:'目'})
        fieldList.push({type:'string',value:'family',text:'科'})
        fieldList.push({type:'string',value:'genus',text:'属'})
        fieldList.push({type:'string',value:'species',text:'种'})
        fieldList.push({type:'string',value:'excavationUnit',text:'发掘单位'})
        fieldList.push({type:'string',value:'excavationDate',text:'发掘日期'})
        fieldList.push({type:'string',value:'excavationReason',text:'发掘原因'})
        fieldList.push({type:'string',value:'excavationReply',text:'发掘批复函'})
        fieldList.push({type:'string',value:'excavationReview',text:'发掘评审'})
        fieldList.push({type:'string',value:'remarks',text:'备注'})
        fieldList.push({type:'string',value:'serialId',text:'自设编号'})
        fieldList.push({type:'string',value:'documentation',text:'文献记录'})
        fieldList.push({type:'string',value:'repairRecord',text:'修复记录'})
        this.superFieldList = fieldList
      }
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less';
</style>