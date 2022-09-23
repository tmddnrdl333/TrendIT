<template>
  <div class="main__page__layout">
    <suspense>
      <main-intro></main-intro>
    </suspense>
    <word-cloud-and-rank
      :wordCloudData="wordCloudData"
      :trendRankData="trendRankData"
    ></word-cloud-and-rank>
    <today-news
      :firstNews="newsData[0].selectedNews"
      :nextNews="newsData.slice(1)"
    ></today-news>
  </div>
</template>

<script>
import { defineComponent, ref } from "vue";
import MainIntro from "src/components/main/MainIntro.vue";
import WordCloudAndRank from "src/components/main/WordCloudAndRank.vue";
import TodayNews from "src/components/main/TodayNews.vue";
import { getTodayTrendsAndNewsData } from "src/boot/mainAxios.js";
import { TodayTrendAndNewsRes } from "src/boot/response/TodayTrendAndNewsRes";

export default defineComponent({
  name: "MainView",
  components: { WordCloudAndRank, TodayNews, MainIntro },
  async setup() {
    let wordCloudData = [];
    let trendRankData = [];
    let newsData = [];

    await getTodayTrendsAndNewsData(
      (response) => {
        const resData = response.data.data;
        // 이 중 30개 키워드와 count + isCompany? or 분류 는 워드 클라우드에
        // 이 중 6개는 키워드와 count 는 랭크에
        // 이 중 5개는 뉴스는 오늘의 뉴스에 넣어줘야됨
        for (let i = 0; i < resData.length; i++) {
          wordCloudData.push(
            new TodayTrendAndNewsRes(
              resData[i].keyword,
              resData[i].count,
              resData[i].newsData
            )
          );
          if (i < 6) {
            if (i < 5)
              newsData.push(
                new TodayTrendAndNewsRes(
                  resData[i].keyword,
                  resData[i].count,
                  resData[i].newsData
                )
              );
            trendRankData.push(
              new TodayTrendAndNewsRes(
                resData[i].keyword,
                resData[i].count,
                resData[i].newsData
              )
            );
          }
        }
      },
      () => {
        console.warn("WARN");
      }
    );

    return {
      wordCloudData,
      trendRankData,
      newsData,
    };
  },
  // created() {
  //   const wordCloudData = ref([]);
  //   const trendRankData = ref([]);
  //   const newsData = ref([]);

  //   getTodayTrendsAndNewsData(
  //     (response) => {
  //       const resData = response.data.data;
  //       // 이 중 30개 키워드와 count + isCompany? or 분류 는 워드 클라우드에
  //       // 이 중 6개는 키워드와 count 는 랭크에
  //       // 이 중 5개는 뉴스는 오늘의 뉴스에 넣어줘야됨
  //       console.log("LENGTH::::" + resData.length);
  //       for (let i = 0; i < resData.length; i++) {
  //         wordCloudData.value.push(resData[i]);
  //         if (i < 6) {
  //           if (i < 5) newsData.value.push(resData[i]);
  //           trendRankData.value.push(resData[i]);
  //         }
  //       }
  //       console.log(wordCloudData.value);
  //       console.log(newsData.value);
  //       console.log(trendRankData.value);
  //     },
  //     () => {
  //       console.warn("WARN");
  //     }
  //   );

  //   return {
  //     wordCloudData,
  //     trendRankData,
  //     newsData,
  //   };
  // },
});
</script>

<style scoped>
.main__page__layout {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>
