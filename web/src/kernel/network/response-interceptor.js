export default class ResponseInterceptor {
  interceptNon2xxResponse(error) {
    return error.response;
  }

  intercept2xxResponse(response) {
    return response;
  }
}
