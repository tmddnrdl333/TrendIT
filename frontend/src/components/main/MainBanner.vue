<template>
  <div class="main-intro-container row">
    <q-img class="intro-img" src="https://cdn.quasar.dev/img/parallax2.jpg" />
    <div class="intro-content">
      <div class="newscount-info">
        <template v-if="totalCount == 0">
          <p>뉴스 수집량을 계산 중입니다...</p>
        </template>
        <template v-else>
          <p>금일 뉴스 수집 : {{ todayCount }} 건</p>
          <p>전체 뉴스 수집 : {{ totalCount }} 건</p>
        </template>
      </div>
      <div class="title">
        <p>쉽고 빠른 IT 트렌드 분석 플랫폼</p>
      </div>
      <div class="subtitle">
        <p>트렌드 분석 -></p>
      </div>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import { getNewsCountApi } from "boot/stats.js";

export default {
  name: "MainIntro",
  setup() {
    return {
      todayCount: ref(0),
      totalCount: ref(0),
    };
  },
  async created() {
    await getNewsCountApi(
      (response) => {
        this.todayCount = response.data.data.todayCount;
        this.totalCount = response.data.data.totalCount;
      },
      () => console.warn("failed to get news count")
    );
  },
};
</script>

<style scoped>
.main-intro-container {
  width: 100%;
  height: 300px;
  background: #88addd;
}

.intro-img {
  height: 300px;
  width: 30%;
}
.intro-content {
  width: 70%;
}
.newscount-info {
  color: aliceblue;
  height: 40px;
  text-align: right;
  margin: 20px 20px 0px 0px;
}
.title {
  height: 50%;
  text-align: center;
  font-size: 30px;
  color: aliceblue;
  position: relative;
  left: -100px;
}

.subtitle {
  text-align: center;
  color: white;
  position: relative;
  left: -100px;
}
</style>
