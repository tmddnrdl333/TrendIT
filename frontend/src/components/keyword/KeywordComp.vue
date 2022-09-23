<template>
  <div>
    <q-card class="keyword-search q-pa-lg">
      <q-card-section>
        <div>키워드 검색</div>
      </q-card-section>
      <q-separator inset />
      <!-- 검색창 -->
      <q-card-section class="row justify-center">
        <q-input
          class="search-bar"
          v-model="search"
          label="검색"
          outlined
          dense
          bottom-slots
          @keydown.enter="doSearch()"
        >
          <template v-slot:after>
            <q-btn round dense @click="doSearch()">
              <q-icon name="search" />
            </q-btn>
          </template>
          <template v-slot:hint>
            <div class="row justify-end">
              <q-btn dense @click="dialog = true" size="5px">
                {{ date_range.from }} ~ {{ date_range.to }}
              </q-btn>
            </div>
          </template>
        </q-input>
      </q-card-section>
    </q-card>
    <!-- Dialogs Start -->
    <q-dialog v-model="dialog">
      <q-card>
        <q-card-section> 검색 기간 설정 </q-card-section>
        <q-separator />
        <q-card-section>
          <q-date v-model="date_range" minimal range />
        </q-card-section>
      </q-card>
    </q-dialog>
    <!-- Dialogs End -->
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
              <template v-if="item.imgLink">
                <!-- q-img에 fit이 있던데 잘 안된다... -->
                <q-img :src="item.imgLink" :alt="item.headline" />
              </template>
              <template v-else>
                <div class="row justify-center">(No Image)</div>
              </template>
            </div>
            <q-card-section class="flex column">
              <div>{{ item.headline }}</div>
              <div>{{ item.newsContent }}</div>
              <div class="q-mt-lg">
                {{ item.newsAgency }} {{ item.newsDate }}
              </div>
              <!-- {{item.newsLink}} -->
            </q-card-section>
          </q-card>
        </template>
      </div>
      <div class="q-pa-lg flex flex-center">
        <q-pagination v-model="page" :max="5" input />
      </div>
    </q-card>
  </div>
</template>

<script>
import { ref } from "vue";
import { searchApi } from "boot/news.js";
import KeywordLineChart from "../charts/KeywordLineChart.vue";

export default {
  setup() {
    return {
      search: ref(""),
      keyword: ref(""),
      dialog: ref(false),
      date_range: ref({ from: "", to: "" }),
      page: ref(1),
      page_max: ref(null),

      result: ref([]),
    };
  },
  mounted() {
    let date = new Date();
    this.date_range.to =
      date.getFullYear() +
      "/" +
      (date.getMonth() + 1).toString().padStart(2, "0") +
      "/" +
      date.getDate();
    date.setMonth(date.getMonth() - 1); // 범위의 초기값
    this.date_range.from =
      date.getFullYear() +
      "/" +
      (date.getMonth() + 1).toString().padStart(2, "0") +
      "/" +
      date.getDate();
  },
  methods: {
    async doSearch() {
      this.keyword = this.search;
      const period =
        this.date_range.from.replaceAll("/", "-") +
        "~" +
        this.date_range.to.replaceAll("/", "-");
      await searchApi(
        { keyword: this.keyword, period: period, page: this.page },
        (response) => {
          this.result = response.data.news;
        },
        (e) => {
          console.log("HERE!!");
          console.error(e);
        }
      );
    },
  },
  components: { KeywordLineChart },
  watch: {
    page: function () {
      this.doSearch();
    },
  },
};
</script>

<style scoped>
/* 1 */
.keyword-search {
  width: 1200px;
  margin: 15px 0px;
}
.search-bar {
  width: 500px;
}

/* 2 */
.keyword-analyze {
  width: 1200px;
  margin: 15px 0px;
}

/* 3 */
.keyword-analyze-result {
  /* TODO: "키워드 분석" 글자 폰트, 사이즈, 패딩 마진 등 결정 후 고정된 높이 줘야 할듯 */
  /* height: 1112px; */
  width: 1200px;
  margin: 15px 0px;
}

.news-card {
  height: 270px;
  width: 230px;
  padding: 0px;
}
.card-img {
  width: 100%;
}
</style>
