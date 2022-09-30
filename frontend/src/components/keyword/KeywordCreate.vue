<template>
  <div>
    <div style="min-height: 100vh">
      <div class="q-pa-md">
        <br />
        <br />
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
                    hint="한글, 영어 대소문자 최대 20자"
                    :rules="[(val) => !!val || '필수값입니다']"
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
                    v-model="taskToSubmit.companyName"
                    label="등록할 키워드"
                    placeholder="(ex. 삼성전자)"
                    hint="한글, 영어 대소문자 최대 20자"
                    :rules="[(val) => !!val || '필수값입니다']"
                    ref="companyName"
                  ></q-input>
                </div>

                <div class="q-mb-sm">
                  <q-input
                    outlined
                    v-model="taskToSubmit.companyCategory"
                    label="업종 분류"
                    placeholder="(ex. 통신 및 방송 장비 제조업)"
                    hint=" "
                    :rules="[
                      (val) =>
                        val.length <= 5 || 'Please use maximum 5 characters',
                    ]"
                    ref="companyCategory"
                  ></q-input>
                </div>

                <div class="q-mb-sm">
                  <q-input
                    outlined
                    v-model="taskToSubmit.companyRepresentative"
                    label="대표자"
                    placeholder="(ex. 한종희, 경계현)"
                    hint=" "
                    :rules="[
                      (val) =>
                        val.length <= 5 || 'Please use maximum 5 characters',
                    ]"
                    ref="companyRepresentative"
                  ></q-input>
                </div>

                <div class="q-mb-sm">
                  <q-input
                    outlined
                    v-model="taskToSubmit.companyLink"
                    label="회사 링크"
                    placeholder="(ex. https://www.samsung.com/sec/)"
                    hint=" "
                    :rules="[
                      (val) =>
                        val.length <= 5 || 'Please use maximum 5 characters',
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
    </div>
  </div>
</template>
<script>
import { ref, computed } from "vue";
import { createNormalKeywordApi } from "boot/keyword.js";
import { createCompanyKeywordApi } from "boot/keyword.js";
import { NormalKeywordReq } from "src/boot/request/KeywordReq";
import { CompanyKeywordReq } from "src/boot/request/KeywordReq";

export default {
  components: {},
  data() {
    return {
      sampleData: "",
      color: ref("normal-keyword"),
      keyword: "",
      taskToSubmit: {
        companyName: "",
        companyCategory: "",
        companyRepresentative: "",
        companyLink: "",
      },
      dense: ref(false),
    };
  },
  setup() {},
  created() {},
  mounted() {},
  unmounted() {},
  methods: {
    // 일반 키워드 일때 - 유효성검사
    submitFormNormalKeyword() {
      if (!this.keyword === "") {
        this.createNormalKeyword();
        console.log("노에러");
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
        console.log("노에러");
      } else alert("에러");
    },

    // 일반 키워드 일때 - API
    async createNormalKeyword() {
      await console.log(this.keyword);
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
          this.taskToSubmit.companyName,
          this.taskToSubmit.companyCategory,
          this.taskToSubmit.companyRepresentative,
          this.taskToSubmit.companyLink
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
  },
};
</script>
