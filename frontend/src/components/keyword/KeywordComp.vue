<template>
  <div>
    <q-card class="keyword-search q-pa-lg">
      <q-card-section>
        <div>키워드 검색</div>
      </q-card-section>
      <q-separator inset />
      <!-- 검색창 -->
      <q-card-section class="col">
        <!--  -->
        <div class="row justify-center">
          <q-select
            class="search-bar"
            outlined
            dense
            label="검색"
            v-model="search"
            use-input
            fill-input
            hide-selected
            input-debounce="0"
            :options="search_options"
            @filter="filterFn"
            @input-value="setModel"
            @keydown.enter="toResult()"
          >
            <template v-slot:after>
              <q-btn round dense @click="toResult()">
                <q-icon name="search" />
              </q-btn>
            </template>

            <template v-slot:no-option>
              <q-item>
                <q-item-section class="text-grey"> No results </q-item-section>
              </q-item>
            </template>
          </q-select>
        </div>
        <div class="row justify-center q-pt-md">
          <q-btn dense @click="dialog = true" size="5px">
            {{ period.from }} ~ {{ period.to }}
          </q-btn>
        </div>
      </q-card-section>
    </q-card>
    <!-- Dialogs Start -->
    <q-dialog v-model="dialog">
      <q-card>
        <q-card-section> 검색 기간 설정 </q-card-section>
        <q-separator />
        <q-card-section>
          <q-date v-model="period" minimal range />
        </q-card-section>
      </q-card>
    </q-dialog>
    <!-- Dialogs End -->

    <router-view />

    <!-- 검색어가 있는 경우 -->
  </div>
</template>

<script>
import { ref } from "vue";
import { getKeywordListApi } from "boot/keyword.js";

export default {
  setup() {
    return {
      // 검색
      search: ref(""), // 사용자가 입력하는 내용 v-model
      dialog: ref(false), // 기간선택 모달 표시여부
      period: ref({ from: "", to: "" }),

      search_options: ref([]), // 입력한 search로 요청해 받은 추천 키워드 리스트

      reload: ref(0),
    };
  },
  mounted() {
    let date = new Date();
    let to_date = date.toISOString().substring(0, 10).replaceAll("-", "/");
    this.period.to = to_date;
    date.setFullYear(date.getFullYear() - 10); // 범위의 초기값
    let from_date = date.toISOString().substring(0, 10).replaceAll("-", "/");
    this.period.from = from_date;
  },
  methods: {
    filterFn(val, update, abort) {
      const needle = val.toLocaleLowerCase().trim();
      if (needle.length == 0) {
        abort();
        return;
      }
      update(() => {
        this.setSearchOptions(needle);
      });
    },

    setModel(val) {
      this.search = val;
    },

    async setSearchOptions(needle) {
      await getKeywordListApi(
        needle,
        (response) => {
          let list = response.data.data.slice(0, 5);
          this.search_options = [];
          list.forEach((item) => {
            this.search_options.push({
              label: item.keyword,
              value: item.keywordId,
            });
          });
        },
        (e) => console.warn(e)
      );
    },

    async toResult() {
      if (typeof this.search == "object" && this.search.value) {
        location.href =
          "/keyword/" +
          this.search.value +
          "?period=" +
          this.period.from.replaceAll("/", "-") +
          "~" +
          this.period.to.replaceAll("/", "-");
      }
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
</style>
