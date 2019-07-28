import SystemConstants from "constants/system-constants";

export default class RequestInterceptor {
  interceptRequestConfig(config) {
    config.headers.common[SystemConstants.HTTP_HEADER_X_REQUESTED_WITH] =
      SystemConstants.HTTP_HEADER_VALUE_X_REQUESTED_WITH;

    return config;
  }

  interceptRequestError(error) {
    Promise.reject(error);
  }
}
