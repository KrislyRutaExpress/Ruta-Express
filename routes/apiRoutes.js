module.exports = function(app) {
  const userController = require('../controllers/UserController');
  const rideController = require('../controllers/RideController');

  // User routes
  app.route('/users/register')
    .post(userController.register);

  app.route('/users/auth')
    .post(userController.auth);

  app.route('/users')
    .get(userController.listUsers);

  app.route('/users/:id')
    .get(userController.getUserById);

  // Ride routes
  app.route('/rides')
    .post(rideController.createRide)
    .get(rideController.listRides);

  app.route('/rides/:id')
    .get(rideController.getRideById)
    .delete(rideController.deleteRide);

  app.route('/rides/:id/accept')
    .put(rideController.acceptRide);

  app.route('/rides/:id/reject')
    .put(rideController.rejectRide);

  app.route('/rides/:id/deliver')
    .put(rideController.deliverRide);

  app.route('/rides/status/:status')
    .get(rideController.getRidesByStatus);

  // Health check
  app.route('/')
    .get((req, res) => {
      res.json({
        message: 'RutaExpress API is running',
        version: '1.0.0',
        endpoints: {
          users: '/users',
          rides: '/rides'
        }
      });
    });
};
