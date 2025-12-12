const User = require('../models/UserModel');
const data = require('../data/data');
const _ = require('underscore');

// Register new user
exports.register = function(req, res) {
  try {
    const { name, email, password, role } = req.body;

    if (!name || !email || !password) {
      return res.json({
        responseCode: 'ERROR',
        message: 'Name, email and password are required'
      });
    }

    // Check if user already exists
    const existingUser = _.findWhere(data.users, { email: email });
    if (existingUser) {
      return res.json({
        responseCode: 'ERROR',
        message: 'User with this email already exists'
      });
    }

    const id = Date.now().toString();
    const newUser = new User(id, name, email, password, role || 'delivery');
    data.users.push(newUser);

    res.json({
      data: newUser.toJSON(),
      responseCode: 'SUCCESSFUL',
      message: 'User registered successfully'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};

// Authenticate user
exports.auth = function(req, res) {
  try {
    const { email, password } = req.body;

    if (!email || !password) {
      return res.json({
        responseCode: 'ERROR',
        message: 'Email and password are required'
      });
    }

    const user = _.findWhere(data.users, { email: email, password: password });

    if (!user) {
      return res.json({
        responseCode: 'ERROR',
        message: 'Invalid credentials'
      });
    }

    if (!user.isActive) {
      return res.json({
        responseCode: 'ERROR',
        message: 'User account is inactive'
      });
    }

    res.json({
      data: user.toJSON(),
      responseCode: 'SUCCESSFUL',
      message: 'Authentication successful'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};

// List all users
exports.listUsers = function(req, res) {
  try {
    const usersWithoutPassword = data.users.map(user => {
      const userObj = user.toJSON();
      return userObj;
    });

    res.json({
      data: usersWithoutPassword,
      responseCode: 'SUCCESSFUL',
      message: 'Users retrieved successfully'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};

// Get user by ID
exports.getUserById = function(req, res) {
  try {
    const { id } = req.params;
    const user = _.findWhere(data.users, { id: id });

    if (!user) {
      return res.json({
        responseCode: 'NOT_FOUND',
        message: 'User not found'
      });
    }

    res.json({
      data: user.toJSON(),
      responseCode: 'SUCCESSFUL',
      message: 'User found'
    });
  } catch (error) {
    res.json({
      responseCode: 'ERROR',
      message: error.message
    });
  }
};
