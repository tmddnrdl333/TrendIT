<template>
  <div class="q-pa-md row items-start q-gutter-md">
    <q-card class="my-card rank__container">
      <q-card-section> 키워드 순위 </q-card-section>
      <q-separator inset />
      <word-rank-piece
        v-for="(item, index) in trendRankData"
        v-bind:key="item"
        v-bind:keywordRank="index + 1"
        v-bind:keywordName="item.getText()"
        v-bind:keywordFrequency="item.getSize()"
      ></word-rank-piece>
    </q-card>
  </div>
</template>

<script>
import WordRankPiece from "./WordRankPiece.vue";
export default {
  name: "WordRank",
  components: {
    WordRankPiece,
  },
  props: {
    trendRankData: Array,
  },
  data() {
    return {
      items: [
        {
          text: "비트코인",
          frequency: 168,
        },
        {
          text: "카카오",
          frequency: 132,
        },
        {
          text: "네이버",
          frequency: 122,
        },
        {
          text: "삼성전자",
          frequency: 111,
        },
        {
          text: "빗썸",
          frequency: 99,
        },
        {
          text: "코인",
          frequency: 78,
        },
      ],
    };
  },

  mounted() {},

  methods: {},
};
</script>

<style scoped>
.rank__container {
  min-width: 200px;
  width: 400px;
  display: flex;
  flex-direction: column;
}
</style>
