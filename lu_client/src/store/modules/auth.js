import { ServiceFactory } from '../../services/ServiceFactory';
const authService = ServiceFactory.get('auth');


const state = {
    todos: [],
    user: []
};

const getters = {
    todos: state => state.todos,
    user: state => state.user
};

const actions = {
    async getTodos({ commit }) {
        let response;
        try {
            response = await authService.test();
            await commit('setTodos', response.data);
        } catch (e) { }
    },
    async getRegistrationFormReader() {
        try {
            await authService.getRegistrationFormReader();
        } catch (e) {

        }
    }
};

const mutations = {
    setTodos: (state, data) => {
        state.todos = data;
    },
};

export default {
    state,
    getters,
    actions,
    mutations
}