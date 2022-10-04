<template>
  <div>
    <div class="section3">
      <div class="s3_wrapper">
        <img :src="LogoTrendIT" alt="로고" class="s3_logo" />
        <div class="s3_left">
          "우리는 매일 수많은 뉴스를 마주치며,
          <br />
          살고 있습니다."
          <div class="s3_left_bar"></div>
        </div>
        <div class="s3_right">
          <span class="span_blank"
            >"대한민국은 검색 및 뉴스 수집 플랫폼을 통한 온라인 뉴스 이용률에서
            2년 연속 세계 1위를 기록했습니다.
            <br />
            대다수 국민들은 더 이상 언론사 홈페이지를 이용하지 않습니다. "</span
          >
          <br />
          <br />
          <br />
          <span class="span_blank"
            >"그렇다면 기존의 뉴스 서비스들은 트랜드를 파악하기 쉬운 구조일까요?
            <br />
            생각보다 현실은 녹록치 않습니다.
          </span>
          <span class="span_blank">
            <br />
            대형 포탈은 실시간 급상승 검색어 폐지 이후 트랜드를 확인할 방법이
            사라졌고,
            <br />
            뉴스 수집 플랫폼은 프리미엄 기사로 결제를 유도하거나 접근성이 떨어져
            <br />
            자칫하면 IT와 멀어질 수 있습니다."
          </span>
          <br />
          <br />
          <br />
          "TrendIT 은 쉽고 빠른 트랜드의 파악을 가능하게 합니다.
          <br />
          누구나 이용할 수 있는 TrendIT 를 통해 IT 에 한 걸음 가까이
          다가오세요."
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LogoTrendIT from "src/assets/LogoTrendIT.png";

export default {
  name: "SectionThird",

  components: {},
  data() {
    return {
      sampleData: "",
      LogoTrendIT,
    };
  },
  setup() {
    return {};
  },
  created() {},
  mounted() {},
  unmounted() {},
  methods: {},
};
</script>

<style scoped>
.section3 {
  background-color: var(--grey6);
  height: 600px;
}
.s3_wrapper {
  padding: 226px 115px;
  box-sizing: border-box;
  width: 100vw;
  height: 100%;
  margin: 0px auto;
  position: relative;
  display: flex;
  flex-direction: row;
}
.s3_logo {
  position: absolute;
  top: 100px;
}
.s3_left {
  font-size: 25px;
  font-weight: bold;
  position: relative;
  z-index: 3;
  white-space: nowrap;
  bottom: 40px;
}
.s3_right {
  width: 100%;
  margin-left: 69px;
  font-size: 17px;
  line-height: 2.06;
  color: rgb(59, 59, 59);
  white-space: nowrap;
  bottom: 100px;
  position: relative;
}

.span_black {
  font-weight: bold;
}
</style>
