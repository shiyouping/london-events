const API_BASE_PATH = "/api/v1";
const API_SERVER_URL = process.env.REACT_APP_API_SERVER_URL;
const APP_VERSION = process.env.REACT_APP_VERSION;

export default class Config {
  static get APP_VERSION() {
    return APP_VERSION;
  }

  static get API_BASE_PATH() {
    return API_BASE_PATH;
  }

  static get API_SERVER_URL() {
    return API_SERVER_URL;
  }

  static get isProduction() {
    return process.env.NODE_ENV === "production";
  }
}
