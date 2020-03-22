<template>
  <div id="map">
    <l-map
      :zoom="zoom"
      :center="center" 
    >
      <l-tile-layer :url="url" :attribution="attribution"/>
      <l-marker
        v-if="$store.getters.isUserLogged"
        name="InitialMarker"
        :draggable="true"
        :lat-lng="initialMarker.location"
        @update:lat-lng="updateMarkerPosition"
      >
        <!-- <l-icon
          :icon-size="[32, 37]"
          :icon-anchor="[16, 37]"
          icon-url="web-front\src\assets\new-marker.png"
        /> -->
        <l-popup>
          <slot>
            <form>
              <div class="modal-card" style="width: auto">
                <header class="modal-card-head">
                  <p class="modal-card-title">New report</p>
                </header>
                <section class="modal-card-body">
                  <div>
                    <b-field label="Title">
                        <b-input v-model="initialMarker.title"></b-input>
                    </b-field>
                    <b-field label="Description">
                        <b-input v-model="initialMarker.description"></b-input>
                    </b-field>
                    
                    <!-- <b-field label="Description">
                        <b-input
                          v-model="initialMarker.description"
                          type="textarea"
                          custom-class="textarea"
                          rows="3"
                          :maxlength="100"
                        />
                    </b-field> -->
                    <b-field label="Image">
                        <b-input v-model="initialMarker.imgUrl"></b-input>
                    </b-field>
                    <!-- <b-field>
                      <b-upload
                        v-model="initialMarker.imgUrl"
                        accept="image/*"
                        drag-drop
                      >
                        <section class="section">
                          <div class="content has-text-centered">
                            <p>
                              <b-icon
                                pack="fas"
                                icon="upload"
                                size="is-large"
                              />
                            </p>
                            <p>Drop your image here or click to upload</p>
                          </div>
                        </section>
                      </b-upload>
                    </b-field>

                    <div class="tags">
                      <span class="tag is-primary imageName" v-if="initialMarker.imgUrl">
                        {{ initialMarker.imgUrl.name.substring(0, 40) }}
                        <button
                          class="delete is-small"
                          type="button"
                          @click="deleteImage"
                        />
                      </span>
                    </div> -->
                    <b-input type="hidden" readonly v-model="initialMarker.user_id"/>
                      
                    <span class="subtitle">{{ $store.getters.user.username }}</span>
                  </div>
                </section>
                <footer class="modal-card-foot">

              <template v-if="isNew">
                <b-button
                  type="is-primary"
                  icon-pack="fas"
                  icon-right="plus"
                  expanded
                  @click="addNewMarker"
                >
                  Add new
                </b-button>
              </template>

              <template v-else>
                <b-button
                  type="is-info"
                  icon-pack="fas"
                  icon-right="info-circle"
                  expanded
                  @click="showDetails"
                >
                  View details
                </b-button>
              </template>
                </footer>
              </div>
            </form>
          </slot>
        </l-popup>
      </l-marker>
      
      <l-marker v-for="marker in markers"
        :key="marker.id"
        :lat-lng="marker.location"
      >
        <l-popup>
          <div>
            <div>
              <img
                class="image"
                :src="marker.imgUrl"
                alt="Report's image">
              <p><strong>Title:</strong> {{ marker.title }}</p>
              <p><strong>Description:</strong> {{ marker.description }}</p>
              <p><strong>Author:</strong> {{ (marker.user_id) ? 'A person' : 'Anonymous' }}</p>
              <!-- <a :href="marker.creator.profileUrl">{{ marker.creator.name }}</a> -->
            </div>
            <!-- <b-button
              type="is-info"
              icon-pack="fas"
              icon-right="info-circle"
              expanded
              @click="showDetails"
            >
              Ver detalles
            </b-button> -->
          </div>
        </l-popup>
      </l-marker>
    </l-map>
  </div>
</template>

<script>
import { LMap, LTileLayer, LMarker, LPopup, LIcon } from 'vue2-leaflet';

export default {
  name: 'MyMap',
  components: {
    LMap,
    LTileLayer,
    LMarker,
    LPopup,
    LIcon
  },
  data() {
    return {
      zoom: 14,
      center: {lat: 18.490, lng: -69.910},
      url: 'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
      attribution: '&copy; <a href="http://osm.org/copyright">OpenStreetMap</a> contributors',
      initialMarker: {
        id: 0,
        title: '',
        description: '',
        location: {lat: 18.472963803253037, lng: -69.91263628005983},
        imgUrl: null,
        user_id: this.$store.getters.user.id
      },
      markers: [
        // {
        //   id: 1,
        //   title: 'Asesinato',
        //   image: 'https://images.unsplash.com/photo-1558981396-5fcf84bdf14d?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80',
        //   description: 'Alguien ha asesinado',
        //   location: {lat: 18.490, lng: -69.913050},
        //   creator: 'Pepe Agallas'
        // },
        // {
        //   id: 2,
        //   title: 'Robo',
        //   image: 'https://images.unsplash.com/photo-1558981403-c5f9899a28bc?ixlib=rb-1.2.1&auto=format&fit=crop&w=1000&q=80',
        //   description: 'Alguien ha sido robado',
        //   location: {lat: 18.490, lng: -69.912030},
        //   creator: 'Pepe Agallas'
        // },
        // {
        //   id: 3,
        //   title: 'Violacion',
        //   image: 'https://images.unsplash.com/photo-1533709742020-cfb4bcbed4a5?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=1000&q=80',
        //   description: 'Alguien ha sido violado',
        //   location: {lat: 18.490, lng: -69.918020},
        //   creator: {
        //     profileUrl: `${this.$store.state.url}/users/`,
        //     name: 'Pepe Agallas'
        //   }
        // }
      ]
    }
  },
  mounted() {
    this.getMarkers();
  },
  methods: {
    // showDetails() {
    //   console.log('clicked');
    // },
    updateMarkerPosition(latLng) {
      this.initialMarker.location = latLng
    },
    getMarkers() {
      this.http({url: this.$store.state.urls.server + "/reports"})
        .then(response => {
          const data = [...response];
          response.forEach(_marker => {
            _marker.location = JSON.parse(_marker.latLng);
          });

          this.markers = data;
        })
        .catch(e => {
          console.error(e);
        });
    },
    addNewMarker() {
      const item = { ...this.initialMarker };
      this.markers.push(item);

      item.latLng = JSON.stringify({
        lat: this.initialMarker.location.lat,
        lng: this.initialMarker.location.lng
      });

      this.http({method: 'POST', url: `${this.$store.state.urls.server}/reports`,
        body: { ...item }
      })
      .then(response => {
        this.setNewMarker();
      })
      .catch(e => console.error(e));
    },
    setNewMarker() {
      this.initialMarker.id = 0;
      this.initialMarker.title = '';
      this.initialMarker.description = '';
      this.deleteImage();
      this.initialMarker.location = {
        lat: this.initialMarker.location.lat + 0.0050,
        lng: this.initialMarker.location.lng
      }
    },
    deleteImage() {
      this.initialMarker.imgUrl = null
    }
  },
  computed: {
    isNew() {
      if (this.initialMarker.id === 0) {
        return true;
      } else {
        return false; 
      }
    }
  }
}
</script>

<style>
#map {
  width: 100%;
  height: 85vh;
  margin: 0;
}

.imageName {
  max-width: 100%;
  word-wrap: break-word;
}

.textarea {
  resize: none;
}
</style>