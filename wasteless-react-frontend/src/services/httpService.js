

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
        console.log('items', response)
        response.forEach(r => this.changeBackTimeFormat(r));
        return response;
    }

    async addGrocery(userId, groceryItem){
        this.changeTimeFormat(groceryItem);
        const response = await fetch(`${this.url}/grocery`,{
            headers: {
                'Authorization' : `Bearer ${userId}`,
                'Content-Type' : 'application/json'
            },
            body : JSON.stringify(groceryItem),
            method : 'POST'
        }).then(resp => resp.json());
        console.log('item');
        return response;
    }

    changeTimeFormat(groceryItem){
        groceryItem.purchaseDate = groceryItem.purchaseDate.getTime();
        groceryItem.consumptionDate = groceryItem.consumptionDate.getTime();
        groceryItem.expirationDate = groceryItem.expirationDate.getTime();
    }

    changeBackTimeFormat(groceryItem){
        groceryItem.purchaseDate = (new Date() ).setTime(groceryItem.purchaseDate);
        groceryItem.consumptionDate =  (new Date() ).setTime(groceryItem.consumptionDate);
        groceryItem.expirationDate = (new Date() ).setTime(groceryItem.expirationDate);
    }

}
const httpInstance = new HttpService();
export default httpInstance;