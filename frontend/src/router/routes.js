import MainLayout from "../layouts/MainLayout.vue";

import MainView from "../pages/MainView.vue";
import ErrorView from "../pages/ErrorView.vue";

const routes = [
  {
    path: "/",
    component: MainLayout,
    children: [
      {
        path: "",
        component: MainView,
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
