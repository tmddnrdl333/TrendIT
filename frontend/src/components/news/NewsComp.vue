<template>
  <q-card class="news-comp q-pa-lg">
    <q-card-section class="row justify-center">
      <!-- 기간 설정 -->
      <div>
        <q-field label="기간 설정" stack-label class="q-mx-md q-mb-md">
          <template v-slot:control>
            <div class="self-center full-width no-outline">
              {{ newsDate.from }} ~ {{ newsDate.to }}
            </div>
          </template>
          <template v-slot:append>
            <q-btn dense @click="newsDateDialog = true">
              <q-icon name="calendar_month" />
            </q-btn>
          </template>
        </q-field>

        <q-field label="언론사 설정" stack-label class="q-mx-md q-mb-md">
          <template v-slot:control>
            <div class="self-center full-width no-outline">
              {{ press(selection.length) }}
            </div>
          </template>
          <template v-slot:append>
            <q-btn dense @click="newsAgencyDialog = true">
              <q-icon name="settings" />
            </q-btn>
          </template>
        </q-field>

        <!-- 날짜 dialog -->
        <q-dialog v-model="newsDateDialog">
          <q-card>
            <q-card-section>
              검색 기간 설정
              <br />
              {{ newsDate.from }} ~ {{ newsDate.to }}
              <q-btn flat label="OK" color="primary" v-close-popup></q-btn>
            </q-card-section>
            <q-separator />
            <q-card-section>
              <div class="q-pa-md">
                <q-date v-model="newsDate" minimal range />
              </div>
            </q-card-section>
          </q-card>
        </q-dialog>

        <!-- 언론사 dialog -->
        <q-dialog v-model="newsAgencyDialog">
          <q-card style="width: 700px; max-width: 80vw">
            <div class="q-pa-md">
              <div class="q-gutter-xs flex content-start">
                <div
                  v-for="newsAgency in newsAgencyOptions"
                  :key="newsAgency.index"
                >
                  <q-chip
                    v-model:selected="newsAgencyOption[newsAgency]"
                    color="primary"
                    text-color="white"
                    :label="newsAgency"
                  >
                  </q-chip>
                </div>
              </div>
            </div>
          </q-card>
        </q-dialog>

        <!-- 검색 버튼 -->
        <q-btn label="검색" color="primary" @click="getNewsByOptions()"></q-btn>
      </div>

      <!-- First Card -->
      <q-card
        class="main-card q-ma-md row"
        @click="myFunction(newsFirst.newsLink)"
      >
        <q-img :src="newsFirst.imgLink" class="col-8" height="400px">
          <div class="absolute-bottom text-h6">
            <div class="first-card-headline">
              {{ newsFirst.headline }}
            </div>
            <br />
            <div class="first-card-agency">
              {{ newsFirst.newsAgency }} / {{ newsFirst.newsDate }}
            </div>
          </div>
        </q-img>
        <q-card-section class="col-4">
          {{ newsFirst.newsContent }}
        </q-card-section>
      </q-card>

      <!-- Card * 12 -->
      <div class="sub-news row justify-between">
        <div v-for="newsitem in newsList" :key="newsitem.newsId">
          <!-- <a v-bind:href="newsitem.newsLink"> -->
          <!-- localhost + link -->
          <!-- <div v-on:click="window.open(newsitem.newsLink, _blank, options)"> -->
          <div @click="myFunction(newsitem.newsLink)">
            <q-card class="sub-card q-mb-lg">
              <q-img
                class="sub-card-img"
                :src="newsitem.imgLink"
                height="200px"
              />
              <q-card-section>
                <div class="cards-headline">
                  {{ newsitem.headline }} / {{ newsitem.newsId }}
                </div>
                <div class="text-subtitle2 cards-content">
                  {{ newsitem.newsContent.substring(0, 15) }}
                </div>
              </q-card-section>
              <q-card-section class="q-pt-none cards-agency">
                {{ newsitem.newsAgency }} / {{ newsitem.newsDate }}
              </q-card-section>
            </q-card>
          </div>
          <!-- </div> -->
          <!-- </a> -->
        </div>
      </div>

      <!-- Pagination -->
      <div>
        <div class="q-pa-lg q-gutter-md flex flex-center">
          <q-pagination
            v-model="page"
            :min="1"
            :max="max"
            input
            direction-links
            unelevated
            color="blue"
            active-color="blue"
            active-text-color="white"
          />
        </div>
      </div>
    </q-card-section>
  </q-card>
</template>

<script>
import { ref } from "vue";
import { getNewsApi } from "boot/news.js";
import { reactive, computed } from "vue";
import { getNewsByOptionsApi } from "src/boot/news.js";

export default {
  data() {
    return {
      max: 1,
      newsAgency: "",
      newsAgencyOptions: [
        "언론사A",
        "언론사B",
        "경향신문",
        "국민일보",
        "내일신문",
        "동아일보",
        "문화일보",
        "서울신문",
        "세계일보",
        "중앙일보",
        "조선일보",
        "한겨레",
        "한국일보",
        "매일경제",
        "머니투데이",
        "서울경제",
        "아시아경제",
        "아주경제",
        "파이낸셜뉴스",
        "한국경제",
        "헤럴드경제",
        "KBS",
        "MBC",
        "OBS",
        "SBS",
        "YTN",
        "디지털타임스",
        "전자신문",
        "강원도민일보",
        "강원일보",
        "경기일보",
        "경남도민일보",
        "경남신문",
        "경상일보",
        "경인일보",
        "광주일보",
        "광주매일신문",
        "국제신문",
        "대구일보",
        "대전일보",
        "매일신문",
        "무등일보",
        "부산일보",
        "영남일보",
        "울산매일",
        "전남일보",
        "전북도민일보",
        "전북일보",
        "제민일보",
        "중도일보",
        "중부매일",
        "중부일보",
        "충북일보",
        "충청일보",
        "충청투데이",
        "한라일보",
      ],
    };
  },
  setup() {
    let date = new Date();

    const toDate =
      date.getFullYear() +
      "/" +
      (date.getMonth() + 1).toString().padStart(2, "0") +
      "/" +
      date.getDate().toString().padStart(2, "0");

    date.setFullYear(date.getFullYear() - 10);
    const fromDate =
      date.getFullYear() +
      "/" +
      (date.getMonth() + 1).toString().padStart(2, "0") +
      "/" +
      date.getDate().toString().padStart(2, "0");

    const newsFirst = ref({
      newsId: 1,
      headline: "",
      newsContent: "",
      selection: [],
      newsAgency: "",
      newsLink: "",
      imgLink: "",
    });
    const newsList = ref([]);
    const newsAgencyOption = reactive({
      언론사A: false,
      언론사B: false,
      경향신문: false,
      국민일보: false,
      내일신문: false,
      동아일보: false,
      문화일보: false,
      서울신문: false,
      세계일보: false,
      중앙일보: false,
      조선일보: false,
      한겨레: false,
      한국일보: false,
      매일경제: false,
      머니투데이: false,
      서울경제: false,
      아시아경제: false,
      아주경제: false,
      파이낸셜뉴스: false,
      한국경제: false,
      헤럴드경제: false,
      KBS: false,
      MBC: false,
      OBS: false,
      SBS: false,
      YTN: false,
      디지털타임스: false,
      전자신문: false,
      강원도민일보: false,
      강원일보: false,
      경기일보: false,
      경남도민일보: false,
      경남신문: false,
      경상일보: false,
      경인일보: false,
      광주일보: false,
      광주매일신문: false,
      국제신문: false,
      대구일보: false,
      대전일보: false,
      매일신문: false,
      무등일보: false,
      부산일보: false,
      영남일보: false,
      울산매일: false,
      전남일보: false,
      전북도민일보: false,
      전북일보: false,
      제민일보: false,
      중도일보: false,
      중부매일: false,
      중부일보: false,
      충북일보: false,
      충청일보: false,
      충청투데이: false,
      한라일보: false,
    });
    return {
      page: ref(1),
      size: ref(13),

      newsDate: ref({ from: fromDate, to: toDate }),
      newsFirst,
      newsList,
      newsDateDialog: ref(false),
      newsAgencyDialog: ref(false),
      newsAgencyOption,
      selection: computed(() => {
        const test = Object.keys(newsAgencyOption).filter(
          (type) => newsAgencyOption[type] === true
        );
        return test;
      }),
    };
  },
  beforeCreate() {
    getNewsApi(
      {
        newsDate:
          this.newsDate.from.replaceAll("/", "-") +
          "~" +
          this.newsDate.to.replaceAll("/", "-"),
        page: this.page,
        size: this.size,
      },
      (response) => {
        let res_data = response.data.data.content;
        res_data.forEach((item) => {
          if (item.imgLink.substring(0, 5) == "/asse") {
            item.imgLink = "src/assets/no-image.png";
          }
        });
        this.newsFirst = res_data[0];
        this.newsFirst.newsLink =
          this.newsFirst.newsLink === ""
            ? "http://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=" +
              this.newsFirst.newsAgency +
              " " +
              this.newsFirst.headline
            : response.data.data.content[0].newsLink;
        this.newsList = res_data.slice(1);
        this.max = response.data.data.totalPages;
      },
      () => console.warn("failed...")
    );
  },
  created() {},
  methods: {
    myFunction: function (newsLink) {
      // 주석지우지 말것
      window.open(newsLink); // -> 샘플데이터가 아니고 실제 크롤링 데이터로 할땐 이거 사용
      // window.open("http://" + newsLink);

      // window.open(newsLink); // localhost + 링크 // blank 가 default
      // window.open(newsLink.href);                // 빈창             // blank 가 default
      // window.open(newsLink, "_parent"); // localhost + 링크
      // window.open(newsLink.href, "_parent");      // 빈창
      // window.open(newsLink, "_self");            // localhost + 링크 + 기존탭
      // window.open(newsLink.href, "_self");       // 안됨
    },

    async getNewsByOptions() {
      await getNewsByOptionsApi(
        {
          newsDate:
            this.newsDate.from.replaceAll("/", "-") +
            "~" +
            this.newsDate.to.replaceAll("/", "-"),
          selection: this.selection
            .toString()
            .replaceAll("[", "")
            .replaceAll("]", "")
            .replaceAll('"', ""),
          page: this.page,
          size: this.size,
        },
        (response) => {
          let res_data = response.data.data.content;
          res_data.forEach((item) => {
            if (item.imgLink.substring(0, 5) == "/asse") {
              item.imgLink = "src/assets/no-image.png";
            }
          });
          this.newsFirst = res_data[0];
          this.newsList = res_data.slice(1);
          this.max = response.data.data.totalPages;
        },
        (error) => {
          console.error(error);
        }
      );
    },
  },
  computed: {
    press() {
      return function (length) {
        if (length == 0) return "전체";
        return length + "개 언론사";
      };
    },
  },
  watch: {
    page: function () {
      this.getNewsByOptions();
    },
    newsList: function () {
      for (let i = 0; i < this.newsList.length; i++) {
        this.newsList[i].newsLink =
          this.newsList[i].newsLink === ""
            ? "http://search.naver.com/search.naver?where=nexearch&sm=top_hty&fbm=1&ie=utf8&query=" +
              this.newsList[i].newsAgency +
              " " +
              this.newsList[i].headline
            : this.newsList[i].newsLink;
      }
    },
  },
};
</script>

<style scoped>
.news-comp {
  height: 100%;
  width: 1200px;
  margin-top: 10px;
  margin-bottom: 10px;
  /* text-align: center; */
}

/* 1 */
.main-card {
  height: 400px;
}

.sub-news {
  margin: 5px;
}

.sub-card {
  height: 340px;
  width: 260px;
}

.first-card-headline {
  font-family: "NanumBarunGothicBold";
  font-size: 28px;
}
.first-card-content {
  font-family: "NanumBarunGothic";
  font-size: 20px;
}
.first-card-agency {
  font-family: "NanumBarunGothic";
  font-size: 16px;
}

.cards-headline {
  font-family: "NanumBarunGothicBold";
  font-size: 16px;
}
.cards-content {
  font-family: "NanumBarunGothic";
  font-size: 14px;
}
.cards-agency {
  color: gray;
  font-family: "NanumBarunGothic";
  font-size: 12px;
}
</style>
