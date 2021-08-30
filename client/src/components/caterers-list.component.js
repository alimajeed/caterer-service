import React, { Component } from "react";
import CatererDataService from "../services/caterer.service";
import { Link } from "react-router-dom";
import Pagination from "react-js-pagination";

export default class CaterersList extends Component {
  constructor(props) {
    super(props);
    this.onChangeSearchCity = this.onChangeSearchCity.bind(this);
    this.setActiveCaterer = this.setActiveCaterer.bind(this);
    this.searchCity = this.searchCity.bind(this);
    this.searchName = this.searchName.bind(this);
    this.handlePageChange = this.handlePageChange.bind(this);

    this.state = {
      caterer: [],
      currentCaterer: null,
      currentIndex: -1,
      searchCity: "",
      activePage:1,
      totalPages: null,
      itemsCountPerPage:null,
      totalItemsCount:null,
      nameSearch: ""
    };
  }

  componentDidMount() {
      this.setState({
          nameSearch: this.props.location.state.nameSearch,
          citySearch: this.props.location.state.citySearch
      });
  }

  onChangeSearchCity(e) {
    const searchCity = e.target.value;

    this.setState({
      searchCity: searchCity
    });
  }


  setActiveCaterer(caterer, index) {
    this.setState({
      currentCaterer: caterer,
      currentIndex : index
    });
  }

  handlePageChange(pageNumber) {
    this.setState({activePage: pageNumber})
    console.log(`active page is ${pageNumber}`);
    CatererDataService.findByCity(this.state.citySearch, this.state.searchCity, pageNumber)
      .then(response => {

        this.setState({
          caterer: response.data.content,
          totalPages: response.data.totalPages,
          totalItemsCount: response.data.totalElements,
          itemsCountPerPage: response.data.size
        });        
      })
      .catch(e => {
        console.log(e);
      });
    }

  searchCity() {
    this.setState({
      currentCaterer: null,
      currentIndex: -1
    });

    CatererDataService.findByCity(this.state.citySearch, this.state.searchCity, this.state.activePage)
      .then(response => {

        this.setState({
          caterer: response.data.content,
          totalPages: response.data.totalPages,
          totalItemsCount: response.data.totalElements,
          itemsCountPerPage: response.data.size
        });        
      })
      .catch(e => {
        console.log(e);
      });
  }

  searchName() {
    this.setState({
      currentCaterer: null,
      currentIndex: -1
    });
    CatererDataService.findByName(this.state.nameSearch, this.state.searchCity, this.state.activePage)
      .then(response => {

        this.setState({
          caterer: response.data.content,
          totalPages: response.data.totalPages,
          totalItemsCount: response.data.totalElements,
          itemsCountPerPage: response.data.size
        });        
      })
      .catch(e => {
        console.log(e);
      });
  }

  populateRowsWithData = () => {
    const caterersData = this.state.caterer.map(caterer => {
        return (<ul className="list-group">
          <li  
          className="list-group-item list-group-item-action" 
          onClick={() => this.setActiveCaterer(caterer)}>
          {caterer.name}
          </li>
        </ul>)
      });

      return caterersData
    }

  render() {
    const { searchCity, searchName, caterer, currentCaterer, currentIndex } = this.state;

    return (
      <div className="list row">
        <div className="col-md-8">
          <div className="input-group mb-3">
            <input
              type="text"
              className="form-control"
              placeholder="Search"
              value={searchCity}
              onChange={this.onChangeSearchCity}
            />
            <div className="input-group-append">
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.searchCity}
              >
                By City
              </button>
              <button
                className="btn btn-outline-secondary"
                type="button"
                onClick={this.searchName}
              >
                By Name
              </button>              
            </div>
          </div>
        </div>


        <div className="col-md-6">
          <h4>Caterers List</h4>


        {this.populateRowsWithData()}
        <div className="d-flex justify-content-center">
        <Pagination
         activePage={this.state.activePage}
         itemsCountPerPage={this.state.itemsCountPerPage}
         totalItemsCount={this.state.totalItemsCount}
         pageRangeDisplayed={3}
         itemClass='page-item'
         linkClass='btn btn-light'
         onChange={this.handlePageChange}
         />
       </div> 


        </div>
        <div className="col-md-6">
          {currentCaterer ? (
            <div>
              <h4>Caterer</h4>
              <div>
                <label>
                  <strong>Name:</strong>
                </label>{" "}
                {currentCaterer.name}
              </div>
              <div>
                <label>
                  <strong>City:</strong>
                </label>{" "}
                {currentCaterer.address.cityName}
              </div>

              <Link
                to={{
                  pathname: "/caterer/" + currentCaterer.id,
                  state: {
                    href : currentCaterer.links[0].href
                  },
                }}
                className="badge badge-warning"
              >
                View Details
              </Link>
            </div>
          ) : (
            <div>
              <br />
              <p>Please click on a Caterer...</p>
            </div>
          )}
        </div>
      </div>
    );
  }
}
