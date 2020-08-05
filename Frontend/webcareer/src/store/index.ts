import { store } from 'quasar/wrappers';
import Vuex from 'vuex';

// import example from './module-example';
// import { ExampleStateInterface } from './module-example/state';

/*
 * If not building with SSR mode, you can
 * directly export the Store instantiation
 */

export interface UserInterface {
  // Define your own store structure, using submodules if needed
  // example: ExampleStateInterface;
  // Declared as unknown to avoid linting issue. Best to strongly type as per the line above.
  id: string | null;
}

export default store(function({ Vue }) {
  Vue.use(Vuex);

  const Store = new Vuex.Store({
    state: {
      id: ''
    } as UserInterface,
    // modules: {
    //   // example
    // },
    mutations: {
      SET_USER_ID(state, id: string) {
        state.id = id;
      },
      RESET_USER_ID(state) {
        state.id = '';
      }
    },
    actions: {
      setUserId(context, id) {
        // your code
        context.commit('SET_USER_ID', id);
      },
      resetUserId(context) {
        context.commit('RESET_USER_ID');
      }
    },
    getters: {
      getUserId: context => {
        return context.id;
      }
    },
    // enable strict mode (adds overhead!)
    // for dev mode only
    strict: !!process.env.DEV
  });

  return Store;
});
