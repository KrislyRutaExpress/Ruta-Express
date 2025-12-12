const Ride = require('../models/RideModel');
const data = require('../data/data');
const _ = require('underscore');

// Create new ride
exports.createRide = function(req, res) {
  try {
    const { restaurantName, customerAddress, finalDestination, deliveryPersonId } = req.body;

    if (!restaurantName || !customerAddress) {
      return res.json({
        responseCode: 'ERROR',
        message: 'Restaurant name and customer address are required'
      });
    }

    const id = Date.now().toString();
    const newRide = new Ride(id, restaurantName, customerAddress, finalDestination, deliveryPersonId);
    data.rides.push(newRide);

    res.json({
      data: newRide.toJSON(),
      responseCode: 'SUCCESSFUL',
      message: 'Ride created successfully'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};

// List all rides
exports.listRides = function(req, res) {
  try {
    const ridesData = data.rides.map(ride => ride.toJSON());

    res.json({
      data: ridesData,
      responseCode: 'SUCCESSFUL',
      message: 'Rides retrieved successfully'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};

// Get ride by ID
exports.getRideById = function(req, res) {
  try {
    const { id } = req.params;
    const ride = _.findWhere(data.rides, { id: id });

    if (!ride) {
      return res.json({
        responseCode: 'NOT_FOUND',
        message: 'Ride not found'
      });
    }

    res.json({
      data: ride.toJSON(),
      responseCode: 'SUCCESSFUL',
      message: 'Ride found'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};

// Accept ride
exports.acceptRide = function(req, res) {
  try {
    const { id } = req.params;
    const { deliveryPersonId, deliveryPersonName } = req.body;

    if (!deliveryPersonId) {
      return res.json({
        responseCode: 'ERROR',
        message: 'Delivery person ID is required'
      });
    }

    const ride = _.findWhere(data.rides, { id: id });

    if (!ride) {
      return res.json({
        responseCode: 'NOT_FOUND',
        message: 'Ride not found'
      });
    }

    if (ride.status !== 'pending') {
      return res.json({
        responseCode: 'ERROR',
        message: 'Ride is not available for acceptance'
      });
    }

    ride.status = 'accepted';
    ride.deliveryPersonId = deliveryPersonId;
    ride.deliveryPersonName = deliveryPersonName || '';
    ride.updatedAt = new Date().toISOString();

    res.json({
      data: ride.toJSON(),
      responseCode: 'SUCCESSFUL',
      message: 'Ride accepted successfully'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};

// Reject ride
exports.rejectRide = function(req, res) {
  try {
    const { id } = req.params;
    const ride = _.findWhere(data.rides, { id: id });

    if (!ride) {
      return res.json({
        responseCode: 'NOT_FOUND',
        message: 'Ride not found'
      });
    }

    ride.status = 'rejected';
    ride.updatedAt = new Date().toISOString();

    res.json({
      data: ride.toJSON(),
      responseCode: 'SUCCESSFUL',
      message: 'Ride rejected successfully'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};

// Mark ride as delivered
exports.deliverRide = function(req, res) {
  try {
    const { id } = req.params;
    const { photoUrl } = req.body;

    const ride = _.findWhere(data.rides, { id: id });

    if (!ride) {
      return res.json({
        responseCode: 'NOT_FOUND',
        message: 'Ride not found'
      });
    }

    if (ride.status !== 'accepted') {
      return res.json({
        responseCode: 'ERROR',
        message: 'Ride must be accepted before delivery'
      });
    }

    ride.status = 'delivered';
    if (photoUrl) {
      ride.photoUrl = photoUrl;
    }
    ride.updatedAt = new Date().toISOString();

    res.json({
      data: ride.toJSON(),
      responseCode: 'SUCCESSFUL',
      message: 'Ride marked as delivered'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};

// Delete ride
exports.deleteRide = function(req, res) {
  try {
    const { id } = req.params;
    const rideIndex = data.rides.findIndex(ride => ride.id === id);

    if (rideIndex === -1) {
      return res.json({
        responseCode: 'NOT_FOUND',
        message: 'Ride not found'
      });
    }

    data.rides.splice(rideIndex, 1);

    res.json({
      responseCode: 'SUCCESSFUL',
      message: 'Ride deleted successfully'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};

// Get rides by status
exports.getRidesByStatus = function(req, res) {
  try {
    const { status } = req.params;
    const filteredRides = data.rides.filter(ride => ride.status === status);
    const ridesData = filteredRides.map(ride => ride.toJSON());

    res.json({
      data: ridesData,
      responseCode: 'SUCCESSFUL',
      message: `Rides with status '${status}' retrieved successfully`
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};
