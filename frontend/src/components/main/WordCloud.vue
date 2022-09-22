<template>
  <div id="word-cloud"></div>
</template>

<script>
import cloud from "d3-cloud";
import "d3";
export default {
  data() {
    return {
      words: [
        { text: "카카오", size: 50 },
        { text: "비트코인", size: 30 },
        { text: "가상화폐", size: 10 },
        { text: "폴드", size: 25 },
        { text: "라인", size: 50 },
        { text: "네이버", size: 40 },
        { text: "플립", size: 30 },
        { text: "삼성전자", size: 24 },
        { text: "파이썬", size: 10 },
        { text: "갤럭시", size: 34 },
      ],
    };
  },
  mounted() {
    this.genLayout();
  },
  methods: {
    genLayout() {
      const width = 500;
      const height = 500;
      //   const cloud = require("d3-cloud");
      cloud()
        .size([width, height])
        .words(this.words)
        .padding(2)
        .rotate(0)
        .font("Impact")
        .text(function (d) {
          return d.text;
        })
        .fontSize(function (d) {
          return d.size;
        })
        .on("end", this.end)
        // .spiral("archimedean")
        .start()
        .stop();
    },
    end(words) {
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
        .data(words)
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
};
</script>

<style scoped>
#word-cloud {
  border: 1px solid rgb(78, 219, 226);
  width: 510px;
  margin: 20px;
  background: rgb(78, 219, 226);
}
</style>
