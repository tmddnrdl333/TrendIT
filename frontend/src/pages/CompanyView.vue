<template>
  <q-page>
    <company-graph />
    <company-list />
  </q-page>
</template>

<script>
import { defineComponent } from "vue";
import CompanyGraph from "../components/company/CompanyGraph.vue";
import CompanyList from "../components/company/CompanyList.vue";

export default defineComponent({
  name: "CompanyView",
  components: {
    CompanyGraph,
    CompanyList,
  },
  data() {
    return {
      sampleData: "",
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
});
</script>
