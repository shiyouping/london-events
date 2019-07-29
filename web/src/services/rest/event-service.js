import Config from "common/config";
import Http from "kernel/network/http";

const basePath = Config.API_BASE_PATH + "/events";

export default class EventService {
  async getEvent(id) {
    return await Http.getInstance().get(`${basePath}/london/${id}`);
  }

  async getLondonEvents(category, page = 0, size = 9, sort = "date,desc") {
    if (category) {
      return await Http.getInstance().get(
        `${basePath}/london?category=${category}&page=${page}&size=${size}&sort=${sort}`
      );
    }

    return await Http.getInstance().get(
      `${basePath}/london?page=${page}&size=${size}&sort=${sort}`
    );
  }
}
