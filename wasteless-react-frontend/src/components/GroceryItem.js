import * as React from "react";
import '../css/grocery-item.css';

export default class GroceryItem extends React.Component{
    constructor(props) {
        super(props);
    }

    render(){
        const {name, quantity, calories, expirationDate, consumptionDate, purchaseDate} = {...this.props.item};
        console.log('Item', name)
        return(
            <div className={"card"} onClick={this.props.onSelect}>
                <div>
                    {name}
                </div>
                <div>
                    {quantity}
                </div>
                <div>
                    {calories}
                </div>
            </div>
        )
    }
}