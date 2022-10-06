<template>
  <q-card class="wordcloud-container">
    <q-card-section class="card-title q-ml-lg">
      <div class="title q-mt-xs">오늘의 트렌드</div>
    </q-card-section>
    <q-card-section class="card-content row items-center justify-center">
      <!-- WordCloud -->
      <div id="wordcloud-box">
        <div id="wordcloud">
          <div></div>
        </div>
      </div>
      <!-- WordCloud -->
      <div class="q-pa-md q-gutter-md">
        <div class="text-center">
          <q-btn-toggle
            v-model="type"
            toggle-color="deep-orange-3"
            toggle-text-color="black"
            square
            :options="[
              { label: '일별', value: 'day' },
              { label: '주별', value: 'week' },
              { label: '월별', value: 'month' },
              { label: '연도별', value: 'year' },
            ]"
          />
        </div>
        <q-card class="rank-container text-center">
          <q-card-section class="rank-container-title">
            키워드 순위
          </q-card-section>
          <q-separator />
          <template v-for="(item, index) in trendRankData" :key="index">
            <div
              class="rank-item row items-center justify-center rank-container-content"
            >
              <div class="col-2">{{ index + 1 }}</div>
              <div class="col-5">{{ item.keyword }}</div>
              <div class="col-2">{{ item.count }}건</div>
            </div>
            <q-separator inset />
          </template>
        </q-card></div
    ></q-card-section>
  </q-card>
</template>

<script>
import { ref } from "vue";
import { getWordcloudApi } from "boot/stats.js";
import cloud from "d3-cloud";
import "d3";

export default {
  name: "WordCloudAndRank",
  setup() {
    return {
      newsData: ref([]),
      wordCloudData: ref([]),
      trendRankData: ref([]),
      data: ref([]),
      type: ref("day"),
    };
  },

  async created() {
    await this.getData();
  },
  async mounted() {
    await this.genLayout();
  },

  methods: {
    async getData() {
      await getWordcloudApi(
        this.type,
        (response) => {
          const resData = response.data.data;
          // 이 중 30개 키워드와 count + isCompany? or 분류 는 워드 클라우드에
          // 이 중 6개는 키워드와 count 는 랭크에
          // 이 중 5개는 뉴스는 오늘의 뉴스에 넣어줘야됨
          this.newsData = resData;
          this.wordCloudData = resData.slice(0, 20);
          this.trendRankData = resData.slice(0, 6);
        },
        () => console.warn("failed to get word cloud")
      );
    },
    async genLayout() {
      let max_size = 0;
      this.data = [];
      this.wordCloudData.forEach((item) => {
        max_size = item.count > max_size ? item.count : max_size;
        this.data.push({
          text: item.keyword,
          size: item.count,
          keywordId: item.keywordId,
        });
      });
      try {
        const ctx = document.getElementById("wordcloud");
        ctx.parentNode.removeChild(ctx);
      } catch (e) {
        console.warn(`can't delete this context`);
      }
      const parent = document.getElementById("wordcloud-box");
      const wcdiv = document.createElement("div");
      wcdiv.setAttribute("id", "wordcloud");
      parent.appendChild(wcdiv);
      const width = 700;
      const height = 400;
      cloud()
        .size([width, height])
        .words(this.data)
        .padding(10)
        .rotate(0)
        .font("Impact")
        .text((d) => d.text)
        .fontSize((d) => (d.size / max_size) * 80)
        .on("end", this.end)
        // .spiral("archimedean")
        .start()
        .stop();
    },
    end() {
      const width = 700;
      const height = 400;

      const date = new Date();
      const to =
        date.getFullYear() +
        "-" +
        (date.getMonth() + 1).toString().padStart(2, "0") +
        "-" +
        date.getDate().toString().padStart(2, "0");
      date.setDate(date.getDate() - 7).toString();
      const from =
        date.getFullYear() +
        "-" +
        (date.getMonth() + 1).toString().padStart(2, "0") +
        "-" +
        date.getDate().toString().padStart(2, "0");

      d3.select("#wordcloud")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .style("background", "#E8F1F6")
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")") // g를 중심에서 단어들을 그리기 때문에 g를 svg 중심으로 이동
        .selectAll("text")
        .data(this.data)
        .enter()
        .append("text")
        .style("cursor", "pointer")
        .style("fill", (d) => {
          if (d.size > 45) return "#EE6C4D";
          return "#3D5A80";
        })
        .style("font-size", (d) => {
          return d.size + "px";
        })

        .style("font-family", "NanumBarunGothicBold")
        .attr("text-anchor", "middle")
        .attr("transform", (d) => {
          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
        })
        .text((d) => d.text)
        .on("click", (d) => {
          this.$router.push({
            name: "search_keyword",
            params: { keyword_id: d.keywordId },
            query: { period: from + "~" + to },
          });
        });
    },
  },
  watch: {
    type: async function () {
      await this.getData();
    },
    wordCloudData: async function () {
      await this.genLayout();
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/earlyaccess/hanna.css");

.title {
  font-family: "NanumBarunGothicBold";
  font-size: 17px;
  margin-top: 10px;
}

.card-title {
  padding: 10px;
}

.card-content {
  padding-top: 5px;
}

.wordcloud-container {
  width: 1050px;
  margin: 20px auto;
  font-family: "NanumBarunGothic";
}
.rank-container {
  width: 250px;
  height: 330px;
}
.rank-container-title {
  font-family: "NanumBarunGothic";
  /* font-family: "Hanna", fantasy; */
  font-size: 18px;
  padding: 12px;
}
.rank-container-content {
  font-family: "NanumBarunGothic";
  /* font-family: "Hanna", fantasy; */
  font-size: 15px;
}
.rank-item {
  height: 45px;
}

.bg-toggle {
  background: #ee6c4d;
}
</style>
