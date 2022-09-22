<template>
  <q-card class="company-list q-pa-lg">
    <q-card-section>
      <div>기업 리스트</div>

      <!-- 검색창 -->
      <div class="row justify-center">
        <q-skeleton type="rect" class="q-ma-md" width="800px" height="40px" />
      </div>
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
</template>

<script>
import { companyListApi } from "boot/company.js";
import { ref } from "vue";
export default {
  setup() {
    const company_list = ref([{ companyName: "" }]);
    const current = ref(1);

    return {
      company_list,
      current,
    };
  },
  created() {
    this.loadCompanyList();
  },
  methods: {
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
