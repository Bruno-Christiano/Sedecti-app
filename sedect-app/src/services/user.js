import { http } from './config'

export default {

    save: (user) => {
        return http.post('user', user);
    },

    update: (user) => {
        return http.put('user', user);
    },

    findAll: () => {
        return http.get('users')
    },

    delete: (user) => {
        return http.delete(`user/${user.id}`)
    }
}