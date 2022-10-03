<template>
  <q-card class="company-list q-pa-lg">
    <q-card-section>
      <div>기업 리스트</div>

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
        </q-input>
      </q-card-section>
    </q-card-section>
    <q-separator inset />
    <div class="flex q-pa-lg justify-around">
      <template v-for="(item, index) of company_list" :key="index">
        <q-card class="company-card q-my-md">
          <q-icon
            name="add_home"
            size="21px"
            class="add-home"
            v-on:click="goToLink(item.companyLink)"
          ></q-icon>

          <q-card-section class="company-title">
            {{ item.companyName }}
          </q-card-section>
          <q-separator />
          <q-card-section class="company-content">
            <div>업종: {{ item.companyCategory }}</div>
            <div>대표자: {{ item.companyRepresentative }}</div>
          </q-card-section>
        </q-card>
      </template>
    </div>
    <div class="q-pa-lg flex flex-center">
      <q-pagination v-model="current" :max="max_page" input />
    </div>
  </q-card>
  <q-dialog v-model="dialog">
    <q-card>
      <q-card-section>
        <div>검색어를 입력해주세요.</div>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { searchCompanyApi } from "boot/company.js";
import { ref } from "vue";
export default {
  setup() {
    return {
      search: ref(""),
      current: ref(1),
      max_page: ref(99),

      dialog: ref(false),

      company_list: ref([]),
    };
  },
  created() {
    this.loadCompanyList();
  },
  methods: {
    async loadCompanyList() {
      await searchCompanyApi(
        { companyName: this.search, page: this.current },
        (response) => {
          this.company_list = response.data.data.content;
          this.max_page = response.data.data.totalPages;
        },
        () => console.warn("failed")
      );
    },
    async doSearch() {
      if (!this.search) {
        window.alert("검색어를 입력하세요");
        return;
      } else if (this.search.length < 2) {
        window.alert("검색어를 두 글자 이상 입력하세요");
        return;
      }
      this.current = 1;
      await this.loadCompanyList();
    },

    goToLink: function (companyLink) {
      window.open(companyLink);
    },
  },
  watch: {
    current: function () {
      this.loadCompanyList();
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@500&display=swap");
@import url("https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css");
@import url("https://fonts.googleapis.com/earlyaccess/hanna.css");

.search-bar {
  width: 500px;
}

.company-list {
  height: 1200px;
  width: 1200px;
  background-color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
}
.company-card {
  height: 200px;
  width: 230px;
  padding: 15px 0 15px 15px;
}

.company-title {
  /* font-family: "NanumBarunGothicBold"; */
  font-family: "Hanna", fantasy;
  font-size: 24px;
}
.company-content {
  /* font-family: "NanumBarunGothicBold"; */
  font-family: "Hanna", fantasy;
  font-size: 16px;
}
.add-home {
  position: absolute;
  margin-left: 180px;
  margin-top: 20px;
  z-index: 2;
}
</style>
