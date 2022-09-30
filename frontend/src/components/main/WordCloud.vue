<template>
  <q-card
    class="wordcloud-container row items-center justify-center q-pa-lg q-my-md"
  >
    <!-- WordCloud -->
    <div id="wordcloud-box">
      <div id="wordcloud">
        <div></div>
      </div>
    </div>
    <!-- WordCloud -->
    <div class="q-pa-md row items-start q-gutter-md">
      <q-card class="rank-container text-center">
        <q-card-section> 키워드 순위 </q-card-section>
        <q-separator />
        <template v-for="(item, index) in trendRankData" :key="index">
          <div class="rank-item row items-center">
            <div class="col-3">{{ index + 1 }}</div>
            <div class="col-3">{{ item.keyword }}</div>
            <div class="col-3">{{ item.count }}건</div>
          </div>
          <q-separator inset />
        </template>
      </q-card>
    </div>
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
    };
  },

  async created() {
    await getWordcloudApi(
      "day",
      (response) => {
        const resData = response.data.data;
        // 이 중 30개 키워드와 count + isCompany? or 분류 는 워드 클라우드에
        // 이 중 6개는 키워드와 count 는 랭크에
        // 이 중 5개는 뉴스는 오늘의 뉴스에 넣어줘야됨
        this.newsData = resData;
        this.wordCloudData = resData.slice(0, 10);
        this.trendRankData = resData.slice(0, 7);
      },
      () => console.warn("failed to get word cloud")
    );
  },
  async mounted() {
    await this.genLayout();
  },

  methods: {
    async genLayout() {
      console.log("drawing wordcloud...");

      let max_size = 0;
      this.wordCloudData.forEach((item) => {
        max_size = item.count > max_size ? item.count : max_size;
        this.data.push({ text: item.keyword, size: item.count });
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
        .padding(2)
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
        .style("fill", (d) => {
          if (d.size > 60) return "#EE6C4D";
          return "blue";
        })
        .style("font-size", (d) => {
          return d.size + "px";
        })
        // .style("padding", "100px")
        // .style("margin", "100px")
        .style("font-family", "Impact")
        .attr("text-anchor", "middle")
        .attr("transform", (d) => {
          return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
        })
        .text((d) => d.text);
    },
  },
  watch: {
    wordCloudData: async function () {
      console.log("data changed");
      await this.genLayout();
    },
  },
};
</script>

<style scoped>
.wordcloud-container {
  width: 1050px;
  margin: 20px auto;
}
.rank-container {
  width: 250px;
  height: 380px;
}
.rank-item {
  height: 45px;
}
</style>
