

import React from "react";
import {Route, Redirect} from 'react-router-dom';


export default class SafeRoute extends React.Component{

    checkToken(){
        let userId = localStorage.getItem('token');
        if (userId == undefined){
            return false;
        }
        return true;
    }

    render() {

        return(
            this.checkToken() ? <Route exact component={this.props.component} path={this.props.path} /> : <Redirect to={"/"} />
        )
    }
}