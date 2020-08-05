import { GetterTree } from 'vuex';
import { UserInterface } from '../index';
import { UserStateInterface } from './state';

const getters: GetterTree<UserStateInterface, UserInterface> = {
  getUserId(context) {
    return context.id;
  }
};

export default getters;
