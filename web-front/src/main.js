import Vue from 'vue';
import App from './App.vue';
import router from './router';
import store from './store';
import Buefy from 'buefy';
import 'buefy/dist/buefy.css';

import 'leaflet/dist/leaflet.css';
import { Icon } from 'leaflet';

delete Icon.Default.prototype._getIconUrl;

Icon.Default.mergeOptions({
  iconRetinaUrl: require('leaflet/dist/images/marker-icon-2x.png'),
  iconUrl: require('leaflet/dist/images/marker-icon.png'),
  shadowUrl: require('leaflet/dist/images/marker-shadow.png'),
});

// USES
Vue.use(Buefy);

Vue.mixin({
  methods: {
    /**
     * 
     * @param {string} url
     * @param {string} [method="GET"]
     * @param {object} [getParams="{}"]
     * @param {object} [data="{}"]
     */
    async http({
      url,
      method = 'GET',
      getParams = {},
      body = {}
    }) {
      try {
        // Creating a url with parameters
        const parsedUrl = new URL(url);
        Object.keys(getParams).forEach(_key => parsedUrl.searchParams.append(_key, getParams[_key]));

        let response;

        switch (method) {
          case 'GET': {
            response = await fetch(parsedUrl, {
              method: 'GET',
              headers: {
                'Content-Type': 'application/json'
              }
            });
            return completeRequest(response);
          }

          case 'POST': {
            response = await fetch(parsedUrl, {
              method: 'POST',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify(body)
            });
            return completeRequest(response);
          }

          case 'PUT': {
            response = await fetch(parsedUrl, {
              method: 'PUT',
              headers: {
                'Content-Type': 'application/json'
              },
              body: JSON.stringify(body)
            });
            return completeRequest(response);
          }

          case 'DELETE': {
            response = await fetch(parsedUrl, {
              method: 'DELETE',
              headers: {
                'Content-Type': 'application/json'
              }
            });
            return completeRequest(response);
          }
        }
      } catch (e) {
        throw {
          status: e.status,
          statusText: 'Error proccessing the request',
          error: e
        }
      }
    }
  }
});


function completeRequest(response) {
  if (response.ok === false) {
    throw response;
  }
  return response.json();
}


Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
