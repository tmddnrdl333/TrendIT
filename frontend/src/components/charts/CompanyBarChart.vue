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
      <ChartComp
        v-if="loaded"
        :chart-data="chartData"
        :chart-options="chartOptions"
        class="chart"
      />
      <div v-else class="chart" style="border: 1px red solid">
        그래프를 표시할 수 없습니다.
      </div>
    </q-card-section>
  </q-card>
</template>

<script>
import { ref } from "vue";
import { companyChartApi } from "boot/stats.js";
import ChartComp from "./ChartComp.vue";

export default {
  setup() {
    const response = ref(null);
    const data_list = ref(null);

    const type = ref("day");

    const loaded = ref(false);
    const chartData = ref(null);
    const chartOptions = ref({
      indexAxis: "y",
      plugins: {
        legend: {
          display: false,
        },
        datalabels: {
          display: false,
        },
        tooltip: {
          boxWidth: 10,
        },
      },
      scales: {
        x: {
          grid: {
            drawBorder: false,
            color: "black",
            lineWidth: 0.2,
          },
          ticks: {
            callback: (val, index) => {
              return val !== 0 ? val : "";
            },
            padding: 3,
            stepSize: 100, // 보조눈금
          },
        },
        y: {
          grid: {
            display: false,
          },
        },
      },
      responsive: true,

      animation: {
        x: {
          duration: 500,
        },
        y: {
          duration: 200,
        },
      },
    });

    return {
      response,
      data_list,

      type,

      loaded,
      chartData,
      chartOptions,
    };
  },
  created() {
    this.loaded = false;
    this.loadChartData();
  },
  methods: {
    async loadChartData() {
      await companyChartApi(
        { type: this.type, val: this.slide },
        (response) => {
          this.response = response.data;
          this.data_list = response.data.data;

          // 초기화
          this.chartData = {
            labels: [],
            datasets: [
              {
                label: "기사 수", // 툴팁 표시 이름
                backgroundColor: "#EE6C4D", // 막대 색상
                barThickness: "15", // 막대 굵기
                data: [],
              },
            ],
          };

          // forEach push item
          this.data_list.forEach((item) => {
            this.chartData.labels.push(item.keyword);
            this.chartData.datasets[0].data.push(item.frequency);
          });
          // console.log(this.chartData);
          this.loaded = true;
        },
        () => console.warn("failed")
      );
    },
  },
  watch: {
    type: function () {
      this.loadChartData();
    },
  },
  components: { ChartComp },
};
</script>

<style scoped>
.chart {
  width: 800px;
  height: 250px;
  margin: 10px auto;
  /* test */
}
.chart-container {
  padding: 6px;
  width: 850px;
  /* test */
}
</style>
