<template>
  <div class="container">
    <div class="row">
      <div class="col text-center">
        <div class="navy-line"></div>
        <h1 style="font-weight: 600; margin-bottom: 24px">숫자로 알아보는 TrendIT</h1>
        <p>
          누적 1백만개 이상의 기사를 분석하는
          <br />
          온라인 IT 뉴스 분석 시스템
        </p>
      </div>
    </div>
    <div class="row justify-center justify-content-center q-gutter-xl q-my-md">
      <div class="col">
        <div class="text-center">
          <q-img :src="CircleBig" alt="CircleBig" style="width: 300px">
            <p class="s5_big_title" style="margin-top: 105px">
              누적 분석 기사
            </p>
            <p class="s5_big_content">2,208,044건</p>
          </q-img>
          <p style="height: 40px; font-weight: 800; color: #000000"></p>
        </div>
      </div>

      <div class="col-7">
        <div class="row q-gutter-lg q-mb-lg">
          <q-img :src="CircleSmall" class="circle-small">
            <div class="column absolute-center">
              <div class="col-5 flex flex-center">
                <p class="s5_title">
                  인용 언론사 <br />
                </p>
              </div>
              <div class="col-7">
                <span class="s5_content">54 </span>
                <span class="s5_content_unit">개</span>
              </div>
            </div>
          </q-img>
          <q-img :src="CircleSmall" class="circle-small">
            <div class="column absolute-center">
              <div class="col-5 flex flex-center">
                <p class="s5_title">
                  최대 뉴스 생산 달 <br />
                </p>
              </div>
              <div class="col-7">
                <span class="s5_content">12 </span>
                <span class="s5_content_unit">월</span>
              </div>
            </div>
          </q-img>
          <q-img :src="CircleSmall" class="circle-small">
            <div class="column absolute-center">
              <div class="col-5 flex flex-center">
                <p class="s5_title">
                  1일 최대 뉴스 수 <br />
                </p>
              </div>
              <div class="col-7">
                <span class="s5_content">1,234 </span>
                <span class="s5_content_unit">건</span>
              </div>
            </div>
          </q-img>
        </div>
        <div class="row q-gutter-lg q-mb-lg">
          <q-img :src="CircleSmall" class="circle-small">
            <div class="column absolute-center">
              <div class="col-5 flex flex-center">
                <p class="s5_title">
                  All Time Best 키워드<br />
                </p>
              </div>
              <div class="col-7">
                <span class="s5_content">SSAFY</span><br />
                <span class="s5_content_unit">(1234 회)</span>
              </div>
            </div>
          </q-img>
          <q-img :src="CircleSmall" class="circle-small">
            <div class="column absolute-center">
              <div class="col-5 flex flex-center">
                <p class="s5_title">
                  All Time Best<br />
                  기업 키워드 <br />
                </p>
              </div>
              <div class="col-7">
                <span class="s5_content">삼성전자</span><br />
              <span class="s5_content_unit">(1234 회)</span>
              </div>
            </div>
          </q-img>
          <q-img :src="CircleSmall" class="circle-small">
            <div class="column  absolute-center">
              <div class="col-5 flex flex-center">
                <p class="s5_title">
                All Time Best 언론사 <br />
                </p>
              </div>
              <div class="col-7">
                <span class="s5_content"> 싸피일보</span>
                <span class="s5_content_unit">(123456 건)</span>
              </div>
            </div>
          </q-img>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CircleSmall from "src/assets/CircleSmall.png";
import CircleBig from "src/assets/CircleBig.png";

export default {
  name: "SectionFifth",

  components: {},
  data() {
    return {
      sampleData: "",
      CircleSmall,
      CircleBig,
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
h1 {
  margin-top: 30px;
  line-height: 1rem;
  font-size: 30px;
  font-weight: 200;
}
.navy-line {
  width: 120px;
  height: 1px;
  margin: 60px auto 0;
  border-bottom: 2px solid #3d5a80;
}
.container {
  font-family: "NanumBarunGothic";
  width: 1050px;
  height: 700px;
  margin: auto;
  text-align: center;
}

.s5_big_title {
  font-size: 25px;
  font-weight: bold;
}
.s5_big_content {
  font-size: 45px;
  font-weight: bold;
  color: #2885fe;
}
.s5_title {
  font-size: 16px;
  font-weight: bold;
  color: #000000;
  align-self: flex-end;
}
.s5_content {
  font-size: 30px;
  font-weight: bold;
  color: #2885fe;
}
.s5_content_unit {
  font-size: 16px;
  font-weight: bold;
  color: #2885fe;
}
.circle-small {
  width: 170px;
}
.column {
  background: none;
  width: 200px;
  height: 150px;
}
</style>
