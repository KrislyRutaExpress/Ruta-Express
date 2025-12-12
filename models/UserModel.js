class User {
  constructor(id, name, email, password, role = 'delivery') {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.role = role; // 'restaurant', 'delivery', 'customer'
    this.isActive = true;
    this.createdAt = new Date().toISOString();
  }

  toJSON() {
    return {
      id: this.id,
      name: this.name,
      email: this.email,
      role: this.role,
      isActive: this.isActive,
      createdAt: this.createdAt
    };
  }
}

module.exports = User;
