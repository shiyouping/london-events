import Toast from "common/toast";
import EventName from "constants/event-names";
import EventEmitter from "kernel/event-emitter";

let lastTimestamp = new Date().getTime();
const handleErrorResponseEvent = (eventName, message) => {
  let nowTimestamp = new Date().getTime();
  if (nowTimestamp - lastTimestamp > 200) {
    Toast.error(message || "Invalid Server Response");
    lastTimestamp = nowTimestamp;
  }
};

EventEmitter.addListener(
  EventName.EVENT_ERROR_RESPONSE,
  handleErrorResponseEvent
);
