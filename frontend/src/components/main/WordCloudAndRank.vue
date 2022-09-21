<template>
  <div class="wordcloud__container">
    <word-cloud></word-cloud>
    <word-rank></word-rank>
  </div>
</template>

<script>
import WordCloud from "./WordCloud.vue";
import WordRank from "./WordRank.vue";

export default {
  name: "WordCloudAndRank",
  components: {
    WordCloud,
    WordRank,
  },
  data() {
    return {};
  },

  mounted() {},

  methods: {},
};
</script>

<style scoped>
.wordcloud__container {
  display: flex;
  flex-direction: row;
  align-items: center;
}
</style>
