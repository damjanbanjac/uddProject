import AuthService from './modules/AuthService';
import UploadService from './modules/UploadService';

const authService = new AuthService();
const uploadService = new UploadService();
const services = {
    auth: authService,
    upload: uploadService
};

export const ServiceFactory = {
    get: name => services[name]
};