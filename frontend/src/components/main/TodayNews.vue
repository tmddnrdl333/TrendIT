<template>
  <div>
    <div class="today__news__container">
      <template v-for="(item, index) in newsData.slice(0, 5)" :key="index">
        <!-- First News -->
        <template v-if="index == 0">
          <div class="first__news__container">
            <img class="main-news-img" :src="item.newsData.imgLink" />
            <div>
              <q-card class="my-card news__container">
                <q-card-section>
                  <div class="news__head">
                    {{ item.newsData.headline }}
                  </div>
                </q-card-section>
                <q-card-section class="q-pt-none news__body">
                  {{ item.newsData.newsContent }}
                </q-card-section>
              </q-card>
            </div>
          </div>
        </template>

        <!-- Second ~ Fifth News -->
        <template v-else>
          <div class="next__next__news__container">
            <div class="next__news__container">
              <img
                class="side-news-img"
                src="https://cdn.quasar.dev/img/parallax2.jpg"
              />
              <div>
                <q-card class="my-card news__container">
                  <q-card-section>
                    <div class="news__head">
                      {{ item.newsData.headline }}
                    </div>
                  </q-card-section>
                  <q-card-section class="q-pt-none news__body">
                    {{ item.newsData.newsContent }}
                  </q-card-section>
                </q-card>
              </div>
            </div>
          </div>
        </template>
      </template>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import { getWordcloudApi } from "boot/stats.js";

export default {
  name: "TodayNews",
  setup() {
    return {
      newsData: ref([]),
      wordCloudData: ref([]),
      trendRankData: ref([]),
    };
  },
  async created() {
    await getWordcloudApi(
      "day",
      (response) => {
        const resData = response.data.data;
        // 이 중 30개 키워드와 count + isCompany? or 분류 는 워드 클라우드에
        // 이 중 6개는 키워드와 count 는 랭크에
        // 이 중 5개는 뉴스는 오늘의 뉴스에 넣어줘야됨
        this.newsData = resData;
        console.log("wordclouddata setting");
        this.wordCloudData = this.newsData.slice(0, 10);
        this.trendRankData = this.newsData.slice(1, 6);
      },
      () => console.warn("WARN")
    );
  },
};
</script>

<style scoped>
.today__next__news__container {
  display: flex;
  flex-direction: column;
}
.today__news__container {
  /* display: flex; */
  flex-direction: row;
  justify-content: center;
  align-items: center;
}
.main-news-img {
  height: 100px;
  width: 100px;
}
.side-news-img {
  height: 50px;
  width: 50px;
}
</style>
