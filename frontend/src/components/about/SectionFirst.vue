<template>
  <div class="section1">
    <div class="section1Wrapper wrapper">
      <div class="slogan1">
        TrendIT 은 매일 누적되는 IT기사를 분석하여
        <br />
        쉽게 이해할 수 있는 트렌드를 제공하고
        <br />
        <span>IT에 대한 문턱이 낮추는 것이 비전입니다.</span>
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
  font-family: "NanumBarunGothic";
  width: 1050px;
  height: 400px;
  margin: auto;
  position: relative;
}
.section1::before {
  content: "";
  opacity: 0.8;
  background-image: url("/src/assets/4.jpg");
  background-position: center center;
  background-repeat: no-repeat;
  background-size: cover;
  position: absolute;
  top: 0px;
  left: 0px;
  right: 0px;
  bottom: 0px;
}
.section1Wrapper {
  height: 100%;
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
  font-size: 35px;
  line-height: 1.5;
  color: white;
}
</style>
