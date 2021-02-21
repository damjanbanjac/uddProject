
import Service from '../Service';

const resource = '/todos';

class AuthService extends Service{
    test() {
        return this.getApiClient().get(`${resource}`);
    }

    getRegistrationFormReader() {
        return this.getApiClient().get();
    }
}


export default AuthService;