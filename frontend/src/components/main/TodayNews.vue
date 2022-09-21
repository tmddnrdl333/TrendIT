<template>
  <div class="today__news__container">
    <first-news :test="data"></first-news>
    <div class="next__next__news__container">
      <next-news
        v-for="(item, index) in datas"
        v-bind:key="index"
        v-bind:test="item"
      ></next-news>
    </div>
  </div>
</template>

<script>
import FirstNews from "./FirstNews.vue";
import NextNews from "./NextNews.vue";
export default {
  name: "TodayNews",
  components: {
    FirstNews,
    NextNews,
  },
  data() {
    return {
      data: {
        headline: "test133",
        content: "test233",
      },
      datas: [
        {
          headline: "test1",
          content: "test2",
        },
        {
          headline: "test32",
          content: "test33",
        },
        {
          headline: "test1",
          content: "test2",
        },
        {
          headline: "test32",
          content: "test33",
        },
      ],
    };
  },
};
</script>

<style scoped>
.today__next__news__container {
  display: flex;
  flex-direction: column;
}
.today__news__container {
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
</style>
