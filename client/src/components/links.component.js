import React, { Component } from "react";
import { Switch, Route, Link } from "react-router-dom";
import CatererDataService from "../services/caterer.service";

import AddCaterer from "./add-caterer.component";
import Caterer from "./caterer.component";
import CaterersList from "./caterers-list.component";

export default class Links extends Component {
	constructor(props) {
		super(props);
    this.getLinks = this.getLinks.bind(this);
    this.state = {
      nameSearch: "",
      citySearch: "",
      saveCaterer: ""
    };
	}

  componentDidMount() {
    this.getLinks();
  }

  getLinks() {
    CatererDataService.getLinks()
      .then(response => {
        this.setState({
          nameSearch: response.data._links.nameSearch.href,
          citySearch: response.data._links.citySearch.href,
          saveCaterer: response.data._links.saveCaterer.href
        });        
      })
      .catch(e => {
        console.log(e);
      });
  }  

  render() {
    const nameSearch = this.state.nameSearch;
    const citySearch = this.state.citySearch;
    const saveCaterer = this.state.saveCaterer;
    return (
      <div>
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          
          <a className="navbar-brand">
            Home Page
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">

              <Link
                to={{
                  pathname: "/caterers",
                  state: {
                    nameSearch : nameSearch,
                    citySearch : citySearch
                  }
                }}
                className="nav-link"
              >
                Search
              </Link>
            </li>
            <li className="nav-item">
              <Link
                to={{
                  pathname: "/save",
                  state: {
                    saveCaterer : saveCaterer
                  },
                }}
                className="nav-link"
              >
                Add
              </Link>
            </li>
          </div>
        </nav>

        <div className="container mt-3">
          <Switch>

            <Route 
                exact path={["/caterers"]} component={CaterersList} />
            <Route exact path="/save" component={AddCaterer} />
            <Route path="/caterer/:id" component={Caterer} />
          </Switch>
        </div>
      </div>
    );
  }
}