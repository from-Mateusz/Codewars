function getOrder(input) {
    let order = ["Burger", "Fries", "Chicken", "Pizza", "Sandwich", "Onionrings", "Milkshake", "Coke"],
        meals = input.match(/(Burger|Fries|Chicken|Pizza|Sandwich|Onionrings|Milkshake|Coke)/gi),
        mealsOrder = [];
    
    for(let meal of order) {
      for(let oMeal of meals) {
        if(meal.toLowerCase() === oMeal) {
          mealsOrder.push(oMeal.charAt(0).toUpperCase() + oMeal.slice(1));
        }
      }
    }
  
    return mealsOrder.join(' ');
  }