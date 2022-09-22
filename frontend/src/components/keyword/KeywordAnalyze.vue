<template>
  <q-card class="keyword-analyze q-pa-lg">
    <q-card-section class="q-gutter-md">
      <div>키워드 분석</div>
      <keyword-line-chart :keyword="keyword" />
    </q-card-section>
  </q-card>
</template>

<script>
import { ref } from "vue";
import KeywordLineChart from "../charts/KeywordLineChart.vue";
export default {
  setup() {
    return {
      keyword: ref("키워드1"),
    };
  },
  components: { KeywordLineChart },
};
</script>

<style scoped>
.keyword-analyze {
  width: 1200px;
  margin: 15px 0px;
}
</style>
