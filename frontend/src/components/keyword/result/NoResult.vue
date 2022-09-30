<template>
  <q-card>
    <q-card-section class="row">
      <div class="col-5 row justify-center q-pa-md">
        <div class="no-img row justify-center items-center">
          <q-img src="../../../assets/no-image.png" width="320px" />
        </div>
      </div>
      <div class="col-5 column justify-center q-pa-md q-gutter-md">
        <div class="title">키워드를 입력해 뉴스를 검색해보세요!</div>
        <div class="subtitle">
          키워드 분석 및 뉴스 검색 결과를 확인할 수 있습니다.
        </div>
        <q-separator></q-separator>
        <div class="row justify-center">추천 키워드로 쉽게 시작해보세요!</div>
        <div class="row justify-center q-gutter-x-md">
          <template v-for="item in recommendations" :key="item.id">
            <!-- router-link to 로 바꾸고 싶은데 item에 keyword_id가 없음. (wordcloudapi 수정하면 좋을 듯) -->
            <div>{{ item.keyword }}</div>
          </template>
        </div>
      </div>
    </q-card-section>
  </q-card>
</template>

<script>
import { ref } from "vue";
import { getWordcloudApi } from "boot/stats.js";
export default {
  async created() {
    await getWordcloudApi(
      "day",
      (response) => {
        this.recommendations = response.data.data.slice(0, 5);
      },
      (e) => console.warn("failed to get today's recommendations")
    );
  },
  setup() {
    return {
      recommendations: ref([]),
    };
  },
};
</script>

<style scoped>
.title {
  font-size: 150%;
}
.subtitle {
  color: gray;
}

.no-img {
  background-color: rgb(30 53 93);
  border-radius: 10px;
  width: 350px;
  height: 350px;
}
</style>
