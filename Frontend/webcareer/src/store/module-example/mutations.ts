import { MutationTree } from 'vuex';
import { UserStateInterface } from './state';

const mutation: MutationTree<UserStateInterface> = {
  // someMutation (/* state: ExampleStateInterface */) {
  // your code

  SET_USER_ID(state, id: string) {
    state.id = id;
  },
  RESET_USER_ID(state) {
    state.id = null;
  }
};

export default mutation;
