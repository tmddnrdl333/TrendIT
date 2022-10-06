<template>
  <q-page>
    <news-comp />
  </q-page>
</template>

<script>
import { defineComponent } from "vue";
import NewsComp from "../components/news/NewsComp.vue";

export default defineComponent({
  name: "NewsView",
  components: {
    NewsComp,
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
