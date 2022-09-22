<template>
  <div class="q-pa-xl">
    <MainBarChart />
    <br />
    <KeywordLineChart :keyword="keyword" />
    <br />
    <CompanyBarChart />
  </div>
</template>

<script>
import { ref } from "vue";
import MainBarChart from "../components/charts/MainBarChart.vue";
import CompanyBarChart from "../components/charts/CompanyBarChart.vue";
import KeywordLineChart from "src/components/charts/KeywordLineChart.vue";
export default {
  components: { MainBarChart, CompanyBarChart, KeywordLineChart },
  setup() {
    const keyword = ref("키워드1");
    return {
      keyword,
    };
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
};
</script>
