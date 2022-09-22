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
      />
      <!-- CHART -->
      <div id="chartbox">
        <!-- <canvas id="myChart" class="chart" width="400" height="250" /> -->
      </div>
    </q-card-section>
  </q-card>
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
    const data_list = ref(null);
    const type = ref("day");

    return {
      data_list,
      type,
    };
  },
  async mounted() {
    await this.drawChart();
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
      this.data_list.forEach((item) => {
        data_array.push(item);
      });
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
  },
  components: {},
};
</script>

<style scoped>
.chart {
  margin: 10px auto;
  /* test */
}
.chart-container {
  padding: 5px;
  width: 450px;
  /* test */
}
</style>
