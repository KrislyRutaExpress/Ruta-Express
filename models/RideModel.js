class Ride {
  constructor(id, restaurantName, customerAddress, finalDestination = '', deliveryPersonId = null) {
    this.id = id;
    this.restaurantName = restaurantName;
    this.customerAddress = customerAddress;
    this.finalDestination = finalDestination;
    this.status = 'pending'; // 'pending', 'accepted', 'rejected', 'delivered'
    this.deliveryPersonId = deliveryPersonId;
    this.deliveryPersonName = '';
    this.photoUrl = null;
    this.createdAt = new Date().toISOString();
    this.updatedAt = new Date().toISOString();
  }

  toJSON() {
    return {
      id: this.id,
      restaurantName: this.restaurantName,
      customerAddress: this.customerAddress,
      finalDestination: this.finalDestination,
      status: this.status,
      deliveryPersonId: this.deliveryPersonId,
      deliveryPersonName: this.deliveryPersonName,
      photoUrl: this.photoUrl,
      createdAt: this.createdAt,
      updatedAt: this.updatedAt
    };
  }
}

module.exports = Ride;
