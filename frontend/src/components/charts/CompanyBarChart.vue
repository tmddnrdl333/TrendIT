<template>
  <q-card flat bordered class="chart-container text-center bg-grey-1">
    <q-card-section>
      <q-btn-toggle
        v-model="type"
        toggle-color="deep-orange-3"
        toggle-text-color="black"
        square
        :options="[
          { label: '일간', value: 'day' },
          { label: '주간', value: 'week' },
          { label: '월간', value: 'month' },
          { label: '연간', value: 'year' },
        ]"
      />
      <!-- CHART -->
      <bar-chart-comp
        v-if="loaded"
        :chart-data="chartData"
        :chart-options="chartOptions"
        :width="550"
        :height="300"
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
import BarChartComp from "./BarChartComp.vue";

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
            stepSize: 100,
          },
          title: {
            display: true,
            align: "end",
            color: "#808080",
            font: {
              size: 12,
              family: "NanumBarunGothic",
              weight: 300,
            },
            text: "(단위: 건)",
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
        this.type,
        (response) => {
          this.response = response.data;
          this.data_list = response.data.data;

          this.chartData = {
            labels: [],
            datasets: [
              {
                label: "기사 수",
                backgroundColor: "#EE6C4D",
                barThickness: "15",
                data: [],
              },
            ],
          };

          this.data_list.forEach((item) => {
            this.chartData.labels.push(item.keyword);
            this.chartData.datasets[0].data.push(item.frequency);
          });
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
  components: { BarChartComp },
};
</script>

<style scoped>
.chart {
  width: 550px;
  height: 280px;
  margin: 10px auto;
}
.chart-container {
  padding: 6px;
  width: 600px;
}
</style>
