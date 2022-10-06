<template>
  <q-card flat bordered class="font bg-grey-1 q-my-sm ">
    <q-card-section 
      class="q-pa-sm"
    >
      <div style="display: flex; flex-direction: row; text-align: center">
        <div style="width: 65%; color: darkgreen">{{ board.userName }}</div>
        <div style="display: flex; flex-direction: column">
          <div style="font-size: 10px; color: rgb(12, 12, 120)">
            {{ board.createdDate.substr(0, 16) }}
          </div>
        </div>
      </div>
      <div class="row" >
        <div class="col-7" style="color: #3D5A80; font-size:12px;">
          <strong>{{ board.userName }}</strong>
        </div>
        <div class="column justify-evenly">
          <div class="col-4 text-grey-6" style="font-size: 9px; text-align:right;" >
            {{ board.createdDate.substr(0, 16) }}
          </div>
          <div style="display: flex; flex-direction: row">
            <div
              style="
                font-size: 10px;
                cursor: pointer;
                color: gray;
                text-align: right;
              "
              @click="
                seamless = true;
                tab = 'update';
              "
            >
              수정
            </div>
            <div
              style="
                font-size: 10px;
                cursor: pointer;
                color: gray;
                text-align: right;
              "
              @click="
                seamless = true;
                tab = 'delete';
              "
            >
              / 삭제
            </div>
          </div>
        </div>
      </div>
      <div>
        {{ board.boardContent }}
      </div>
    </q-card-section>
    <!-- <q-card-section
      style="
        height: 120px;
        background-color: antiquewhite;
        border: 2px solid antiquewhite;
        border-radius: 3px;
        text-align: center;
      "
      >{{ board.boardContent }}</q-card-section
    > -->
  </q-card>

  <q-dialog v-model="seamless" seamless>
    <q-card style="height: 300px; width: 450px;">
      <q-card-section class="q-pa-xs">
        <q-btn flat icon="close" v-close-popup/>
      </q-card-section>
      <q-card-section class="q-pa-xs">
        <q-tabs
          v-model="tab"
          dense
          class="text-grey"
          active-color="primary"
          indicator-color="primary"
          align="justify"
          narrow-indicator
        >
          <q-tab name="update" label="수정" />
          <q-tab name="delete" label="삭제" />
        </q-tabs>

        <q-separator />

        <q-tab-panels v-model="tab" animated>
          <q-tab-panel name="update" class="text-center">
            <q-input
            class="q-py-sm"
            dense
            filled
            outlined
            type="password"
            v-model="updatePassword"
            label="비밀번호를 입력해주세요"
            stack-label
          />
          <q-input
            class="q-py-sm"
              filled autogrow
            v-model="updateContent"
            label="수정할 내용을 입력해주세요"
            stack-label
          />
            
            <q-btn class="" color="blue-7" @click="updateBoard">수정</q-btn>
            
          </q-tab-panel>

          <q-tab-panel name="delete" class="text-center" >
            <q-input
              class="q-py-sm"
              dense
              filled
              outlined
              type="password"
              v-model="deletePassword"
              label="비밀번호를 입력해주세요"
              stack-label
            />
            <q-btn color="red-5" @click="deleteBoard">삭제</q-btn>
          </q-tab-panel>
        </q-tab-panels>
      </q-card-section>
    </q-card>
  </q-dialog>
</template>

<script>
import { ref } from "vue";
import { deleteBoard, updateBoard } from "src/boot/board.js";
import { BoardUpdateReq } from "src/boot/request/BoardReq";

export default {
  props: {
    board: Object,
    idx: Number,
  },
  setup() {
    return {
      seamless: ref(false),
      tab: ref("update"),
      updatePassword: ref(""),
      deletePassword: ref(""),
      updateContent: ref(""),
    };
  },
  methods: {
    updateBoard() {
      updateBoard(
        new BoardUpdateReq(
          this.board.boardId,
          this.updatePassword,
          this.updateContent
        ),
        (response) => {
          console.log(response);

          if (response.status === 200) {
            // 성공 시
            this.$emit("updateBoard", this.idx, this.updateContent);
          }
        },
        () => {
          alert("입력정보를 확인해주세요");
        }
      );
    },
    deleteBoard() {
      deleteBoard(
        this.board.boardId,
        this.deletePassword,
        (response) => {
          console.log(response);
          if (response.status === 200) {
            // 성공시
            this.$emit("deleteBoard", this.idx);
          }
        },
        () => {
          alert("입력정보를 확인해주세요");
        }
      );
      {
      }
      console.log(this.deletePassword);
    },
  },
};
</script>

<style>
.font {
  font-family: "NanumBarunGothic";
}
</style>
