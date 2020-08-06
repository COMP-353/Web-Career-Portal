import { Module } from 'vuex';
import { UserInterface } from '../index';
import state, { UserStateInterface } from './state';
import actions from './actions';
import getters from './getters';
import mutations from './mutations';

const exampleModule: Module<UserInterface, UserStateInterface> = {
  namespaced: true,
  actions,
  getters,
  mutations,
  state
};

export default exampleModule;
