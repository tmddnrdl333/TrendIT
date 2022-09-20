<template>
  <q-page class="flex flex-center">
    <main-news />
    <sub-news />
  </q-page>
</template>

<script>
import { defineComponent } from "vue";
import MainNews from "../components/news/MainNews.vue";
import SubNews from "../components/news/SubNews.vue";

export default defineComponent({
  name: "NewsView",
  components: {
    MainNews,
    SubNews,
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
