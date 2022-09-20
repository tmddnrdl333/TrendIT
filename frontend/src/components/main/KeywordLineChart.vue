<template>
  <q-card class="q-pa-md" style="width: 500px">
    <q-card-section> Keyword Line Chart... </q-card-section>
    <q-separator />
    <q-card-section>
      <!-- TYPE -->
      <div class="text-center">
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
      </div>
      <!-- CHART -->
      <canvas id="myChart" width="400" height="400"></canvas>
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
  async mounted() {
    this.drawChart();
  },
  setup() {
    const data_list = ref(null);
    const type = ref("day");

    return {
      data_list,
      type,
    };
  },
  created() {
    this.loadChartData();
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
      const ctx = document.getElementById("myChart");

      // this.data_list에 response를 채워줌
      await this.loadChartData();

      /*
        TODO: labels를 배열로 반환해주는 함수를 따로 만들어야 할듯
      */
      let labels = [];
      for (let i = 6; i >= 0; i--) {
        let date_val = new Date();
        date_val.setDate(date_val.getDate() - i);
        let date_label = date_val.getMonth() + 1 + "/" + date_val.getDate();
        labels.push(date_label);
      }
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
