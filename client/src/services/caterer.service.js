import http from "../http-common";

class CatererDataService {

  getLinks(){
    return http.get();
  }

  findByCity(link, city, page) {
    return http.get(`${link}/${city}?page=${page}`);
  }

  findByName(link, name, page) {
    return http.get(`${link}/${name}?page=${page}`);
  }

  create(link, data) {
    return http.post("/api/v1/caterers/save", data);
  }  
}

export default new CatererDataService();