import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import '../css/login.css'
import React, {Component} from 'react';
import httpInstance from "../services/httpService";
import Calendar from "react-calendar";
import CalendarSelect from "./CalendarSelect";

class AddItem extends Component {
    constructor(props){
        super(props);
        this.state={
            name:'',
            quantity:0,
            calories:0,
            purchaseDate: new Date(),
            consumptionDate: new Date(),
            expirationDate: new Date(),
            seePurchase: false,
            seeConsumption: false,
            seeExpiration: false

        }
    }


    changeQuantity(newQuantity){
        console.log('trying to change quantity', newQuantity)
        let newQ = parseInt(newQuantity);
        if (newQuantity === "") {
            newQ = 0;
        }
        if(isNaN(newQ) || newQ < 0){
            console.log('nok')
            alert("Invalid quantity. Please enter a positive integer.")
            return;
        }
        this.setState({
            quantity: newQ
        })
    }
    changeCalories(newCalories){
        let newQ = parseInt(newCalories);
        if (newCalories === "")
            newQ = 0;
        if(isNaN(newQ) || newQ < 0){
            alert("Invalid calories. Please enter a positive integer.")
            return;
        }
        this.setState({
            calories: newQ
        })
    }


    changePurchase(newDate){
        console.log('New purchase date ', newDate)
       this.setState({
           purchaseDate: newDate
       })
    }
    showPurchaseDialog(){
        this.setState({
            seePurchase: !this.state.seePurchase
        })
    }

    changeConsumption(newDate){
        console.log('New consumption date ', newDate)

        this.setState({
            consumptionDate: newDate
        })
    }
    showConsumptionDialog(){
        this.setState({
            seeConsumption: !this.state.seeConsumption
        })
    }

    changeExpiration(newDate){
        console.log('New expiration date ', newDate)

        this.setState({
            expiration: newDate
        })

    }

    showExpirationDialog(){
        this.setState({
            seeExpiration: !this.state.seeExpiration
        })
    }



    render() {
        return (
            <div >
                <MuiThemeProvider>
                    <div>
                        <h3>Add</h3>
                        <TextField
                            hintText="Enter gorcery name"
                            floatingLabelText="Name"
                            onChange = {(event,newValue) => this.setState({name:newValue})}
                            on
                        />
                        <br/>
                        <TextField
                            hintText="Enter grocery quantity"
                            floatingLabelText="Quantity"
                            onChange = {(event,newValue) => this.changeQuantity(newValue)}
                        />
                        <br/>
                        <TextField
                            hintText="Enter grocery calories"
                            floatingLabelText="Calories"
                            onChange = {(event,newValue) => this.changeCalories(newValue)}
                        />
                        <br/>
                        <RaisedButton label="Select purchase date" primary={true} style={style} onClick={(event) => this.showPurchaseDialog()}/>
                        <br/>
                        <RaisedButton label="Select consumption date" primary={true} style={style} onClick={(event) => this.showConsumptionDialog()}/>
                        <br/>
                        <RaisedButton label="Select expiration date" primary={true} style={style} onClick={(event) => this.showExpirationDialog()}/>
                        <br/>
                        <RaisedButton label="Add" primary={true} style={style} onClick={(event) => this.props.add(this.state)}/>

                        <CalendarSelect isOpened={this.state.seePurchase} onClose={this.showPurchaseDialog.bind(this)} onChange={this.changePurchase.bind(this)}
                                        selectedDate={this.state.purchaseDate} />
                        <CalendarSelect isOpened={this.state.seeConsumption} onClose={this.showConsumptionDialog.bind(this)} onChange={this.changeConsumption.bind(this)}
                                        selectedDate={this.state.consumptionDate} />
                        <CalendarSelect isOpened={this.state.seeExpiration} onClose={this.showExpirationDialog.bind(this)} onChange={this.changeExpiration.bind(this)}
                                        selectedDate={this.state.expirationDate} />

                    </div>
                </MuiThemeProvider>
            </div>
        );
    }
}
const style = {
    margin: 15,
};
export default AddItem;