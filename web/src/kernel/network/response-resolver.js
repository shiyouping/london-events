export default class ResponseResolver {
  static isPositive(response) {
    if (response && response.status && response.status < 300) {
      return true;
    }

    return false;
  }
}
