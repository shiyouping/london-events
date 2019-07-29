import EventEmitter from "kernel/event-emitter";
import EventName from "constants/event-names";

export default class ResponseInterceptor {
  interceptNon2xxResponse(error) {
    // Notify UI layer to show a toast
    EventEmitter.emit(EventName.EVENT_ERROR_RESPONSE);
    return error.response;
  }

  intercept2xxResponse(response) {
    return response;
  }
}
