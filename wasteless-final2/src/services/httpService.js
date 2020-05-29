

class HttpService{

    constructor() {
        this.url = "http://localhost:8080";
    }

    async initLogin(username, password){
        const response = await fetch(`${this.url}/login`, {
            body: JSON.stringify({
                name: username,
                password: password
            }),
            method: 'POST',
            headers:  {'Content-Type' : 'application/json' }
        }).then(response => response.json());
        return response;
    }

    async getGroceries(userId){
        const response = await fetch(`${this.url}/grocery`,{
            headers: {
                'Authorization' : `Bearer ${userId}`,
                'Content-Type' : 'application/json'
            }
        }).then(response => response.json());
        const mapped = response.map(r => this.changeBackTimeFormat(r));
        console.log('items', response)

        return mapped;
    }

    async addGrocery(userId, groceryItem){
        const newItem =  this.changeTimeFormat(groceryItem);
        const response = await fetch(`${this.url}/grocery`,{
            headers: {
                'Authorization' : `Bearer ${userId}`,
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(newItem),
            method : 'POST'
        }).then(resp => resp.json());
        console.log('item', response);
        return response;
    }

    async getReports(userId){
        const response = await fetch(`${this.url}/stats/all`,{
            headers: {
                'Authorization' : `Bearer ${userId}`,
                'Content-Type' : 'application/json'
            }
        }).then(resp => resp.json());
        return response;

    }

    async donateItem(userId, itemId){
        console.log('item id', itemId)
        const response = await fetch(`${this.url}/donate?itemId=${itemId}`,{
            headers: {
                'Authorization' : `Bearer ${userId}`,
                'Content-Type' : 'application/json'
            },
            method: 'DELETE'
        }).then(resp => resp.json());
        return response;
    }

    changeTimeFormat(groceryItem){
        const newItem = JSON.parse(JSON.stringify(groceryItem))
        newItem.purchaseDate = groceryItem.purchaseDate.getTime();
        if(groceryItem.consumptionDate !== null){
            newItem.consumptionDate = groceryItem.consumptionDate.getTime();

        }
        else{
            newItem.consumptionDate = null;
        }
        newItem.expirationDate = groceryItem.expirationDate.getTime();
        return newItem;
    }

    changeBackTimeFormat(groceryItem){
        const newItem = JSON.parse(JSON.stringify(groceryItem))
        newItem.purchaseDate = (new Date() ).setTime(groceryItem.purchaseDate);
        newItem.consumptionDate =  (new Date() ).setTime(groceryItem.consumptionDate);
        newItem.expirationDate = (new Date() ).setTime(groceryItem.expirationDate);
        return newItem;
    }



}
const httpInstance = new HttpService();
export default httpInstance;