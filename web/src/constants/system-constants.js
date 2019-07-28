const HTTP_HEADER_X_REQUESTED_WITH = "X-Requested-With";
const HTTP_HEADER_VALUE_X_REQUESTED_WITH = "XMLHttpRequest";

export default class SystemConstants {
  static get HTTP_HEADER_X_REQUESTED_WITH() {
    return HTTP_HEADER_X_REQUESTED_WITH;
  }

  static get HTTP_HEADER_VALUE_X_REQUESTED_WITH() {
    return HTTP_HEADER_VALUE_X_REQUESTED_WITH;
  }
}
