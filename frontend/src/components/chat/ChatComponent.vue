<template>
  <q-card>
    <q-card-section>
      <div>{{ board.userName }}</div>
      <div @click="seamless = true">수정 삭제</div>
      <div>{{ board.createdDate }}</div>
    </q-card-section>
    <q-card-section>{{ board.boardContent }}</q-card-section>
  </q-card>

  <q-dialog v-model="seamless" seamless>
    <q-card>
      <q-card-section
        ><q-btn flat round icon="close" v-close-popup
      /></q-card-section>
      <q-card-section>
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
          <q-tab-panel name="update">
            <q-input
              dense
              outlined
              type="password"
              v-model="updatePassword"
              label="비밀번호를 입력해주세요"
            />
            <q-input
              outlined
              autogrow
              v-model="updateContent"
              label="수정할 내용을 입력해주세요"
            />
            <q-btn color="primary" @click="updateBoard">수정</q-btn>
          </q-tab-panel>

          <q-tab-panel name="delete">
            <q-input
              dense
              outlined
              type="password"
              v-model="deletePassword"
              label="비밀번호를 입력해주세요"
            />
            <q-btn color="deep-orange" @click="deleteBoard">삭제</q-btn>
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
        },
        () => {
          console.warn();
        }
      );
    },
    deleteBoard() {
      deleteBoard(
        this.board.boardId,
        this.deletePassword,
        (response) => {
          console.log(response);
        },
        () => {
          console.warn();
        }
      );
      {
      }
      console.log(this.deletePassword);
    },
  },
};
</script>

<style></style>
>
