<template>
  <div class="word__container">
    <div class="word__container__rank">{{ keywordRank }}</div>
    <div class="word__container__name">{{ keywordName }}</div>
    <div class="word__container__frequency">{{ keywordFrequency }}건</div>
  </div>
  <q-separator inset />
</template>

<script>
export default {
  name: "WordRankPiece",

  props: {
    keywordRank: Number,
    keywordName: String,
    keywordFrequency: Number,
  },
  data() {
    return {};
  },

  mounted() {},

  methods: {},
};
</script>

<style scoped>
.word__container {
  margin: 10px;
  display: flex;
}
.word__container__rank {
  text-align: center;
  flex-basis: 20%;
}
.word__container__name {
  text-align: center;
  flex-basis: 50%;
}
.word__container__frequency {
  font-size: 10px;
  color: rgb(120, 125, 129);
  text-align: center;
  flex-basis: 30%;
}
</style>
