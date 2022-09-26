<template>
  <div>
    <main-intro class="col" />
    <word-cloud-and-rank class="col" />
    <today-news class="col" />
  </div>
</template>

<script>
import { defineComponent } from "vue";
import MainIntro from "src/components/main/MainIntro.vue";
import WordCloudAndRank from "src/components/main/WordCloudAndRank.vue";
import TodayNews from "src/components/main/TodayNews.vue";

export default defineComponent({
  name: "MainView",
  setup() {},
  components: { TodayNews, MainIntro, WordCloudAndRank },
});
</script>
