<template>
  <Abc
    v-if="loaded"
    :chart-options="chartOptions"
    :chart-data="chartData"
    :chart-id="chartId"
    :dataset-id-key="datasetIdKey"
    :plugins="plugins"
    :css-classes="cssClasses"
    :styles="myStyles"
    :width="300"
    :height="height"
  />
</template>

<script>
import { Line as Abc } from "vue-chartjs/legacy";
import {
  Chart as ChartJS,
  Title,
  Tooltip,
  Legend,
  BarElement,
  LineElement,
  CategoryScale,
  LinearScale,
} from "chart.js";

ChartJS.register(
  Title,
  Tooltip,
  Legend,
  BarElement,
  LineElement,
  CategoryScale,
  LinearScale
);

const chartAPI = "https://m.myapp2go.de/services/getstatistik_tables.php";

export default {
  name: "LineChart",
  components: { Abc },

  async mounted() {
    this.loaded = false;

    try {
      const response = await fetch(chartAPI);
      const chartArray = await response.json();
      this.chartdata = chartArray.items;

      // Get all the numbers/labels from the response
      this.chartData.labels = this.chartdata.map((i) => i.table).slice(1);
      this.chartData.datasets[0].data = this.chartdata
        .map((i) => i.count)
        .slice(1);

      console.log("chart Data ", JSON.stringify(this.chartdata));
      console.log("chart Data numbers: ", this.newChartArray);

      this.loaded = true;
    } catch (e) {
      console.error(e);
    }
  },
  computed: {
    myStyles() {
      return {
        position: "relative",
      };
    },
  },
  props: {
    chartId: {
      type: String,
      default: "line-chart",
    },
    datasetIdKey: {
      type: String,
      default: "label",
    },
    width: {
      type: Number,
      default: 200,
    },
    height: {
      type: Number,
      default: 0,
    },
    cssClasses: {
      default: "",
      type: String,
    },
    styles: {
      type: Object,
      default: () => {},
    },
    plugins: {
      type: Array,
      default: () => [],
    },
  },
  data() {
    return {
      chartData: {
        labels: [
          "num",
          "call",
          "blog",
          "key",
          "mod,",
          "pic",
          "acc",
          "ifc",
          "req",
          "inf",
        ],
        datasets: [
          {
            label: "Database statistics",
            borderWidth: 1,

            data: [],
          },
        ],
      },
      chartOptions: {
        responsive: true,
        maintainAspectRatio: false,
      },
      newChartArray: [],
      loaded: false,
    };
  },
};
</script>
