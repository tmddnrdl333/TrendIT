<template>
  <q-card class="keyword-analyze q-pa-lg">
    <q-card-section class="q-gutter-md">
      <div>키워드 분석</div>
      <div class="row justify-center items-center">
        <keyword-line-chart :keyword="keyword" />
      </div>
    </q-card-section>
  </q-card>
  <q-card class="keyword-analyze-result q-pa-lg">
    <div>키워드 분석 결과</div>
    <q-separator inset />
    <div class="flex q-pa-lg justify-around">
      <!-- TODO: img중앙정렬, 글자 폰트 적용 후 위치 조정 -->
      <template v-for="(item, index) of result" :key="index">
        <q-card class="news-card q-my-md">
          <div style="height: 150px; overflow: hidden; padding: auto">
            <!-- q-img에 fit이 있던데 잘 안된다... -->
            <q-img :src="item.imgLink" :alt="item.headline" />
          </div>
          <q-card-section class="flex column">
            <div>{{ item.headline }}</div>
            <div>{{ item.newsContent }}</div>
            <div class="q-mt-lg">{{ item.newsAgency }} {{ item.newsDate }}</div>
            <!-- {{item.newsLink}} -->
          </q-card-section>
        </q-card>
      </template>
    </div>
    <div class="q-pa-lg flex flex-center">
      <q-pagination v-model="page" :max="5" input />
    </div>
  </q-card>
</template>

<script>
import { ref } from "vue";
import KeywordLineChart from "../../charts/KeywordLineChart.vue";
import { getKeywordApi } from "boot/keyword.js";
import { keywordChartApi } from "boot/stats.js";

export default {
  components: { KeywordLineChart },
  setup() {
    return {
      keword_id: ref(""),
      keyword: ref(""),
    };
  },
  async created() {
    console.log(this.$route.params.keyword_id);
    this.keyword_id = this.$route.params.keyword_id;
    await getKeywordApi(
      this.keyword_id,
      (response) => {
        this.keyword = response.data.data.keyword;
      },
      () => console.warn("failed to find keyword")
    );
    await keywordChartApi(
      this.keyword,
      () => {},
      () => console.warn("failed to get line-chart data")
    );
  },
};
</script>

<style scoped></style>
