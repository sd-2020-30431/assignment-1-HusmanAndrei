import * as React from "react";
import GroceryItem from "./GroceryItem";
import '../css/grocery-list.css';
import GroceryHeader from "./GroceryHeader";
export default class GroceryList extends React.Component{
    constructor(props) {
        super(props);
    }


    mapItems(){
        return this.props.items.map(item => <GroceryItem item={item} onSelect={() => this.props.onSelect(item)} />);
    }

    render() {
        return (
            <div className={"grocery-list"}>
                <h3>Grocery list</h3>
                <GroceryHeader headers={this.props.headers} />
                {this.mapItems()}
            </div>
        )
    }


}