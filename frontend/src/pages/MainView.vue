<template>
  <div class="flex flex-center">
    <main-banner />
    <word-cloud />
    <main-news />
  <q-card class="company-graph  q-pa-md">
     <q-card-section class="row justify-center">
      <main-bar-chart />
    </q-card-section>

  </q-card>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import MainBanner from "src/components/main/MainBanner.vue";
import WordCloud from "src/components/main/WordCloud.vue";
import MainNews from "src/components/main/MainNews.vue";
import MainBarChart from "src/components/charts/MainBarChart.vue";

export default defineComponent({
  name: "MainView",
  setup() {},
  components: { MainBanner, WordCloud, MainNews, MainBarChart },
});
</script>

<style scoped>
.company-graph {
  /* common */
  width: 1050px;
  background-color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
  font-family: "NanumBarunGothic";
}
</style>
