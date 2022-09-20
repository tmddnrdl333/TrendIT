<template>
  <q-page class="flex flex-center">
    <!-- 메인 페이지에 넣을 컴포넌트들을 채워주삼 -->
    <today-trend />
    <today-issue />
    <trend-rank />
  </q-page>
</template>

<script>
import { defineComponent } from "vue";
import TodayTrend from "../components/main/TodayTrend.vue";
import TodayIssue from "../components/main/TodayIssue.vue";
import TrendRank from "../components/main/TrendRank.vue";

export default defineComponent({
  name: "MainView",
  components: {
    TodayTrend,
    TodayIssue,
    TrendRank,
  },
});
</script>
