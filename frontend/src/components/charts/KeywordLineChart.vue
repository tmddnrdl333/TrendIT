<template>
  <q-card class="chart-container text-center">
    <q-card-section>
      <q-btn-toggle
        v-model="type"
        toggle-color="primary"
        :options="[
          { label: '일별', value: 'day' },
          { label: '주별', value: 'week' },
          { label: '월별', value: 'month' },
          { label: '연도별', value: 'year' },
        ]"
        class="label-option"
      />
      <!-- CHART -->
      <div id="chartbox">
        <!-- <canvas id="myChart" class="chart" width="400" height="250" /> -->
      </div>
    </q-card-section>
  </q-card>
  <div class="q-pa-md q-gutter-md">
    <q-card class="side-card q-pa-sm column justify-evenly">
      <div class="row justify-center">
        <q-icon name="event" size="21px" left />
        <div class="most-news-date-title">
          가장 기사건수가 많았던&nbsp;
          <strong>{{ side_card.word1 }}</strong>
        </div>
      </div>
      <div class="row justify-center most-news-date-content">
        {{ side_card.date }}
      </div>
    </q-card>
    <q-card class="side-card q-pa-sm column justify-evenly">
      <div class="row justify-center">
        <q-icon name="trending_up" size="21px" left />
        <div class="most-news-date-title">
          <strong class="rating-title">{{ side_card.word2 }}</strong>
          &nbsp;대비 증감률
        </div>
      </div>
      <div class="row justify-center rating-content">
        <q-icon
          v-if="side_card.percentage > 0"
          name="arrow_drop_up"
          color="red"
          size="35px"
        />
        <q-icon v-else name="arrow_drop_down" color="blue" size="35px" />
        <div class="rating-content" :style="{ color: updownColor }">
          {{ Math.abs(side_card.percentage) }}%
        </div>
      </div>
    </q-card>
  </div>
</template>

<script>
import { ref } from "vue";
import { keywordChartApi } from "boot/stats.js";
import Chart from "chart.js/auto";

export default {
  props: {
    keyword: {
      type: String,
      default: null,
    },
  },
  setup() {
    return {
      data_list: ref(null),
      type: ref("day"),
      side_card: ref({
        word1: "null",
        word2: "null",
        date: "null",
        percentage: "null",
      }),
      updownColor: ref("black"),
    };
  },
  async mounted() {
    await this.drawChart();
  },
  computed: {
    word1() {
      switch (this.type) {
        case "day":
          return "날";
        case "week":
          return "주";
        case "month":
          return "달";
        case "year":
          return "연도";
        default:
          return null;
      }
    },
    word2() {
      switch (this.type) {
        case "day":
          return "전일";
        case "week":
          return "전주";
        case "month":
          return "전월";
        case "year":
          return "전년";
        default:
          return null;
      }
    },
  },
  methods: {
    async loadChartData() {
      await keywordChartApi(
        { type: this.type, keyword: this.keyword },
        (response) => {
          this.data_list = response.data.data;
        },
        () => console.warn("failed")
      );
    },
    async drawChart() {
      try {
        const ctx = document.getElementById("myChart");
        ctx.parentNode.removeChild(ctx);
      } catch (e) {
        // console.warn(e);
      }
      const parent = document.getElementById("chartbox");
      const canvas = document.createElement("canvas");
      canvas.setAttribute("id", "myChart");
      canvas.setAttribute("class", "chart");
      canvas.setAttribute("width", "400");
      canvas.setAttribute("height", "250");
      parent.appendChild(canvas);

      const ctx = document.getElementById("myChart");

      // this.data_list에 response를 채워줌
      await this.loadChartData();

      /*
        TODO: labels를 배열로 반환해주는 함수를 따로 만들어야 할듯
      */
      // 여기부터 ~
      let labels = [];
      for (let i = 6; i >= 0; i--) {
        let date_val = new Date();
        date_val.setDate(date_val.getDate() - i);
        let date_label = date_val.getMonth() + 1 + "/" + date_val.getDate();
        labels.push(date_label);
      }
      // ~ 여기까지를 함수화

      let data_array = [];
      let max_label = null;
      let max_val = 0;
      if (this.data_list) {
        for (let i = 0; i < this.data_list.length; i++) {
          data_array.push(this.data_list[i]);
          if (this.data_list[i] >= max_val) {
            max_val = this.data_list[i];
            max_label = labels[i];
          }
        }
      }

      this.side_card.word1 = this.word1;
      this.side_card.word2 = this.word2;
      this.side_card.date = max_label + " (" + max_val + "건)";
      this.side_card.percentage =
        Math.round(
          (this.data_list[this.data_list.length - 1] /
            this.data_list[this.data_list.length - 2] -
            1) *
            10000
        ) / 100;
      if (this.side_card.percentage > 0) {
        this.updownColor = "red";
      } else {
        this.updownColor = "blue";
      }

      let data = {
        labels: labels,
        datasets: [
          {
            label: this.keyword, // 범례
            data: data_array,
            fill: false,
            borderColor: "#EE6C4D",
            tension: 0.1,
          },
        ],
      };

      const myChart = new Chart(ctx, {
        type: "line",
        data: data,
        options: {
          scales: {
            y: {
              beginAtZero: true,
            },
          },
        },
      });
      myChart;
    },
  },
  watch: {
    type: function () {
      /*
      TODO: watch로 type 선택할 때마다 바꾸는거 오류 해결해야 함
      */
      this.drawChart();
    },
    keyword: function () {
      this.drawChart();
    },
  },
  components: {},
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/earlyaccess/hanna.css");

.chart {
  margin: 10px auto;

  /* test */
}
.chart-container {
  padding: 5px;
  width: 450px;
  /* test */
}
.side-card {
  width: 300px;
  height: 130px;
}

.most-news-date-title {
  font-family: "Hanna", fantasy;
  font-size: 16px;
}
.most-news-date-content {
  font-family: "Hanna", fantasy;
  font-size: 24px;
}
.rating-title {
  font-family: "Hanna", fantasy;
  font-size: 16px;
}
.rating-content {
  font-family: "Hanna", fantasy;
  font-size: 24px;
}
.label-option {
  font-family: "Hanna", fantasy;
  font-size: 21px;
}
</style>
