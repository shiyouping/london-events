import Config from "common/config";
import { unregister } from "kernel/service-worker";

const configureServiceWorker = () => {
  unregister();
};

const configureConsole = () => {
  if (Config.isProduction) {
    const noop = function() {};
    const methods = [
      "assert",
      "clear",
      "count",
      "debug",
      "dir",
      "dirxml",
      "error",
      "exception",
      "group",
      "groupCollapsed",
      "groupEnd",
      "info",
      "log",
      "markTimeline",
      "profile",
      "profileEnd",
      "table",
      "time",
      "timeEnd",
      "timeStamp",
      "trace",
      "warn"
    ];

    if (window.console) {
      let length = methods.length;

      while (length--) {
        let method = methods[length];
        if (console[method]) {
          console[method] = noop;
        }
      }
    }
  }
};

/**
 * The primary service to perform system level initialization work after the application starts up
 */
configureServiceWorker();
configureConsole();
