<template>
  <div>
    <div style="margin: 10px 0 20px 0; font-size: 30px; font-weight: bold">{{ news.title }}</div>
    <div style="margin: 10px 0; font-size: 13px; color: #666">{{ news.author }}  <span style="margin-left: 20px">{{ news.time }}</span></div>
    <div style=" box-shadow: 0 0 10px rgba(0, 0, 0, .12); padding: 10px">
      <div v-html="news.content" ></div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'newsDetail',
  data() {
    return {
      news: {}
    }
  },
  created() {
    let id = this.$route.query.id
    if (!id) {
      return
    }
    this.$request.get('/news/selectById/' + id).then(res => {
      this.news = res.data
    })
  }
}
</script>