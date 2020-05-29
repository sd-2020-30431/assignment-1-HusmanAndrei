import * as React from "react";
import '../css/hea.css';

export default class GroceryHeader extends React.Component{
    constructor(props) {
        super(props);
    }

    mapHeader(){
        return this.props.headers.map(header => <div>{header}</div>)
    }

    render(){

        console.log('Item', )
        return(
            <div className={"header"}>
                {this.mapHeader()}
            </div>
        )
    }
}