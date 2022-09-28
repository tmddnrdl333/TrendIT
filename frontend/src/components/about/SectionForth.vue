<template>
  <div>
    <section class="comments gray-section" id="service">
      <div class="container">
        <div class="row">
          <div class="col-lg-12 text-center">
            <div class="navy-line"></div>
            <h1 style="font-weight: 600">Services</h1>
            <p>Services provided by TrendIT</p>
          </div>
        </div>

        <div class="row features-block">
          <div class="col-lg-3" style="visibility: visible">
            <div class="text-center">
              <p
                style="
                  height: 40px;
                  font-size: 20px;
                  font-weight: 800;
                  color: #000000;
                "
              >
                트랜드 분석
              </p>
              <img :src="service2" alt="service2" class="m-b-lg" />
              <p style="height: 40px; font-weight: 800; color: #000000">
                워드클라우드와 어쩌고 저쩌고를 통해
                <br />
                트랜드를 알기 쉽게 한눈에 파악하고
                <br />
                사용자에게 보여줄게
              </p>
            </div>
          </div>
          <div class="col-lg-3" style="visibility: visible">
            <div class="text-center">
              <p
                style="
                  height: 40px;
                  font-size: 20px;
                  font-weight: 800;
                  color: #000000;
                "
              >
                키워드 분석
              </p>
              <img :src="service3" alt="service3" class="m-b-lg" />
              <p style="height: 40px; font-weight: 800; color: #000000">
                워드클라우드와 어쩌고 저쩌고를 통해
                <br />
                트랜드를 알기 쉽게 한눈에 파악하고
                <br />
                사용자에게 보여줄게
              </p>
            </div>
          </div>
          <div class="col-lg-3" style="visibility: visible">
            <div class="text-center">
              <p
                style="
                  height: 40px;
                  font-size: 20px;
                  font-weight: 800;
                  color: #000000;
                "
              >
                기업 리스트
              </p>
              <img :src="service4" alt="service4" class="m-b-lg" />
              <p style="height: 40px; font-weight: 800; color: #000000">
                워드클라우드와 어쩌고 저쩌고를 통해
                <br />
                트랜드를 알기 쉽게 한눈에 파악하고
                <br />
                사용자에게 보여줄게
              </p>
            </div>
          </div>
          <div class="col-lg-3" style="visibility: visible">
            <div class="text-center">
              <p
                style="
                  height: 40px;
                  font-size: 20px;
                  font-weight: 800;
                  color: #000000;
                "
              >
                최신뉴스
              </p>
              <img :src="service1" alt="service1" class="m-b-lg" />
              <p style="height: 40px; font-weight: 800; color: #000000">
                워드클라우드와 어쩌고 저쩌고를 통해
                <br />
                트랜드를 알기 쉽게 한눈에 파악하고
                <br />
                사용자에게 보여줄게
              </p>
            </div>
          </div>
        </div>
      </div>
    </section>
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
section.comments {
  padding-bottom: 80px;
}
.gray-section {
  background: #f4f4f4;
  margin-top: 60px;
}
.container {
  width: 1170px;
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
}
.row {
  margin-right: -80px;
  margin-left: -80px;
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
img {
}
.features-block {
  margin-top: 20px;
}
img {
  vertical-align: middle;
}
</style>
