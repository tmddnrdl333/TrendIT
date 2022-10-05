<template>
  <q-card class="news-comp q-pa-lg">
    <q-card-section class="row justify-center">
      <!-- 기간 설정 -->
      <div class="q-gutter-lg">
        <q-btn
          square
          text-color="grey-8"
          @click="newsDateDialog = true"
          size="13px"
        >
          <q-icon size="xs" class="q-pr-sm" name="today" />
          {{ newsDate.from }} ~ {{ newsDate.to }}
        </q-btn>

        <q-btn square text-color="grey-8" @click="newsAgencyDialog = true">
          <q-icon size="xs" class="q-pr-sm" name="settings" />
          {{ press(selection.length) }}
        </q-btn>

        <!-- 날짜 dialog -->
        <q-dialog v-model="newsDateDialog">
          <q-card>
            <q-card-section class="q-py-sm">
              <div
                class="relative-position container flex flex-center"
                style="color: grey; font-size: 12px"
              >
                검색 기간 설정
              </div>
              <div
                class="relative-position container flex flex-center q-mt-sm"
                style=""
              >
                {{ newsDate.from }} ~ {{ newsDate.to }}
                <q-btn flat label="OK" color="primary" v-close-popup></q-btn>
              </div>
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
                    color="light-blue-7"
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
        <q-btn
          push
          label="검색"
          color="deep-orange-3"
          text-color="black"
          @click="getNewsByOptions()"
        />
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
          <div @click="myFunction(newsitem.newsLink)">
            <q-card class="sub-card q-mb-lg">
              <q-img
                class="sub-card-img"
                :src="newsitem.imgLink"
                height="200px"
              />
              <q-card-section>
                <div class="cards-headline">
                  {{ newsitem.headline }}
                </div>
                <div class="text-subtitle2 cards-content">
                  {{ newsitem.newsContent.substring(0, 20) + "..." }}
                </div>
              </q-card-section>
              <q-card-section class="q-pt-none cards-agency">
                {{ newsitem.newsAgency }} / {{ newsitem.newsDate }}
              </q-card-section>
            </q-card>
          </div>
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

    date.setDate(date.getDate() - 7);
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
          if (res_data.length == 0) {
            window.alert("해당 조건으로 뉴스를 검색할 수 없습니다.");
            this.$router.go(); // 새로고침인데... 만약 검색할 때마다 페이지를 리로드하게 고친다면 go(-1)로 수정할 것
            return;
          }
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
          window.alert("오류가 발생했습니다.");
        }
      );
    },
  },
  computed: {
    press() {
      return function (length) {
        if (length == 0) return "전체 언론사";
        return length + "개 언론사";
      };
    },
  },
  watch: {
    newsDate: function () {
      if (this.newsDate.from == undefined) {
        let target = this.newsDate;
        this.newsDate = { from: "", to: "" };
        this.newsDate.from = target;
        this.newsDate.to = target;
      }
    },

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
  font-family: "NanumBarunGothic";
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
  font-size: 20px;
}
.first-card-agency {
  font-size: 16px;
}

.cards-headline {
  font-family: "NanumBarunGothicBold";
  font-size: 16px;
}
.cards-content {
  font-size: 14px;
}
.cards-agency {
  color: gray;
  font-size: 12px;
}
</style>
