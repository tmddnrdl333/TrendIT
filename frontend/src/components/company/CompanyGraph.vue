<template>
  <q-card class="company-graph q-pa-md">
    <q-card-section class="card-title">
        <div class="title q-mt-xs">기업별 뉴스 분석</div>
      </q-card-section>
    <q-separator inset />
    <q-card-section class="row justify-center">
      <company-bar-chart />
    </q-card-section>
  </q-card>
</template>

<script>
import CompanyBarChart from "../charts/CompanyBarChart.vue";
export default {
  setup() {},
  components: { CompanyBarChart },
};
</script>

<style scoped>
.title {
  font-family: "NanumBarunGothicBold";
  font-size: 17px;
  /* margin-top:10px; */
  
}
.company-graph {
  /* common */
  max-width: 1050px;
  width: 100%;
  background-color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;

  font-family: "NanumBarunGothic";
}
</style>
