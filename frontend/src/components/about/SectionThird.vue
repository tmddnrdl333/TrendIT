<template>
  <div>
    <div class="section3">
      <div class="s3_wrapper">
        <img :src="LogoTrendIT" alt="로고" class="s3_logo" />
        <div class="s3_left">
          "우리는 매일 수많은 뉴스를 마주치며"
          <br />
          "살고 있습니다."
          <div class="s3_left_bar"></div>
        </div>
        <div class="s3_right">
          <span class="span_blank"
            >"대부분의 언론은 높은 트래픽을 통해 광고 수익을 만들어낼 수 있는
            저품질 뉴스를 양산합니다. <br />
            팩트체크 보다 속보 경쟁이 우선이고, 돈을 받고 기사를 쓰기도 합니다.
            "</span
          >
          <br />
          <span class="span_blank"
            >"대부분의 언론은 높은 트래픽을 통해 광고 수익을 만들어낼 수 있는
            저품질 뉴스를 양산합니다. <br />팩트체크 보다 속보 경쟁이 우선이고,
            돈을 받고 기사를 쓰기도 합니다. "</span
          >
          <br />
          <br />
          "우리가 좋은 뉴스를 볼 수 있을 방법이 있습니다, <br />저널리스트들이
          세상을 바꾸는 좋은 질문을 찾을 수 있도록 도울 방법이 있습니다."
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
  height: 400px;
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
  top: 40px;
}
.s3_left {
  font-size: 25px;
  font-weight: bold;
  position: relative;
  z-index: 3;
  white-space: nowrap;
  bottom: 100px;
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
