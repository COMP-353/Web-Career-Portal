import { ActionTree } from 'vuex';
import { UserInterface } from '../index';
import { UserStateInterface } from './state';

const actions: ActionTree<UserStateInterface, UserInterface> = {
  setUserId(context, id) {
    // your code
    context.commit('SET_USER_ID', id);
  },
  resetUserId(context) {
    context.commit('RESET_USER_ID');
  }
};

export default actions;
