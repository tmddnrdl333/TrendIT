<template>
  <div>
    <div class="section1">
      <div class="section1Wrapper wrapper">
        <div class="slogan1">
          TrendIT 는 매일 누적되는 IT기사를 분석하여
          <br />
          쉬운 트랜드 파악을 제공하고,
          <br />
          <span>IT에 대한 문턱이 낮추는 것이 비전입니다.</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import image_first from "src/assets/4.jpg";

export default {
  name: "SectionFirst",

  components: {},
  data() {
    return {
      sampleData: "",
      image: image_first,
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
.section1 {
  width: 100vw;
  height: 50vh;
  background-image: url("/src/assets/4.jpg");
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
}
.section1Wrapper {
  height: 100%;
  width: 1366px;
  margin: 0px auto;
  position: relative;
  display: flex;
  -webkit-box-align: center;
  align-items: center;
  padding-left: 122px;
  box-sizing: border-box;
}
.slogan1 {
  font-weight: 300;
  font-size: 40px;
  color: white;
}
</style>
