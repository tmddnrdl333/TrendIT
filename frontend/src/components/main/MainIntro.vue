<template>
  <div class="main__intro__container">
    <img
      class="main__intro__pageinfo__img"
      src="https://cdn.quasar.dev/img/parallax2.jpg"
    />
    <div class="main__intro__pageinfo__container">
      <div class="main__intro__pageinfo__newscount">
        <p>금일 뉴스 수집 : {{ newsCollectData.todayNewsCount }} 건</p>
        <p>전체 뉴스 수집 : {{ newsCollectData.allNewsCount }} 건</p>
      </div>
      <div class="main__intro__pageinfo__pageintro">
        <p>쉽고 빠른 IT 트렌드 분석 플랫폼</p>
      </div>
      <div class="main__intro__pageinfo__remainder">
        <p>트렌드 분석 -></p>
      </div>
    </div>
  </div>
</template>

<script>
import { getNewsCollectData } from "boot/stats.js";
import { NewsCollectRes } from "boot/response/NewsCollectRes.js";
export default {
  name: "MainIntro",
  async setup() {
    let newsCollectData;
    await getNewsCollectData(
      (response) => {
        newsCollectData = new NewsCollectRes(
          response.data.data.todayCount,
          response.data.data.totalCount
        );
      },
      () => {
        // TODO 예외처리 어떻게?
        console.warn("WARN!");
      }
    );
    console.log(newsCollectData);
    return {
      newsCollectData,
    };
  },
  data() {
    return {};
  },

  mounted() {},

  methods: {},
};
</script>

<style scoped>
.main__intro__container {
  display: flex;
  flex-direction: row;
  width: 100%;
  /* align-items: center; */
  background: blue;
}

.main__intro__pageinfo__img {
  width: 30%;
  margin-left: 20px;
}
.main__intro__pageinfo__container {
  width: 70%;
  display: flex;
  flex-direction: column;
}
.main__intro__pageinfo__newscount {
  color: aliceblue;
  height: 20%;
  text-align: right;
  margin-top: 30px;
  margin-right: 30px;
}
.main__intro__pageinfo__pageintro {
  height: 50%;
  text-align: center;
  font-size: 30px;
  color: aliceblue;
}

.main__intro__pageinfo__remainder {
  text-align: center;
  color: white;
}
</style>
