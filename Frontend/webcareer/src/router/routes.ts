import { RouteConfig } from 'vue-router';

const routes: RouteConfig[] = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: 'login', component: () => import('pages/Index.vue') },
      // { path: 'index', component: () => import('pages/Index.vue') },
      {
        path: 'sign-up-employer',
        component: () => import('pages/sign-up-employer.vue')
      },
      {
        path: 'sign-up-jobSeeker',
        component: () => import('pages/sign-up-jobSeeker.vue')
      },
      { path: 'employer', component: () => import('pages/employer.vue') },
      { path: 'job-seeker', component: () => import('pages/job-seeker.vue') },
      { path: 'addjob', component: () => import('pages/addjob.vue') }
      {
        path: 'forgotPassword',
        component: () => import('pages/forgotPassword.vue')
      }

    ]
  },

  // Always leave this as last one,
  // but you can also remove it
  {
    path: '*',
    component: () => import('pages/Error404.vue')
  }
];

export default routes;
