<template>
  <div class="container column">
    <div class="col text-center">
        <div class="navy-line"></div>
        <h4 style="font-weight: 600; margin-bottom: 24px">Background</h4>
        <p>Background of the birth of TrendIT</p>
    </div>
    <div class="col-9 section2">
      <div class="s2_box">
        <q-img :src="Circle1">
          <p class="s2_content">
            2030 세대 70%
            <br />
            인터넷 매체를 통해
            <br />
            뉴스 및 시사정보 이용
          </p>
        </q-img>
        <div class="s2_title">
          한국언론진흥재단
          <br />
          '2021 언론수용자 조사'
        </div>
      </div>
      <div class="s2_box">
        <q-img :src="Circle2">
          <p class="s2_content">
            검색 및 뉴스 수집 플랫폼
            <br />
            통한 온라인 뉴스 이용률,
            <br />
            대한민국 전세계 1위
          </p>
        </q-img>
        <div class="s2_title">
          로이터저널리즘연구소
          <br />
          '디지털뉴스 리포트 2021'
        </div>
      </div>
      <div class="s2_box">
        <q-img :src="Circle3">
          <p class="s2_content">
            관심분야 뉴스 서비스 이용 이유
            <br />
            1위 트렌드와 이슈의 쉬운 파악
            <br />
            2위 쉽고 빠른 정보 획득
          </p>
        </q-img>

        <div class="s2_title">
          엠브레인 트렌드모니터
          <br />
          '2021 뉴스 서비스 관련 조사'
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Circle1 from "src/assets/Circle1.png";
import Circle2 from "src/assets/Circle2.png";
import Circle3 from "src/assets/Circle3.png";

export default {
  name: "SectionSecond",

  components: {},
  data() {
    return {
      sampleData: "",
      Circle1,
      Circle2,
      Circle3,
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
.container{
  font-family: "NanumBarunGothic";
  width: 1050px;
  height: 600px;
  margin: auto;
  background-color: #f4f4f4;
}
.section2 {
  display: grid;
  -webkit-box-pack: center;
  -webkit-box-align: center;
  align-items: center;
  grid-template-columns: repeat(3, 340px);
  justify-content: center;
  background-color: #f4f4f4;
}
.s2_box {
  width: 280px;
  text-align: center;
  margin: auto;
}
.s2_title {
  white-space: pre-line;
  font-size: 15px;
  font-weight: 800;
  line-height: 1.47;
  margin-top: 19px;
}
.s2_content {
  white-space: pre-line;
  font-size: 20px;
  font-weight: 800;
  line-height: 1.47;
  margin-top: 100px;
  color: #ffffff;
}
.row {
  height: 150px;
  justify-content: center;
  background-color: #ffffff;
}
.text-center {
  text-align: center;
}
h4 {
  margin-top: 30px;
  line-height: 1rem;
  font-size: 30px;
  font-weight: 200;
}
.navy-line {
  width: 180px;
  height: 1px;
  margin: 60px auto 0;
  border-bottom: 2px solid #3d5a80;
}
</style>
