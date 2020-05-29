import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const styles = {
    root: {
        minWidth: 275,
        backgroundColor: 'antiquewhite',

    },
    bullet: {
        display: 'inline-block',
        margin: '0 2px',
        transform: 'scale(0.8)',
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
};

export default class GroceryCard extends React.Component {


    getTime(date){
        console.log(date)
        if(date === 0){
            return "-"
        }
        return (new Date(date)).toISOString().substring(0, 10);
    }

    render() {

        const {id, name, quantity, calories, expirationDate, consumptionDate, purchaseDate} = {...this.props.item};


        return (
            <Card style={styles.root} variant="outlined">
                <CardContent>
                    <Typography style={styles.title} color="textSecondary" gutterBottom>
                        {id}
                    </Typography>
                    <Typography variant="h5" component="h2">
                        {name}
                    </Typography>
                    <Typography style={styles.pos} color="textSecondary">
                        Calories: {calories}
                    </Typography>
                    <Typography style={styles.pos} color="textSecondary">
                        Quantity: {quantity}
                    </Typography>
                    <Typography variant="body2" component="p">
                        Purchase date
                        <br/>
                        {this.getTime(purchaseDate)}
                        <br/><br/>
                        Consumption date
                        <br/>
                        {this.getTime(consumptionDate)}
                        <br/><br/>
                        Expiry date
                        <br/>
                        {this.getTime(expirationDate)}
                    </Typography>
                    <Button variant="contained" color="secondary" style={{margin: 10}}onClick={() => this.props.donateItem(id)}>
                        Donate
                    </Button>
                </CardContent>

            </Card>
        );
    }
}