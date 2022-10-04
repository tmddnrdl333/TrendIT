<template>
  <div>
    <section class="comments gray-section" id="service">
      <!-- style="background: #ffffff" -->

      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <div class="navy-line"></div>
            <h1 style="font-weight: 600">숫자로 알아보는 TrendIT</h1>
            <p>
              누적 1백만개 이상의 기사를 분석하는
              <br />
              온라인 IT 뉴스 분석 시스템
            </p>
          </div>
        </div>

        <div
          class="row justify-center justify-content-center q-gutter-xl q-mb-lg"
          style="margin-top: -30px"
        >
          <div class="col-3" style="visibility: visible">
            <div class="text-center">
              <q-img :src="CircleBig" alt="CircleBig" style="width: 400px">
                <p class="s5_big_title" style="margin-top: 120px">
                  누적 분석 기사
                </p>
                <p class="s5_big_content">1,234,567개</p>
              </q-img>
              <p style="height: 40px; font-weight: 800; color: #000000"></p>
            </div>
          </div>

          <div class="" style="visibility: visible">
            <div class="row q-gutter-lg q-mb-lg">
              <div class="">
                <q-img :src="CircleSmall" style="width: 200px">
                  <p class="s5_title" style="margin-top: 30px">
                    인용 언론사 <br />
                  </p>
                  <span class="s5_content">54</span>
                  <span class="s5_content_unit">개</span>
                </q-img>
              </div>
              <div class="">
                <q-img :src="CircleSmall" style="width: 200px">
                  <p class="s5_title" style="margin-top: 30px">
                    최대 뉴스 생산 달 <br />
                  </p>
                  <span class="s5_content">12</span>
                  <span class="s5_content_unit">월</span>
                </q-img>
              </div>
              <div class="">
                <q-img :src="CircleSmall" style="width: 200px">
                  <p class="s5_title" style="margin-top: 30px">
                    1일 최대 뉴스 수 <br />
                  </p>
                  <span class="s5_content">1,234</span>
                  <span class="s5_content_unit">건</span>
                </q-img>
              </div>
            </div>
            <div class="row q-gutter-lg q-mb-lg">
              <div class="">
                <q-img :src="CircleSmall" style="width: 200px">
                  <p class="s5_title" style="margin-top: 30px">
                    All Time Best 키워드
                    <br />
                  </p>
                  <span class="s5_content">SSAFY</span><br />
                  <span class="s5_content_unit">(1234 회)</span>
                </q-img>
              </div>
              <div class="">
                <q-img :src="CircleSmall" style="width: 200px">
                  <p class="s5_title" style="margin-top: 10px">
                    All Time Best
                    <br />
                    기업 키워드 <br />
                  </p>
                  <span class="s5_content">삼성전자</span><br />
                  <span class="s5_content_unit">(1234 회)</span>
                </q-img>
              </div>
              <div class="">
                <q-img :src="CircleSmall" style="width: 200px">
                  <p class="s5_title" style="margin-top: 30px">
                    All Time Best 언론사 <br />
                  </p>
                  <span class="s5_content"> 싸피일보</span>
                  <span class="s5_content_unit">(123456 건)</span>
                </q-img>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
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
.comments {
}
.gray-section {
}
.container {
  width: 100%;
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
  text-align: center;
}

.s5_big_title {
  font-size: 30px;
  font-weight: bold;
}
.s5_big_content {
  font-size: 60px;
  font-weight: bold;
  color: #2885fe;
}
.s5_title {
  font-size: 16px;
  font-weight: bold;
}
.s5_content {
  font-size: 36px;
  font-weight: bold;
  color: #2885fe;
}
.s5_content_unit {
  font-size: 16px;
  font-weight: bold;
  color: #2885fe;
}
</style>
