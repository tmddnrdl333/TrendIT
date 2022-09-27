<template>
  <div>
    <main-banner />
    <word-cloud />
    <main-news />
  </div>
</template>

<script>
import { defineComponent } from "vue";
import MainBanner from "src/components/main/MainBanner.vue";
import WordCloud from "src/components/main/WordCloud.vue";
import MainNews from "src/components/main/MainNews.vue";

export default defineComponent({
  name: "MainView",
  setup() {},
  components: { MainBanner, WordCloud, MainNews },
});
</script>
