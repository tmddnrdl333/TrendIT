<template>
  <div class="container column">
    <div class="col text-center">
      <div class="navy-line"></div>
      <h1 style="font-weight: 600">Services</h1>
      <p>Services provided by TrendIT</p>
    </div>

    <div class="col-8 row">
      <div class="col-lg-3" style="visibility: visible">
        <div class="text-center">
          <p class="title text-center">
            트렌드 분석
          </p>
          <img :src="service2" alt="service2" class="m-b-lg" />
          <p style="height: 40px; font-weight: 800; color: #000000">
            WordCloud 를 통해 기간별 트렌드를
            <br />
            알기 쉽게 한 눈에 파악할 수 있습니다.
          </p>
        </div>
      </div>
      <div class="col-lg-3" style="visibility: visible">
        <div class="text-center">
          <p class="title">
            키워드 분석
          </p>
          <img :src="service3" alt="service3" class="m-b-lg" />
          <p style="height: 40px; font-weight: 800; color: #000000">
            특정 키워드 검색을 통해 트렌드 추이를
            <br />
            파악하고, 관련 뉴스를 제공합니다.
            <br />
          </p>
        </div>
      </div>
      <div class="col-lg-3" style="visibility: visible">
        <div class="text-center">
          <p class="title">
            기업 리스트
          </p>
          <img :src="service4" alt="service4" class="m-b-lg" />
          <p style="height: 40px; font-weight: 800; color: #000000">
            기업별 기간별 트렌드를 추이를 확인하고
            <br />
            700여 개의 기업정보를 제공합니다.
            <br />
          </p>
        </div>
      </div>
      <div class="col-lg-3" style="visibility: visible">
        <div class="text-center">
          <p class="title">
            최신뉴스
          </p>
          <img :src="service1" alt="service1" class="m-b-lg" />
          <p style="height: 40px; font-weight: 800; color: #000000">
            54개의 언론사와 날짜별 필터링을 통해
            <br />
            최신 뉴스를 간편하게 조회할 수 있습니다.
            <br />
          </p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import service1 from "src/assets/service1.png";
import service2 from "src/assets/service2.png";
import service3 from "src/assets/service3.png";
import service4 from "src/assets/service4.png";

export default {
  name: "SectionForth",

  components: {},
  data() {
    return {
      sampleData: "",
      service1,
      service2,
      service3,
      service4,
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
.container {
  font-family: "NanumBarunGothic";
  width: 1050px;
  height: 600px;
  margin: auto;
  background-color: #f4f4f4;
  margin-top: 60px;
}
.col-lg-12 {
  width: 100%;
  position: relative;
  min-height: 1px;
  padding-right: 15px;
  padding-left: 15px;
}
.text-center {
  text-align: center;
}
.navy-line {
  width: 120px;
  height: 1px;
  margin: 60px auto 0;
  border-bottom: 2px solid #3d5a80;
}
h1 {
  margin-top: 30px;
  line-height: 1rem;
  font-size: 30px;
  font-weight: 200;
}
p {
  display: block;
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 0px;
  margin-inline-end: 0px;
}
.title {
  height: 40px;
  font-size: 20px;
  font-weight: 800;
}
img {
  vertical-align: middle;
  width: 200px;
}
</style>
