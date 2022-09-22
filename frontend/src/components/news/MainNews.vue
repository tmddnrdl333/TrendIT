<template>
  <div class="main-news q-pa-lg">
    <div>최신뉴스</div>
    <div class="flex flex-center">
      {{ newsDate }}
      {{ newsAgency }}
      <q-btn
        label="기간설정"
        color="primary"
        icon="mail"
        @click="alert = true"
      ></q-btn>

      <q-dialog v-model="alert">
        <div class="flex flex-center">
          <div class="q-pa-md">
            <q-date v-model="newsDate" range />
          </div>
          <q-btn flat label="OK" color="primary" v-close-popup></q-btn>
        </div>
      </q-dialog>

      <!-- https://codepen.io/pen?&editors=101 -->
      <div class="q-pa-md">
        <div class="q-gutter-y-md column" style="max-width: 300px">
          <q-select
            clearable
            filled
            color="blue"
            v-model="newsAgency"
            :options="newsAgencyOptions"
            label="언론사"
          ></q-select>
        </div>
      </div>

      <q-btn
        label="검색"
        color="primary"
        @click="dateNewsAgencySearch(newsDate, newsAgency)"
      ></q-btn>
    </div>
  </div>
</template>

<script>
import { defineComponent } from "vue";
import { matCalendarMonth } from "@quasar/extras/material-icons";
import { dateNewsAgencySearchApi } from "boot/news.js";

import { ref } from "vue";

export default defineComponent({
  name: "MainNews",
  components: {},
  data() {
    return {
      sampleData: [{}],
      // newsDate: { from: "2010/01/01", to: "2022/09/01" },
      newsDate: {},
      newsAgency: "",
      newsAgencyOptions: [
        "경향신문",
        "국민일보",
        "내일신문",
        "동아일보",
        "문화일보",
        "서울신문",
        "세계일보",
        "중앙일보",
        "조선일보",
        "한겨레",
        "한국일보",
        "매일경제",
        "머니투데이",
        "서울경제",
        "아시아경제",
        "아주경제",
        "파이낸셜뉴스",
        "한국경제",
        "헤럴드경제",
        "KBS",
        "MBC",
        "OBS",
        "SBS",
        "YTN",
        "디지털타임스",
        "전자신문",
        "강원도민일보",
        "강원일보",
        "경기일보",
        "경남도민일보",
        "경남신문",
        "경상일보",
        "경인일보",
        "광주일보",
        "광주매일신문",
        "국제신문",
        "대구일보",
        "대전일보",
        "매일신문",
        "무등일보",
        "부산일보",
        "영남일보",
        "울산매일",
        "전남일보",
        "전북도민일보",
        "전북일보",
        "제민일보",
        "중도일보",
        "중부매일",
        "중부일보",
        "충북일보",
        "충청일보",
        "충청투데이",
        "한라일보",
      ],
    };
  },
  setup() {
    return {
      alert: ref(false),
    };
  },
  created() {
    this.matCalendarMonth = matCalendarMonth;
  },
  mounted() {},
  unmounted() {},
  methods: {
    dateNewsAgencySearch: function (newsDate, newsAgency) {
      const success = "성공";
      const fail = "실패";
      dateNewsAgencySearchApi(newsDate, newsAgency, success, fail);
    },
  },
});
</script>

<style scoped>
.main-news {
  height: 480px;
  width: 1200px;
  background-color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
