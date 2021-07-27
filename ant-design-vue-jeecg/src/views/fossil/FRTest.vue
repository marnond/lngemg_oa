<template>
  <a-card :bordered='false'>
    <iframe-fine-report-view ref='iframePage' :url='this.iframeUrl'></iframe-fine-report-view>
  </a-card>
</template>

<script>
import IframeFineReportView from '@/components/layouts/IframeFineReportView'
import { getAction } from '@/api/manage'

export default {
  name: 'FRTest',
  components: { IframeFineReportView },
  data() {
    return {
      height: '800px',
      token: '',
      iframeUrl: '',
      cptUrl: window._CONFIG['fineReport_base_url'] + '/webroot/decision/view/report?viewlet=lnNatureOa/fossilSpecimen/fossil_statistics_test.cpt',
      cptName: 'lnNatureOa/fossilSpecimen/fossil_statistics_test.cpt',
      url: {
        getToken: '/fr/getToken'
      }
    }
  },
  created() {
  },
  mounted() {
    this.$nextTick(() => {
      this.initIframe()
    })
  },
  methods: {
    initIframe() {
      let that = this
      getAction(this.url.getToken, { path: that.cptName }).then(res => {
        if (res.success) {
          that.token = res.result
          that.iframeUrl = that.cptUrl + '&fine_digital_signature=' + that.token
        } else {
          that.$message.warning('请求frtoken失败，返回错误：' + res.message)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>