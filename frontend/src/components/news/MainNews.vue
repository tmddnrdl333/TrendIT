<template>
  <div class="main-news q-pa-lg">
    <div>최신뉴스</div>
    Your pick: {{ selection }}

    <div class="flex flex-center">
      {{ newsAgency }}

      <div class="row justify-end">
        <q-btn dense @click="newsDateDialog = true" size="5px">
          {{ newsDate.from }} ~ {{ newsDate.to }}
        </q-btn>
      </div>

      <q-btn dense @click="newsAgencyDialog = true" label="언론사설정">
        <q-icon name="settings" />
      </q-btn>

      <q-dialog v-model="newsDateDialog">
        <q-card>
          <q-card-section>
            검색 기간 설정
            <br />
            {{ newsDate.from }} ~ {{ newsDate.to }}
            <q-btn flat label="OK" color="primary" v-close-popup></q-btn>
          </q-card-section>
          <q-separator />
          <q-card-section>
            <div class="q-pa-md">
              <q-date v-model="newsDate" minimal range />
            </div>
          </q-card-section>
        </q-card>
      </q-dialog>

      <q-dialog v-model="newsAgencyDialog">
        <q-card style="width: 700px; max-width: 80vw">
          <div class="q-pa-md">
            <div class="q-gutter-xs flex content-start">
              <div
                v-for="newsAgency in newsAgencyOptions"
                :key="newsAgency.index"
              >
                <q-chip
                  v-model:selected="newsAgencyOption[newsAgency]"
                  color="primary"
                  text-color="white"
                  :label="newsAgency"
                >
                </q-chip>
              </div>
            </div>
          </div>
        </q-card>
      </q-dialog>

      <q-btn
        label="검색"
        color="primary"
        @click="getnewsByOptions(newsDate, selection)"
      ></q-btn>
    </div>
  </div>
</template>

<script>
import { defineComponent, reactive, computed } from "vue";
import { matCalendarMonth } from "@quasar/extras/material-icons";
import { getnewsByOptionsApi } from "src/boot/getnewsByOptions.js";

import { ref } from "vue";

export default defineComponent({
  name: "MainNews",
  components: {},
  data() {
    return {
      sampleData: [{}],
      // newsDate: { from: "2010/01/01", to: "2022/09/01" },
      newsDate: { from: "", to: "" },
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
    const newsAgencyOption = reactive({
      경향신문: false,
      국민일보: false,
      내일신문: false,
      동아일보: false,
      문화일보: false,
      서울신문: false,
      세계일보: false,
      중앙일보: false,
      조선일보: false,
      한겨레: false,
      한국일보: false,
      매일경제: false,
      머니투데이: false,
      서울경제: false,
      아시아경제: false,
      아주경제: false,
      파이낸셜뉴스: false,
      한국경제: false,
      헤럴드경제: false,
      KBS: false,
      MBC: false,
      OBS: false,
      SBS: false,
      YTN: false,
      디지털타임스: false,
      전자신문: false,
      강원도민일보: false,
      강원일보: false,
      경기일보: false,
      경남도민일보: false,
      경남신문: false,
      경상일보: false,
      경인일보: false,
      광주일보: false,
      광주매일신문: false,
      국제신문: false,
      대구일보: false,
      대전일보: false,
      매일신문: false,
      무등일보: false,
      부산일보: false,
      영남일보: false,
      울산매일: false,
      전남일보: false,
      전북도민일보: false,
      전북일보: false,
      제민일보: false,
      중도일보: false,
      중부매일: false,
      중부일보: false,
      충북일보: false,
      충청일보: false,
      충청투데이: false,
      한라일보: false,
    });
    return {
      newsDateDialog: ref(false),
      newsAgencyDialog: ref(false),
      newsAgencyOption,
      selection: computed(() => {
        return Object.keys(newsAgencyOption)
          .filter((type) => newsAgencyOption[type] === true)
          .join(", ");
      }),
    };
  },
  created() {
    this.matCalendarMonth = matCalendarMonth;
    let date = new Date();
    this.newsDate.to =
      date.getFullYear() +
      "/" +
      (date.getMonth() + 1).toString().padStart(2, "0") +
      "/" +
      date.getDate();
    date.setFullYear(date.getFullYear() - 10);
    this.newsDate.from =
      date.getFullYear() +
      "/" +
      (date.getMonth() + 1).toString().padStart(2, "0") +
      "/" +
      date.getDate();
  },
  mounted() {},
  unmounted() {},
  methods: {
    getnewsByOptions: function (newsDate, selection) {
      const success = "성공";
      const fail = "실패";
      getnewsByOptionsApi(newsDate, selection, success, fail);
    },
  },
});
</script>

<style scoped>
.main-news {
  height: 120px;
  width: 1200px;
  background-color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
