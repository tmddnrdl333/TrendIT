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
        <keyword-line-chart :keyword="search" />
      </q-card-section>
    </q-card>
    <q-card class="keyword-analyze-result q-pa-lg">
      <div>키워드 분석 결과</div>
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
      search: ref("키워드1"),
      dialog: ref(false),
      date_range: ref({ from: "", to: "" }),
      page: ref(1),
      /*
        TODO:
        result를 vuex로 넣거나, search를 vuex로 넣어서 거기서 api를 쓴 후 결과를 KeywordAnalyzeResult.vue 컴포넌트에서 받아서 목록을 출력해준다.
      */
      result: ref({}),
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
      const period =
        this.date_range.from.replaceAll("/", "-") +
        "~" +
        this.date_range.to.replaceAll("/", "-");
      await searchApi(
        { keyword: this.search, period: period, page: this.page },
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
};
</script>

<style scoped>
.keyword-search {
  height: 180px;
  width: 1200px;
  margin: 15px 0px;
}
.btn {
  height: 30px;
}
.search-bar {
  width: 500px;
}

.keyword-analyze {
  width: 1200px;
  margin: 15px 0px;
}

.keyword-analyze-result {
  height: 1430px;
  width: 1200px;
  margin: 15px 0px;
}
</style>
