<template>
  <q-card class="keyword-analyze q-pa-lg">
    <q-card-section class="q-gutter-md">
      <div>키워드 분석</div>
      <CompanyBarChart />
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
.keyword-analyze {
  width: 1200px;
  margin: 15px 0px;
}
</style>
