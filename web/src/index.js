// Add polyfill to support IE9,10,11
import "react-app-polyfill/ie9";

// Apply global styles
import "normalize.css";
import "bootstrap/dist/css/bootstrap.css";
import "react-toastify/dist/ReactToastify.css";

import React from "react";
import { render } from "react-dom";
import { ToastContainer } from "react-toastify";
import { BrowserRouter as Router } from "react-router-dom";

import App from "app";

// Initilize system-scoped service
import "services/app/system-service";
import "services/app/notification-service";

const rootElement = document.querySelector("#root");
if (rootElement) {
  const reactApp = (
    <Router>
      <div>
        <App />
        <ToastContainer
          position="bottom-right"
          autoClose={3000}
          hideProgressBar={false}
          newestOnTop
          closeOnClick
          rtl={false}
          pauseOnVisibilityChange
          draggable
          pauseOnHover
        />
      </div>
    </Router>
  );

  render(reactApp, document.getElementById("root"));
}
