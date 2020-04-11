import * as React from "react";
import {Dialog} from 'material-ui'
import {Calendar} from "react-calendar";
import {MuiThemeProvider} from 'material-ui'
import RaisedButton from "material-ui/RaisedButton";

export default class CalendarSelect extends React.Component{
    constructor(props) {
        super(props);

    }



    render() {
        return (
        <Dialog
            open={this.props.isOpened}
            onClose={this.props.onClose}
            aria-labelledby="alert-dialog-title"
            aria-describedby="alert-dialog-description"
        >
            <Calendar
                onChange={this.props.onChange}
                value={this.props.selectedDate}
            />
            <MuiThemeProvider>
                <RaisedButton label="Select" primary={true}  onClick={this.props.onClose}/>
            </MuiThemeProvider>

        </Dialog>
        )
    }


}