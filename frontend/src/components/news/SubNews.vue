<template>
  <div class="sub-news q-pa-lg">
    <div>서브뉴스</div>
    <div>{{ newsFirst }}</div>
    <!-- <div v-for="newsitem in newsList" :key="newsitem.newsId"> -->
    <!-- {{ newsFirst.headline }}
    {{ newsFirst.imgLink }} -->
    <!-- </div> -->

    <q-card class="my-card">
      <q-card-section horizontal>
        <q-img
          class="rounded-borders"
          :src="newsFirst.imgLink"
          width="843px"
          height="400px"
        >
          <div class="absolute-bottom text-h6">
            <div class="text-h4">
              {{ newsFirst.headline }}
            </div>
            <br />
            {{ newsFirst.newsAgency }} / {{ newsFirst.newsDate }}
          </div>
        </q-img>
        <q-card-section>
          {{ newsFirst.newsContent }}
        </q-card-section>
      </q-card-section>
    </q-card>

    <div id="q-app" style="min-height: 100vh">
      <div class="q-pa-md row items-start q-gutter-md">
        <div v-for="newsitem in newsList" :key="newsitem.newsId">
          <!-- <a v-bind:href="newsitem.newsLink"> -->
          <!-- localhost + link -->
          <!-- <div v-on:click="window.open(newsitem.newsLink, _blank, options)"> -->
          <div v-on:click="myFunction(newsitem.newsLink)">
            <q-card class="my-card">
              <q-img :src="newsitem.imgLink" />
              <q-card-section>
                <div class="text-h6">{{ newsitem.headline }}</div>
                <div class="text-subtitle2">
                  {{ newsitem.newsContent.substring(0, 15) }}
                </div>
              </q-card-section>
              <q-card-section class="q-pt-none">
                {{ newsitem.newsAgency }} / {{ newsitem.newsDate }}
              </q-card-section>
            </q-card>
          </div>
          <!-- </div> -->
          <!-- </a> -->
        </div>
      </div>
    </div>
    <!-- :to-fn="
            (page) => {
              goPage: {
                page;
              }
            }
          " -->
    <div>
      <div class="q-pa-lg q-gutter-md flex flex-center">
        <q-pagination
          v-model="current"
          :min="1"
          :max="15"
          input
          direction-links
          unelevated
          color="blue"
          active-color="blue"
          active-text-color="white"
        ></q-pagination>
      </div>
    </div>
  </div>
</template>

<script>
// import { defineComponent } from "vue";
import { ref } from "vue";
import { getNewsApi } from "boot/getnews.js";

export default {
  data() {
    return {
      sampleData: [],
      current: 1,
      currentPage: 1,
    };
  },
  setup() {
    const newsFirst = ref({
      newsId: 1,
      headline: "",
      newsContent: "",
      newsDate: "",
      newsAgency: "",
      newsLink: "",
      imgLink: "",
    });
    const newsList = ref([]);
    // const current = ref(1);
    return {
      newsFirst,
      newsList,
      // current,
    };
  },
  beforeCreate() {
    getNewsApi(
      (response) => {
        this.newsFirst = response.data.data[0];
        this.newsList = response.data.data.slice(1);
        console.log("getNewsApi 성공!!");
      },
      (response) => console.warn(response, "failed . . . .. . ")
    );
  },
  beforeMount() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    myFunction: function (newsLink) {
      // 주석지우지 말것
      window.open("http://" + newsLink); // localhost + 링크 // blank 가 default
      // window.open(newsLink); // localhost + 링크 // blank 가 default
      // window.open(newsLink.href);                // 빈창             // blank 가 default
      // window.open(newsLink, "_parent"); // localhost + 링크
      // window.open(newsLink.href, "_parent");      // 빈창
      // window.open(newsLink, "_self");            // localhost + 링크 + 기존탭
      // window.open(newsLink.href, "_self");       // 안됨
    },
    goPage: function (page) {
      console.log(page, " 페이지 누름");
    },
    // https://codepen.io/Pratik__007/pen/PowpOeL
    // getData() {
    //   return this.posts.slice(
    //     (this.page - 1) * this.totalPages,
    //     (this.page - 1) * this.totalPages + this.totalPages
    //   );
    // },
  },
};
</script>

<style scoped>
.sub-news {
  height: 1200px;
  width: 1200px;
  background-color: #ffffff;
  margin-top: 10px;
  margin-bottom: 10px;
}
</style>
