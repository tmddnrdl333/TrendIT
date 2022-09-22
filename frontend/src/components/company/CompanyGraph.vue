<template>
  <q-card class="company-graph q-pa-lg">
    <q-card-section>
      <div>기업별 뉴스분석</div>
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
.company-graph {
  /* common */
  width: 1200px;
  background-color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
