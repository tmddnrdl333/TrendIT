<template>
  <div class="main-intro-container row">
    <div class="banner">
      <video class="video" autoplay muted>
        <source src="~/assets/banner.mp4" type="video/mp4">
      </video>
      <div class="newscount-info">
        <template v-if="totalCount == 0">
          <p>뉴스 수집량을 계산 중입니다...</p>
        </template>
        <template v-else>
          <p>금일 뉴스 수집 : {{ num_filter(todayCount) }} 건</p>
          <p>전체 뉴스 수집 : {{ num_filter(totalCount) }} 건</p>
        </template>
      </div>
      <Transition name="fade" appear>
        <div
          :style="{ transitionDelay: delay }"
          v-if="show"
          class="keyword-direct"
          transition-show="jump-down"
        >
          <q-btn push color="deep-orange-11" :to="{ name: 'empty_keyword' }"
            >키워드 분석 바로가기<q-icon
              size="xs"
              class="q-pl-xs"
              name="arrow_forward"
          /></q-btn>
        </div>
      </Transition>
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
      show: ref(false),
      delay: ref("0.5s"),
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
  mounted() {
    this.show = true; // might need this.$nextTick
  },
  computed: {
    num_filter() {
      return function (num) {
        return num.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
      };
    },
  },
};
</script>

<style scoped>
.main-intro-container {
  width: 100%;
  height: 400px;
  font-family: "NanumBarunGothicBold";
}

.intro-img {
  height: 300px;
  width: 30%;
}
.intro-content {
  width: 70%;
}

.banner {
  max-width: 1050px;
  width: 100%;
  overflow: hidden;
  margin: auto;
  position: relative;
}
.video {
  margin: auto;
  width: 100%;
  display: block;
}

.keyword-direct {
  position: absolute;
  left: 45px;
  bottom: 120px;
}

.newscount-info {
  position: absolute;
  right: 10px;
  top: 10px;
  color: #3d5a80;
  text-align: left;
}
.newscount-info > p {
  margin: 0px auto;
  font-size: 12px;
}
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.5s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
