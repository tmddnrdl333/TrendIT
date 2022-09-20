<template>
  <q-page class="flex flex-center">
    <keyword-search />
    <keyword-analyze />
    <keyword-analyze-result />
  </q-page>
</template>

<script>
import { defineComponent } from "vue";
import KeywordSearch from "../components/keyword/KeywordSearch.vue";
import KeywordAnalyze from "../components/keyword/KeywordAnalyze.vue";
import KeywordAnalyzeResult from "../components/keyword/KeywordAnalyzeResult.vue";

export default defineComponent({
  name: "KeywordView",
  components: {
    KeywordSearch,
    KeywordAnalyze,
    KeywordAnalyzeResult,
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
