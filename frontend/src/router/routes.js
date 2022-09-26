import MainLayout from "../layouts/MainLayout.vue";

import MainView from "../pages/MainView.vue";
import ErrorView from "../pages/ErrorView.vue";
import KeywordView from "../pages/KeywordView.vue";
import CompanyView from "../pages/CompanyView.vue";
import NewsView from "../pages/NewsView.vue";
import AboutView from "../pages/AboutView.vue";
import TestView from "../pages/TestView.vue";
import TestPageView from "../pages/TestPageView.vue";
import ChatTurnOnButtonVue from "src/components/chat/ChatTurnOnButton.vue";

const routes = [
  {
    path: "/",
    component: MainLayout,
    children: [
      {
        path: "",
        component: MainView,
      },
      {
        path: "keyword",
        component: KeywordView,
      },
      {
        path: "company",
        component: CompanyView,
      },
      {
        path: "news",
        component: NewsView,
      },
      {
        path: "about",
        component: AboutView,
      },
      {
        path: "test",
        component: TestView,
      },
      {
        path: "test2",
        component: TestPageView,
      },
      {
        path: "dev",
        component: ChatTurnOnButtonVue,
      },
    ],
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: "/:catchAll(.*)*",
    component: ErrorView,
  },
];

export default routes;
