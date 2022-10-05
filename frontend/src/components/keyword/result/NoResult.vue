<template>
  <q-card>
    <q-card-section class="row">
      <div class="col-5 row justify-center q-pa-md">
        <div class="no-img row justify-center items-center">
          <q-img src="../../../assets/no-image.png" width="320px" />
        </div>
      </div>
      <div class="col-5 column justify-center q-pa-md q-gutter-md">
        <div class="title">키워드를 입력해 뉴스를 검색해보세요!</div>
        <div class="subtitle">
          키워드 분석 및 뉴스 검색 결과를 확인할 수 있습니다.
        </div>
        <q-separator></q-separator>
        <div class="row justify-center">추천 키워드로 쉽게 시작해보세요!</div>
        <div class="row justify-center q-gutter-x-md">
          <template v-for="item in recommendations" :key="item.id">
            <!-- router-link to 로 바꾸고 싶은데 item에 keyword_id가 없음. (wordcloudapi 수정하면 좋을 듯) -->
            <div>{{ item.keyword }}</div>
          </template>
        </div>
        <q-btn
          @click="keywordCreate = true"
          label="새로운 키워드 등록"
          color="primary"
        />
      </div>
    </q-card-section>
  </q-card>

  <!-- 키워드 추가 dialog -->

  <q-dialog v-model="keywordCreate">
    <div>
      <form @submit.prevent="submitForm">
        <q-card>
          <q-card-section class="row">
            <div class="text-h6">키워드 등록</div>
            <q-space />
            <q-btn v-close-popup flat round dense icon="close"> </q-btn>
          </q-card-section>

          <!-- Radio Button -->
          <q-list class="row">
            <q-item tag="label" v-ripple>
              <q-item-section avatar>
                <q-radio
                  v-model="color"
                  val="normal-keyword"
                  color="blue"
                ></q-radio>
              </q-item-section>
              <q-item-section>
                <q-item-label>일반 키워드 추가</q-item-label>
                <q-item-label caption>With description </q-item-label>
              </q-item-section>
            </q-item>

            <q-item tag="label" v-ripple>
              <q-item-section avatar top>
                <q-radio
                  v-model="color"
                  val="company-keyword"
                  color="blue"
                ></q-radio>
              </q-item-section>
              <q-item-section>
                <q-item-label>기업 키워드 추가</q-item-label>
                <q-item-label caption>With description </q-item-label>
              </q-item-section>
            </q-item>
          </q-list>

          <!-- 일반 키워드 일때-->
          <template v-if="color === 'normal-keyword'">
            <q-card-section>
              <div class="q-mb-sm">
                <q-input
                  outlined
                  v-model="keyword"
                  label="등록할 키워드"
                  placeholder="(ex. 블록체인)"
                  hint="한글, 영어 대소문자, 숫자 최대 20자"
                  :rules="[(val) => !!val || '필수값입니다.']"
                  ref="keyword"
                ></q-input>

                <q-card-section align="right">
                  <q-btn
                    label="등록"
                    color="primary"
                    @click="submitFormNormalKeyword()"
                  ></q-btn>
                </q-card-section>
              </div>
            </q-card-section>
          </template>

          <!-- 회사 키워드 일때 -->
          <template v-if="color === 'company-keyword'">
            <q-card-section>
              <div class="q-mb-sm">
                <q-input
                  outlined
                  v-model="companyName"
                  label="등록할 키워드"
                  placeholder="(ex. 삼성전자)"
                  hint="한글, 영어 대소문자, 숫자 최대 20자"
                  :rules="[(val) => !!val || '필수값입니다.']"
                  ref="companyName"
                ></q-input>
              </div>
              <!-- :rules="[(val) => denySpacialChar(val) || '특수문자가 허용되지 않는 필수값입니다']" -->

              <div class="q-mb-sm">
                <q-input
                  outlined
                  v-model="companyCategory"
                  label="업종 분류"
                  placeholder="(ex. 통신 및 방송 장비 제조업)"
                  hint=" "
                  :rules="[
                    (val) =>
                      val.length <= 50 || 'Please use maximum 50 characters',
                  ]"
                  ref="companyCategory"
                ></q-input>
              </div>

              <div class="q-mb-sm">
                <q-input
                  outlined
                  v-model="companyRepresentative"
                  label="대표자"
                  placeholder="(ex. 한종희, 경계현)"
                  hint=" "
                  :rules="[
                    (val) =>
                      val.length <= 50 || 'Please use maximum 50 characters',
                  ]"
                  ref="companyRepresentative"
                ></q-input>
              </div>

              <div class="q-mb-sm">
                <q-input
                  outlined
                  v-model="companyLink"
                  label="회사 링크"
                  placeholder="(ex. https://www.samsung.com/sec/)"
                  hint=" "
                  :rules="[
                    (val) =>
                      val.length <= 500 || 'Please use maximum 500 characters',
                  ]"
                  ref="companyLink"
                ></q-input>
              </div>
            </q-card-section>

            <q-card-section align="right">
              <q-btn label="등록" color="primary" type="submit" />
            </q-card-section>
          </template>
        </q-card>
      </form>
    </div>
  </q-dialog>
</template>

<script>
import { ref } from "vue";
import { getWordcloudApi } from "boot/stats.js";
import { createNormalKeywordApi } from "boot/keyword.js";
import { createCompanyKeywordApi } from "boot/keyword.js";
import { NormalKeywordReq } from "src/boot/request/KeywordReq";
import { CompanyKeywordReq } from "src/boot/request/KeywordReq";
export default {
  components: {},
  async created() {
    await getWordcloudApi(
      "day",
      (response) => {
        this.recommendations = response.data.data.slice(0, 5);
      },
      (e) => console.warn("failed to get today's recommendations")
    );
  },
  data() {
    return {
      sampleData: "",
      color: ref("normal-keyword"),
      keyword: "",
      companyName: "",
      companyCategory: "",
      companyRepresentative: "",
      companyLink: "",
      dense: ref(false),
    };
  },
  setup() {
    return {
      recommendations: ref([]),
      keywordCreate: ref(false),
    };
  },
  methods: {
    // 일반 키워드 일때 - 유효성검사
    submitFormNormalKeyword() {
      if (!this.keyword === "") {
        this.createNormalKeyword();
      } else alert("에러");
    },

    // 회사 키워드 일때 - 유효성검사
    submitForm() {
      // this.$refs.companyName.validate();
      // this.$refs.companyCategory.validate();
      // this.$refs.companyRepresentative.validate();
      // this.$refs.companyLink.validate();
      if (
        !this.$refs.companyName.hasError &
        !this.$refs.companyCategory.hasError &
        !this.$refs.companyRepresentative.hasError &
        !this.$refs.companyLink.hasError
      ) {
        this.createCompanyKeyword();
      } else alert("에러");
    },

    // 일반 키워드 일때 - API
    async createNormalKeyword() {
      await createNormalKeywordApi(
        new NormalKeywordReq(this.keyword),
        (response) => {
          console.log(response, this.keyword, "createNormalKeywordApi 성공");
        },
        (error) => {
          console.warn(error, this.keyword, "createNormalKeywordApi 실패");
        }
      );
    },

    // 회사 키워드 일때 - API
    async createCompanyKeyword() {
      await createCompanyKeywordApi(
        new CompanyKeywordReq(
          this.companyName,
          this.companyCategory,
          this.companyRepresentative,
          this.companyLink
        ),
        (response) => {
          console.log(
            response,
            CompanyKeywordReq,
            "createCompanyKeywordApi 성공"
          );
        },
        (error) => {
          console.warn(
            error,
            CompanyKeywordReq,
            "createCompanyKeywordApi 실패"
          );
        }
      );
    },
    // denySpacialChar(val) {
    //   const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
    //   if (regExp.exec(val) !== null || val === "") {
    //     this.keyword = this.keyword.slice(0, -1);
    //     return false;
    //   } else return true;
    // },
  },

  watch: {
    // 현재 대소문자 / 한글 / 숫자 / 띄어쓰기 가능
    keyword(val) {
      const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
      if (regExp.exec(val) !== null || val.length > 20) {
        return (this.keyword = this.keyword.slice(0, -1));
      }
    },

    companyName(val) {
      const regExp = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>@\#$%&\'\"\\\(\=]/gi;
      if (regExp.exec(val) !== null || val.length > 20) {
        return (this.companyName = this.companyName.slice(0, -1));
      }
    },
  },
};
</script>

<style scoped>
.title {
  font-size: 150%;
}
.subtitle {
  color: gray;
}

.no-img {
  background-color: rgb(30 53 93);
  border-radius: 10px;
  width: 350px;
  height: 350px;
}
</style>
