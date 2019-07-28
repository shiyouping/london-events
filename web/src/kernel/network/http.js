import axios from "axios";

import Config from "common/config";
import RequestInterceptor from "kernel/network/request-interceptor";
import ResponseInterceptor from "kernel/network/response-interceptor";

const requestInterceptor = new RequestInterceptor();
const responseInterceptor = new ResponseInterceptor();

export default class Http {
  /**
   * @returns {object} An Axios instance with base url pointing to the configured API server
   */
  static getInstance(config) {
    let instance = axios.create({ ...config, baseURL: Config.API_SERVER_URL });

    instance.interceptors.request.use(
      requestInterceptor.interceptRequestConfig,
      requestInterceptor.interceptRequestError
    );

    instance.interceptors.response.use(
      responseInterceptor.intercept2xxResponse,
      responseInterceptor.interceptNon2xxResponse
    );

    return instance;
  }
}
