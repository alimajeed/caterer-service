import React, { Component } from "react";
import axios from "axios";

export default class Caterer extends Component {
  constructor(props) {
    super(props);
    this.getCaterer = this.getCaterer.bind(this);

    this.state = {
      currentCaterer: {
        id: null,
        name: "",
        address: {
          cityName : "",
          streetAddress : "",
          postalCode : "N/A"
        },
        capacity: {
          minGuestNo : 1,
          maxGuestNo : 10
        },
        contact: {
          email : "",
          mobileNumber : "",
          phoneNumber : "N/A"
        }
      }
    };
  }

  componentDidMount() {
    this.getCaterer(this.props.location.state.href);
  }

  getCaterer(href) {
    axios.get(href)
      .then(response => {
        this.setState({
          currentCaterer: response.data
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  render() {
    const { currentCaterer } = this.state;

    return (
      <div>
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
              <div>
                <label>
                  <strong>Street Address:</strong>
                </label>{" "}
                {currentCaterer.address.streetAddress}
              </div>
              <div>
                <label>
                  <strong>Postal Code :</strong>
                </label>{" "}
                {currentCaterer.address.postalCode}
              </div>
              <div>
                <label>
                  <strong>Min Guest:</strong>
                </label>{" "}
                {currentCaterer.capacity.minGuestNo}
              </div>
              <div>
                <label>
                  <strong>Max Guest:</strong>
                </label>{" "}
                {currentCaterer.capacity.maxGuestNo}
              </div>
              <div>
                <label>
                  <strong>Email:</strong>
                </label>{" "}
                {currentCaterer.contact.email}
              </div>
              <div>
                <label>
                  <strong>Mobile Number:</strong>
                </label>{" "}
                {currentCaterer.contact.mobileNumber}
              </div>
              <div>
                <label>
                  <strong>Phone Number:</strong>
                </label>{" "}
                {currentCaterer.contact.phoneNumber}
              </div>              
            </div>
        ) : (
          <div>
            <br />
            <p>Please click on a Caterer...</p>
          </div>
        )}
      </div>
    );
  }
}
