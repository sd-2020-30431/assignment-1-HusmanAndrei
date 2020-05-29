import React from 'react';
import httpInstance from "../services/httpService";
import GroceryList from "../components/GroceryList";
import {MuiThemeProvider} from 'material-ui'
import RaisedButton from "material-ui/RaisedButton";
import AddItem from "../components/AddItem";
import CalendarSelect from "../components/CalendarSelect";
import GroceryCard from "../components/GroceryCard";
import Report from "../components/Report";


export default class HomePage extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            groceryList: [],
            selectedItem: null,
            report: {
                weekCalories: -1,
                monthCalories: -1,
                weekQuantity: -1,
                monthQuantity: -1
            }
        }

    }

    async componentDidMount() {
        const id = localStorage.getItem('token');
        const groceries = await httpInstance.getGroceries(id);
        this.setState({
            groceryList: groceries
        });
        await this.fetchReports(id)
    }

    async fetchReports(id){
        const reports = await httpInstance.getReports(id);
        this.setState({
            report: reports
        });
    }

    validateDates(purchase, consumption, expiration) {
        return purchase <= consumption <= expiration;
    }

    async add(groceryItem) {
        const id = localStorage.getItem('token');
        if (!this.validateDates(groceryItem.purchaseDate, groceryItem.consumptionDate, groceryItem.expirationDate)) {
            alert('Cannot add item. The dates should be in ascending order.')
            return;
        }
        const returnedItem = await httpInstance.addGrocery(id, groceryItem);
        if (returnedItem.id === null) {
            alert('Cannot add item.');
        } else {
            const newItems = JSON.parse(JSON.stringify(this.state.groceryList));
            newItems.push(returnedItem);
            this.setState({
                groceryList: newItems
            })
        }
    }

    selectItem(item){
        this.setState({
            selectedItem: item
        })
    }

    async donateItem(itemId){
        const id = localStorage.getItem('token');
        const response = await httpInstance.donateItem(id, itemId);
        if(this.state.selectedItem.id === itemId){
            this.setState({
                selectedItem : null
            })
        }
        const newItems = this.state.groceryList.filter(item => item.id !== itemId);
        this.setState({
            groceryList: newItems
        });
    }


    render() {
        return (
            <MuiThemeProvider>
                <div style={{display: 'flex', flexDirection: 'column', paddingBottom: 40}}>
                    <div style={{
                        display: 'flex',
                        height: '100%',
                        width: '100%',
                        alignItems: 'center',
                        justifyContent: 'space-around',
                    }}>
                        <div style={{flexGrow: 2, margin: 30}}>
                            <h3> My groceries</h3>

                            <GroceryList items={this.state.groceryList} headers={["Name", "Quantity", "Calories"]} onSelect={this.selectItem.bind(this)}/>
                        </div>
                        <div style={{flexGrow: 1}}>
                            <AddItem add={this.add.bind(this)}/>
                        </div>
                    </div>
                    <div style={{display: 'flex', justifyContent: 'space-around', marginLeft: 30}}>
                        <div style={{flexGrow: 1}}>
                        <h3>Selected grocery</h3>
                    {this.state.selectedItem !== null ? <GroceryCard item={this.state.selectedItem} donateItem={this.donateItem.bind(this)} /> : null}
                        </div>
                        <div style={{flexGrow: 1, padding: 30}}>
                            <h3>Reports</h3>
                            <Report report={this.state.report}  />
                        </div>
                    </div>
                </div>
            </MuiThemeProvider>

        );
    }
}