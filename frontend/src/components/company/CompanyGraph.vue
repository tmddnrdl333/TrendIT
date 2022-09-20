<template>
  <div class="company-graph q-pa-lg">
    <div>기업별 뉴스분석</div>
    <CompanyBarChart />
    <KeywordLineChart />
  </div>
</template>

<script>
import CompanyBarChart from "../charts/CompanyBarChart.vue";
import KeywordLineChart from "../charts/KeywordLineChart.vue";
export default {
  setup() {},
  components: { CompanyBarChart, KeywordLineChart },
};
</script>

<style scoped>
.company-graph {
  height: 450px;
  /* common */
  width: 1200px;
  background-color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
