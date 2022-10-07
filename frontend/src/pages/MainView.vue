<template>
  <main-banner />
  <word-cloud />
  <main-news />
  <q-card class="company-graph  q-pa-md" style="margin: auto">
    <q-card-section class="card-title q-ml-lg">
      <div class="title q-mt-xs">기간별 트렌드 분석</div>
    </q-card-section>
     <q-card-section class="row justify-center">
      <main-bar-chart />
    </q-card-section>
  </q-card>
  <br />
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
.title {
  font-family: "NanumBarunGothicBold";
  font-size: 17px;
  margin-top: 10px;
}

.card-title {
  padding: 10px;
}
.company-graph {
  /* common */
  width: 1050px;
  background-color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
  font-family: "NanumBarunGothic";
}
</style>
