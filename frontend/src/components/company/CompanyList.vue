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
          <q-card-section>
            {{ item.companyName }}
          </q-card-section>
          <q-separator />
          <q-card-section>
            <div>업종: {{ item.companyCategory }}</div>
            <div>음음: {{ item.companyMainItem }}</div>
            <div>링크: {{ item.companyLink }}</div>
            <!-- <div>{{ item.keyword }}</div> -->
          </q-card-section>
        </q-card>
      </template>
    </div>
    <div class="q-pa-lg flex flex-center">
      <q-pagination v-model="current" :max="5" input />
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
import { companyListApi, searchCompanyApi } from "boot/company.js";
import { ref } from "vue";
export default {
  setup() {
    return {
      search: ref(""),
      current: ref(1),

      dialog: ref(false),

      company_list: ref([{ companyName: "" }]),
    };
  },
  created() {
    this.loadCompanyList();
  },
  methods: {
    async doSearch() {
      if (!this.search) {
        this.dialog = true;
        return;
      }
      await searchCompanyApi(
        this.search,
        (response) => {
          console.log(response);
          console.log(response.data);
          console.log(response.data.data);
          this.company_list = response.data.data.content;
        },
        () => console.warn("failed")
      );
    },
    async loadCompanyList() {
      await companyListApi(
        (response) => {
          this.company_list = response.data.data;
        },
        () => console.warn("failed")
      );
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
  padding: 15px;
}
</style>
