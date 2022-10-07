<template>
  <q-card class="news-container">
    <q-card-section class="card-title q-ml-lg">
      <div class="title q-mt-xs">오늘의 트렌드 뉴스</div>
    </q-card-section>
    <q-card-section class="card-content row items-center justify-center">
      <div
        class="main-news-container col-6 q-gutter-md cursor-pointer"
        @click="goToLink(newsData[0])"
      >
        <template v-if="newsData[0]">
          <q-img
            class="main-news-img shadow-1"
            :src="newsData[0].newsData.imgLink"
          />
          <div class="main-news-title">
            <strong>{{
              headline_filter(newsData[0].newsData.headline)
            }}</strong>
          </div>
          <div class="main-news-content">
            {{ newsData[0].newsData.newsContent.substring(0, 75) + "..." }}
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
          <div
            class="side-news row q-ma-xs cursor-pointer"
            @click="goToLink(item)"
          >
            <q-img
              class="col side-news-img shadow-1 q-mr-md"
              :src="item.newsData.imgLink"
            />
            <div class="col q-gutter-sm">
              <div class="side-news-title">
                <strong>{{ headline_filter(item.newsData.headline) }}</strong>
              </div>
              <div class="side-news-content">
                {{ item.newsData.newsContent.substring(0, 45) + "..." }}
              </div>
              <div class="main-news-agency">
                {{ item.newsData.newsAgency }} &nbsp;&nbsp;&nbsp;&nbsp;
                {{ item.newsData.newsDate }}
              </div>
            </div>
          </div>
        </template>
      </div>
    </q-card-section>
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
        this.newsData = [];
        resData.forEach((item) => {
          if (item.newsData.imgLink.substring(0, 5) == "/asse") {
            item.newsData.imgLink = "~assets/no-image.png";
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
    headline_filter() {
      return function (text) {
        if (text.length > 50) return text.substring(0, 45) + "...";
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

.title {
  font-family: "NanumBarunGothicBold";
  font-size: 17px;
  margin-top: 10px;
}

.card-title {
  padding: 10px;
}

.card-content {
  padding-top: 5px;
  justify-content: center;
  margin: auto;
}
.news-container {
  /* display: flex; */
  flex-direction: row;
  align-items: center;
  max-width: 1050px;
  width: 100%;
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
