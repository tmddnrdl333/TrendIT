<template>
  <div class="main__page__layout">
    <suspense>
      <main-intro></main-intro>
    </suspense>
    <word-cloud-and-rank></word-cloud-and-rank>
    <today-news></today-news>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import MainIntro from "src/components/main/MainIntro.vue";
import WordCloudAndRank from "src/components/main/WordCloudAndRank.vue";
import TodayNews from "src/components/main/TodayNews.vue";

export default defineComponent({
  name: "MainView",
  components: { WordCloudAndRank, TodayNews, MainIntro },
});
</script>

<style scoped>
.main__page__layout {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
