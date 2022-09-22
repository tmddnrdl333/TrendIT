<template>
  <div class="first__news__container">
    <img
      class="first__news__img"
      src="https://cdn.quasar.dev/img/parallax2.jpg"
    />
    <news-head-body-piece :test="test"></news-head-body-piece>
  </div>
</template>

<script>
import NewsHeadBodyPiece from "./NewsHeadBodyPiece.vue";
export default {
  name: "FirstNews",
  components: {
    NewsHeadBodyPiece,
  },
  props: {
    test: Object,
  },
  created() {
    console.log(this.$props);
  },
};
</script>

<style scoped>
.first__news__container {
  max-width: 400px;
  display: flex;
  flex-direction: column;
}
.first__news__img {
  border-radius: 3px;
}
</style>
