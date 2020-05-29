import React from "react";
import {BrowserRouter, Route, Switch} from 'react-router-dom';
import Login from "../pages/Login";
import Home from "../pages/Home";
import SafeRoute from "./SafeRoute";


export default class Routes extends React.Component{


    constructor(props) {
        super(props);
        this.state = {
            routes : [ { path: "/", component:  Login  }],
            safeRoutes : [{path: "/home", component: Home}]
        }
    }

    createPages(){
        return this.state.routes.map(route => <Route exact path={route.path} component={route.component} /> )
    }

    createSafePages(){
        return this.state.safeRoutes.map(route => <SafeRoute path={route.path} component={route.component} /> )
    }


    render() {

        return(
            <BrowserRouter>
                <Switch>
                {this.createPages()}
                {this.createSafePages()}
                </Switch>
            </BrowserRouter>

        )
    }
}