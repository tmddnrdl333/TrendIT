<template>
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
      >
        <template v-slot:after>
          <q-btn round dense @click="dialog = true">
            <q-icon name="settings" />
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
  <!-- Dialogs -->
  <q-dialog v-model="dialog">
    <q-card>
      <q-card-section> 검색 기간 설정 </q-card-section>
      <q-separator />
      <q-card-section>
        <q-date v-model="date_range" minimal range />
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { ref } from "vue";
export default {
  setup() {
    return {
      search: ref(""),
      dialog: ref(false),
      date_range: ref({ from: "", to: "" }),
    };
  },
  created() {
    let date = new Date();
    this.date_range.to =
      date.getFullYear() +
      "/" +
      (date.getMonth() + 1).toString().padStart(2, "0") +
      "/" +
      date.getDate();
    date.setFullYear(date.getFullYear() - 10);
    this.date_range.from =
      date.getFullYear() +
      "/" +
      (date.getMonth() + 1).toString().padStart(2, "0") +
      "/" +
      date.getDate();
  },
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
</style>
