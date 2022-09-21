<template>
  <div class="company-graph q-pa-lg">
    <div>기업별 뉴스분석</div>
    <q-separator inset />
    <CompanyBarChart />
  </div>
</template>

<script>
import CompanyBarChart from "../charts/CompanyBarChart.vue";
export default {
  setup() {},
  components: { CompanyBarChart },
};
</script>

<style scoped>
.company-graph {
  /* common */
  width: 1200px;
  background-color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
