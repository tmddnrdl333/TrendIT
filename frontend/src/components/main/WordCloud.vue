<template>
  <div id="word-cloud-container">
    <div id="word-cloud">
      <div></div>
    </div>
  </div>
</template>

<script>
import { ref } from "vue";
import cloud from "d3-cloud";
import "d3";

export default {
  props: {
    wordCloudData: {
      type: Array,
      default: null,
    },
  },
  setup() {
    return {
      data: ref([]),
    };
  },
  async mounted() {
    console.log("mounted, wordCloudData");
    console.log(this.wordCloudData);
    await this.genLayout();
  },
  methods: {
    async genLayout() {
      console.log("genLayout called");
      this.wordCloudData.forEach((item) => {
        this.data.push({ text: item.keyword, size: item.count });
      });
      try {
        const ctx = document.getElementById("word-cloud");
        ctx.parentNode.removeChild(ctx);
      } catch (e) {
        console.warn(`can't delete this context`);
      }
      const parent = document.getElementById("word-cloud-container");
      const wcdiv = document.createElement("div");
      wcdiv.setAttribute("id", "word-cloud");
      parent.appendChild(wcdiv);
      const width = 500;
      const height = 500;
      cloud()
        .size([width, height])
        .words(this.data)
        .padding(2)
        .rotate(0)
        .font("Impact")
        .text((d) => d.text)
        .fontSize((d) => d.size)
        .on("end", this.end)
        // .spiral("archimedean")
        .start()
        .stop();
    },
    end() {
      const width = 500;
      const height = 500;
      d3.select("#word-cloud")
        .append("svg")
        .attr("width", width)
        .attr("height", height)
        .style("background", "rgb(78, 219, 226)")
        .append("g")
        .attr("transform", "translate(" + width / 2 + "," + height / 2 + ")") // g를 중심에서 단어들을 그리기 때문에 g를 svg 중심으로 이동
        .selectAll("text")
        .data(this.data)
        .enter()
        .append("text")
        .style("fill", (d) => {
          if (d.size > 30) return "red";
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
      // await this.genLayout();
    },
  },
};
</script>

<style scoped>
/* #word-cloud {
  border: 1px solid rgb(78, 219, 226);
  width: 510px;
  margin: 20px;
  background: rgb(78, 219, 226);
} */
</style>
