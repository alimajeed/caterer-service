import React, { Component } from "react";
import CatererDataService from "../services/caterer.service";

export default class AddCaterer extends Component {
  constructor(props) {
    super(props);
    this.onChangeTitle = this.onChangeTitle.bind(this);
    this.onChangeCity = this.onChangeCity.bind(this);
    this.onChangeStreet = this.onChangeStreet.bind(this);
    this.onChangePostalCode = this.onChangePostalCode.bind(this);
    this.onChangeMinGuestNo = this.onChangeMinGuestNo.bind(this);
    this.onChangeMaxGuestNo = this.onChangeMaxGuestNo.bind(this);
    this.onChangeEmail = this.onChangeEmail.bind(this);
    this.onChangeMobileNumber = this.onChangeMobileNumber.bind(this);
    this.onChangePhoneNumer = this.onChangePhoneNumer.bind(this);
    this.saveCaterer = this.saveCaterer.bind(this);
    this.newCaterer = this.newCaterer.bind(this);

    this.state = {
      id: null,
      name: "",
      address: {
        cityName : "",
        streetAddress : "",
        postalCode : ""
      },
      capacity : {
        minGuestNo:"",
        maxGuestNo:""
      },
      contact:{
        email: "",
        mobileNumber: "",
        phoneNumber: ""
      },
      submitted: false
    };
  }

  componentDidMount() {
      this.setState({
          saveLink: this.props.location.state.saveCaterer
      });
  }

  onChangeTitle(e) {
    var newValue = e.target.value;
    this.setState({
      name: newValue
    });
  }

  onChangeCity(e) {
    var data = {
      cityName: e.target.value,
      streetAddress: this.state.address.streetAddress,
      postalCode: this.state.address.postalCode
    };
    this.setState({
      address : data
    });
  }

  onChangeStreet(e) {
    var data = {
      cityName: this.state.address.cityName,
      streetAddress: e.target.value,
      postalCode: this.state.address.postalCode
    };
    this.setState({
      address : data
    });
  }

  onChangePostalCode(e) {
    var data = {
      cityName: this.state.address.cityName,
      streetAddress: this.state.address.streetAddress,
      postalCode: e.target.value
    };
    this.setState({
      address : data
    });
  }

  onChangeMinGuestNo(e) {
    var data = {
      minGuestNo: e.target.value,
      maxGuestNo: this.state.capacity.maxGuestNo
    };
    this.setState({
      capacity : data
    });
  }

  onChangeMaxGuestNo(e) {
    var data = {
      minGuestNo: this.state.capacity.minGuestNo,
      maxGuestNo: e.target.value
    };
    this.setState({
      capacity : data
    });
  }

  onChangeEmail(e) {
    var data = {
      email: e.target.value,
      mobileNumber: this.state.contact.mobileNumber,
      phoneNumber: this.state.contact.phoneNumber
    };
    this.setState({
      contact : data
    });
  }


  onChangeMobileNumber(e) {
    var data = {
      email: this.state.contact.email,
      mobileNumber: e.target.value,
      phoneNumber: this.state.contact.phoneNumber
    };
    this.setState({
      contact : data
    });
  }  

  onChangePhoneNumer(e) {
    var data = {
      email: this.state.contact.email,
      mobileNumber: this.state.contact.mobileNumber,
      phoneNumber: e.target.value
    };
    this.setState({
      contact : data
    });
  } 

  saveCaterer() {
    var data = {
      name: this.state.name,
      address: this.state.address,
      capacity: this.state.capacity,
      contact: this.state.contact
    };
    CatererDataService.create(this.state.saveLink, data)
      .then(response => {
        this.setState({
          id: response.data.id,
          name: response.data.name,
          address: response.data.address,

          submitted: true
        });
      })
      .catch(e => {
        console.log(e);
      });
  }

  newCaterer() {
    this.setState({
      id: null,
      title: "",
      address: {
        cityName : "",
        streetAddress : "",
        postalCode : null
      },
      capacity : {
        minGuestNo:"",
        maxGuestNo:""
      },
      contact:{
        email: "",
        mobileNumber: "",
        phoneNumber: null
      },
      submitted: false
    });
  }

  render() {
    return (
      <div className="submit-form">
      {this.state.submitted ? (
          <div>
            <h4>You submitted successfully!</h4>
            <button className="btn btn-success" onClick={this.newCaterer}>
              Add
            </button>
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="title">Name</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Name"
                id="title"
                required
                value={this.state.name}
                onChange={this.onChangeTitle}
                name="title"
              />
            </div>

            <div className="form-group">
              <label htmlFor="city">City</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter City"
                id="city"
                required
                value={this.state.address.cityName}
                onChange={this.onChangeCity}
                name="city"
              />
            </div>

            <div className="form-group">
              <label htmlFor="street">Street</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Street"
                id="street"
                required
                value={this.state.address.streetAddress}
                onChange={this.onChangeStreet}
                name="street"
              />
            </div>

            <div className="form-group">
              <label htmlFor="postal">Postal Code</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Postal Code"
                id="postal"
                value={this.state.address.postalCode}
                onChange={this.onChangePostalCode}
                name="postal"
              />
            </div>       

            <div className="form-group">
              <label htmlFor="minGuestNo">Min Guest</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Min No. of Guest"
                id="minGuestNo"
                value={this.state.capacity.minGuestNo}
                onChange={this.onChangeMinGuestNo}
                name="minGuestNo"
              />
            </div>                    
            <div className="form-group">
              <label htmlFor="maxGuestNo">Max Guest</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Max No. of Guest"
                id="maxGuestNo"
                value={this.state.capacity.maxGuestNo}
                onChange={this.onChangeMaxGuestNo}
                name="maxGuestNo"
              />
            </div>   
            <div className="form-group">
              <label htmlFor="email">Email</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Email"
                id="email"
                value={this.state.contact.email}
                onChange={this.onChangeEmail}
                name="email"
              />
            </div>   
            <div className="form-group">
              <label htmlFor="mobile">Mobile Number</label>
              <input
                type="text"
                className="form-control"
                placeholder="Enter Mobiel Number"
                id="mobile"
                value={this.state.address.mobileNumber}
                onChange={this.onChangeMobileNumber}
                name="mobile"
              />
            </div>               
            <button onClick={this.saveCaterer} className="btn btn-success">
              Submit
            </button>
          </div>
          )}
      </div>
    );
  }
}
