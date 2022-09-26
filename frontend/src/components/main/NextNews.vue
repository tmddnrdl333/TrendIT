<template>
  <div class="next__news__container">
    <img
      class="next__nexs__img"
      src="https://cdn.quasar.dev/img/parallax2.jpg"
    />
    <news-head-body-piece class="next__news__piece" :news="news" />
  </div>
</template>

<script>
import NewsHeadBodyPiece from "./NewsHeadBodyPiece.vue";
export default {
  name: "FrontendNextNews",
  data() {
    return {};
  },
  props: {
    news: Object,
  },
  mounted() {},
  methods: {},
  components: { NewsHeadBodyPiece },
};
</script>

<style scoped>
.next__news__container {
  margin: 0 auto;
  display: flex;
  flex-direction: row;
  max-width: 500px;
  max-height: 200px;
}
.next__nexs__img {
  overflow: auto;
  max-width: 200px;
  border-radius: 4px;
  margin: 10px;
  width: 30%;
}
.next__news__piece {
  width: 70%;
  max-width: 300px;
  margin: 5px;
}
</style>
