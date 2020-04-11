import React from 'react';
import logo from './logo.svg';
import './App.css';
import Routes from "./routing/Routes";
import AppBar from "material-ui/AppBar";
import MuiThemeProvider from "material-ui/styles/MuiThemeProvider";

function App() {
  return (
    <div className="App">
      <MuiThemeProvider>

      <AppBar
          title="Wasteless"

      />
      </MuiThemeProvider>
      <Routes />
    </div>
  );
}

export default App;
