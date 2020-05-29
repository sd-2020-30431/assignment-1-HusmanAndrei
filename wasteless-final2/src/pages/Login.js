import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import AppBar from 'material-ui/AppBar';
import RaisedButton from 'material-ui/RaisedButton';
import TextField from 'material-ui/TextField';
import '../css/login.css'
import React, {Component} from 'react';
import httpInstance from "../services/httpService";

class Login extends Component {
    constructor(props){
        super(props);
        this.state={
            username:'',
            password:''
        }
    }

    async handleClick(event){
        console.log('Logging in ', this.state.username, this.state.password);
        const httpService = httpInstance;
        const response = await httpService.initLogin(this.state.username, this.state.password);
        console.log(response)
        if(response.id !== null){
            localStorage.setItem("token", response.id);
            this.props.history.push("/home");
        }
        else{
            localStorage.removeItem("token");
            alert("Cannot login. Try again!");
        }
    }

    render() {
        return (
            <div className={"loginPage"}>
                <MuiThemeProvider>
                    <div>
                        <h1>Login</h1>
                        <TextField
                            hintText="Enter your Username"
                            floatingLabelText="Username"
                            onChange = {(event,newValue) => this.setState({username:newValue})}
                        />
                        <br/>
                        <TextField
                            type="password"
                            hintText="Enter your Password"
                            floatingLabelText="Password"
                            onChange = {(event,newValue) => this.setState({password:newValue})}
                        />
                        <br/>
                        <RaisedButton label="Submit" primary={true} style={style} onClick={(event) => this.handleClick(event)}/>
                    </div>
                </MuiThemeProvider>
            </div>
        );
    }
}
const style = {
    margin: 15,
};
export default Login;