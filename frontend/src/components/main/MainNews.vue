<template>
  <q-card class="news-container row q-pa-lg">
    <!-- MAIN -->
    <div
      class="main-news-container col-6 q-gutter-md"
      v-on:click="goToLink(newsData[0])"
    >
      <template v-if="newsData[0]">
        <q-img
          class="main-news-img shadow-1"
          :src="newsData[0].newsData.imgLink"
        />
        <div class="main-news-title">
          <strong>{{ newsData[0].newsData.headline }}</strong>
        </div>
        <div class="main-news-content">
          {{ contentfilter(newsData[0].newsData.newsContent) }}
        </div>
        <div class="main-news-agency">
          {{ newsData[0].newsData.newsAgency }} &nbsp;&nbsp;&nbsp;&nbsp;
          {{ newsData[0].newsData.newsDate }}
        </div>
      </template>
    </div>
    <!-- SIDE -->
    <div class="side-news-container col-6">
      <template v-for="(item, index) in newsData.slice(1, 5)" :key="index">
        <div class="side-news row q-ma-xs" v-on:click="goToLink(item)">
          <q-img
            class="col side-news-img shadow-1 q-mr-md"
            :src="item.newsData.imgLink"
          />

          <div class="col q-gutter-sm">
            <div class="side-news-title">
              <strong>{{ item.newsData.headline }}</strong>
            </div>
            <div class="side-news-content">
              {{ contentfilter(item.newsData.newsContent) }}
            </div>
          </div>
        </div>
      </template>
    </div>
  </q-card>
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
        this.newsData = [];
        resData.forEach((item) => {
          if (item.newsData.imgLink.substring(0, 5) == "/asse") {
            item.newsData.imgLink = "src/assets/img-noImg.png";
          }
          this.newsData.push(item);
        });
        // this.newsData = resData;
        this.wordCloudData = this.newsData.slice(0, 10);
        this.trendRankData = this.newsData.slice(1, 6);
      },
      () => console.warn("WARN")
    );
  },
  computed: {
    contentfilter() {
      return function (text) {
        if (text.length > 50) return text.substring(0, 45) + "..";
        else return text;
      };
    },
  },
  methods: {
    goToLink: function (newsData) {
      if (newsData.newsData.newsLink === "") {
        window.open(
          "http://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=" +
            newsData.newsData.newsAgency +
            " " +
            newsData.newsData.headline
        );
      } else window.open(newsData.newsData.newsLink);
    },
  },
};
</script>

<style scoped>
/* 네이버 나눔 바른 고딕*/
@import url("https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css");

.news-container {
  /* display: flex; */
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 1050px;
  margin: auto;
  margin-top: 20px;
  margin-bottom: 20px;
}

.main-news-container {
  height: 570px;
}

.side-news-container {
  height: 570px;
}

.main-news-img {
  max-height: 380px;
  max-width: 470px;
  border-radius: 5px;
}

.side-news {
  height: 140px;
}

.side-news-img {
  max-width: 190px;
  max-height: 120px;
  border-radius: 5px;
}
.main-news-title {
  font-family: "NanumBarunGothicBold";
  font-size: 20px;
}
.main-news-content {
  font-family: "NanumBarunGothic";
  font-size: 16px;
}
.main-news-agency {
  color: gray;
  font-family: "NanumBarunGothic";
}
.side-news-title {
  font-family: "NanumBarunGothicBold";
  font-size: 16px;
}
.side-news-content {
  font-family: "NanumBarunGothic";
  font-size: 14px;
}
</style>
