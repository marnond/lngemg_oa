<template>

  <iframe :id="id" :src="url" frameborder="0" width="100%" :height="height" scrolling="auto"
          style="background-color: #fff;"></iframe>

</template>

<script>

  export default {
    name: 'IframeFineReportView',
    props: {
      url: {
        type: String,
        required: true
      },
      height: {
        type: String,
        default: '800px'
      }
    },
    data() {
      return {
        // url: '',
        id: ''
      }
    },
    created() {
      // this.goUrl()
    },
    updated() {
      // this.goUrl()
    },
    watch: {
      $route(to, from) {
        // this.goUrl()
      }
    },
    methods: {
      goUrl() {
        let url = this.$route.meta.url
        this.id = this.$route.path
        //url = "http://www.baidu.com"
        console.log('------url------' + url)
        if (url !== null && url !== undefined) {
          this.url = url
          //window.open(this.url);
        }
      }
    }
  }
</script>

<style>
</style>