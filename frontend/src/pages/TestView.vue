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
import MainBarChart from "../components/main/MainBarChart.vue";
import CompanyBarChart from "../components/main/CompanyBarChart.vue";
import KeywordLineChart from "src/components/main/KeywordLineChart.vue";
export default {
  components: { MainBarChart, CompanyBarChart, KeywordLineChart },
  setup() {
    const keyword = ref("카카오");
    return {
      keyword,
    };
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
});
</script>
